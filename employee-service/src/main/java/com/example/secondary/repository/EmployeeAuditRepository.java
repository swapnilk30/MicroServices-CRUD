package com.example.secondary.repository;

import com.example.secondary.entity.EmployeeAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAuditRepository extends JpaRepository<EmployeeAudit, Long> {
}
