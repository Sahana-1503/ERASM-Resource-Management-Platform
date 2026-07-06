package com.erasm.service.implementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.erasm.dto.ProjectDTO;
import com.erasm.entity.Project;
import com.erasm.exception.ProjectNotFound;
import com.erasm.repository.ProjectRepository;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImpleTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImple projectService;

    private Project project;
    private ProjectDTO dto;

    @BeforeEach
    void setUp() {

        project = new Project();
        project.setProjectId(1);
        project.setProjectName("ERASM");
        project.setClientName("Infosys");
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now().plusMonths(6));
        project.setTechnologyStack("Spring Boot");
        project.setBudget(500000);

        dto = new ProjectDTO();
        dto.setProjectId(1);
        dto.setProjectName("ERASM");
        dto.setClientName("Infosys");
        dto.setStartDate(LocalDate.now());
        dto.setEndDate(LocalDate.now().plusMonths(6));
        dto.setTechnologyStack("Spring Boot");
        dto.setBudget(500000);
    }

    @Test
    void testAddProject() {

        when(projectRepository.save(any(Project.class)))
                .thenReturn(project);

        ProjectDTO result = projectService.addProject(dto);

        assertNotNull(result);
        assertEquals("ERASM", result.getProjectName());
    }

    @Test
    void testGetProjectById() {

        when(projectRepository.findById(1))
                .thenReturn(Optional.of(project));

        ProjectDTO result = projectService.getProjectById(1);

        assertEquals("ERASM", result.getProjectName());
    }

    @Test
    void testGetProjectByIdNotFound() {

        when(projectRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(ProjectNotFound.class,
                () -> projectService.getProjectById(1));
    }

    @Test
    void testGetAllProjects() {

        when(projectRepository.findAll())
                .thenReturn(Arrays.asList(project));

        assertEquals(1, projectService.getAllProjects().size());
    }

    @Test
    void testUpdateProject() {

        when(projectRepository.findById(1))
                .thenReturn(Optional.of(project));

        when(projectRepository.save(any(Project.class)))
                .thenReturn(project);

        ProjectDTO result = projectService.updateProject(1, dto);

        assertEquals("ERASM", result.getProjectName());
    }

    @Test
    void testUpdateProjectNotFound() {

        when(projectRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(ProjectNotFound.class,
                () -> projectService.updateProject(1, dto));
    }

    @Test
    void testDeleteProject() {

        when(projectRepository.existsById(1))
                .thenReturn(true);

        doNothing().when(projectRepository).deleteById(1);

        projectService.deleteProject(1);

        verify(projectRepository).deleteById(1);
    }

    @Test
    void testDeleteProjectNotFound() {

        when(projectRepository.existsById(1))
                .thenReturn(false);

        assertThrows(ProjectNotFound.class,
                () -> projectService.deleteProject(1));
    }
}