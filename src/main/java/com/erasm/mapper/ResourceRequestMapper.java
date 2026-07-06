package com.erasm.mapper;

import com.erasm.dto.ResourceRequestDTO;
import com.erasm.entity.ResourceRequest;

public class ResourceRequestMapper {

    public static ResourceRequestDTO toDTO(ResourceRequest resourceRequest) {

        if (resourceRequest == null) {
            return null;
        }

        ResourceRequestDTO dto = new ResourceRequestDTO();
        dto.setRequestId(resourceRequest.getRequestId());
        dto.setRequiredSkill(resourceRequest.getRequiredSkill());
        dto.setRequiredCount(resourceRequest.getRequiredCount());
        dto.setStatus(resourceRequest.getStatus());

        if (resourceRequest.getProject() != null) {
            dto.setProjectId(resourceRequest.getProject().getProjectId());
        }

        return dto;
    }

    public static ResourceRequest toEntity(ResourceRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        ResourceRequest resourceRequest = new ResourceRequest();
        resourceRequest.setRequestId(dto.getRequestId());
        resourceRequest.setRequiredSkill(dto.getRequiredSkill());
        resourceRequest.setRequiredCount(dto.getRequiredCount());
        resourceRequest.setStatus(dto.getStatus());

        return resourceRequest;
    }
}