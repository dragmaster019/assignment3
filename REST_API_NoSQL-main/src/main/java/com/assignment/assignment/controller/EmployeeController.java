package com.assignment.assignment.controller;

import com.assignment.assignment.model.Employee;
import com.assignment.assignment.repository.EmployeeRepository;
import com.assignment.assignment.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping("/listallemp")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> list = employeeService.getAllEmployees();
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listAllpage")
    public ResponseEntity<Map<String, Object>> getAllEmployees(@RequestParam(required = false) String sortBy,
                                                          @RequestParam(defaultValue = "0") int pageNo,
                                                          @RequestParam(defaultValue = "3") int pageSize) {
        List<Employee> list = employeeService.getAllEmployees(pageNo, pageSize, sortBy);
        if(list == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);
        map.put("content", list);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") String id) {
        Employee emp = employeeService.getEmployeeById(id);
       if (emp != null)
           return new ResponseEntity<>(emp, HttpStatus.OK);
       else
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getNthManager/{id}/{n}")
    public ResponseEntity<Employee> getNthManager(@PathVariable(name = "id") String id, @PathVariable(name = "n") Integer n) {
        try {
            Employee emp = employeeService.getNthManager(id, n);
            if(emp != null)
                return new ResponseEntity<Employee>(emp, HttpStatus.FOUND);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee emp) {
        try {
            return new ResponseEntity<>(employeeService.createEmployee(emp), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
//        UUID uuid = UUID.fromString(id);
        Employee emp = employeeService.updateEmployee(employee);
        if(emp != null)
            return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable String id) {
        if(employeeService.deleteEmployee(id))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
