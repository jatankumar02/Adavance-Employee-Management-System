package com.jatan.ems.dto;
import com.jatan.ems.model.EmployeeStatus;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
public class EmployeeForm {
 @NotBlank @Size(max=60) private String firstName;
 @NotBlank @Size(max=60) private String lastName;
 @NotBlank @Email @Size(max=120) private String email;
 @Pattern(regexp="^$|^[0-9+() -]{7,20}$",message="Enter a valid phone number") private String phone;
 @NotBlank private String department;
 @NotBlank private String designation;
 @NotNull @DecimalMin("0.00") private BigDecimal salary;
 @NotNull @PastOrPresent private LocalDate joiningDate;
 @NotNull private EmployeeStatus status=EmployeeStatus.ACTIVE;
 public String getFirstName(){return firstName;} public void setFirstName(String v){firstName=v;}
 public String getLastName(){return lastName;} public void setLastName(String v){lastName=v;}
 public String getEmail(){return email;} public void setEmail(String v){email=v;}
 public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
 public String getDepartment(){return department;} public void setDepartment(String v){department=v;}
 public String getDesignation(){return designation;} public void setDesignation(String v){designation=v;}
 public BigDecimal getSalary(){return salary;} public void setSalary(BigDecimal v){salary=v;}
 public LocalDate getJoiningDate(){return joiningDate;} public void setJoiningDate(LocalDate v){joiningDate=v;}
 public EmployeeStatus getStatus(){return status;} public void setStatus(EmployeeStatus v){status=v;}
}
