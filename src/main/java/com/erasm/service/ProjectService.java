package com.erasm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.erasm.dto.ProjectDTO;
@Service
public interface ProjectService {
	ProjectDTO addProject(ProjectDTO projectDTO);
	ProjectDTO updateProject(int projectId,ProjectDTO projectDTO);
	void deleteProject(int projectId);
	ProjectDTO getProjectById(int projectId);
	List<ProjectDTO> getAllProjects();


}
