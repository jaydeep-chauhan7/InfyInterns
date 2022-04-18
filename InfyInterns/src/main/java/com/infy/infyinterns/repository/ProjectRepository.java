package com.infy.infyinterns.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.infyinterns.entity.Project;

//Extend appropriate Repository for interface
//CrudRepository with Entity object and entity primary id in Integer

public interface ProjectRepository extends CrudRepository<Project, Integer>
{

    // add methods if required

}
