package com.example.simpleapi.Repositories;

import com.example.simpleapi.entities.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    @Transactional
    @Modifying
    @Query("update Department  set  contactEmail= :contactEmail where departmentId= :departmentId")
    void updateDepartmentContact(Integer departmentId, String contactEmail);

//    Department findDepartmentByByDepartmentId(Integer departmentId);

}
