package com.jrp.pma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

	IStaffRepository iStaffRepository;

	@Autowired
	@Qualifier("staffRepositoryImpl1")
	public void setiStaffRepository(IStaffRepository iStaffRepository) {
		this.iStaffRepository = iStaffRepository;
	}

}
