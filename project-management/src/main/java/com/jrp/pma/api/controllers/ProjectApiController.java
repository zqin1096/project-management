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

import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Project;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {

	@Autowired
	ProjectRepository projectRepository;

	@GetMapping
	public List<Project> getProjects() {
		return projectRepository.findAll();
	}

	@GetMapping("/{id}")
	public Project getProjectById(@PathVariable("id") Long id) {
		return projectRepository.findById(id).get();
	}

	// Need the CSRF token in the request header.
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Project creatProject(@RequestBody Project project) {
		return projectRepository.save(project);
	}

	// Need the CSRF token.
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Project updateProject(@RequestBody Project project, @PathVariable("id") Long id) {
		Project p = projectRepository.findById(id).get();
		if (project.getDescription() != null) {
			p.setDescription(project.getDescription());
		}
		if (project.getName() != null) {
			p.setName(project.getName());
		}
		if (project.getStage() != null) {
			p.setStage(project.getStage());
		}
		return projectRepository.save(p);
	}

	// Need the CSRF token.
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		try {
			projectRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
		}
	}

}
