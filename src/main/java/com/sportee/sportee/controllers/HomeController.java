package com.sportee.sportee.controllers;


import com.sportee.sportee.services.GymClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {


    private GymClassService gymClassService;

    @Autowired
    public HomeController(GymClassService gymClassService) {
        this.gymClassService = gymClassService;
    }

    @RequestMapping(value = {"", "home"}, method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "gym", method = RequestMethod.GET)
    public String gym() {
        return "gym";
    }

    @RequestMapping(value = "trainers", method = RequestMethod.GET)
    public String trainers() {
        return "trainers";
    }

    @RequestMapping(value = "schedule", method = RequestMethod.GET)
    public ModelAndView showTimetable() {
        ModelAndView mv = new ModelAndView("timetable");
        mv.addObject("timetable", gymClassService.getTimetable());
        return mv;
    }

    @RequestMapping(value = "contact", method = RequestMethod.GET)
    public String contact() {
        return "contact";
    }


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

}
