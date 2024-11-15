package com.example.simpleapi.controllers;

import com.example.simpleapi.entities.department.Department;
import com.example.simpleapi.services.department.DepartmentServiceImplementation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/departments")
public class DepartmentController {
    private final DepartmentServiceImplementation departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> displayDepartments () {
       return ResponseEntity.ok().body(departmentService.displayDepartments());
    }

    @GetMapping("{departmentId}")
    public void displayDepartment (@PathVariable("departmentId") Integer departmentId,
                                   HttpServletResponse response) {
        departmentService.displayDepartment(departmentId, response);
    }


    @DeleteMapping("{departmentId}")
    public void deleteDepartment (@PathVariable("departmentId") Integer departmentId,
                                  HttpServletResponse response) {
        departmentService.deleteDepartment(departmentId, response);
    }

    @PostMapping("add")
    public void addDepartment (@RequestBody Department department,
                                  HttpServletResponse response) {
        departmentService.addDepartment(department, response);
    }

    @PostMapping("addMany")
    public void addDepartment (@RequestBody List<Department> departments,
                               HttpServletResponse response) {
        departmentService.addManyDepartment(departments, response);
    }

    @PatchMapping("update")
    public void updateDepartment (@RequestParam("departmentId") Integer departmentId,
                                  @RequestParam("contactInfo") String contactInfo,
                                  HttpServletResponse response) {
        departmentService.updateDepartmentContact(departmentId, contactInfo, response);
    }


}
