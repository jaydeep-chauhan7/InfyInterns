package com.infy.infyinterns.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.infyinterns.dto.MentorDTO;
import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.exception.InfyInternException;
import com.infy.infyinterns.service.ProjectAllocationService;
//Rest controlller for crud operation
//Validated for Java Bean Validation API

@RestController
@Validated
@RequestMapping(value = "infyinterns")
public class ProjectAllocationAPI
{
	@Autowired
	private ProjectAllocationService projectAllocationService;
	
	@Autowired
	private Environment environment;
	
	
    // add new project along with mentor details
	//@RequestBody annotation for JSON data fillindg in DTO objects
	@PostMapping(value = "/project")
    public ResponseEntity<String> allocateProject(@Valid @RequestBody ProjectDTO project) throws InfyInternException
    {
		Integer projetId=projectAllocationService.allocateProject(project);
		String successMessage=environment.getProperty("API.ALLOCATION_SUCCESS") + projetId;
		return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
    }
	
    // get mentors based on idea owner
	//@PathVariable for URL values
	@GetMapping(value = "mentor/{numberOfProjectsMentored}")
    public ResponseEntity<List<MentorDTO>> getMentors(@PathVariable Integer numberOfProjectsMentored) throws InfyInternException
    {
		List<MentorDTO> mentorDTOs=projectAllocationService.getMentors(numberOfProjectsMentored);
		return new ResponseEntity<>(mentorDTOs,HttpStatus.OK);
    }

    // update the mentor of a project
	@PutMapping(value = "project/{projectId}/{mentorId}")
    public ResponseEntity<String> updateProjectMentor(@PathVariable Integer projectId, @PathVariable @Min(value = 1000, message = "{mentor.mentorid.invalid}") @Max(value = 9999, message = "{mentor.mentorid.invalid}")
						      Integer mentorId) throws InfyInternException
    {
		projectAllocationService.updateProjectMentor(projectId, mentorId);
		String successMessage=environment.getProperty("API.PROJECT_UPDATE_SUCCESS");
		return new ResponseEntity<>(successMessage,HttpStatus.OK);
    }

    // delete a project
	@DeleteMapping(value = "project/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable Integer projectId) throws InfyInternException
    {
		projectAllocationService.deleteProject(projectId);
		String successMessage=environment.getProperty("API.PROJECT_DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage,HttpStatus.OK);
    }

}
