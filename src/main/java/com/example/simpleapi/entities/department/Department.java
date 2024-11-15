package com.example.simpleapi.entities.department;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue
    @Column(name = "department_id")
    private Integer departmentId;

    private String departmentName;

    private String contactEmail;

    /*
        // Here is the problem of nesting loops that will make an infinite response during get products
      //  @OneToMany(mappedBy = "department")
      //  private List<Product> products;
    */
    public Department(String departmentName, String contactEmail) {
        this.departmentName = departmentName;
        this.contactEmail = contactEmail;
    }
}
