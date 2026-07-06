package com.erasm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
import com.erasm.service.implementation.SkillServiceImple;

@ExtendWith(MockitoExtension.class)
public class SkillServiceTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillServiceImple skillService;

    @Test
    void testAddSkill() {

        SkillDTO dto = new SkillDTO();
        dto.setSkillName("Java");

        Skill skill = new Skill();
        skill.setSkillName("Java");

        when(skillRepository.findBySkillName("Java"))
                .thenReturn(Optional.empty());

        when(skillRepository.save(any(Skill.class)))
                .thenReturn(skill);

        SkillDTO result = skillService.addSkill(dto);

        assertEquals("Java", result.getSkillName());

        verify(skillRepository).save(any(Skill.class));
    }

    @Test
    void testAddSkillAlreadyExists() {

        SkillDTO dto = new SkillDTO();
        dto.setSkillName("Java");

        Skill skill = new Skill();
        skill.setSkillName("Java");

        when(skillRepository.findBySkillName("Java"))
                .thenReturn(Optional.of(skill));

        assertThrows(SkillAlreadyExistsException.class,
                () -> skillService.addSkill(dto));
    }

    @Test
    void testGetSkillById() {

        Skill skill = new Skill();
        skill.setSkillId(1);
        skill.setSkillName("Java");

        when(skillRepository.findById(1))
                .thenReturn(Optional.of(skill));

        SkillDTO dto = skillService.getSkillById(1);

        assertEquals("Java", dto.getSkillName());

        verify(skillRepository).findById(1);
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

        Skill s1 = new Skill();
        s1.setSkillName("Java");

        Skill s2 = new Skill();
        s2.setSkillName("Spring");

        when(skillRepository.findAll())
                .thenReturn(Arrays.asList(s1, s2));

        List<SkillDTO> list = skillService.getAllSkills();

        assertEquals(2, list.size());

        verify(skillRepository).findAll();
    }

    @Test
    void testUpdateSkill() {

        Skill skill = new Skill();
        skill.setSkillId(1);
        skill.setSkillName("Old");

        SkillDTO dto = new SkillDTO();
        dto.setSkillName("New");

        when(skillRepository.findById(1))
                .thenReturn(Optional.of(skill));

        when(skillRepository.save(any(Skill.class)))
                .thenReturn(skill);

        SkillDTO result = skillService.updateSkill(1, dto);

        assertEquals("New", result.getSkillName());

        verify(skillRepository).save(any(Skill.class));
    }

    @Test
    void testDeleteSkill() {

        when(skillRepository.existsById(1))
                .thenReturn(true);

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