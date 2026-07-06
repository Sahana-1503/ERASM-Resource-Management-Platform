package com.erasm.service;

import java.util.List;

import com.erasm.dto.AllocationDTO;
import com.erasm.dto.SkillReportDTO;
import com.erasm.dto.UtilizationDTO;

public interface ReportService {
	List<SkillReportDTO> getSkillReport();
	List<UtilizationDTO> getUtilizationReport();
	List<AllocationDTO> getProjectAllocationReport();

}
