package com.erasm.service;

import java.util.List;



import com.erasm.dto.AllocationDTO;

public interface AllocationService {
	AllocationDTO addAllocation(AllocationDTO allocationDTO);
	AllocationDTO updateAllocation(int allocationId,AllocationDTO allocationDTO);
	void deleteAllocation(int allocationId);
	AllocationDTO getAllocationById(int allocationId);
	List<AllocationDTO> getAllAllocations();

}
