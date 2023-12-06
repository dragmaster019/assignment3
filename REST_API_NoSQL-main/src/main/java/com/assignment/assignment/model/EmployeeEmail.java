package com.assignment.assignment.model;

public class EmployeeEmail {

    private String empName;
    private String empEmail;
    private Integer empPhone;

    public EmployeeEmail() {
    }

    public EmployeeEmail(String empName, String empEmail, Integer empPhone) {
        this.empName = empName;
        this.empEmail = empEmail;
        this.empPhone = empPhone;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public Integer getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(Integer empPhone) {
        this.empPhone = empPhone;
    }

    @Override
    public String toString() {
        return "The employee: " + empName + ", ,Working under.\n" + ",Mobile no: " + empPhone + " ,email address: " + empEmail;
    }
}
