package com.myapp.controller;

import com.myapp.database.entity.User;
import com.myapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
class MainController{

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String indexPage(ModelMap map)
    {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String tryLogin(ModelMap map,
                            HttpServletRequest req,
                            @RequestParam(value = "logout", required = false) String logout) {
        if (logout != null) {
            req.getSession().invalidate();
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(ModelMap map)
    {
        map.put("roles", userService.getRoles());
        return "template/register";
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(ModelMap map, @RequestBody User user){
        userService.addUser(user);
        return null;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultPage(ModelMap map,
        HttpServletRequest req) {
        return "task/registry";
    }
}