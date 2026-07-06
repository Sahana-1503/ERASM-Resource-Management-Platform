package com.erasm.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.erasm.dto.ProjectDTO;
import com.erasm.entity.Project;
import com.erasm.exception.ProjectNotFound;
import com.erasm.mapper.ProjectMapper;
import com.erasm.repository.ProjectRepository;
import com.erasm.service.ProjectService;

@Service
public class ProjectServiceImple implements ProjectService {

    private final ProjectRepository projectRepository;

    private static final Logger logger =
            LoggerFactory.getLogger(ProjectServiceImple.class);

    public ProjectServiceImple(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectDTO addProject(ProjectDTO projectDTO) {

        try {

            Project project = ProjectMapper.toEntity(projectDTO);

            Project savedProject = projectRepository.save(project);

        
            logger.info("Project '{}' created successfully.",
                    savedProject.getProjectName());

            return ProjectMapper.toDTO(savedProject);

        } catch (Exception e) {

            
            logger.error("Database failure while creating project.", e);

            throw e;
        }
    }

    @Override
    public ProjectDTO updateProject(int projectId, ProjectDTO projectDTO) {

        try {

            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() ->
                            new ProjectNotFound(
                                    "Project not found with id : " + projectId));

            project.setProjectName(projectDTO.getProjectName());
            project.setClientName(projectDTO.getClientName());
            project.setStartDate(projectDTO.getStartDate());
            project.setEndDate(projectDTO.getEndDate());
            project.setTechnologyStack(projectDTO.getTechnologyStack());
            project.setBudget(projectDTO.getBudget());

            Project updatedProject = projectRepository.save(project);

            logger.info("Project '{}' updated successfully.",
                    updatedProject.getProjectName());

            return ProjectMapper.toDTO(updatedProject);

        } catch (Exception e) {

            logger.error("Database failure while updating project.", e);

            throw e;
        }
    }

    @Override
    public void deleteProject(int projectId) {

        try {

            if (!projectRepository.existsById(projectId)) {

                logger.warn("Invalid request. Project not found with ID {}",
                        projectId);

                throw new ProjectNotFound(
                        "Project not found with id : " + projectId);
            }

            projectRepository.deleteById(projectId);

            logger.info("Project deleted successfully with ID {}",
                    projectId);

        } catch (Exception e) {

            logger.error("Database failure while deleting project.", e);

            throw e;
        }
    }

    @Override
    public ProjectDTO getProjectById(int projectId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new ProjectNotFound(
                                "Project not found with id : " + projectId));

        return ProjectMapper.toDTO(project);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {

        return projectRepository.findAll()
                .stream()
                .map(ProjectMapper::toDTO)
                .toList();
    }
}