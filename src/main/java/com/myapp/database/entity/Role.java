package com.myapp.database.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Role")
public class Role {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column
	Long id;

	@Column
	String role;

	@Column
	String name;

	public Long getId()
	{
		return id;
	}

	public String getRole()
	{
		return role;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
