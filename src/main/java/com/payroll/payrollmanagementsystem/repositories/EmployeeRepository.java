package com.payroll.payrollmanagementsystem.repositories;

import com.payroll.payrollmanagementsystem.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(nativeQuery = true, value = "call get_employees")
    List<Employee> getAllEmployees();
    @Query(nativeQuery = true, value = "call get_employee_id(:emp_id)")
    Employee getEmployeeById(@Param("emp_id") int emp_id);
    @Query(nativeQuery = true, value = "call delete_emp_id(:emp_id)")
    void deleteByEmployeeById(@Param("emp_id") int emp_id);
}
