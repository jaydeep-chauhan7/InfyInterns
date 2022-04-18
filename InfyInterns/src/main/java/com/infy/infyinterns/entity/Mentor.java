package com.infy.infyinterns.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mentor")
public class Mentor
{
	@Id
    private Integer mentorId;
    private String mentorName;
    @Column(name = "projects_mentored")
    private Integer numberOfProjectsMentored;

    public Integer getMentorId()
    {
	return mentorId;
    }

    public void setMentorId(Integer mentorId)
    {
	this.mentorId = mentorId;
    }

    public String getMentorName()
    {
	return mentorName;
    }

    public void setMentorName(String mentorName)
    {
	this.mentorName = mentorName;
    }

    public Integer getNumberOfProjectsMentored()
    {
	return numberOfProjectsMentored;
    }

    public void setNumberOfProjectsMentored(Integer numberOfProjectsMentored)
    {
	this.numberOfProjectsMentored = numberOfProjectsMentored;
    }

    @Override
    public int hashCode()
    {
	final int prime = 31;
	int result = 1;
	result = prime * result
		 + ((mentorId == null) ? 0 : mentorId.hashCode());
	result = prime * result
		 + ((mentorName == null) ? 0 : mentorName.hashCode());
	result = prime * result
		 + ((numberOfProjectsMentored == null) ? 0
						       : numberOfProjectsMentored.hashCode());
	return result;
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
	Mentor other = (Mentor) obj;
	if (this.getMentorId() == null)
	{
	    if (other.getMentorId() != null)
	    {
		return false;
	    }
	}
	else if (!this.getMentorId().equals(other.getMentorId()))
	    return false;
	return true;
    }

}
