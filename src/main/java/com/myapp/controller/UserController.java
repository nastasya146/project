package com.myapp.controller;

import com.myapp.database.entity.User;
import com.myapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value="/users")
public class UserController
{

	@Autowired
	UserService userService;

	@RequestMapping(value="/registry", method = RequestMethod.GET)
	public String getRegistry(ModelMap modelMap){
		modelMap.put("roles", userService.getRoles());
		return "users";
	}

	@ResponseBody
	@RequestMapping(value="/get/all", method = RequestMethod.GET)
	public List<User> getUsers(){
		return userService.getUsers();
	}

	@ResponseBody
	@RequestMapping(value="/change_role", method = RequestMethod.GET)
	public String changeUserRole(@RequestParam Long id,
								@RequestParam Long role_id){
		userService.changeUserRole(id, role_id);
		return "OK!";
	}
}
