package se.iths.johan.grupp_5_projektarbete.service;

import org.springframework.stereotype.Service;
import se.iths.johan.grupp_5_projektarbete.exception.RoleNotFoundException;
import se.iths.johan.grupp_5_projektarbete.model.Role;
import se.iths.johan.grupp_5_projektarbete.repository.RoleRepository;
import se.iths.johan.grupp_5_projektarbete.validator.RoleValidator;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleValidator roleValidator;

    public RoleService(RoleRepository roleRepository, RoleValidator roleValidator) {
        this.roleRepository = roleRepository;
        this.roleValidator = roleValidator;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role getOne(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role not found for id:" + id));

    }

    public Role save(Role role) {
        roleValidator.validate(role);
        return roleRepository.save(role);

    }

    public void delete(Long id) {
        Role roleToDelete = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role not found for id:" + id));
        roleRepository.delete(roleToDelete);
    }

    public Role updateRole(Long id, Role role) {
        Role roleToUpdate = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role not found for id:" + id));

        roleToUpdate.setTitle(role.getTitle());
        roleToUpdate.setLevel(role.getLevel());
        roleToUpdate.setDescription(role.getDescription());
        roleToUpdate.setManager(role.isManager());

        roleValidator.validate(role);

        return roleRepository.save(roleToUpdate);

    }


}
