package com.jrp.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;

@Service
public class EmployeeService {
	// Constructor injection.
//	EmployeeRepository employeeRepository;
//
//	public EmployeeService(EmployeeRepository employeeRepository) {
//		super();
//		this.employeeRepository = employeeRepository;
//	}

	// Setter injection.
//	EmployeeRepository employeeRepository;
//
//	@Autowired
//	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
//		this.employeeRepository = employeeRepository;
//	}

	// Field injection.
//	@Autowired
//	EmployeeRepository employeeRepository;

//	IStaffRepository iStaffRepository;
//
//	// The first character should be lower case.
//	public EmployeeService(@Qualifier("staffRepositoryImpl1") IStaffRepository iStaffRepository) {
//		super();
//		this.iStaffRepository = iStaffRepository;
//	}

//	@Qualifier("staffRepositoryImpl1")
//	@Autowired
//	IStaffRepository iStaffRepository;

//	IStaffRepository iStaffRepository;
//
//	@Autowired
//	@Qualifier("staffRepositoryImpl1")
//	public void setiStaffRepository(IStaffRepository iStaffRepository) {
//		this.iStaffRepository = iStaffRepository;
//	}

	@Autowired
	EmployeeRepository employeeRepository;

	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	public List<EmployeeProject> employeeProjects() {
		return employeeRepository.employeeProjects();
	}

	public Employee getEmployeeById(long id) {
		return employeeRepository.findById(id).get();
	}

	public void deleteById(long id) {
		employeeRepository.deleteById(id);
	}
}
