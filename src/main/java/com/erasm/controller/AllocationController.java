package com.erasm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erasm.dto.AllocationDTO;
import com.erasm.service.AllocationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/allocations")

public class AllocationController {
private final AllocationService allocationService;

	
	
	public AllocationController(AllocationService allocationService) {
	this.allocationService = allocationService;
}

	@PostMapping
	@PreAuthorize("hasRole('RESOURCE_MANAGER')")
	public ResponseEntity<AllocationDTO> addAllocation(@Valid @RequestBody AllocationDTO allocationDTO){
		AllocationDTO savedAllocation=allocationService.addAllocation(allocationDTO);
		return new ResponseEntity<>(savedAllocation,HttpStatus.CREATED);
		
		}
	
	@GetMapping
	public ResponseEntity<List<AllocationDTO>> getAllAllocations(){
		return ResponseEntity.ok(allocationService.getAllAllocations());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AllocationDTO> getAllocationById(@PathVariable int id){
		return ResponseEntity.ok(allocationService.getAllocationById(id));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('RESOURCE_MANAGER')")
	public ResponseEntity<AllocationDTO> updateAllocation(
			@PathVariable int id,
			@Valid @RequestBody AllocationDTO allocationDTO){
		return ResponseEntity.ok(allocationService.updateAllocation(id, allocationDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAllocation(@PathVariable int id){
		allocationService.deleteAllocation(id);
		return ResponseEntity.ok("Certification deleted Successfully");
	}
}
	




