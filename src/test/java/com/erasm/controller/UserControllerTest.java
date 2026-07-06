package com.erasm.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.erasm.dto.UserDTO;
import com.erasm.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void testRegisterUser() throws Exception {

        UserDTO dto = new UserDTO();
        dto.setUserId(1);
        dto.setEmail("sahana@gmail.com");
        dto.setPassword("password123");
        dto.setRoleId(1);

        when(userService.registerUser(any(UserDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/auth/register")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("sahana@gmail.com"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetAllUsers() throws Exception {

        UserDTO dto = new UserDTO();
        dto.setUserId(1);
        dto.setEmail("sahana@gmail.com");

        when(userService.getAllUsers()).thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/auth"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("sahana@gmail.com"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetUserById() throws Exception {

        UserDTO dto = new UserDTO();
        dto.setUserId(1);
        dto.setEmail("sahana@gmail.com");

        when(userService.getUserById(1)).thenReturn(dto);

        mockMvc.perform(get("/auth/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("sahana@gmail.com"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateUser() throws Exception {

        UserDTO dto = new UserDTO();
        dto.setUserId(1);
        dto.setEmail("updated@gmail.com");
        dto.setPassword("password123");
        dto.setRoleId(1);

        when(userService.updateUser(anyInt(), any(UserDTO.class))).thenReturn(dto);

        mockMvc.perform(put("/auth/1")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("updated@gmail.com"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteUser() throws Exception {

        doNothing().when(userService).deleteUser(1);

        mockMvc.perform(delete("/auth/1")
                .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testChangePassword() throws Exception {

        UserDTO dto = new UserDTO();
        dto.setUserId(1);
        dto.setEmail("sahana@gmail.com");
        dto.setPassword("newpassword123");
        dto.setRoleId(1);

        when(userService.changePassword(anyInt(), anyString())).thenReturn(dto);

        mockMvc.perform(put("/auth/1/password")
                .with(csrf())
                .param("password", "newpassword123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.password").value("newpassword123"));
    }
}