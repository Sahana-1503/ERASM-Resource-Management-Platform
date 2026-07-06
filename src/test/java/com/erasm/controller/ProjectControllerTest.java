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

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.erasm.dto.ProjectDTO;
import com.erasm.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProjectService projectService;

    @Test
    @WithMockUser(roles = "DELIVERY_MANAGER")
    void testAddProject() throws Exception {

        ProjectDTO dto = new ProjectDTO();
        dto.setProjectId(1);
        dto.setProjectName("ERASM");
        dto.setClientName("Infosys");
        dto.setStartDate(LocalDate.of(2026, 7, 1));
        dto.setEndDate(LocalDate.of(2026, 12, 31));
        dto.setTechnologyStack("Spring Boot");
        dto.setBudget(500000);

        when(projectService.addProject(any(ProjectDTO.class)))
                .thenReturn(dto);

        mockMvc.perform(post("/projects")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.projectName").value("ERASM"));
    }

    @Test
    @WithMockUser
    void testGetAllProjects() throws Exception {

        ProjectDTO dto = new ProjectDTO();
        dto.setProjectId(1);
        dto.setProjectName("ERASM");
        dto.setClientName("Infosys");

        when(projectService.getAllProjects())
                .thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].projectName").value("ERASM"));
    }

    @Test
    @WithMockUser
    void testGetProjectById() throws Exception {

        ProjectDTO dto = new ProjectDTO();
        dto.setProjectId(1);
        dto.setProjectName("ERASM");

        when(projectService.getProjectById(1))
                .thenReturn(dto);

        mockMvc.perform(get("/projects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectName").value("ERASM"));
    }

    @Test
    @WithMockUser(roles = "DELIVERY_MANAGER")
    void testUpdateProject() throws Exception {

        ProjectDTO dto = new ProjectDTO();
        dto.setProjectId(1);
        dto.setProjectName("Updated Project");
        dto.setClientName("TCS");
        dto.setStartDate(LocalDate.of(2026, 7, 1));
        dto.setEndDate(LocalDate.of(2026, 12, 31));
        dto.setTechnologyStack("Java");
        dto.setBudget(700000);

        when(projectService.updateProject(anyInt(), any(ProjectDTO.class)))
                .thenReturn(dto);

        mockMvc.perform(put("/projects/1")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectName").value("Updated Project"));
    }

    @Test
    @WithMockUser
    void testDeleteProject() throws Exception {

        doNothing().when(projectService).deleteProject(1);

        mockMvc.perform(delete("/projects/1")
                .with(csrf()))
                .andExpect(status().isOk());
    }
}