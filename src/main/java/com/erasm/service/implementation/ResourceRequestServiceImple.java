package com.erasm.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.erasm.dto.ResourceRequestDTO;
import com.erasm.entity.Project;
import com.erasm.entity.ResourceRequest;
import com.erasm.exception.ProjectNotFound;
import com.erasm.exception.ResourceRequestNotFoundException;
import com.erasm.mapper.ResourceRequestMapper;
import com.erasm.repository.ProjectRepository;
import com.erasm.repository.ResourceRequestRepository;
import com.erasm.service.ResourceRequestService;
@Service
public class ResourceRequestServiceImple implements ResourceRequestService {
	
	private final ResourceRequestRepository resourceRequestRepository ;
	private final ProjectRepository projectRepository;
	private static final Logger logger=LoggerFactory.getLogger(ResourceRequestServiceImple.class);
	




	public ResourceRequestServiceImple(ResourceRequestRepository resourceRequestRepository,
			ProjectRepository projectRepository) {
		super();
		this.resourceRequestRepository = resourceRequestRepository;
		this.projectRepository = projectRepository;
	}

	@Override
	public ResourceRequestDTO addResourceRequest(ResourceRequestDTO dto) {
		// TODO Auto-generated method stub
		Project project=projectRepository.findById(dto.getProjectId())
				.orElseThrow(()->new ProjectNotFound(
						"Project not found with id:"+dto.getProjectId()));
		
		ResourceRequest request=ResourceRequestMapper.toEntity(dto);
		request.setProject(project);
		
		ResourceRequest saved=resourceRequestRepository.save(request);
		logger.info("Resource Request created successfully with ID:{}",saved.getRequestId());
		return ResourceRequestMapper.toDTO(saved);
		
	}

	@Override
	public ResourceRequestDTO updateResourceRequest(int requestId, ResourceRequestDTO dto) {
		// TODO Auto-generated method stub
		ResourceRequest request=resourceRequestRepository.findById(requestId)
				.orElseThrow(()->
				new ResourceRequestNotFoundException(
						"Resource Request not found with id:"+requestId));
		
		Project project=projectRepository.findById(dto.getProjectId())
				.orElseThrow(()->new ProjectNotFound(
						"Project not found with id:"+dto.getProjectId()));
		request.setRequiredSkill(dto.getRequiredSkill());
		request.setRequiredCount(dto.getRequiredCount());
		request.setStatus(dto.getStatus());
		request.setProject(project);
		

		
		
				
		ResourceRequest updated=resourceRequestRepository.save(request);
		logger.info("Resource Request updated successfully with ID:{}",updated.getRequestId());
		
		
		return ResourceRequestMapper.toDTO(updated);
	}

	@Override
	public void deleteResourceRequest(int requestId) {
		// TODO Auto-generated method stub
		if(!resourceRequestRepository.existsById(requestId)) {
			logger.warn("Resource Request not found with ID:{}",requestId);
			throw new ResourceRequestNotFoundException(
					"Resource Request not found with id:"+requestId);
		}
		resourceRequestRepository.deleteById(requestId);
		logger.info("Resource Request deleted successfully with ID:{}",requestId);
		

		
	}

	@Override
	public ResourceRequestDTO getResourceRequestById(int requestId) {
		// TODO Auto-generated method stub
		ResourceRequest request=resourceRequestRepository.findById(requestId)
				.orElseThrow(()->
				new ResourceRequestNotFoundException(
						"Resource Request not found with id:"+requestId));
		return ResourceRequestMapper.toDTO(request);
	}

	@Override
	public List<ResourceRequestDTO> getAllResourceRequests() {
		// TODO Auto-generated method stub
		
		return resourceRequestRepository.findAll()
				.stream()
				.map(ResourceRequestMapper::toDTO)
				.toList();
	}

	@Override
	public ResourceRequestDTO submittRequest(int requestId) {
		// TODO Auto-generated method stub
		ResourceRequest request=resourceRequestRepository.findById(requestId)
				.orElseThrow(()->
				new ResourceRequestNotFoundException(
						"Resource Request not found with id:"+requestId));
		request.setStatus("SUBMITTED");
		ResourceRequest updated=resourceRequestRepository.save(request);
		return ResourceRequestMapper.toDTO(updated);

		
		
	}

	@Override
	public ResourceRequestDTO approveRequest(int requestId) {
		// TODO Auto-generated method stub
		ResourceRequest request=resourceRequestRepository.findById(requestId)
				.orElseThrow(()->
				new ResourceRequestNotFoundException(
						"Resource Request not found with id:"+requestId));
		request.setStatus("APPROVED");
		ResourceRequest updated=resourceRequestRepository.save(request);
		return ResourceRequestMapper.toDTO(updated);

		
	}

	@Override
	public ResourceRequestDTO rejectRequest(int requestId) {
		// TODO Auto-generated method stub
		ResourceRequest request=resourceRequestRepository.findById(requestId)
				.orElseThrow(()->
				new ResourceRequestNotFoundException(
						"Resource Request not found with id:"+requestId));
		request.setStatus("REJECTED");
		ResourceRequest updated=resourceRequestRepository.save(request);
		return ResourceRequestMapper.toDTO(updated);

		
	}

	@Override
	public ResourceRequestDTO allocationRequest(int requestId) {
		// TODO Auto-generated method stub
		ResourceRequest request=resourceRequestRepository.findById(requestId)
				.orElseThrow(()->
				new ResourceRequestNotFoundException(
						"Resource Request not found with id:"+requestId));
		request.setStatus("ALLOCATED");
		ResourceRequest updated=resourceRequestRepository.save(request);
		return ResourceRequestMapper.toDTO(updated);

		
	}

	@Override
	public ResourceRequestDTO completeRequest(int requestId) {
		// TODO Auto-generated method stub
		ResourceRequest request=resourceRequestRepository.findById(requestId)
				.orElseThrow(()->
				new ResourceRequestNotFoundException(
						"Resource Request not found with id:"+requestId));
		request.setStatus("COMPLETED");
		ResourceRequest updated=resourceRequestRepository.save(request);
		return ResourceRequestMapper.toDTO(updated);

		
	}
	
	

	



}
