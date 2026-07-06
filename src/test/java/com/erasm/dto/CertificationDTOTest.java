package com.erasm.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CertificationDTOTest {

    @Test
    void testNoArgsConstructor() {
        CertificationDTO dto = new CertificationDTO();
        assertNotNull(dto);
    }

    @Test
    void testSettersAndGetters() {

        CertificationDTO dto = new CertificationDTO();

        dto.setCertificationId(1);
        dto.setCertificationName("AWS Certified Developer");
        dto.setIssuingOrganization("Amazon");
        dto.setEmployeeId(101);

        assertEquals(1, dto.getCertificationId());
        assertEquals("AWS Certified Developer", dto.getCertificationName());
        assertEquals("Amazon", dto.getIssuingOrganization());
        assertEquals(101, dto.getEmployeeId());
    }
}