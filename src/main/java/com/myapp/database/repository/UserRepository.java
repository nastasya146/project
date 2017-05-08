package com.myapp.database.repository;

import com.myapp.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	User getUserByLogin(String login);
	List<User> getUserByRole_Id(Long id);
}