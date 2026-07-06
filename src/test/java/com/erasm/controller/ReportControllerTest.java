package com.erasm.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.erasm.dto.AllocationDTO;
import com.erasm.dto.SkillReportDTO;
import com.erasm.dto.UtilizationDTO;
import com.erasm.service.ReportService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportService reportService;

    @Test
    void testSkillReport() throws Exception {

        SkillReportDTO dto = new SkillReportDTO();
        dto.setSkillName("Java");

        when(reportService.getSkillReport())
                .thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/reports/skills"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].skillName").value("Java"));
    }

    @Test
    void testUtilizationReport() throws Exception {

        UtilizationDTO dto = new UtilizationDTO();

        when(reportService.getUtilizationReport())
                .thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/reports/utilization"))
                .andExpect(status().isOk());
    }

    @Test
    void testProjectAllocationReport() throws Exception {

        AllocationDTO dto = new AllocationDTO();
        dto.setAllocationId(1);

        when(reportService.getProjectAllocationReport())
                .thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/reports/project-allocation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].allocationId").value(1));
    }
}