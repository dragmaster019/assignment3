package com.assignment.assignment.service;

import com.assignment.assignment.model.Employee;
import com.assignment.assignment.model.EmployeeEmail;
import com.assignment.assignment.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeEmailService emailService;

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeRepository.findAll());
    }

    public List<Employee> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy) {
        if (sortBy == null) {
            Pageable paging = PageRequest.of(pageNo, pageSize);
            Page<Employee> pagedResult = employeeRepository.findAll(paging);
            if(pagedResult.hasContent()) {
                return pagedResult.getContent();
            } else {
                return new ArrayList<Employee>();
            }
        } else if (sortBy.equalsIgnoreCase("email")) {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("empEmail"));
            Page<Employee> pagedResult = employeeRepository.findAll(paging);
            if(pagedResult.hasContent()) {
                return pagedResult.getContent();
            } else {
                return new ArrayList<Employee>();
            }
        } else if (sortBy.equalsIgnoreCase("name")) {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("employeeName"));
            Page<Employee> pagedResult = employeeRepository.findAll(paging);
            if(pagedResult.hasContent()) {
                return pagedResult.getContent();
            } else {
                return new ArrayList<Employee>();
            }
        } else
            return null;
    }

    public Employee getEmployeeById(String id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.orElse(null);
    }

    private Employee findNthManager(Employee emp, int n) {
        while (n-- > 0 && emp.getReportsTo() != null) {
            if (employeeRepository.findById(emp.getReportsTo()).isPresent())
                emp = employeeRepository.findById(emp.getReportsTo()).get();
            else
                break;
        }
        return emp;
    }
    public Employee getNthManager(String id, int n) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.map(employee -> findNthManager(employee, n)).orElse(null);
    }

    public Employee createEmployee(Employee emp) {
        if(emp.getId() == null || emp.getId().equals(""))
            emp.setId(UUID.randomUUID().toString());
        Employee newEmp = employeeRepository.save(new Employee(emp));
        log.info("New Employee {} created.", newEmp.getId());
        Employee fManager = findNthManager(emp, 1);
        log.info("Mail to: {}", fManager);

        try {
            sendEmail(fManager.getEmpEmail(),
                    "New employee assignment",
                    new EmployeeEmail(newEmp.getEmployeeName(), newEmp.getEmpEmail(), newEmp.getPhoneNumber()).toString());
        } catch(Exception e) {
            log.error(e.toString());
        }

        return emp;
    }

    private void sendEmail(String toEmail, String subject, String body){
        emailService.sendMail(toEmail, subject, body);
    }

    public Employee updateEmployee(Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());
        if (optionalEmployee.isPresent()) {
            Employee temp = optionalEmployee.get();
            if (employee.getEmployeeName() != null)
                temp.setEmployeeName(employee.getEmployeeName());
            if (employee.getEmpEmail() != null)
                temp.setEmpEmail(employee.getEmpEmail());
            if (employee.getEmpImgUrl() != null)
                temp.setEmpImgUrl(employee.getEmpImgUrl());
            if (employee.getReportsTo() != null)
                temp.setReportsTo(employee.getReportsTo());
            if (employee.getPhoneNumber() != null)
                temp.setPhoneNumber(employee.getPhoneNumber());

            employeeRepository.save(temp);
            return temp;
        } else
            return null;
    }

    public boolean deleteEmployee(String id) {
        try {
            employeeRepository.deleteById(id);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

}
