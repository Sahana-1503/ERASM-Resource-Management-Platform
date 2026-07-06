package com.erasm.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.erasm.dto.RoleDTO;
import com.erasm.entity.Role;
import com.erasm.repository.RoleRepository;
import com.erasm.service.implementation.RoleServiceImple;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImple roleService;

    @Test
    void testAddRole() {

        RoleDTO dto = new RoleDTO();
        dto.setRoleName("ADMIN");

        Role role = new Role();
        role.setRoleName("ADMIN");

        when(roleRepository.save(any(Role.class)))
                .thenReturn(role);

        RoleDTO result = roleService.addRole(dto);

        assertEquals("ADMIN", result.getRoleName());

        verify(roleRepository).save(any(Role.class));
    }

    @Test
    void testGetRoleById() {

        Role role = new Role();
        role.setRoleID(1);
        role.setRoleName("ADMIN");

        when(roleRepository.findById(1))
                .thenReturn(Optional.of(role));

        RoleDTO dto = roleService.getRoleById(1);

        assertEquals("ADMIN", dto.getRoleName());

        verify(roleRepository).findById(1);
    }

    @Test
    void testGetAllRoles() {

        Role r1 = new Role();
        r1.setRoleName("ADMIN");

        Role r2 = new Role();
        r2.setRoleName("EMPLOYEE");

        when(roleRepository.findAll())
                .thenReturn(Arrays.asList(r1, r2));

        List<RoleDTO> list = roleService.getAllRoles();

        assertEquals(2, list.size());

        verify(roleRepository).findAll();
    }

    @Test
    void testUpdateRole() {

        Role role = new Role();
        role.setRoleID(1);
        role.setRoleName("EMPLOYEE");

        RoleDTO dto = new RoleDTO();
        dto.setRoleName("ADMIN");

        when(roleRepository.findById(1))
                .thenReturn(Optional.of(role));

        when(roleRepository.save(any(Role.class)))
                .thenReturn(role);

        RoleDTO result = roleService.updateRole(1, dto);

        assertEquals("ADMIN", result.getRoleName());

        verify(roleRepository).save(any(Role.class));
    }

    @Test
    void testUpdateRoleNotFound() {

        RoleDTO dto = new RoleDTO();
        dto.setRoleName("ADMIN");

        when(roleRepository.findById(1))
                .thenReturn(Optional.empty());

        RoleDTO result = roleService.updateRole(1, dto);

        assertNull(result);
    }

    @Test
    void testDeleteRole() {

        roleService.deleteRole(1);

        verify(roleRepository).deleteById(1);
    }

}