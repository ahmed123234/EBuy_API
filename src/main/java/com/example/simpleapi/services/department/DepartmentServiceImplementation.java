package com.example.simpleapi.services.department;

import com.example.simpleapi.Repositories.DepartmentRepository;
import com.example.simpleapi.entities.department.Department;
import com.example.simpleapi.responsehandeling.Response;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImplementation implements DepartmentService{
   private final DepartmentRepository departmentRepository;

    @Override
    public void addDepartment(Department department, HttpServletResponse response) {
        Map<String, String> body = new HashMap<>();

        departmentRepository.save(department);
        body.put("message", "Department is created successfully");
        Response.setResponse(response,201, body );
    }

    @Override
    public void addDepartment(Department department) {
        departmentRepository.save(department);
    }


    @Override
    public void addManyDepartment(List<Department> departments, HttpServletResponse response) {
        departmentRepository.saveAll(departments);

        Map<String, String> body = new HashMap<>();
        body.put("message", "Departments are created successfully");

        Response.setResponse(response, 201, body);
    }

    @Override
    public void displayDepartment(Integer departmentId, HttpServletResponse response) {

        Optional<Department> department = departmentRepository.findById(departmentId);

        Response.checkObjectAvailability(response, department,
                "No such department with the specified id %s", departmentId );
    }

    @Override
    public List<Department> displayDepartments() {
        return departmentRepository.findAll();
    }


    @Override
    public void deleteDepartment(Integer departmentId, HttpServletResponse response) {
        boolean isPresent = Response.checkObjectAvailability(response,
                departmentRepository.findById(departmentId),
                "Department is deleted successfully",
                "Department is not found, therefore it can't be deleted",
                departmentId);

        if(isPresent) {
            departmentRepository.deleteById(departmentId);
            log.info("department with Id {} is successfully deleted", departmentId);

        } else {
            log.error("department with Id {} not found!", departmentId);
        }
    }

    @Override
    public void updateDepartmentContact(Integer departmentId, String contactInfo, HttpServletResponse response) {

        boolean isPresent = Response.checkObjectAvailability(response,
                departmentRepository.findById(departmentId),
                "Department is updated successfully",
                "Department is not found, therefore it can't be updated",
                departmentId);

        if(isPresent) {
            departmentRepository.updateDepartmentContact(departmentId, contactInfo);
            log.info("department with Id {} is successfully updated", departmentId);

        } else {
            log.error("department with Id {} not found!", departmentId);
        }
    }


}
