package com.jatan.ems.controller;
import com.jatan.ems.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class DashboardController {
 private final EmployeeService service;
 public DashboardController(EmployeeService service){this.service=service;}
 @GetMapping({"/","/dashboard"}) public String dashboard(Model model){
  model.addAttribute("totalEmployees",service.total()); model.addAttribute("activeEmployees",service.active()); return "dashboard";
 }
}
