package com.erasm.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.erasm.dto.SkillDTO;
import com.erasm.entity.Skill;

public class SkillMapperTest {

    @Test
    void testToDTO() {

        Skill skill = new Skill();
        skill.setSkillId(1);
        skill.setSkillName("Java");

        SkillDTO dto = SkillMapper.toDTO(skill);

        assertEquals(1, dto.getSkillId());
        assertEquals("Java", dto.getSkillName());
    }

    @Test
    void testToEntity() {

        SkillDTO dto = new SkillDTO();
        dto.setSkillId(1);
        dto.setSkillName("Spring");

        Skill skill = SkillMapper.toEntity(dto);

        assertEquals(1, skill.getSkillId());
        assertEquals("Spring", skill.getSkillName());
    }

    @Test
    void testNull() {
        assertNull(SkillMapper.toDTO(null));
        assertNull(SkillMapper.toEntity(null));
    }
}