package com.erasm.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.erasm.dto.EmployeeSkillDTO;
import com.erasm.entity.EmployeeSkill;

public class EmployeeSkillMapperTest {

    @Test
    void testToDTO() {
        EmployeeSkill skill = new EmployeeSkill();
        skill.setEmployeeskillId(1);
        skill.setSkillLevel("Advanced");
        skill.setExperience(5.0);

        EmployeeSkillDTO dto = EmployeeSkillMapper.toDTO(skill);

        assertEquals(1, dto.getEmployeeSkillId());
        assertEquals("Advanced", dto.getSkillLevel());
    }

    @Test
    void testToEntity() {
        EmployeeSkillDTO dto = new EmployeeSkillDTO();
        dto.setEmployeeSkillId(1);
        dto.setSkillLevel("Advanced");
        dto.setExperience(5.0);

        EmployeeSkill skill = EmployeeSkillMapper.toEntity(dto);

        assertEquals(1, skill.getEmployeeskillId());
        assertEquals("Advanced", skill.getSkillLevel());
    }

    @Test
    void testNull() {
        assertNull(EmployeeSkillMapper.toDTO(null));
        assertNull(EmployeeSkillMapper.toEntity(null));
    }
}