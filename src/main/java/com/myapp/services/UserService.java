package com.myapp.services;

import com.myapp.database.entity.Role;
import com.myapp.database.entity.User;
import com.myapp.database.repository.RoleRepository;
import com.myapp.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService
{
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	public List<User> getUsers(){
		return userRepository.findAll();
	}

	public void addUser(User user) {
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		user.setRegDate(new Date());
		userRepository.save(user);
	}

	public User getByLogin(String login){
		return userRepository.getUserByLogin(login);
	}

	public Boolean login(User user, String password){
		try {
			if(BCrypt.checkpw(password, user.getPassword())){
				return true;
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	public User login(String name, String password, String ip){
		User user = getByLogin(name);
		if (login(user, password)){
			return user;
		}
		return null;
	}

	public List<Role> getRoles(){
		return roleRepository.findAll();
	}

	public User getCurrentUser(){
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
			if (details != null && details.getClass() == User.class) {
				return (User)details;
			}
		}
		return null;
	}

	public List<User> getEmployees(){
		return userRepository.getUserByRole_Id(2L);
	}

	public void changeUserRole(Long id, Long role_id){
		User user = userRepository.findOne(id);
		Role role = roleRepository.findOne(role_id);
		user.setRole(role);
		userRepository.save(user);
	}
}
