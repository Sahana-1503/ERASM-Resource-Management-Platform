package com.erasm.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SkillDTOTest {

    @Test
    void testNoArgsConstructor() {
        SkillDTO dto = new SkillDTO();
        assertNotNull(dto);
    }

    @Test
    void testAllArgsConstructor() {

        SkillDTO dto = new SkillDTO(1, "Java");

        assertEquals(1, dto.getSkillId());
        assertEquals("Java", dto.getSkillName());
    }

    @Test
    void testSettersAndGetters() {

        SkillDTO dto = new SkillDTO();

        dto.setSkillId(2);
        dto.setSkillName("Spring Boot");

        assertEquals(2, dto.getSkillId());
        assertEquals("Spring Boot", dto.getSkillName());
    }
}