package com.payroll.payrollmanagementsystem.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Data
@Entity
@Table(name = "tbl_Employee")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeNO;
    private String employeeFNAME;
    private String employeeONAME;
    private Date employeeDOB;
    private Date employeeHDATE;
    private LocalDateTime employeeDATECREATED;
    private int employeeRANK;
    @OneToOne(mappedBy = "employee")
    private PayDetails payDetails;
    @OneToMany(mappedBy = "employee")
    private List<Deduction> deductions;
    @OneToMany(mappedBy = "employee")
    private List<SickLeave> sickLeaves;
    @OneToMany(mappedBy = "employee")
    private List<Holiday> holidays;
    @OneToMany(mappedBy = "employee")
    private List<Bonus> bonuses;
    @OneToMany(mappedBy = "employee")
    private List<PayHistory> payHistories;
}
