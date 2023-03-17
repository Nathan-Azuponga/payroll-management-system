package com.payroll.payrollmanagementsystem.services;

import com.payroll.payrollmanagementsystem.models.Employee;
import com.payroll.payrollmanagementsystem.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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

    public ResponseEntity<Employee> getEmployeeById(int id) {
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

    public ResponseEntity<HttpStatus> deleteEmployeeById(int id) {
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

    public ResponseEntity<Employee> newEmployee(Employee employee) {
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

    private Employee getEmployeeRes(int id) {
        Optional<Employee> empObject = Optional.of(employeeRepository.getEmployeeById(id));

        return empObject.orElse(null);
    }
}
