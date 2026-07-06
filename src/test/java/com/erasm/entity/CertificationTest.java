package com.erasm.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CertificationTest {

    @Test
    void testNoArgsConstructor() {
        Certification certification = new Certification();
        assertNotNull(certification);
    }

    @Test
    void testSettersAndGetters() {

        Certification certification = new Certification();

        Employee employee = new Employee();

        certification.setCertificationId(1);
        certification.setCertificationName("AWS Certified Developer");
        certification.setIssuingOrganization("Amazon");
        certification.setEmployee(employee);

        assertEquals(1, certification.getCertificationId());
        assertEquals("AWS Certified Developer", certification.getCertificationName());
        assertEquals("Amazon", certification.getIssuingOrganization());
        assertEquals(employee, certification.getEmployee());
    }
}