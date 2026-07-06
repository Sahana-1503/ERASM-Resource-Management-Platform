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

import com.erasm.dto.SkillDTO;
import com.erasm.service.SkillService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/skills")
public class SkillController {
	private final SkillService skillService;
	public SkillController(SkillService skillService) {
		this.skillService=skillService;
	}
	
	//add
	@PostMapping
	
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<SkillDTO> addSkill(@Valid @RequestBody SkillDTO skillDTO){
		SkillDTO savedSkill=skillService.addSkill(skillDTO);
		return new ResponseEntity<>(savedSkill,HttpStatus.CREATED);
	}
	
	//get
	@GetMapping
	public ResponseEntity<List<SkillDTO>> getAllSkills(){
		return ResponseEntity.ok(skillService.getAllSkills());
	}
	
	//get skill by id
	@GetMapping("/{id}")
	public ResponseEntity<SkillDTO> getSkillById(@PathVariable int id){
		return ResponseEntity.ok(skillService.getSkillById(id));
		
	}
	
	//update
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<SkillDTO> updateSkill(
			@PathVariable int id,
			@Valid @RequestBody SkillDTO skillDTO){
		return ResponseEntity.ok(skillService.updateSkill(id, skillDTO));
	}
	
	//delete
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")

	public ResponseEntity<String> deleteSkill(@PathVariable int id){
		skillService.deleteSkill(id);
		return ResponseEntity.ok("Skill deleted successfully");
	}

}
