 package com.erasm.service;

import java.util.List;

import com.erasm.dto.ResourceRequestDTO;

public interface ResourceRequestService {
	ResourceRequestDTO addResourceRequest(ResourceRequestDTO resourceRequestDTO);
	ResourceRequestDTO updateResourceRequest(int requestId,ResourceRequestDTO resourceRequestDTO);
	void deleteResourceRequest(int requestId);
	ResourceRequestDTO getResourceRequestById(int requestId);
	List<ResourceRequestDTO> getAllResourceRequests();
	ResourceRequestDTO submittRequest(int requestId);
	ResourceRequestDTO approveRequest(int requestId);
	ResourceRequestDTO rejectRequest(int requestId);
	ResourceRequestDTO allocationRequest(int requestId);
	ResourceRequestDTO completeRequest(int requestId);



	
	

}
