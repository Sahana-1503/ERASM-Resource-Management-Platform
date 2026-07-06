package com.erasm.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.erasm.dto.ResourceRequestDTO;
import com.erasm.service.ResourceRequestService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ResourceRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ResourceRequestService resourceRequestService;

    @Test
    @WithMockUser(roles="MANAGER")
    void testAddResourceRequest() throws Exception {

        ResourceRequestDTO dto = new ResourceRequestDTO();
        dto.setRequestId(1);
        dto.setRequiredSkill("Java");
        dto.setRequiredCount(5);
        dto.setStatus("NEW");
        dto.setProjectId(1);

        when(resourceRequestService.addResourceRequest(any(ResourceRequestDTO.class)))
                .thenReturn(dto);

        mockMvc.perform(post("/resource-requests")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.requiredSkill").value("Java"));
    }

    @Test
    void testGetAllResourceRequests() throws Exception {

        ResourceRequestDTO dto = new ResourceRequestDTO();
        dto.setRequiredSkill("Java");

        when(resourceRequestService.getAllResourceRequests())
                .thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/resource-requests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].requiredSkill").value("Java"));
    }

    @Test
    void testGetResourceRequestById() throws Exception {

        ResourceRequestDTO dto = new ResourceRequestDTO();
        dto.setRequestId(1);

        when(resourceRequestService.getResourceRequestById(1))
                .thenReturn(dto);

        mockMvc.perform(get("/resource-requests/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.requestId").value(1));
    }

    @Test
    void testUpdateResourceRequest() throws Exception {

        ResourceRequestDTO dto = new ResourceRequestDTO();
        dto.setStatus("UPDATED");

        when(resourceRequestService.updateResourceRequest(anyInt(), any(ResourceRequestDTO.class)))
                .thenReturn(dto);

        mockMvc.perform(put("/resource-requests/1")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UPDATED"));
    }

    @Test
    void testDeleteResourceRequest() throws Exception {

        doNothing().when(resourceRequestService).deleteResourceRequest(1);

        mockMvc.perform(delete("/resource-requests/1")
                .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void testSubmitRequest() throws Exception {

        ResourceRequestDTO dto = new ResourceRequestDTO();
        dto.setStatus("SUBMITTED");

        when(resourceRequestService.submittRequest(1)).thenReturn(dto);

        mockMvc.perform(put("/resource-requests/1/submit").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUBMITTED"));
    }

    @Test
    void testApproveRequest() throws Exception {

        ResourceRequestDTO dto = new ResourceRequestDTO();
        dto.setStatus("APPROVED");

        when(resourceRequestService.approveRequest(1)).thenReturn(dto);

        mockMvc.perform(put("/resource-requests/1/approve").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("APPROVED"));
    }

    @Test
    void testRejectRequest() throws Exception {

        ResourceRequestDTO dto = new ResourceRequestDTO();
        dto.setStatus("REJECTED");

        when(resourceRequestService.rejectRequest(1)).thenReturn(dto);

        mockMvc.perform(put("/resource-requests/1/reject").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("REJECTED"));
    }

    @Test
    void testAllocateRequest() throws Exception {

        ResourceRequestDTO dto = new ResourceRequestDTO();
        dto.setStatus("ALLOCATED");

        when(resourceRequestService.allocationRequest(1)).thenReturn(dto);

        mockMvc.perform(put("/resource-requests/1/allocate").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ALLOCATED"));
    }

    @Test
    void testCompleteRequest() throws Exception {

        ResourceRequestDTO dto = new ResourceRequestDTO();
        dto.setStatus("COMPLETED");

        when(resourceRequestService.completeRequest(1)).thenReturn(dto);

        mockMvc.perform(put("/resource-requests/1/complete").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("COMPLETED"));
    }
}