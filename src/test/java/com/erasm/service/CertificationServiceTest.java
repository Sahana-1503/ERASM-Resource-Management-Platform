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

import com.erasm.dto.CertificationDTO;
import com.erasm.entity.Certification;
import com.erasm.entity.Employee;
import com.erasm.exception.EmployeeNotFoundException;
import com.erasm.repository.CertificationRepository;
import com.erasm.repository.EmployeeRepository;
import com.erasm.service.implementation.CertificationServiceImple;

@ExtendWith(MockitoExtension.class)
public class CertificationServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private CertificationRepository certificationRepository;

    @InjectMocks
    private CertificationServiceImple certificationService;

    @Test
    void testAddCertification() {

        Employee employee = new Employee();
        employee.setEmployeeId(1);

        Certification certification = new Certification();

        CertificationDTO dto = new CertificationDTO();
        dto.setEmployeeId(1);

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(employee));

        when(certificationRepository.save(any(Certification.class)))
                .thenReturn(certification);

        certificationService.addCertification(dto);

        verify(certificationRepository).save(any(Certification.class));
    }

    @Test
    void testAddCertificationEmployeeNotFound() {

        CertificationDTO dto = new CertificationDTO();
        dto.setEmployeeId(1);

        when(employeeRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class,
                () -> certificationService.addCertification(dto));
    }

    @Test
    void testGetCertificationById() {

        Certification certification = new Certification();

        when(certificationRepository.findById(1))
                .thenReturn(Optional.of(certification));

        certificationService.getCertificationById(1);

        verify(certificationRepository).findById(1);
    }

    @Test
    void testGetAllCertifications() {

        Certification c1 = new Certification();
        Certification c2 = new Certification();

        when(certificationRepository.findAll())
                .thenReturn(Arrays.asList(c1, c2));

        List<CertificationDTO> list =
                certificationService.getAllCertifications();

        assertEquals(2, list.size());

        verify(certificationRepository).findAll();
    }

    @Test
    void testUpdateCertification() {

        Employee employee = new Employee();
        employee.setEmployeeId(1);

        Certification certification = new Certification();

        CertificationDTO dto = new CertificationDTO();
        dto.setEmployeeId(1);
        dto.setCertificationName("AWS");
        dto.setIssuingOrganization("Amazon");

        when(certificationRepository.findById(1))
                .thenReturn(Optional.of(certification));

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(employee));

        when(certificationRepository.save(any(Certification.class)))
                .thenReturn(certification);

        certificationService.updateCertification(1, dto);

        verify(certificationRepository).save(any(Certification.class));
    }

    @Test
    void testDeleteCertification() {

        when(certificationRepository.existsById(1))
                .thenReturn(true);

        certificationService.deleteCertification(1);

        verify(certificationRepository).deleteById(1);
    }

    @Test
    void testDeleteCertificationNotFound() {

        when(certificationRepository.existsById(1))
                .thenReturn(false);

        assertThrows(RuntimeException.class,
                () -> certificationService.deleteCertification(1));
    }

    @Test
    void testUpdateCertificationEmployeeNotFound() {

        Certification certification = new Certification();

        CertificationDTO dto = new CertificationDTO();
        dto.setEmployeeId(1);

        when(certificationRepository.findById(1))
                .thenReturn(Optional.of(certification));

        when(employeeRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class,
                () -> certificationService.updateCertification(1, dto));
    }
}