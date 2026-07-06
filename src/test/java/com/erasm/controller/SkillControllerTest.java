package com.erasm.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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

import com.erasm.dto.SkillDTO;
import com.erasm.service.SkillService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class SkillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SkillService skillService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void testAddSkill() throws Exception {

        SkillDTO dto = new SkillDTO();
        dto.setSkillId(1);
        dto.setSkillName("Java");

        when(skillService.addSkill(any(SkillDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/skills")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.skillName").value("Java"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetAllSkills() throws Exception {

        SkillDTO dto = new SkillDTO();
        dto.setSkillId(1);
        dto.setSkillName("Java");

        when(skillService.getAllSkills()).thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/skills"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].skillName").value("Java"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetSkillById() throws Exception {

        SkillDTO dto = new SkillDTO();
        dto.setSkillId(1);
        dto.setSkillName("Java");

        when(skillService.getSkillById(1)).thenReturn(dto);

        mockMvc.perform(get("/skills/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.skillName").value("Java"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateSkill() throws Exception {

        SkillDTO dto = new SkillDTO();
        dto.setSkillId(1);
        dto.setSkillName("Spring Boot");

        when(skillService.updateSkill(anyInt(), any(SkillDTO.class))).thenReturn(dto);

        mockMvc.perform(put("/skills/1")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.skillName").value("Spring Boot"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteSkill() throws Exception {

        doNothing().when(skillService).deleteSkill(1);

        mockMvc.perform(delete("/skills/1")
                .with(csrf()))
                .andExpect(status().isOk());
    }

}