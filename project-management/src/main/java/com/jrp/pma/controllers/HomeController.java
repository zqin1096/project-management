package com.jrp.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;

@Controller

public class HomeController {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<>();

		List<Project> projects = projectRepository.findAll();
		model.addAttribute("projects", projects);

		List<ChartData> projectData = projectRepository.getProjectStatus();

		// Convert projectData to a JSON object.
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(projectData);
		// [["NOTSTARTED", 1], ["COMPLETED", 2], ["INPROGRESS", 1]]

		model.addAttribute("projectStatusCount", json);

		List<EmployeeProject> employeesProjectCount = employeeRepository.employeeProjects();
		model.addAttribute("employees", employeesProjectCount);

		return "main/home";
	}

}
