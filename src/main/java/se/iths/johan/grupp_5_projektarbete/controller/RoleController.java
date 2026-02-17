package se.iths.johan.grupp_5_projektarbete.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.johan.grupp_5_projektarbete.model.Role;
import se.iths.johan.grupp_5_projektarbete.service.RoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Get all roles
    @GetMapping()
    public String getAllRoles(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "roles";
    }

    //Create role form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("role", new Role());
        return "create-role";
    }

    // save new role
    @PostMapping
    public String createRole(@ModelAttribute Role role) {
        roleService.save(role);
        return "redirect:/roles";
    }

    // Find role by id
    @GetMapping("/{id}")
    public String getRoleById(@PathVariable Long id, Model model) {
        model.addAttribute("role", roleService.getOne(id));
        return "role";

    }

    //update role form
    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Role role = roleService.getOne(id);
        model.addAttribute("role", role);
        return "edit-role";
    }

    // Update role
    @PutMapping("/{id}/edit")
    public String editRole(@PathVariable Long id, @ModelAttribute Role role) {
        roleService.updateRole(id, role);
        return "redirect:/roles";
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable Long id) {
        roleService.delete(id);
        return "redirect:/roles";
    }

}
