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

import com.erasm.dto.AllocationDTO;
import com.erasm.service.AllocationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AllocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AllocationService allocationService;

    @Test
    @WithMockUser(roles = "RESOURCE_MANAGER")
    void testAddAllocation() throws Exception {

        AllocationDTO dto = new AllocationDTO();
        dto.setAllocationId(1);
        dto.setEmployeeId(101);
        dto.setProjectId(201);
        dto.setAllocationPercentage(100);
        dto.setAllocationDate(LocalDate.of(2026, 7, 1));
        dto.setReleaseDate(LocalDate.of(2026, 12, 31));
        dto.setStatus("Allocated");

        when(allocationService.addAllocation(any(AllocationDTO.class)))
                .thenReturn(dto);

        mockMvc.perform(post("/allocations")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.allocationId").value(1))
                .andExpect(jsonPath("$.status").value("Allocated"));
    }

    @Test
    @WithMockUser
    void testGetAllAllocations() throws Exception {

        AllocationDTO dto = new AllocationDTO();
        dto.setAllocationId(1);
        dto.setStatus("Allocated");

        when(allocationService.getAllAllocations())
                .thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/allocations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].allocationId").value(1))
                .andExpect(jsonPath("$[0].status").value("Allocated"));
    }

    @Test
    @WithMockUser
    void testGetAllocationById() throws Exception {

        AllocationDTO dto = new AllocationDTO();
        dto.setAllocationId(1);
        dto.setStatus("Allocated");

        when(allocationService.getAllocationById(1))
                .thenReturn(dto);

        mockMvc.perform(get("/allocations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.allocationId").value(1))
                .andExpect(jsonPath("$.status").value("Allocated"));
    }

    @Test
    @WithMockUser(roles = "RESOURCE_MANAGER")
    void testUpdateAllocation() throws Exception {

        AllocationDTO dto = new AllocationDTO();
        dto.setAllocationId(1);
        dto.setEmployeeId(101);
        dto.setProjectId(201);
        dto.setAllocationPercentage(50);
        dto.setAllocationDate(LocalDate.of(2026, 7, 1));
        dto.setReleaseDate(LocalDate.of(2026, 12, 31));
        dto.setStatus("Updated");

        when(allocationService.updateAllocation(anyInt(), any(AllocationDTO.class)))
                .thenReturn(dto);

        mockMvc.perform(put("/allocations/1")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Updated"));
    }

    @Test
    @WithMockUser
    void testDeleteAllocation() throws Exception {

        doNothing().when(allocationService).deleteAllocation(1);

        mockMvc.perform(delete("/allocations/1")
                .with(csrf()))
                .andExpect(status().isOk());
    }
}