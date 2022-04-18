package com.infy.infyinterns.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.infyinterns.dto.MentorDTO;
import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.entity.Mentor;
import com.infy.infyinterns.entity.Project;
import com.infy.infyinterns.exception.InfyInternException;
import com.infy.infyinterns.repository.MentorRepository;
import com.infy.infyinterns.repository.ProjectRepository;

@Service(value = "projectService")
@Transactional
public class ProjectAllocationServiceImpl implements ProjectAllocationService {
	
	@Autowired
	private ProjectRepository projectRepository;	
	
	@Autowired
	private MentorRepository mentorRepository;

	@Override
	public Integer allocateProject(ProjectDTO project) throws InfyInternException {
		//Use Crud repo method to retrive optional object
		//Used Optional method orWElseThrow for null check
		Optional<Mentor> mentorRepo=mentorRepository.findById(project.getMentorDTO().getMentorId());
		Mentor mentor=mentorRepo.orElseThrow(() -> new InfyInternException("Service.MENTOR_NOT_FOUND"));
		if (mentorRepo.get().getNumberOfProjectsMentored() >=3) {
			throw new InfyInternException("Service.CANNOT_ALLOCATE_PROJECT");
		}
		//Create new Project Details with Existing Mentor that we find in above method
		Project project2=new Project();
		project2.setIdeaOwner(project.getIdeaOwner());
		project2.setProjectId(project.getProjectId());
		project2.setProjectName(project.getProjectName());
		project2.setReleaseDate(project.getReleaseDate());
		
		//Assign Mentor and Increase project number
		project2.setMentor(mentor);
		mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored()+1);
		projectRepository.save(project2);//Save Entity in Database
		return project2.getProjectId();
	}

	
	@Override
	public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws InfyInternException {
		List<Mentor> mentorRepoList=mentorRepository.getMentors(numberOfProjectsMentored);
		//System.out.println(mentorRepoList);
		if (mentorRepoList.isEmpty()) {
			throw new InfyInternException("Service.MENTOR_NOT_FOUND");
		}
		//Create DTO list from Entity List
		List<MentorDTO> resultList=new ArrayList<>();
		for(Mentor m:mentorRepoList) {
			MentorDTO mDto=new MentorDTO();
			mDto.setMentorId(m.getMentorId());
			mDto.setMentorName(m.getMentorName());
			mDto.setNumberOfProjectsMentored(m.getNumberOfProjectsMentored());
			resultList.add(mDto);
		}
		return resultList;
	}


	@Override
	public void updateProjectMentor(Integer projectId, Integer mentorId) throws InfyInternException {
		Optional<Mentor> mentorOptional=mentorRepository.findById(mentorId);
		Mentor mentor=mentorOptional.orElseThrow(() -> new InfyInternException("Service.MENTOR_NOT_FOUND"));
		if (mentor.getNumberOfProjectsMentored() >= 3) {
			throw new InfyInternException("Service.CANNOT_ALLOCATE_PROJECT");
		}
		Optional<Project> projectOptional=projectRepository.findById(projectId);
		Project project=projectOptional.orElseThrow(() -> new InfyInternException("Service.PROJECT_NOT_FOUND"));
		project.setMentor(mentor);
		mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored()+1);
	}

	@Override
	public void deleteProject(Integer projectId) throws InfyInternException {
		Optional<Project> projectOptional=projectRepository.findById(projectId);
		Project project=projectOptional.orElseThrow(() -> new InfyInternException("Service.PROJECT_NOT_FOUND"));
		if (project.getMentor() == null) {
			projectRepository.deleteById(projectId);
		}else {
			project.getMentor().setNumberOfProjectsMentored(project.getMentor().getNumberOfProjectsMentored() - 1);
			project.setMentor(null);
			projectRepository.deleteById(projectId);
		}
		
	}
}