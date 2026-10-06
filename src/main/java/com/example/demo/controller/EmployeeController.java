package com.example.demo.controller;

import com.example.demo.model.dto.EmployeeDTO;
import com.example.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // 1. Display list of employees (Dashboard)
    @GetMapping
    public String viewDashboard(Model model) {
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "index";
    }

    // 2. Display form to add a new employee
    @GetMapping("/new")
    public String showNewEmployeeForm(Model model) {
        model.addAttribute("employee", new EmployeeDTO());
        return "new-employee";
    }

    // 3. Save new or edited employee
    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") EmployeeDTO employeeDTO,
                               BindingResult result) {
        if (result.hasErrors()) {
            return employeeDTO.getId() == null ? "new-employee" : "update-employee";
        }
        employeeService.saveEmployee(employeeDTO);
        return "redirect:/employees";
    }

    // 4. Display form to edit an existing employee
    @GetMapping("/edit/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "update-employee";
    }

    // 5. Delete an employee by ID
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }
}