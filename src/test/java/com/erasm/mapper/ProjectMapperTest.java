package com.erasm.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.erasm.dto.ProjectDTO;
import com.erasm.entity.Project;

public class ProjectMapperTest {

    @Test
    void testToDTO() {

        Project project = new Project();
        project.setProjectId(1);
        project.setProjectName("ERASM");
        project.setClientName("Infosys");
        project.setStartDate(LocalDate.now());

        ProjectDTO dto = ProjectMapper.toDTO(project);

        assertEquals(1, dto.getProjectId());
        assertEquals("ERASM", dto.getProjectName());
    }

    @Test
    void testToEntity() {

        ProjectDTO dto = new ProjectDTO();
      
        dto.setProjectId(1);
        dto.setProjectName("ERASM");
        dto.setClientName("ABC");
        dto.setTechnologyStack("Java");
        dto.setBudget(100000);
        Project project = ProjectMapper.toEntity(dto);

        assertEquals(1, project.getProjectId());
        assertEquals("ERASM", project.getProjectName());
    }

    @Test
    void testNull() {
        assertNull(ProjectMapper.toDTO(null));
        assertNull(ProjectMapper.toEntity(null));
    }
}