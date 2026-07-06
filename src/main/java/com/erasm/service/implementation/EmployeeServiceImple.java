package com.erasm.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.erasm.dto.EmployeeDTO;
import com.erasm.entity.Employee;
import com.erasm.exception.EmployeeNotFoundException;
import com.erasm.mapper.EmployeeMapper;
import com.erasm.repository.EmployeeRepository;
import com.erasm.service.EmployeeService;

@Service
public class EmployeeServiceImple implements EmployeeService {
	private final EmployeeRepository employeeRepository;
	private static final Logger logger=LoggerFactory.getLogger(EmployeeServiceImple.class);
	

	public EmployeeServiceImple(EmployeeRepository employeeRepository) {
		
		this.employeeRepository = employeeRepository;
	}

	@Override
	public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
		// TODO Auto-generated method stub
		Employee employee=EmployeeMapper.toEntity(employeeDTO);
		Employee savedEmployee=employeeRepository.save(employee);
		logger.info("Employee created successfully with ID:{}",savedEmployee.getEmployeeId());
		return EmployeeMapper.toDTO(savedEmployee);
		
	}

	@Override
	public EmployeeDTO updateEmployee(int employeeId, EmployeeDTO employeeDTO) {
		// TODO Auto-generated method stub
		Employee employee=employeeRepository.findById(employeeId)
				.orElseThrow(()->
				new EmployeeNotFoundException(
						"Employee not found with id:"+employeeId));
		employee.setEmployeeName(employeeDTO.getEmployeeName());
		employee.setPhoneNumber(employeeDTO.getPhoneNumber());
		employee.setDepartment(employeeDTO.getDepartment());
		employee.setDesignation(employeeDTO.getDesignation());
		employee.setExperience(employeeDTO.getExperience());
		employee.setBenchStatus(employeeDTO.getBenchStatus());
		
		Employee updatedEmployee=employeeRepository.save(employee);
		logger.info("Employee updated successfully with ID:{}",updatedEmployee.getEmployeeId());
		
		return EmployeeMapper.toDTO(updatedEmployee);
	}

	@Override
	public void deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		
		if(!employeeRepository.existsById(employeeId)) {
			logger.warn("Employee not found with ID:{}",employeeId);
			throw new EmployeeNotFoundException(
					"Employee not found with id:"+employeeId);
		}
		
		employeeRepository.deleteById(employeeId);
		logger.info("Employee deleted successfully with ID:{}",employeeId);
		
	}

	@Override
	public EmployeeDTO getEmployeeById(int employeeId) {
		// TODO Auto-generated method stub
		Employee employee=employeeRepository.findById(employeeId)
				.orElseThrow(()->
				new EmployeeNotFoundException(
						"Employee not found with id:"+employeeId));
		return EmployeeMapper.toDTO(employee);
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		// TODO Auto-generated method stub
		
		return employeeRepository.findAll()
				.stream()
				.map(EmployeeMapper::toDTO)
				.toList();
	}
	

}
