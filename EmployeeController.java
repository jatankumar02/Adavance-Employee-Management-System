package com.jatan.ems.controller;
import com.jatan.ems.dto.EmployeeForm;
import com.jatan.ems.model.EmployeeStatus;
import com.jatan.ems.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller @RequestMapping("/employees")
public class EmployeeController {
 private final EmployeeService service;
 public EmployeeController(EmployeeService service){this.service=service;}
 @GetMapping public String list(@RequestParam(defaultValue="") String keyword,@RequestParam(defaultValue="") String department,
  @RequestParam(defaultValue="0") int page,Model model){
  Page<?> result=service.search(keyword,department,page,10);
  model.addAttribute("employees",result); model.addAttribute("keyword",keyword); model.addAttribute("department",department); return "employees";
 }
 @GetMapping("/new") public String createForm(Model model){model.addAttribute("employeeForm",new EmployeeForm()); addStatuses(model); return "employee-form";}
 @PostMapping("/save") public String save(@Valid @ModelAttribute EmployeeForm employeeForm,BindingResult errors,Model model,RedirectAttributes flash){
  if(errors.hasErrors()){addStatuses(model);return "employee-form";}
  try{service.save(employeeForm);flash.addFlashAttribute("success","Employee created successfully.");return "redirect:/employees";}
  catch(IllegalArgumentException ex){model.addAttribute("businessError",ex.getMessage());addStatuses(model);return "employee-form";}
 }
 @GetMapping("/{id}/edit") public String edit(@PathVariable Long id,Model model){
  var e=service.find(id); EmployeeForm f=new EmployeeForm(); f.setFirstName(e.getFirstName());f.setLastName(e.getLastName());f.setEmail(e.getEmail());
  f.setPhone(e.getPhone());f.setDepartment(e.getDepartment());f.setDesignation(e.getDesignation());f.setSalary(e.getSalary());f.setJoiningDate(e.getJoiningDate());f.setStatus(e.getStatus());
  model.addAttribute("employeeForm",f);model.addAttribute("employeeId",id);addStatuses(model);return "employee-form";
 }
 @PostMapping("/{id}/update") public String update(@PathVariable Long id,@Valid @ModelAttribute EmployeeForm employeeForm,BindingResult errors,Model model,RedirectAttributes flash){
  if(errors.hasErrors()){model.addAttribute("employeeId",id);addStatuses(model);return "employee-form";}
  try{service.update(id,employeeForm);flash.addFlashAttribute("success","Employee updated successfully.");return "redirect:/employees";}
  catch(IllegalArgumentException ex){model.addAttribute("employeeId",id);model.addAttribute("businessError",ex.getMessage());addStatuses(model);return "employee-form";}
 }
 @PostMapping("/{id}/delete") public String delete(@PathVariable Long id,RedirectAttributes flash){
  service.delete(id);flash.addFlashAttribute("success","Employee deleted.");return "redirect:/employees";
 }
 private void addStatuses(Model model){model.addAttribute("statuses",EmployeeStatus.values());}
}
