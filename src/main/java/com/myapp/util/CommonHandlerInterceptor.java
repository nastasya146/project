package com.myapp.util;

import com.myapp.database.entity.User;
import com.myapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonHandlerInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	UserService userService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);

		if (modelAndView != null && modelAndView.getViewName()!=null && !modelAndView.getViewName().startsWith("redirect:")) {
			modelAndView.getModelMap().addAttribute("contextPath", request.getContextPath());
			ModelMap model = modelAndView.getModelMap();
			User user = userService.getCurrentUser();
			model.addAttribute("currentUser", user);
		}
	}
}
