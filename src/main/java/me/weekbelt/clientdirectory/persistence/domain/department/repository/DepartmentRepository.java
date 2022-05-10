package me.weekbelt.clientdirectory.persistence.domain.department.repository;

import me.weekbelt.clientdirectory.persistence.domain.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
}
