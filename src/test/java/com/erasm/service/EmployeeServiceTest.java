package com.erasm.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.erasm.dto.EmployeeDTO;
import com.erasm.entity.Employee;
import com.erasm.exception.EmployeeNotFoundException;
import com.erasm.repository.EmployeeRepository;
import com.erasm.service.implementation.EmployeeServiceImple;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeServiceImple employeeService;
	@Test
	void testAddEmployee() {
		EmployeeDTO dto=new EmployeeDTO();
		dto.setEmployeeName("Sahana");
		
		Employee employee=new Employee();
		employee.setEmployeeName("Sahana");
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
		EmployeeDTO result=employeeService.addEmployee(dto);
		assertEquals("Sahana",result.getEmployeeName());
		verify(employeeRepository).save(any(Employee.class));

	}
	@Test
	void testGetEmployeeById() {
		Employee employee=new Employee();
		employee.setEmployeeId(1);
		employee.setEmployeeName("Sahana");
		
		when(employeeRepository.findById(1))
				.thenReturn(java.util.Optional.of(employee));
		
		EmployeeDTO dto=employeeService.getEmployeeById(1);
		assertEquals("Sahana",dto.getEmployeeName());
		verify(employeeRepository).findById(1);
	
	}
	@Test
	void testGetAllEmployees() {
		Employee e1=new Employee();
		Employee e2=new Employee();

		e1.setEmployeeName("Sahana");
		e2.setEmployeeName("Rahul");
		
		when(employeeRepository.findAll())
				.thenReturn(Arrays.asList(e1,e2));
		
		List<EmployeeDTO> list=employeeService.getAllEmployees();
		assertEquals(2,list.size());
		verify(employeeRepository).findAll();
	
	}
	@Test
	void testUpdateEmployees() {
		Employee employee=new Employee();
		

		employee.setEmployeeId(1);
		employee.setEmployeeName("Old");
		
		EmployeeDTO dto=new EmployeeDTO();
		

		
		dto.setEmployeeName("New");

		
		when(employeeRepository.findById(1))
				.thenReturn(Optional.of(employee));
		when(employeeRepository.save(any(Employee.class)))
				.thenReturn(employee);

		
		EmployeeDTO result=employeeService.updateEmployee(1,dto);
		assertEquals("New",result.getEmployeeName());
		verify(employeeRepository).save(any(Employee.class));
	}
		
		@Test
		void testDeleteEmployeeNotFound() {
			when(employeeRepository.existsById(1))
			.thenReturn(false);
			assertThrows(EmployeeNotFoundException.class,()->employeeService.deleteEmployee(1));
	
	} 
		@Test
		void testDeleteEmployee() {
			when(employeeRepository.existsById(1))
			.thenReturn(true);
			employeeService.deleteEmployee(1);		
			verify(employeeRepository).deleteById(1);
		}
		@Test
		void testGetEmployeeByIdNotFound() {
			when(employeeRepository.findById(1))
			.thenReturn(Optional.empty());
			assertThrows(EmployeeNotFoundException.class,()->employeeService.getEmployeeById(1));
	
	}

	

	

}
