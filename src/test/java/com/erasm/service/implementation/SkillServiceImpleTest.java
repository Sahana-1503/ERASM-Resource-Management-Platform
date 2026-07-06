package com.erasm.service.implementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.erasm.dto.SkillDTO;
import com.erasm.entity.Skill;
import com.erasm.exception.SkillAlreadyExistsException;
import com.erasm.exception.SkillNotFound;
import com.erasm.repository.SkillRepository;

@ExtendWith(MockitoExtension.class)
class SkillServiceImpleTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillServiceImple skillService;

    private Skill skill;
    private SkillDTO dto;

    @BeforeEach
    void setUp() {

        skill = new Skill();
        skill.setSkillId(1);
        skill.setSkillName("Java");

        dto = new SkillDTO();
        dto.setSkillId(1);
        dto.setSkillName("Java");
    }

    @Test
    void testAddSkill() {

        when(skillRepository.findBySkillName("Java"))
                .thenReturn(Optional.empty());

        when(skillRepository.save(any(Skill.class)))
                .thenReturn(skill);

        SkillDTO result = skillService.addSkill(dto);

        assertNotNull(result);
        assertEquals("Java", result.getSkillName());
    }

    @Test
    void testAddSkillAlreadyExists() {

        when(skillRepository.findBySkillName("Java"))
                .thenReturn(Optional.of(skill));

        assertThrows(SkillAlreadyExistsException.class,
                () -> skillService.addSkill(dto));
    }

    @Test
    void testGetSkillById() {

        when(skillRepository.findById(1))
                .thenReturn(Optional.of(skill));

        SkillDTO result = skillService.getSkillById(1);

        assertEquals("Java", result.getSkillName());
    }

    @Test
    void testGetSkillByIdNotFound() {

        when(skillRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(SkillNotFound.class,
                () -> skillService.getSkillById(1));
    }

    @Test
    void testGetAllSkills() {

        when(skillRepository.findAll())
                .thenReturn(Arrays.asList(skill));

        assertEquals(1, skillService.getAllSkills().size());
    }

    @Test
    void testUpdateSkill() {

        when(skillRepository.findById(1))
                .thenReturn(Optional.of(skill));

        when(skillRepository.save(any(Skill.class)))
                .thenReturn(skill);

        SkillDTO result = skillService.updateSkill(1, dto);

        assertEquals("Java", result.getSkillName());
    }

    @Test
    void testUpdateSkillNotFound() {

        when(skillRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(SkillNotFound.class,
                () -> skillService.updateSkill(1, dto));
    }

    @Test
    void testDeleteSkill() {

        when(skillRepository.existsById(1))
                .thenReturn(true);

        doNothing().when(skillRepository).deleteById(1);

        skillService.deleteSkill(1);

        verify(skillRepository).deleteById(1);
    }

    @Test
    void testDeleteSkillNotFound() {

        when(skillRepository.existsById(1))
                .thenReturn(false);

        assertThrows(SkillNotFound.class,
                () -> skillService.deleteSkill(1));
    }
}