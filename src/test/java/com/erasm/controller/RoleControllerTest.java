package com.erasm.controller;

import static org.mockito.ArgumentMatchers.any;
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

import com.erasm.dto.RoleDTO;
import com.erasm.service.RoleService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RoleService roleService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void testAddRole() throws Exception {

        RoleDTO dto = new RoleDTO();
        dto.setRoleId(1);
        dto.setRoleName("ADMIN");

        when(roleService.addRole(any(RoleDTO.class)))
                .thenReturn(dto);

        mockMvc.perform(post("/roles")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roleName").value("ADMIN"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetAllRoles() throws Exception {

        RoleDTO dto = new RoleDTO();
        dto.setRoleId(1);
        dto.setRoleName("ADMIN");

        when(roleService.getAllRoles())
                .thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/roles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].roleName").value("ADMIN"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetRoleById() throws Exception {

        RoleDTO dto = new RoleDTO();
        dto.setRoleId(1);
        dto.setRoleName("ADMIN");

        when(roleService.getRoleById(1))
                .thenReturn(dto);

        mockMvc.perform(get("/roles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roleName").value("ADMIN"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateRole() throws Exception {

        RoleDTO dto = new RoleDTO();
        dto.setRoleId(1);
        dto.setRoleName("EMPLOYEE");

        when(roleService.updateRole(any(Integer.class), any(RoleDTO.class)))
                .thenReturn(dto);

        mockMvc.perform(put("/roles/1")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roleName").value("EMPLOYEE"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteRole() throws Exception {

        doNothing().when(roleService).deleteRole(1);

        mockMvc.perform(delete("/roles/1")
                .with(csrf()))
                .andExpect(status().isOk());
    }

}