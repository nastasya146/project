package com.myapp.database.repository;

import com.myapp.database.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TaskRepository extends JpaRepository <Task, Long>{

	List<Task> getTaskByUserId(Long userId);
}
