package com.erasm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.erasm.dto.ProjectDTO;
import com.erasm.entity.Project;
import com.erasm.exception.ProjectNotFound;
import com.erasm.repository.ProjectRepository;
import com.erasm.service.implementation.ProjectServiceImple;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImple projectService;

    @Test
    void testAddProject() {

    	ProjectDTO dto = new ProjectDTO();
    	dto.setProjectName("ERASM");
    	dto.setClientName("Infosys");
    	dto.setStartDate(LocalDate.now());
    	dto.setEndDate(LocalDate.now().plusMonths(6));
    	dto.setTechnologyStack("Java, Spring Boot");
    	dto.setBudget(1000000);
        Project project = new Project();
        project.setProjectName("ERASM");

        when(projectRepository.save(any(Project.class)))
                .thenReturn(project);

        ProjectDTO result = projectService.addProject(dto);

        assertEquals("ERASM", result.getProjectName());

        verify(projectRepository).save(any(Project.class));
    }

    @Test
    void testGetProjectById() {

        Project project = new Project();
        project.setProjectId(1);
        project.setProjectName("ERASM");

        when(projectRepository.findById(1))
                .thenReturn(Optional.of(project));

        ProjectDTO dto = projectService.getProjectById(1);

        assertEquals("ERASM", dto.getProjectName());

        verify(projectRepository).findById(1);
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

        Project p1 = new Project();
        p1.setProjectName("Project A");

        Project p2 = new Project();
        p2.setProjectName("Project B");

        when(projectRepository.findAll())
                .thenReturn(Arrays.asList(p1, p2));

        List<ProjectDTO> list = projectService.getAllProjects();

        assertEquals(2, list.size());

        verify(projectRepository).findAll();
    }

    @Test
    void testUpdateProject() {

        Project project = new Project();
        project.setProjectId(1);
        project.setProjectName("Old");

        ProjectDTO dto = new ProjectDTO();
        dto.setProjectName("New");
        dto.setClientName("Infosys");
        dto.setStartDate(LocalDate.now());
        dto.setEndDate(LocalDate.now().plusMonths(6));
        dto.setTechnologyStack("Java, Spring Boot");
        dto.setBudget(1000000);

        when(projectRepository.findById(1))
                .thenReturn(Optional.of(project));

        when(projectRepository.save(any(Project.class)))
                .thenReturn(project);

        ProjectDTO result = projectService.updateProject(1, dto);

        assertEquals("New", result.getProjectName());

        verify(projectRepository).save(any(Project.class));
    }

    @Test
    void testUpdateProject1() {

        Project project = new Project();
        project.setProjectId(1);
        project.setProjectName("Old");

        ProjectDTO dto = new ProjectDTO();
        dto.setProjectName("New");
        dto.setClientName("Infosys");
        dto.setStartDate(LocalDate.now());
        dto.setEndDate(LocalDate.now().plusMonths(6));
        dto.setTechnologyStack("Java, Spring Boot");
        dto.setBudget(1000000);

        when(projectRepository.findById(1))
                .thenReturn(Optional.of(project));

        when(projectRepository.save(any(Project.class)))
                .thenReturn(project);

        ProjectDTO result = projectService.updateProject(1, dto);

        assertEquals("New", result.getProjectName());

        verify(projectRepository).save(any(Project.class));
    }    @Test
    void testDeleteProject() {

        when(projectRepository.existsById(1))
                .thenReturn(true);

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