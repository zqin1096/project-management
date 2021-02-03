package com.jrp.pma.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;

// Annotation: UniqueValue. Email: String.
public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {
	// Need to prevent double validation. Only perform validation on the client
	// side.
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		System.out.println("Enter");
		Employee employee = employeeRepository.findByEmail(value);
		return employee == null;
	}
}
