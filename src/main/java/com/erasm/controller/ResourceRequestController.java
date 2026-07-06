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

import com.erasm.dto.ResourceRequestDTO;
import com.erasm.service.ResourceRequestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/resource-requests")

public class ResourceRequestController {
	private final ResourceRequestService resourceRequestService;

	
	public ResourceRequestController(ResourceRequestService resourceRequestService) {
		super();
		this.resourceRequestService = resourceRequestService;
	}

	@PostMapping
	@PreAuthorize("hasRole('MANAGER')")
	public ResponseEntity<ResourceRequestDTO> addResourceRequest(@Valid @RequestBody ResourceRequestDTO resourceRequestDTO){
		ResourceRequestDTO saved=resourceRequestService.addResourceRequest(resourceRequestDTO);
		return new ResponseEntity<>(saved,HttpStatus.CREATED);
		
		}
	
	@GetMapping
	public ResponseEntity<List<ResourceRequestDTO>> getAllResourceRequests(){
		return ResponseEntity.ok(resourceRequestService.getAllResourceRequests());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResourceRequestDTO> getResourceRequestById(@PathVariable int id){
		return ResponseEntity.ok(resourceRequestService.getResourceRequestById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResourceRequestDTO> updateResourceRequest(
			@PathVariable int id,
			@Valid @RequestBody ResourceRequestDTO resourceRequestDTO){
		return ResponseEntity.ok(resourceRequestService.updateResourceRequest(id, resourceRequestDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteResourceRequest(@PathVariable int id){
		resourceRequestService.deleteResourceRequest(id);
		return ResponseEntity.ok("Resource Request deleted Successfully");
	}
	@PutMapping("/{id}/submit")
	public ResponseEntity<ResourceRequestDTO> submittRequest(@PathVariable int id){
		return ResponseEntity.ok(resourceRequestService.submittRequest(id));
	}
	@PutMapping("/{id}/approve")
	public ResponseEntity<ResourceRequestDTO> approveRequest(@PathVariable int id){
		return ResponseEntity.ok(resourceRequestService.approveRequest(id));
	}
	@PutMapping("/{id}/reject")
	public ResponseEntity<ResourceRequestDTO> rejectRequest(@PathVariable int id){
		return ResponseEntity.ok(resourceRequestService.rejectRequest(id));
		
	}
	
	@PutMapping("/{id}/allocate")
	public ResponseEntity<ResourceRequestDTO> allocateRequest(@PathVariable int id){
		return ResponseEntity.ok(resourceRequestService.allocationRequest(id));
	}
	@PutMapping("/{id}/complete")
	public ResponseEntity<ResourceRequestDTO> completeRequest(@PathVariable int id){
		return ResponseEntity.ok(resourceRequestService.completeRequest(id));
	}



}
