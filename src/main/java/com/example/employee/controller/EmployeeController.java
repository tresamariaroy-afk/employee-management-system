
package com.example.employee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.employee.models.Employee;
import com.example.employee.service.EmployeeService;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Display homepage
    // Display homepage and search employees
@GetMapping("/")
public String home(@org.springframework.web.bind.annotation.RequestParam(
        value = "keyword", required = false) String keyword,
        Model model) {

    if (keyword != null && !keyword.trim().isEmpty()) {

        model.addAttribute("employees",
                employeeService.searchEmployees(keyword));

    } else {

        model.addAttribute("employees",
                employeeService.getAllEmployees());
    }

    model.addAttribute("keyword", keyword);

    return "index";
}
    // Display Add Employee form
    @GetMapping("/add")
    public String addEmployee(Model model) {

        model.addAttribute("employee", new Employee());

        return "add";
    }

    // Save Employee
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {

        employeeService.saveEmployee(employee);

        return "redirect:/";
    }
// Display Manager Login Page
@GetMapping("/login")
public String login() {
    return "login";
}




    // Display Edit Employee form
    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Integer id, Model model) {

        Employee employee = employeeService.getEmployeeById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid employee ID: " + id));

        model.addAttribute("employee", employee);

        return "edit";
    }

    // Update Employee
    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employee employee) {

        employeeService.saveEmployee(employee);

        return "redirect:/";
    }



    // Delete Employee
    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id) {

    employeeService.deleteEmployee(id);

    return "redirect:/";
    }
}
