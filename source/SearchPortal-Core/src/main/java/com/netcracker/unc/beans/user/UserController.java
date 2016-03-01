package com.netcracker.unc.beans.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    @Qualifier("userDAO")
    UserDAO userDAO;

    @RequestMapping(value = "testing/users", method = RequestMethod.GET)
    public ModelAndView getAllUsers(ModelAndView modelAndView) {
        List<User> list = new ArrayList<User>();
        User user = null;
        for (Object object : userDAO.getAllObjectsDB()) {
            user = (User) object;
            list.add(user);
        }
        modelAndView.addObject("list", list);
        modelAndView.setViewName("test");
        modelAndView.addObject("report", "Модель работает");
        return modelAndView;
    }

    @RequestMapping(value = "testing/getUser", method = RequestMethod.GET)
    public ModelAndView getUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        user = (User) userDAO.getObject(user);
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
