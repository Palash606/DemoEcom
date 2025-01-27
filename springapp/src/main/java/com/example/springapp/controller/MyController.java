package com.example.springapp.controller;

import com.example.springapp.exception.InvalidCredentialsException;
import com.example.springapp.model.User;
import com.example.springapp.service.MyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    private MyService myService;   //DI

    @GetMapping("/")
    public ModelAndView showLogin(
            Model model,
            HttpServletRequest httpServletRequest,
            ModelAndView mav) {

        model.addAttribute("username", "harry@gmail.com");

        List<String> listSports = Arrays.asList("Chess", "Cricket", "Badminton");
        httpServletRequest.setAttribute("list", listSports);

        mav.setViewName("demo");
        mav.addObject("current_date", LocalDate.now());

        return mav;
    }

    @GetMapping("/welcome-page")
    public String handleWelcomeRequest(HttpServletRequest req) {
        String username = req.getParameter("username");
        req.setAttribute("username", username);
        return "welcome";
    }

    @GetMapping("/login-form")
    public String readLogin(HttpServletRequest req) { //DI type 2
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            User user = myService.isValid(username, password);
            return "dashboard";
        } catch (InvalidCredentialsException e) {
            req.setAttribute("msg", e.getMessage());
            return "login";
        }
    }
}