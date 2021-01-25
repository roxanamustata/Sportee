package com.sportee.sportee.controllers;

import com.sportee.sportee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@PreAuthorize("hasRole('ROLE_admin')")
@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @RequestMapping( "/")
    public ModelAndView showAllUsers() {
        ModelAndView mv = new ModelAndView("users");
        mv.addObject("users", userService.getAllUsers());
        return mv;
    }

    @GetMapping("/insert")
    public String insertUser() {
        return "insertUser";

    }

    @PostMapping("/insert")
    public String insertUser(String userName, String password, String firstName, String lastName,
                                  Date birthDate, int height, String role) {

        userService.insertUser(userName, password, firstName, lastName, birthDate, height, role);
       return "redirect:/login";

    }


    @RequestMapping("/{id}/delete")
    public String deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return "redirect:/users";

    }


}
