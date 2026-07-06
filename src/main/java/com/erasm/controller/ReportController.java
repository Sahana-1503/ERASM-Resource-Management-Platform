package com.erasm.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erasm.dto.AllocationDTO;
import com.erasm.dto.SkillReportDTO;
import com.erasm.dto.UtilizationDTO;
import com.erasm.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {
	private final ReportService reportService;

	public ReportController(ReportService reportService) {
		super();
		this.reportService = reportService;
	}
	@GetMapping("/skills")
	public ResponseEntity<List<SkillReportDTO>> getSkillReport(){
		return ResponseEntity.ok(reportService.getSkillReport());
	}
	@GetMapping("/utilization")
	public ResponseEntity<List<UtilizationDTO>> getUtilizationReport(){
		return ResponseEntity.ok(reportService.getUtilizationReport());
	}
	@GetMapping("/project-allocation")
	public ResponseEntity<List<AllocationDTO>> getProjectAllocationReport(){
		return ResponseEntity.ok(reportService.getProjectAllocationReport());
	}



}
