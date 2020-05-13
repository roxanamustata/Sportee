package com.sportee.sportee.controllers;

import com.sportee.sportee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller

public class UserController {
    private UserService userService;
//    private MemberService memberService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
//        this.memberService = memberService;
    }


//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String showRegistrationForm(WebRequest request, Model model) {
//        UserDTO userDTO = new UserDTO();
//        model.addAttribute("user", userDTO);
//        return "login";
//    }

    @RequestMapping({"/users/showAll", "/"})
    public ModelAndView showAllUsers() {
        ModelAndView mv = new ModelAndView("users");
        mv.addObject("users", userService.getAllUsers());
        return mv;
    }

    @GetMapping("/users/insert")
    public String insertUser() {
        return "insertUser";

    }

    @PostMapping("/users/insert")
    public ModelAndView insertUser(String userName, String password, String firstName, String lastName,
                                  Date birthDate,


                                   int height, int role) {


        userService.insertUser(userName, password, firstName, lastName, birthDate, height, role);

        ModelAndView mv = new ModelAndView("membership");
        return mv;


    }
//    @RequestMapping("/user/{id}/insertMemberDetails")
//    public ModelAndView insertMemberDetails() {
//        ModelAndView mv = new ModelAndView("insertMemberDetails");
//                return mv;
//
//    }
//
//    @PostMapping("/user/{id}/insertMemberDetails")
//    public ModelAndView insertMemberDetails( @PathVariable Integer id, String firstName, String lastName, /*@DateTimeFormat(pattern = "yyyy-MM-dd")*/
//            Date birthDate, int height) {
//        memberService.insertMemberDetails(firstName, lastName, birthDate, height, id);
//        ModelAndView mv = new ModelAndView("members");
//        return mv;
//    }






    @RequestMapping("/users/{id}/delete")
    public ModelAndView deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return showAllUsers();

    }
}
