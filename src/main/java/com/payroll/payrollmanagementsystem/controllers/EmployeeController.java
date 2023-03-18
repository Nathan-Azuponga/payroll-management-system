package com.payroll.payrollmanagementsystem.controllers;

import com.payroll.payrollmanagementsystem.models.Employee;
import com.payroll.payrollmanagementsystem.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @GetMapping("/employess")
    public ResponseEntity<List> getEmployees(){
       return employeeService.getEmployee();
    }
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id){
       return employeeService.getEmployeeById(id);
    }
    @DeleteMapping("/employee/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable("id") int id){
        System.out.println("id"+ id);
       return employeeService.deleteEmployeeById(id);
    }
    @PostMapping("/employee")
    public ResponseEntity<Employee> newEmployee(@RequestBody Employee employee){
       return employeeService.addEmployee(employee);
    }
    @PostMapping("/empS")
    public ResponseEntity<HttpStatus> addNewEmployee(@RequestBody Employee employee) {
        return employeeService.addNewEmployee(employee);
    }
    @PutMapping("/employee/update")
    public ResponseEntity<HttpStatus> updateEmployee(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }
}
