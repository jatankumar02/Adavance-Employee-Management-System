package com.jatan.ems.service;
import com.jatan.ems.dto.EmployeeForm;
import com.jatan.ems.model.Employee;
import com.jatan.ems.repository.EmployeeRepository;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Locale;
@Service @Transactional
public class EmployeeService {
 private final EmployeeRepository employees;
 public EmployeeService(EmployeeRepository employees){this.employees=employees;}
 @Transactional(readOnly=true)
 public Page<Employee> search(String keyword,String department,int page,int size){
  Specification<Employee> spec=(root,q,cb)->cb.conjunction();
  if(keyword!=null&&!keyword.isBlank()){
   String term="%"+keyword.toLowerCase(Locale.ROOT)+"%";
   spec=spec.and((root,q,cb)->cb.or(cb.like(cb.lower(root.get("firstName")),term),
    cb.like(cb.lower(root.get("lastName")),term),cb.like(cb.lower(root.get("email")),term),
    cb.like(cb.lower(root.get("designation")),term)));
  }
  if(department!=null&&!department.isBlank())
   spec=spec.and((root,q,cb)->cb.equal(cb.lower(root.get("department")),department.toLowerCase(Locale.ROOT)));
  return employees.findAll(spec,PageRequest.of(Math.max(0,page),Math.min(Math.max(1,size),50),Sort.by("lastName").ascending()));
 }
 public Employee save(EmployeeForm form){
  if(employees.existsByEmailIgnoreCase(form.getEmail())) throw new IllegalArgumentException("An employee with this email already exists.");
  Employee e=new Employee(); copy(form,e); return employees.save(e);
 }
 public Employee update(Long id,EmployeeForm form){
  Employee e=employees.findById(id).orElseThrow(()->new IllegalArgumentException("Employee not found."));
  if(employees.existsByEmailIgnoreCaseAndIdNot(form.getEmail(),id)) throw new IllegalArgumentException("That email belongs to another employee.");
  copy(form,e); return employees.save(e);
 }
 public void delete(Long id){if(!employees.existsById(id)) throw new IllegalArgumentException("Employee not found."); employees.deleteById(id);}
 @Transactional(readOnly=true) public Employee find(Long id){return employees.findById(id).orElseThrow(()->new IllegalArgumentException("Employee not found."));}
 private void copy(EmployeeForm f,Employee e){
  e.setFirstName(f.getFirstName().trim()); e.setLastName(f.getLastName().trim()); e.setEmail(f.getEmail().trim().toLowerCase());
  e.setPhone(f.getPhone()); e.setDepartment(f.getDepartment()); e.setDesignation(f.getDesignation());
  e.setSalary(f.getSalary()); e.setJoiningDate(f.getJoiningDate()); e.setStatus(f.getStatus());
 }
 public long total(){return employees.count();}
 public long active(){return employees.countActive();}
}
