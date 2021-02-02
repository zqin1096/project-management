package com.jrp.pma.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {
	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id) {
		return employeeRepository.findById(id).get();
	}

	// Need the CSRF token in the request header.
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee creatEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	// Need the CSRF token.
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Employee updatEmployee(@RequestBody Employee employee, @PathVariable("id") Long id) {
		Employee e = employeeRepository.findById(id).get();
		e.setFirstName(employee.getFirstName());
		e.setLastName(employee.getLastName());
		e.setEmail(employee.getEmail());
		return employeeRepository.save(e);
	}

	// Need the CSRF token.
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		try {
			employeeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
		}
	}
}
