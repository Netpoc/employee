package net.javarest.ems_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javarest.ems_backend.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}