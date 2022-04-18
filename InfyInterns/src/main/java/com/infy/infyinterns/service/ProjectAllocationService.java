package com.infy.infyinterns.service;

import java.util.List;

import com.infy.infyinterns.dto.MentorDTO;
import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.exception.InfyInternException;

public interface ProjectAllocationService {

	public Integer allocateProject(ProjectDTO projectAllocation) throws InfyInternException;

	public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws InfyInternException;
	
	public void updateProjectMentor(Integer projectId, Integer mentorId) throws InfyInternException;
	
	public void deleteProject(Integer projectId) throws InfyInternException;
}
