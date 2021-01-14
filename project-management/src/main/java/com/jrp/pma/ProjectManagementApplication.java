package com.jrp.pma;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@SpringBootApplication
public class ProjectManagementApplication {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ProjectRepository projectRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		// Callback used to run the bean.
		return args -> {
			Employee emp1 = new Employee("John", "Warton", "warton@gmail.com");
			Employee emp2 = new Employee("Mike", "Lanister", "lanister@gmail.com");
			Employee emp3 = new Employee("Steve", "Reeves", "Reeves@gmail.com");

			Employee emp4 = new Employee("Ronald", "Connor", "connor@gmail.com");
			Employee emp5 = new Employee("Jim", "Salvator", "Sal@gmail.com");
			Employee emp6 = new Employee("Peter", "Henley", "henley@gmail.com");

			Employee emp7 = new Employee("Richard", "Carson", "carson@gmail.com");
			Employee emp8 = new Employee("Honor", "Miles", "miles@gmail.com");
			Employee emp9 = new Employee("Tony", "Roggers", "roggers@gmail.com");

			Project pro1 = new Project("Large Production Deploy", "NOTSTARTED",
					"This requires all hands on deck for" + "the final deployment of the software into production");
			Project pro2 = new Project("New Employee Budget", "COMPLETED",
					"Decide on a new employee bonus budget" + "for the year and figureout who will be promoted");
			Project pro3 = new Project("Office Reconstruction", "INPROGRESS", "The office building in Monroe has "
					+ "been damaged due to hurricane in the region. This needs to be reconstructed");
			Project pro4 = new Project("Improve Intranet Security", "INPROGRESS",
					"With the recent data hack, the office"
							+ "security needs to be improved and proper security team needs to be hired for "
							+ "implementation");

			pro1.addEmployee(emp1);
			pro1.addEmployee(emp2);
			pro2.addEmployee(emp3);
			pro3.addEmployee(emp1);
			pro4.addEmployee(emp1);
			pro4.addEmployee(emp3);

			emp1.setProjects(Arrays.asList(pro1, pro3, pro4));
			emp2.setProjects(Arrays.asList(pro1));
			emp3.setProjects(Arrays.asList(pro2, pro4));

			employeeRepository.save(emp1);
			employeeRepository.save(emp2);
			employeeRepository.save(emp3);
			employeeRepository.save(emp4);
			employeeRepository.save(emp5);
			employeeRepository.save(emp6);
			employeeRepository.save(emp7);
			employeeRepository.save(emp8);
			employeeRepository.save(emp9);

			projectRepository.save(pro1);
			projectRepository.save(pro2);
			projectRepository.save(pro3);
			projectRepository.save(pro4);
		};
	}

}
