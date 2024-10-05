package com.luv2code.springboot.cruddemo.controller;


import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/list")
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "list-employees";

    }

    @GetMapping("/getNewEmployee")
    public String showFormForAdd( Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new-employee";
    }

    @GetMapping("/updateEmployee")
    public String showFormForAdd(@RequestParam("employeeId") int employeeId ,Model model ) {
        Employee employee = employeeService.findById(employeeId);
        model.addAttribute("employee", employee);
        return "new-employee";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        log.info("save employee : {}", employee);
        employeeService.save(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int employeeId) {
        employeeService.deleteById(employeeId);
        return "redirect:/employees/list";
    }

}
