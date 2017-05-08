package com.myapp.services;

import com.myapp.database.entity.Status;
import com.myapp.database.entity.Task;
import com.myapp.database.repository.StatusRepository;
import com.myapp.database.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	StatusRepository statusRepository;

	public List<Task> getAllTasks(Long userId) {
		if (userId==null) {
			return taskRepository.findAll();
		} else {
			return taskRepository.getTaskByUserId(userId);
		}
	}

	public void deleteTask(Long id) {
		taskRepository.delete(id);
	}

	public void saveTask(Task task){
		taskRepository.save(task);
	}

	public void addTask(Task task) {
		Status status = statusRepository.findOne(1L);
		task.setStatus(status);
		saveTask(task);
	}

	public List<Status> getStatuses(){
		return statusRepository.findAll();
	}

	public Task getTask(Long id){
		return taskRepository.findOne(id);
	}
}
