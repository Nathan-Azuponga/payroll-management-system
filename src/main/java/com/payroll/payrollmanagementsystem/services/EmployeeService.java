package com.payroll.payrollmanagementsystem.services;

import com.payroll.payrollmanagementsystem.models.Employee;
import com.payroll.payrollmanagementsystem.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public ResponseEntity<List> getEmployee() {
        try {
            return new ResponseEntity<>(employeeRepository.getAllEmployees(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Employee> getEmployeeById(long id) {
        try {
            Employee employeeObject = getEmployeeRes(id);
            if (employeeObject != null) {
                return new ResponseEntity<>(employeeObject, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteEmployeeById(long id) {
        try {
            Employee emp = getEmployeeRes(id);

            if (emp != null) {
                employeeRepository.deleteByEmployeeById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Employee> addEmployee(Employee employee) {
        Employee newEmployee = employeeRepository
                .save(Employee.builder().
                        employeeFNAME(employee.getEmployeeFNAME())
                        .employeeHDATE(employee.getEmployeeHDATE())
                        .employeeDOB(employee.getEmployeeDOB())
                        .employeeONAME(employee.getEmployeeONAME())
                        .employeeRANK(employee.getEmployeeRANK())
                        .build());
        return new ResponseEntity<>(newEmployee, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> addNewEmployee(Employee employee) {
        employeeRepository.addNewEmployee(employee.getEmployeeFNAME(),
                employee.getEmployeeONAME(),
                employee.getEmployeeDOB(),
                employee.getEmployeeHDATE(),
                employee.getEmployeeRANK());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpStatus> updateEmployee(Employee employee) {
        try {
            Employee emp = getEmployeeRes(employee.getEmployeeNO());

            if (emp != null) {
                employeeRepository.updateEmployee(employee.getEmployeeNO(),
                        employee.getEmployeeFNAME(),
                        employee.getEmployeeONAME(),
                        employee.getEmployeeDOB(),
                        employee.getEmployeeHDATE(),
                        employee.getEmployeeRANK());
                System.out.println("Employee with ID:" + employee.getEmployeeNO() + "was updated successfully");
                return new ResponseEntity<>(HttpStatus.OK);
            }
            System.out.println("Employee with ID:" + employee.getEmployeeNO() + "was not found so can not be updated");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private Employee getEmployeeRes(long id) {
        Optional<Employee> empObject = Optional.of(employeeRepository.getEmployeeById(id));

        return empObject.orElse(null);
    }
}
