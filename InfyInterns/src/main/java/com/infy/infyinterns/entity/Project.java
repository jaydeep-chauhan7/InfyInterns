package com.infy.infyinterns.entity;

import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;
    private String projectName;
    private Integer ideaOwner;
    private LocalDate releaseDate;
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    public Integer getProjectId()
    {
	return projectId;
    }

    public void setProjectId(Integer projectId)
    {
	this.projectId = projectId;
    }

    public LocalDate getReleaseDate()
    {
	return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate)
    {
	this.releaseDate = releaseDate;
    }

    public String getProjectName()
    {
	return projectName;
    }

    public void setProjectName(String projectName)
    {
	this.projectName = projectName;
    }

    public Mentor getMentor()
    {
	return mentor;
    }

    public void setMentor(Mentor mentor)
    {
	this.mentor = mentor;
    }

    public Integer getIdeaOwner()
    {
	return ideaOwner;
    }

    public void setIdeaOwner(Integer ideaOwner)
    {
	this.ideaOwner = ideaOwner;
    }

    @Override
    public int hashCode()
    {
	return 31;
    }

    @Override
    public boolean equals(Object obj)
    {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Project other = (Project) obj;
	if (this.getProjectId() == null)
	{
	    if (other.getProjectId() != null)
	    {
		return false;
	    }
	}
	else if (!this.getProjectId().equals(other.getProjectId()))
	    return false;
	return true;
    }

}
