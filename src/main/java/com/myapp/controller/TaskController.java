package com.myapp.controller;

import com.myapp.database.entity.Status;
import com.myapp.database.entity.Task;
import com.myapp.database.entity.User;
import com.myapp.services.TaskService;
import com.myapp.services.UserService;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value="/task")
public class TaskController
{
	@Autowired
	TaskService taskService;

	@Autowired
	UserService userService;

	@RequestMapping(value="/registry", method = RequestMethod.GET)
	public String getRegistry(){
		return "tasks/registry";
	}

	@RequestMapping(value="/form", method = RequestMethod.GET)
	public String getCreateForm(ModelMap modelMap,
								@RequestParam(required = false) String id){
		if (id!=null) {
			Task task = taskService.getTask(Long.parseLong(id));
			modelMap.put("task", task);
		}
		List<Status> statuses = taskService.getStatuses();
		User currentUser = userService.getCurrentUser();
		if (Objects.equals(currentUser.getRole().getRole(), "user"))
			statuses = statuses.subList(0,3);

		modelMap.put("statuses", statuses);
		modelMap.put("employees", userService.getEmployees());
		return "tasks/create";
	}

	@ResponseBody
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String getCreateForm(@RequestBody Task task){
		if (task.getId() == null) {
			taskService.addTask(task);
		} else {
			taskService.saveTask(task);
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String deleteTask(@RequestParam String id){
		taskService.deleteTask(Long.parseLong(id));
		return null;
	}


	@ResponseBody
	@RequestMapping(value="/get/all", method = RequestMethod.GET)
	public List<Task> getTasks(){
		Long userId = null;
		User currentUser = userService.getCurrentUser();
		if (Objects.equals(currentUser.getRole().getRole(), "user")) userId = currentUser.getId();
		return taskService.getAllTasks(userId);
	}
}
