package com.erasm.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ResourceRequestTest {

    @Test
    void testNoArgsConstructor() {
        ResourceRequest request = new ResourceRequest();
        assertNotNull(request);
    }

    @Test
    void testSettersAndGetters() {

        ResourceRequest request = new ResourceRequest();

        Project project = new Project();

        request.setRequestId(1);
        request.setRequiredSkill("Java");
        request.setRequiredCount(3);
        request.setStatus("DRAFT");
        request.setProject(project);

        assertEquals(1, request.getRequestId());
        assertEquals("Java", request.getRequiredSkill());
        assertEquals(3, request.getRequiredCount());
        assertEquals("DRAFT", request.getStatus());
        assertEquals(project, request.getProject());
    }

    @Test
    void testAllArgsConstructor() {

        Project project = new Project();

        ResourceRequest request = new ResourceRequest(
                1,
                "Spring Boot",
                2,
                "SUBMITTED",
                project);

        assertEquals(1, request.getRequestId());
        assertEquals("Spring Boot", request.getRequiredSkill());
        assertEquals(2, request.getRequiredCount());
        assertEquals("SUBMITTED", request.getStatus());
        assertEquals(project, request.getProject());
    }
}