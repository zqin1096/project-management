package com.jrp.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// 1. Field injection.
	// @Autowired would not work if there is no @Controller annotation.
//	@Autowired
//	EmployeeRepository employeeRepository;

	// 2. Constructor injection. @Autowired is not needed.
//	EmployeeRepository employeeRepository;
//
//	public EmployeeController(EmployeeRepository employeeRepository) {
//		this.employeeRepository = employeeRepository;
//	}

	// 3. Setter injection. @Autowired is needed.
	EmployeeRepository employeeRepository;

	@Autowired
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		// Bind an empty Java object Project called "project" to the form.

		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employees/new-employee";
	}

	@PostMapping("/save")
	public String createEmployee(@ModelAttribute(value = "employee") Employee employee) {
		employeeRepository.save(employee);
		return "redirect:/employees/new";
	}

	@GetMapping
	public String displayEmployees(Model model) {
		model.addAttribute("employees", employeeRepository.findAll());
		return "employees/list-employees";
	}
}
