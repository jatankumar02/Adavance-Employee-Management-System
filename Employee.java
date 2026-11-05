package com.jatan.ems.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="employees", indexes={@Index(name="idx_employee_email", columnList="email", unique=true),
 @Index(name="idx_employee_department", columnList="department")})
public class Employee {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=60) private String firstName;
 @Column(nullable=false,length=60) private String lastName;
 @Column(nullable=false,unique=true,length=120) private String email;
 @Column(length=30) private String phone;
 @Column(nullable=false,length=80) private String department;
 @Column(nullable=false,length=80) private String designation;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal salary;
 private LocalDate joiningDate;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private EmployeeStatus status=EmployeeStatus.ACTIVE;
 private LocalDateTime createdAt;
 private LocalDateTime updatedAt;
 @PrePersist void onCreate(){createdAt=LocalDateTime.now(); updatedAt=createdAt;}
 @PreUpdate void onUpdate(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public String getFirstName(){return firstName;} public void setFirstName(String v){firstName=v;}
 public String getLastName(){return lastName;} public void setLastName(String v){lastName=v;}
 public String getEmail(){return email;} public void setEmail(String v){email=v;}
 public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
 public String getDepartment(){return department;} public void setDepartment(String v){department=v;}
 public String getDesignation(){return designation;} public void setDesignation(String v){designation=v;}
 public BigDecimal getSalary(){return salary;} public void setSalary(BigDecimal v){salary=v;}
 public LocalDate getJoiningDate(){return joiningDate;} public void setJoiningDate(LocalDate v){joiningDate=v;}
 public EmployeeStatus getStatus(){return status;} public void setStatus(EmployeeStatus v){status=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
