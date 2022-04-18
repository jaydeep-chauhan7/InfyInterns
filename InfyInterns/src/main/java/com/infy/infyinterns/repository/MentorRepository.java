package com.infy.infyinterns.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.infy.infyinterns.entity.Mentor;

//Extend appropriate Repository for interface
//CrudRepository with Entity object and entity primary id in Integer

public interface MentorRepository extends CrudRepository<Mentor, Integer>
{
    // add methods if required
	//Do not take DTO objects 
	//Here Entity Object are coming
	//Do not put any method type here ex:- public or private
	
	@Query(value = "SELECT * FROM mentor WHERE mentor.projects_mentored= :numberOfProjectMentored",nativeQuery = true)
	List<Mentor> getMentors(@Param("numberOfProjectMentored") Integer numberOfProjectMentored);
}
