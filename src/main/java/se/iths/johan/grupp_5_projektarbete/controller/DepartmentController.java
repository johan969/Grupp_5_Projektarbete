package se.iths.johan.grupp_5_projektarbete.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.johan.grupp_5_projektarbete.model.Department;
import se.iths.johan.grupp_5_projektarbete.service.DepartmentService;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping()
    public String getAllDepartments(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "departments";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("department", new Department());
        return "create-department";
    }

    @PostMapping
    public String createDepartment(@ModelAttribute Department department) {
        departmentService.createDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/{id}")
    public String getDepartment(@PathVariable Long id, Model model) {
        model.addAttribute("department", departmentService.getDepartment(id));
        return "department";
    }

    @PutMapping("/{id}")
    public String updateDepartment(@PathVariable Long id, @ModelAttribute Department department) {
        Department department1 = departmentService.updateDepartment(id, department);
        return "redirect:/departments";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Department department = departmentService.getDepartment(id);
        model.addAttribute("department", department);
        return "edit-department";
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }
}
