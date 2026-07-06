package com.erasm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erasm.entity.Allocation;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation,Integer> {
	List<Allocation> findByEmployeeEmployeeId(int employeeId);
}
