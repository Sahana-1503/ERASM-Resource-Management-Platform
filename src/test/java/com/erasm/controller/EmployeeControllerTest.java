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

import com.erasm.dto.EmployeeDTO;
import com.erasm.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void testAddEmployee() throws Exception {

        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId(1);
        dto.setEmployeeName("Sahana");
        dto.setPhoneNumber("9876543210");
        dto.setDepartment("IT");
        dto.setDesignation("Developer");
        dto.setExperience(2.0);
        dto.setBenchStatus("Available");
        dto.setUserId(1);

        when(employeeService.addEmployee(any(EmployeeDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/employees")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.employeeName").value("Sahana"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetAllEmployees() throws Exception {

        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId(1);
        dto.setEmployeeName("Sahana");

        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employeeName").value("Sahana"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetEmployeeById() throws Exception {

        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId(1);
        dto.setEmployeeName("Sahana");

        when(employeeService.getEmployeeById(1)).thenReturn(dto);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeName").value("Sahana"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateEmployee() throws Exception {

        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId(1);
        dto.setEmployeeName("Updated");
        dto.setPhoneNumber("9999999999");
        dto.setDepartment("HR");
        dto.setDesignation("Manager");
        dto.setExperience(5.0);
        dto.setBenchStatus("Allocated");
        dto.setUserId(1);

        when(employeeService.updateEmployee(any(Integer.class), any(EmployeeDTO.class)))
                .thenReturn(dto);

        mockMvc.perform(put("/employees/1")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeName").value("Updated"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteEmployee() throws Exception {

        doNothing().when(employeeService).deleteEmployee(1);

        mockMvc.perform(delete("/employees/1")
                .with(csrf()))
                .andExpect(status().isOk());
    }
}