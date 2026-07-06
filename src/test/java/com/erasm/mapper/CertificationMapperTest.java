package com.erasm.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.erasm.dto.CertificationDTO;
import com.erasm.entity.Certification;

public class CertificationMapperTest {

    @Test
    void testToDTO() {
        Certification cert = new Certification();
        cert.setCertificationId(1);
        cert.setCertificationName("Java");
        cert.setIssuingOrganization("Oracle");

        CertificationDTO dto = CertificationMapper.toDTO(cert);

        assertEquals(1, dto.getCertificationId());
        assertEquals("Java", dto.getCertificationName());
    }

    @Test
    void testToEntity() {
        CertificationDTO dto = new CertificationDTO();
        dto.setCertificationId(1);
        dto.setCertificationName("Java");
        dto.setIssuingOrganization("Oracle");

        Certification cert = CertificationMapper.toEntity(dto);

        assertEquals(1, cert.getCertificationId());
        assertEquals("Java", cert.getCertificationName());
    }

    @Test
    void testNull() {
        assertNull(CertificationMapper.toDTO(null));
        assertNull(CertificationMapper.toEntity(null));
    }
}