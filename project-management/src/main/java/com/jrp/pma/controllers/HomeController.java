package com.jrp.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

@Controller
public class HomeController {

	// Read a value from application.properties.
	@Value("${version}")
	private String ver;

	@Autowired
	ProjectService projectService; // The actual Bean exists within the spring framework for CrudRepository.

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		// Add the envVersionNum in the run configuration (eclipse environment).
		// Right click pom.vml, clean, then install. Run the jar file (windows
		// environment).
		model.addAttribute("version", ver);
		Map<String, Object> map = new HashMap<>();

		List<Project> projects = projectService.getAll();
		model.addAttribute("projects", projects);

		List<ChartData> projectData = projectService.getProjectStatus();

		// Convert projectData to a JSON object.
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(projectData);
		// [["NOTSTARTED", 1], ["COMPLETED", 2], ["INPROGRESS", 1]]

		model.addAttribute("projectStatusCount", json);

		List<EmployeeProject> employeesProjectCount = employeeService.employeeProjects();
		model.addAttribute("employees", employeesProjectCount);

		return "main/home";
	}

}
