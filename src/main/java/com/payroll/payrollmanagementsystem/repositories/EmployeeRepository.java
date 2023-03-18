package com.payroll.payrollmanagementsystem.repositories;

import com.payroll.payrollmanagementsystem.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(nativeQuery = true, value = "call get_employees")
    List<Employee> getAllEmployees();

    @Query(nativeQuery = true, value = "call get_employee_id(:emp_id)")
    Employee getEmployeeById(@Param("emp_id") long emp_id);

    @Query(nativeQuery = true, value = "call delete_emp_id(:emp_id)")
    void deleteByEmployeeById(@Param("emp_id") long emp_id);

    @Query(nativeQuery = true, value = "call add_employee(:emp_fname, :emp_lname,:emp_dob,:emp_hdate,:emp_rank)")
    void addNewEmployee(@Param("emp_fname") String emp_fname,
                        @Param("emp_lname") String emp_lname,
                        @Param("emp_dob") Date emp_dob,
                        @Param("emp_hdate") Date emp_hdate,
                        @Param("emp_rank") int emp_rank);

    @Query(nativeQuery = true, value = "call update_employee(:emp_id,:emp_fname, :emp_lname,:emp_dob,:emp_hdate,:emp_rank)")
    void updateEmployee(@Param("emp_id") long emp_id,
                        @Param("emp_fname") String emp_fname,
                        @Param("emp_lname") String emp_lname,
                        @Param("emp_dob") Date emp_dob,
                        @Param("emp_hdate") Date emp_hdate,
                        @Param("emp_rank") int emp_rank);

}
