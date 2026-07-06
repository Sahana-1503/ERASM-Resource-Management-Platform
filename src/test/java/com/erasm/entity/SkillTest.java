package com.erasm.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class SkillTest {

    @Test
    void testNoArgsConstructor() {
        Skill skill = new Skill();
        assertNotNull(skill);
    }

    @Test
    void testAllArgsConstructor() {

        Skill skill = new Skill(1, "Java", new ArrayList<>());

        assertEquals(1, skill.getSkillId());
        assertEquals("Java", skill.getSkillName());
        assertNotNull(skill.getEmployeeSkills());
    }

    @Test
    void testSettersAndGetters() {

        Skill skill = new Skill();

        skill.setSkillId(2);
        skill.setSkillName("Spring Boot");
        skill.setEmployeeSkills(new ArrayList<>());

        assertEquals(2, skill.getSkillId());
        assertEquals("Spring Boot", skill.getSkillName());
        assertNotNull(skill.getEmployeeSkills());
    }
}