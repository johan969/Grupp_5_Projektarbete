package se.iths.johan.grupp_5_projektarbete.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.johan.grupp_5_projektarbete.exception.RoleNotFoundException;
import se.iths.johan.grupp_5_projektarbete.model.Role;
import se.iths.johan.grupp_5_projektarbete.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    RoleService roleService;


    private Role role1;
    private List<Role> roles;

    @BeforeEach
    public void setUp() {

        role1 = new Role();
        role1.setTitle("role1Title");
        role1.setDescription("role1Description");
        role1.setLevel("role1Level");
        role1.setManager(true);


        roles = new ArrayList<>();
        roles.add(role1);

    }

    @Test
    public void findAllTest() {

        //mock
        when(roleRepository.findAll()).thenReturn(roles);

        //act
        List<Role> result = roleService.findAll();

        //verify + assert
        verify(roleRepository).findAll();
        assertEquals(1, result.size());
        assertEquals("role1Title", roles.get(0).getTitle());
    }

    @Test
    public void findByIdTest() {

        //mock
        when(roleRepository.findById(1L)).thenReturn(Optional.of(roles.get(0)));

        //act
        Role result = roleService.getOne(1L);

        //verify + assert
        verify(roleRepository).findById(1L);
        assertEquals(roles.get(0), result);
    }

    @Test
    public void findByIdNotFoundTest() {

        //mock
        when(roleRepository.findById(1L)).thenReturn(Optional.empty());

        //act + assert
        assertThrows(RoleNotFoundException.class, () -> roleService.getOne(1L));

        //verify
        verify(roleRepository).findById(1L);
    }

    @Test
    public void saveTest() {

        //Setup
        Role roleToSave = new Role();
        roleToSave.setTitle("roleToSaveTitle");
        Role savedRole = new Role();
        savedRole.setTitle("savedRoleTitle");

        //mock
        when(roleRepository.save(roleToSave)).thenReturn(savedRole);

        //act
        Role result = roleService.save(roleToSave);

        //verify + assert
        verify(roleRepository).save(roleToSave);
        assertEquals("savedRoleTitle", result.getTitle());


    }

    @Test
    public void deleteTest() {

        //mock
        when(roleRepository.findById(1L)).thenReturn(Optional.of(roles.get(0)));

        //act
        roleService.delete(1L);

        //verify
        verify(roleRepository).findById(1L);
        verify(roleRepository).delete(roles.get(0));

    }

    @Test
    public void deleteNotFoundTest() {

        //mock
        when(roleRepository.findById(1L)).thenReturn(Optional.empty());

        //act + assert
        assertThrows(RoleNotFoundException.class, () -> roleService.delete(1L));

        //verify
        verify(roleRepository).findById(1L);

    }

    @Test
    public void updateTest() {

        //setup
        Role roleToUpdate = roles.get(0);
        Role updatedRole = new Role();
        updatedRole.setTitle("updatedRoleTitle");

        //mock
        when(roleRepository.findById(1L)).thenReturn(Optional.of(roleToUpdate));
        when(roleRepository.save(roleToUpdate)).thenReturn(roleToUpdate);


        //act
        Role result = roleService.updateRole(1L, updatedRole);

        //verify
        verify(roleRepository).findById(1L);
        verify(roleRepository).save(roleToUpdate);

        //assert
        assertEquals("updatedRoleTitle", result.getTitle());


    }

    @Test
    public void updateNotFoundTest() {
        when(roleRepository.findById(1L)).thenReturn(Optional.empty());

        //act + assert
        assertThrows(RoleNotFoundException.class, () -> roleService.updateRole(1L, roles.get(0)));

        //verify
        verify(roleRepository).findById(1L);

    }
}
