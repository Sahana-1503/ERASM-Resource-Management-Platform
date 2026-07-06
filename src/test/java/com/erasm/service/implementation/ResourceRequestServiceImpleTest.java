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

import com.erasm.dto.ResourceRequestDTO;
import com.erasm.entity.Project;
import com.erasm.entity.ResourceRequest;
import com.erasm.exception.ProjectNotFound;
import com.erasm.exception.ResourceRequestNotFoundException;
import com.erasm.repository.ProjectRepository;
import com.erasm.repository.ResourceRequestRepository;

@ExtendWith(MockitoExtension.class)
class ResourceRequestServiceImpleTest {

    @Mock
    private ResourceRequestRepository resourceRequestRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ResourceRequestServiceImple service;

    private ResourceRequest request;
    private ResourceRequestDTO dto;
    private Project project;

    @BeforeEach
    void setUp() {

        project = new Project();
        project.setProjectId(1);
        project.setProjectName("ERASM");

        request = new ResourceRequest();
        request.setRequestId(1);
        request.setRequiredSkill("Java");
        request.setRequiredCount(2);
        request.setStatus("DRAFT");
        request.setProject(project);

        dto = new ResourceRequestDTO();
        dto.setRequestId(1);
        dto.setRequiredSkill("Java");
        dto.setRequiredCount(2);
        dto.setStatus("DRAFT");
        dto.setProjectId(1);
    }

    @Test
    void testAddResourceRequest() {

        when(projectRepository.findById(1)).thenReturn(Optional.of(project));
        when(resourceRequestRepository.save(any(ResourceRequest.class))).thenReturn(request);

        ResourceRequestDTO result = service.addResourceRequest(dto);

        assertNotNull(result);
        assertEquals("Java", result.getRequiredSkill());

        verify(resourceRequestRepository).save(any(ResourceRequest.class));
    }

    @Test
    void testAddResourceRequestProjectNotFound() {

        when(projectRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ProjectNotFound.class,
                () -> service.addResourceRequest(dto));
    }

    @Test
    void testGetAllResourceRequests() {

        when(resourceRequestRepository.findAll())
                .thenReturn(Arrays.asList(request));

        assertEquals(1, service.getAllResourceRequests().size());
    }

    @Test
    void testGetResourceRequestById() {

        when(resourceRequestRepository.findById(1))
                .thenReturn(Optional.of(request));

        ResourceRequestDTO result = service.getResourceRequestById(1);

        assertEquals("Java", result.getRequiredSkill());
    }

    @Test
    void testGetResourceRequestByIdNotFound() {

        when(resourceRequestRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(ResourceRequestNotFoundException.class,
                () -> service.getResourceRequestById(1));
    }

    @Test
    void testUpdateResourceRequest() {

        when(resourceRequestRepository.findById(1))
                .thenReturn(Optional.of(request));

        when(projectRepository.findById(1))
                .thenReturn(Optional.of(project));

        when(resourceRequestRepository.save(any(ResourceRequest.class)))
                .thenReturn(request);

        ResourceRequestDTO result = service.updateResourceRequest(1, dto);

        assertNotNull(result);

        verify(resourceRequestRepository).save(any(ResourceRequest.class));
    }

    @Test
    void testDeleteResourceRequest() {

        when(resourceRequestRepository.existsById(1))
                .thenReturn(true);

        doNothing().when(resourceRequestRepository).deleteById(1);

        service.deleteResourceRequest(1);

        verify(resourceRequestRepository).deleteById(1);
    }

    @Test
    void testDeleteResourceRequestNotFound() {

        when(resourceRequestRepository.existsById(1))
                .thenReturn(false);

        assertThrows(ResourceRequestNotFoundException.class,
                () -> service.deleteResourceRequest(1));
    }

    @Test
    void testSubmitRequest() {

        when(resourceRequestRepository.findById(1))
                .thenReturn(Optional.of(request));

        when(resourceRequestRepository.save(any(ResourceRequest.class)))
                .thenReturn(request);

        assertNotNull(service.submittRequest(1));
    }

    @Test
    void testApproveRequest() {

        when(resourceRequestRepository.findById(1))
                .thenReturn(Optional.of(request));

        when(resourceRequestRepository.save(any(ResourceRequest.class)))
                .thenReturn(request);

        assertNotNull(service.approveRequest(1));
    }

    @Test
    void testRejectRequest() {

        when(resourceRequestRepository.findById(1))
                .thenReturn(Optional.of(request));

        when(resourceRequestRepository.save(any(ResourceRequest.class)))
                .thenReturn(request);

        assertNotNull(service.rejectRequest(1));
    }

    @Test
    void testAllocationRequest() {

        when(resourceRequestRepository.findById(1))
                .thenReturn(Optional.of(request));

        when(resourceRequestRepository.save(any(ResourceRequest.class)))
                .thenReturn(request);

        assertNotNull(service.allocationRequest(1));
    }

    @Test
    void testCompleteRequest() {

        when(resourceRequestRepository.findById(1))
                .thenReturn(Optional.of(request));

        when(resourceRequestRepository.save(any(ResourceRequest.class)))
                .thenReturn(request);

        assertNotNull(service.completeRequest(1));
    }
}