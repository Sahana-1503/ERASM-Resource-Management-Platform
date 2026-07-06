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

import com.erasm.dto.ProjectDTO;
import com.erasm.service.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	private final ProjectService projectService;

	
	

	public ProjectController(ProjectService projectService) {
	super();
	this.projectService = projectService;
}

	@PostMapping
	@PreAuthorize("hasRole('DELIVERY_MANAGER')")

	public ResponseEntity<ProjectDTO> addProject(@Valid @RequestBody ProjectDTO projectDTO){
		ProjectDTO savedProject=projectService.addProject(projectDTO);
		return new ResponseEntity<>(savedProject,HttpStatus.CREATED);
		
		}
	
	@GetMapping
	public ResponseEntity<List<ProjectDTO>> getAllProjects(){
		return ResponseEntity.ok(projectService.getAllProjects());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProjectDTO> getProjectById(@PathVariable int id){
		return ResponseEntity.ok(projectService.getProjectById(id));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('DELIVERY_MANAGER')")
	public ResponseEntity<ProjectDTO> updateProject(
			@PathVariable int id,
			@Valid @RequestBody ProjectDTO projectDTO){
		return ResponseEntity.ok(projectService.updateProject(id, projectDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProject(@PathVariable int id){
		projectService.deleteProject(id);
		return ResponseEntity.ok("Project deleted Successfully");
	}


}
