package com.sportee.sportee.controllers;


import com.sportee.sportee.services.GymClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private GymClassService gymClassService;
@Autowired
    public HomeController(GymClassService gymClassService) {
        this.gymClassService = gymClassService;
    }

    @GetMapping({"/","/home"})
    public String home()
    {
        return "home";
    }

    @GetMapping({"/gym"})
    public String gym()
    {
        return "gym";
    }

    @GetMapping({"/trainers"})
    public String trainers()
    {
        return "trainers";
    }

    @GetMapping({"/schedule"})
    public ModelAndView showTimetable() {
        ModelAndView mv = new ModelAndView("timetable");
        mv.addObject("timetable", gymClassService.getTimetable());
        return mv;
    }

//    public String schedule()
//    {
//        return "timetable";
//    }

    @GetMapping({"/contact"})
    public String contact()
    {
        return "contact";
    }


    @GetMapping({"/membership"})
    public String login()
    {
        return "membership";
    }



}
