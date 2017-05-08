package com.myapp.database.entity;

import javax.persistence.*;

@Entity(name = "Status")
public class Status {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column
	Long id;

	@Column
	String status;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}
}

