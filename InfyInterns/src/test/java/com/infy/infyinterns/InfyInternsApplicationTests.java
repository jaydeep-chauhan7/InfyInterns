package com.infy.infyinterns;

import java.time.LocalDate;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import com.infy.infyinterns.dto.MentorDTO;
import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.entity.Mentor;
import com.infy.infyinterns.entity.Project;
import com.infy.infyinterns.exception.InfyInternException;
import com.infy.infyinterns.repository.MentorRepository;
import com.infy.infyinterns.service.ProjectAllocationService;
import com.infy.infyinterns.service.ProjectAllocationServiceImpl;

@SpringBootTest
public class InfyInternsApplicationTests {

	@Mock
	private MentorRepository mentorRepository;

	@InjectMocks
	private ProjectAllocationService projectAllocationService = new ProjectAllocationServiceImpl();

	@Test
	public void allocateProjectCannotAllocateTest() throws InfyInternException {
		ProjectDTO projectDTO=new ProjectDTO();
		
		projectDTO.setIdeaOwner(7);
		projectDTO.setProjectId(1);
		projectDTO.setProjectName("jj");
		projectDTO.setReleaseDate(LocalDate.now());
		
		MentorDTO mentorDTO=new MentorDTO();
		mentorDTO.setMentorId(1);
		mentorDTO.setMentorName("js");
		mentorDTO.setNumberOfProjectsMentored(3);
		
		projectDTO.setMentorDTO(mentorDTO);
		
		Mentor mentor=new Mentor();
		mentor.setMentorId(mentorDTO.getMentorId());
		mentor.setMentorName(mentorDTO.getMentorName());
		mentor.setNumberOfProjectsMentored(mentorDTO.getNumberOfProjectsMentored());
		
		Project project=new Project();
		project.setIdeaOwner(projectDTO.getIdeaOwner());
		project.setMentor(mentor);
		project.setProjectId(projectDTO.getProjectId());
		project.setProjectName(projectDTO.getProjectName());
		project.setReleaseDate(projectDTO.getReleaseDate());
		
		
		
		InfyInternException exceptionInteger=Assertions.assertThrows(InfyInternException.class,() -> projectAllocationService.allocateProject(projectDTO));
		Assertions.assertEquals("Service.CANNOT_ALLOCATE_PROJECT", exceptionInteger.getMessage());

	}

	@Test
	public void allocateProjectMentorNotFoundTest() throws Exception {
		ProjectDTO projectDTO=new ProjectDTO();
		
		projectDTO.setIdeaOwner(7);
		projectDTO.setProjectId(1);
		projectDTO.setProjectName("jj");
		projectDTO.setReleaseDate(LocalDate.now());
		
		MentorDTO mentorDTO=new MentorDTO();
		mentorDTO.setMentorId(1);
		mentorDTO.setMentorName("js");
		mentorDTO.setNumberOfProjectsMentored(3);
		
		Mentor mentor=new Mentor();
		mentor.setMentorId(mentorDTO.getMentorId());
		mentor.setMentorName(mentorDTO.getMentorName());
		mentor.setNumberOfProjectsMentored(mentorDTO.getNumberOfProjectsMentored());
		
		projectDTO.setMentorDTO(mentorDTO);
		
		mentorRepository.save(mentor);
		
		
		
		InfyInternException exception=Assertions.assertThrows(InfyInternException.class, () -> mentorRepository.findById(7));
		Assertions.assertEquals("Service.MENTOR_NOT_FOUND", exception.getMessage());

	}
}