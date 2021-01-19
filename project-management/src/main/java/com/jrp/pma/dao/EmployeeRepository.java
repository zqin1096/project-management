package com.jrp.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	@Override
	public List<Employee> findAll(); // Default implementation returns Iterable.

	// Native query refers to actual sql queries (referring to actual database
	// objects).

	// The LEFT JOIN keyword returns all records from the left table (table1), and
	// the matched records from the right table (table2).

	// The GROUP BY statement groups rows that have the same values into summary
	// rows.

	// order by 3 desc: sort by the values of the 3rd column.

	@Query(nativeQuery = true, value = "select e.first_name as firstName, e.last_name as lastName, count(pe.employee_id) as projectCount from employee e left join project_employee pe on pe.employee_id = e.employee_id group by e.first_name, e.last_name order by 3 desc")
	public List<EmployeeProject> employeeProjects();
}
