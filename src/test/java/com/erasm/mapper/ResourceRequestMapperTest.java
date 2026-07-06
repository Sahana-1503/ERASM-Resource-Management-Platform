package com.erasm.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.erasm.dto.ResourceRequestDTO;
import com.erasm.entity.ResourceRequest;

public class ResourceRequestMapperTest {

    @Test
    void testToDTO() {
        ResourceRequest request = new ResourceRequest();
        request.setRequestId(1);
        request.setRequiredSkill("Java");
        request.setRequiredCount(2);
        request.setStatus("DRAFT");

        ResourceRequestDTO dto = ResourceRequestMapper.toDTO(request);

        assertEquals(1, dto.getRequestId());
        assertEquals("Java", dto.getRequiredSkill());
    }

    @Test
    void testToEntity() {
        ResourceRequestDTO dto = new ResourceRequestDTO();
        dto.setRequestId(1);
        dto.setRequiredSkill("Java");
        dto.setRequiredCount(2);
        dto.setStatus("DRAFT");

        ResourceRequest request = ResourceRequestMapper.toEntity(dto);

        assertEquals(1, request.getRequestId());
        assertEquals("Java", request.getRequiredSkill());
    }

    @Test
    void testNull() {
        assertNull(ResourceRequestMapper.toDTO(null));
        assertNull(ResourceRequestMapper.toEntity(null));
    }
}