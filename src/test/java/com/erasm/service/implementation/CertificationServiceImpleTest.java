package com.erasm.service.implementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.erasm.dto.CertificationDTO;
import com.erasm.entity.Certification;
import com.erasm.entity.Employee;
import com.erasm.exception.EmployeeNotFoundException;
import com.erasm.repository.CertificationRepository;
import com.erasm.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class CertificationServiceImpleTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private CertificationRepository certificationRepository;

    @InjectMocks
    private CertificationServiceImple service;

    private Employee employee;
    private Certification certification;
    private CertificationDTO dto;

    @BeforeEach
    void setUp() {

        employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmployeeName("Sahana");

        certification = new Certification();
        certification.setCertificationId(1);
        certification.setCertificationName("AWS");
        certification.setIssuingOrganization("Amazon");
        certification.setEmployee(employee);

        dto = new CertificationDTO();
        dto.setCertificationId(1);
        dto.setCertificationName("AWS");
        dto.setIssuingOrganization("Amazon");
        dto.setEmployeeId(1);
    }

    @Test
    void testAddCertification() {

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(employee));

        when(certificationRepository.save(any(Certification.class)))
                .thenReturn(certification);

        CertificationDTO result = service.addCertification(dto);

        assertNotNull(result);
        assertEquals("AWS", result.getCertificationName());

        verify(certificationRepository).save(any(Certification.class));
    }

    @Test
    void testAddCertificationEmployeeNotFound() {

        when(employeeRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class,
                () -> service.addCertification(dto));
    }

    @Test
    void testGetCertificationById() {

        when(certificationRepository.findById(1))
                .thenReturn(Optional.of(certification));

        CertificationDTO result = service.getCertificationById(1);

        assertEquals("AWS", result.getCertificationName());
    }

    @Test
    void testGetCertificationByIdNotFound() {

        when(certificationRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> service.getCertificationById(1));
    }

    @Test
    void testGetAllCertifications() {

        when(certificationRepository.findAll())
                .thenReturn(Arrays.asList(certification));

        assertEquals(1, service.getAllCertifications().size());
    }

    @Test
    void testUpdateCertification() {

        when(certificationRepository.findById(1))
                .thenReturn(Optional.of(certification));

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(employee));

        when(certificationRepository.save(any(Certification.class)))
                .thenReturn(certification);

        CertificationDTO result = service.updateCertification(1, dto);

        assertNotNull(result);

        verify(certificationRepository).save(any(Certification.class));
    }

    @Test
    void testUpdateCertificationEmployeeNotFound() {

        when(certificationRepository.findById(1))
                .thenReturn(Optional.of(certification));

        when(employeeRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class,
                () -> service.updateCertification(1, dto));
    }

    @Test
    void testDeleteCertification() {

        when(certificationRepository.existsById(1))
                .thenReturn(true);

        doNothing().when(certificationRepository).deleteById(1);

        service.deleteCertification(1);

        verify(certificationRepository).deleteById(1);
    }

    @Test
    void testDeleteCertificationNotFound() {

        when(certificationRepository.existsById(1))
                .thenReturn(false);

        assertThrows(RuntimeException.class,
                () -> service.deleteCertification(1));
    }

}