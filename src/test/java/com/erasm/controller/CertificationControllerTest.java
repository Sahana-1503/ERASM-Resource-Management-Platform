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

import com.erasm.dto.CertificationDTO;
import com.erasm.service.CertificationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CertificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CertificationService certificationService;

    @Test
    @WithMockUser(roles = "EMPLOYEE")
    void testAddCertification() throws Exception {

        CertificationDTO dto = new CertificationDTO();
        dto.setCertificationId(1);
        dto.setCertificationName("Java Certification");
        dto.setIssuingOrganization("Oracle");
        dto.setEmployeeId(1);

        when(certificationService.addCertification(any(CertificationDTO.class)))
                .thenReturn(dto);

        mockMvc.perform(post("/certifications")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.certificationName").value("Java Certification"));
    }

    @Test
    @WithMockUser
    void testGetAllCertifications() throws Exception {

        CertificationDTO dto = new CertificationDTO();
        dto.setCertificationId(1);
        dto.setCertificationName("Java Certification");
        dto.setIssuingOrganization("Oracle");
        dto.setEmployeeId(1);

        when(certificationService.getAllCertifications())
                .thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/certifications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].certificationName").value("Java Certification"));
    }

    @Test
    @WithMockUser
    void testGetCertificationById() throws Exception{

        CertificationDTO dto = new CertificationDTO();
        dto.setCertificationId(1);
        dto.setCertificationName("Java Certification");
        dto.setIssuingOrganization("Oracle");
        dto.setEmployeeId(1);

        when(certificationService.getCertificationById(1))
                .thenReturn(dto);

        
        
        mockMvc.perform(get("/certifications/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.certificationName").value("Java Certification"));
    }

    @Test
    @WithMockUser(roles = "EMPLOYEE")
    void testUpdateCertification() throws Exception {

        CertificationDTO dto = new CertificationDTO();
        dto.setCertificationId(1);
        dto.setCertificationName("Spring Boot Certification");
        dto.setIssuingOrganization("Oracle");
        dto.setEmployeeId(1);

        when(certificationService.updateCertification(anyInt(), any(CertificationDTO.class)))
                .thenReturn(dto);

        mockMvc.perform(put("/certifications/1")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.certificationName").value("Spring Boot Certification"));
    }

    @Test
    @WithMockUser
    void testDeleteCertification() throws Exception {

        doNothing().when(certificationService).deleteCertification(1);

        mockMvc.perform(delete("/certifications/1")
                .with(csrf()))
                .andExpect(status().isOk());
    }
}