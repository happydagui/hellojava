package lj.controllers;

import lj.entities.Users;
import lj.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sfwn on 2015/9/10.
 */
@Controller
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping({"/hello"})
    public String hello() {
        System.out.println("hello");
        return "hello";
    }

    @RequestMapping("/foo")
    public ModelAndView bar() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("foo");
//        String[] results = new String[] {"abc", "def"};
        mv.addObject("users", userService.list());
        return mv;
    }
}
