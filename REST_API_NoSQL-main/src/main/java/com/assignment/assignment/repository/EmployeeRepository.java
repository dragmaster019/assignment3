package com.assignment.assignment.repository;

import com.assignment.assignment.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Page<Employee> findAll(Pageable pageable);
    Page<Employee> findByEmployeeName(String employeeName, Pageable pageable);
    Page<Employee> findByEmpEmail(String empEmail, Pageable pageable);

}
