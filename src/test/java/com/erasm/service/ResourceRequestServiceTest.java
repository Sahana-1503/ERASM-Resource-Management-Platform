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

import com.erasm.dto.ResourceRequestDTO;
import com.erasm.entity.Project;
import com.erasm.entity.ResourceRequest;
import com.erasm.exception.ProjectNotFound;
import com.erasm.exception.ResourceRequestNotFoundException;
import com.erasm.repository.ProjectRepository;
import com.erasm.repository.ResourceRequestRepository;
import com.erasm.service.implementation.ResourceRequestServiceImple;

@ExtendWith(MockitoExtension.class)
public class ResourceRequestServiceTest {

    @Mock
    private ResourceRequestRepository resourceRequestRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ResourceRequestServiceImple resourceRequestService;

    @Test
    void testAddResourceRequest() {

        Project project = new Project();
        project.setProjectId(1);

        ResourceRequest request = new ResourceRequest();

        ResourceRequestDTO dto = new ResourceRequestDTO();
        dto.setProjectId(1);

        when(projectRepository.findById(1)).thenReturn(Optional.of(project));
        when(resourceRequestRepository.save(any(ResourceRequest.class))).thenReturn(request);

        resourceRequestService.addResourceRequest(dto);

        verify(resourceRequestRepository).save(any(ResourceRequest.class));
    }

    @Test
    void testAddResourceRequestProjectNotFound() {

        ResourceRequestDTO dto = new ResourceRequestDTO();
        dto.setProjectId(1);

        when(projectRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ProjectNotFound.class,
                () -> resourceRequestService.addResourceRequest(dto));
    }

    @Test
    void testGetResourceRequestById() {

        ResourceRequest request = new ResourceRequest();

        when(resourceRequestRepository.findById(1))
                .thenReturn(Optional.of(request));

        resourceRequestService.getResourceRequestById(1);

        verify(resourceRequestRepository).findById(1);
    }

    @Test
    void testGetResourceRequestByIdNotFound() {

        when(resourceRequestRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(ResourceRequestNotFoundException.class,
                () -> resourceRequestService.getResourceRequestById(1));
    }

    @Test
    void testGetAllResourceRequests() {

        ResourceRequest r1 = new ResourceRequest();
        ResourceRequest r2 = new ResourceRequest();

        when(resourceRequestRepository.findAll())
                .thenReturn(Arrays.asList(r1, r2));

        List<ResourceRequestDTO> list =
                resourceRequestService.getAllResourceRequests();

        assertEquals(2, list.size());

        verify(resourceRequestRepository).findAll();
    }

    @Test
    void testUpdateResourceRequest() {

        ResourceRequest request = new ResourceRequest();

        Project project = new Project();
        project.setProjectId(1);

        ResourceRequestDTO dto = new ResourceRequestDTO();
        dto.setProjectId(1);
        dto.setRequiredSkill("Java");
        dto.setRequiredCount(2);
        dto.setStatus("DRAFT");

        when(resourceRequestRepository.findById(1))
                .thenReturn(Optional.of(request));

        when(projectRepository.findById(1))
                .thenReturn(Optional.of(project));

        when(resourceRequestRepository.save(any(ResourceRequest.class)))
                .thenReturn(request);

        resourceRequestService.updateResourceRequest(1, dto);

        verify(resourceRequestRepository).save(any(ResourceRequest.class));
    }

    @Test
    void testDeleteResourceRequest() {

        when(resourceRequestRepository.existsById(1))
                .thenReturn(true);

        resourceRequestService.deleteResourceRequest(1);

        verify(resourceRequestRepository).deleteById(1);
    }

    @Test
    void testDeleteResourceRequestNotFound() {

        when(resourceRequestRepository.existsById(1))
                .thenReturn(false);

        assertThrows(ResourceRequestNotFoundException.class,
                () -> resourceRequestService.deleteResourceRequest(1));
    }

    @Test
    void testSubmitRequest() {

        ResourceRequest request = new ResourceRequest();

        when(resourceRequestRepository.findById(1))
                .thenReturn(Optional.of(request));

        when(resourceRequestRepository.save(any(ResourceRequest.class)))
                .thenReturn(request);

        resourceRequestService.submittRequest(1);

        assertEquals("SUBMITTED", request.getStatus());
    }

    @Test
    void testApproveRequest() {

        ResourceRequest request = new ResourceRequest();

        when(resourceRequestRepository.findById(1))
                .thenReturn(Optional.of(request));

        when(resourceRequestRepository.save(any(ResourceRequest.class)))
                .thenReturn(request);

        resourceRequestService.approveRequest(1);

        assertEquals("APPROVED", request.getStatus());
    }

    @Test
    void testRejectRequest() {

        ResourceRequest request = new ResourceRequest();

        when(resourceRequestRepository.findById(1))
                .thenReturn(Optional.of(request));

        when(resourceRequestRepository.save(any(ResourceRequest.class)))
                .thenReturn(request);

        resourceRequestService.rejectRequest(1);

        assertEquals("REJECTED", request.getStatus());
    }

    @Test
    void testAllocationRequest() {

        ResourceRequest request = new ResourceRequest();

        when(resourceRequestRepository.findById(1))
                .thenReturn(Optional.of(request));

        when(resourceRequestRepository.save(any(ResourceRequest.class)))
                .thenReturn(request);

        resourceRequestService.allocationRequest(1);

        assertEquals("ALLOCATED", request.getStatus());
    }

    @Test
    void testCompleteRequest() {

        ResourceRequest request = new ResourceRequest();

        when(resourceRequestRepository.findById(1))
                .thenReturn(Optional.of(request));

        when(resourceRequestRepository.save(any(ResourceRequest.class)))
                .thenReturn(request);

        resourceRequestService.completeRequest(1);

        assertEquals("COMPLETED", request.getStatus());
    }
}