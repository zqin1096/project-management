package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectService projectService; // Spring injects projectRepository when ProjectController is created.

	@Autowired
	EmployeeService employeeService;

	@RequestMapping("/new")
	public String displayProjectForm(Model model) {
		// Bind an empty Java object Project called "project" to the form.

		Project project = new Project();
		model.addAttribute("project", project);
		List<Employee> employees = employeeService.getAll();
		model.addAttribute("employees", employees);
		return "projects/new-project";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String createProject(@ModelAttribute(value = "project") Project project) {
		// The name "employees" need to be the same as the th:field in the html.
		// Save the new project to the database.

		// If multiple employees are assigned to a project, Hibernate will create
		// corresponding records in the joined table.
		projectService.save(project);

		// Redirection prevents duplicate submissions.
		return "redirect:/projects";
	}

	@GetMapping
	public String displayProjects(Model model) {
		model.addAttribute("projects", projectService.getAll());
		return "projects/list-projects";
	}
}
