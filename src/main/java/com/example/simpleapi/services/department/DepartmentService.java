package com.example.simpleapi.services.department;

import com.example.simpleapi.entities.department.Department;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    void displayDepartment(Integer departmentId, HttpServletResponse response);

    void addDepartment(Department department);

//    HttpServletResponse deleteDepartment(Integer departmentId);

    void addDepartment(Department department, HttpServletResponse response);

    void deleteDepartment(Integer departmentId, HttpServletResponse response);

//    void updateDepartmentContact(Integer departmentId, String contactInfo);

    void updateDepartmentContact(Integer departmentId, String contactInfo, HttpServletResponse response);

    List<Department> displayDepartments();

    void addManyDepartment(List<Department> departments, HttpServletResponse response);

}
