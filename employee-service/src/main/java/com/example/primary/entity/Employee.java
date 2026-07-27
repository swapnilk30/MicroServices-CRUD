package com.example.primary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;
    private String firstName;
    private String lastName;
    //private String department;
    private Double salary;

    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // Many employees can have one manager
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    // One manager can have many employees
    @OneToMany(mappedBy = "manager")
    private List<Employee> subordinates = new ArrayList<>();
}
