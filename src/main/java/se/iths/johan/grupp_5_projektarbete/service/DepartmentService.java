package se.iths.johan.grupp_5_projektarbete.service;

import org.springframework.stereotype.Service;
import se.iths.johan.grupp_5_projektarbete.exception.DepartmentNotFoundException;
import se.iths.johan.grupp_5_projektarbete.model.Department;
import se.iths.johan.grupp_5_projektarbete.repository.DepartmentRepository;
import se.iths.johan.grupp_5_projektarbete.validator.DepartmentValidator;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentValidator departmentValidator;

    public DepartmentService(DepartmentRepository departmentRepository, DepartmentValidator departmentValidator) {
        this.departmentRepository = departmentRepository;
        this.departmentValidator = departmentValidator;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department createDepartment(Department department) {

        // fälten i department valideras
        departmentValidator.validateDepartmentName(department.getDepartmentName());
        departmentValidator.validateCostCenter(department.getCostCenter());
        departmentValidator.validateLocation(department.getLocation());
        departmentValidator.validateNumberOfEmployees(department.getNumberOfEmployees());

        return departmentRepository.save(department);
    }

    public Department getDepartment(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException("No department found with id: " + id));
    }

    public Department updateDepartment(Long id, Department updatedDepartment) {

        // hämtar existerande department om den finns, annars kastas exception
        Department department = departmentRepository.findById(id).orElseThrow(() ->
                new DepartmentNotFoundException("Department with id " + id + " not found"));

        // fälten i department som ska uppdateras valideras
        departmentValidator.validateDepartmentName(updatedDepartment.getDepartmentName());
        departmentValidator.validateCostCenter(updatedDepartment.getCostCenter());
        departmentValidator.validateLocation(updatedDepartment.getLocation());
        departmentValidator.validateNumberOfEmployees(updatedDepartment.getNumberOfEmployees());

        // uppdaterade fälten sparas i existerande department som hämtades
        department.setDepartmentName(updatedDepartment.getDepartmentName());
        department.setCostCenter(updatedDepartment.getCostCenter());
        department.setLocation(updatedDepartment.getLocation());
        department.setNumberOfEmployees(updatedDepartment.getNumberOfEmployees());

        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.findById(id).orElseThrow(() ->
                new DepartmentNotFoundException("No department found with id: " + id));
        departmentRepository.deleteById(id);
    }
}
