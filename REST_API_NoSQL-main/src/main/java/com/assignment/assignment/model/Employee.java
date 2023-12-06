package com.assignment.assignment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Employee")
public class Employee {

    @Id
    private String id;
    private String employeeName;
    private Integer phoneNumber;
    private String empEmail;
    private String reportsTo;
    private String empImgUrl;

    public Employee() {

    }

    public Employee(String id, String employeeName, Integer phoneNumber, String empEmail, String reportsTo, String empImgUrl) {
        this.id = id;
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.empEmail = empEmail;
        this.reportsTo = reportsTo;
        this.empImgUrl = empImgUrl;
    }

    public Employee(String employeeName, Integer phoneNumber, String empEmail, String reportsTo, String empImgUrl) {
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.empEmail = empEmail;
        this.reportsTo = reportsTo;
        this.empImgUrl = empImgUrl;
    }

    public Employee(String employeeName, Integer phoneNumber, String empEmail, String empImgUrl) {
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.empEmail = empEmail;
        this.empImgUrl = empImgUrl;
    }

    public Employee(Employee employee) {
        this.employeeName = employee.getEmployeeName();
        this.id = employee.getId();
        this.empEmail = employee.getEmpEmail();
        this.empImgUrl = employee.getEmpImgUrl();
        this.phoneNumber = employee.getPhoneNumber();
        this.reportsTo = employee.getReportsTo();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }

    public String getEmpImgUrl() {
        return empImgUrl;
    }

    public void setEmpImgUrl(String empImgUrl) {
        this.empImgUrl = empImgUrl;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", empEmail='" + empEmail + '\'' +
                ", reportsTo=" + reportsTo +
                ", empImgUrl='" + empImgUrl + '\'' +
                '}';
    }
}
