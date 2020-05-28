package com.sportee.sportee.controllers;


import com.sportee.sportee.services.GymClassService;
import com.sportee.sportee.services.MeasurementService;
import com.sportee.sportee.services.SubscriptionService;
import com.sportee.sportee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private GymClassService gymClassService;
    private MeasurementService measurementService;
    private UserService userService;
    private SubscriptionService subscriptionService;

    @Autowired
    public HomeController(GymClassService gymClassService, MeasurementService measurementService, UserService userService, SubscriptionService subscriptionService) {
        this.gymClassService = gymClassService;
        this.measurementService = measurementService;
        this.userService = userService;
        this.subscriptionService = subscriptionService;
    }

    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping({"/gym"})
    public String gym() {
        return "gym";
    }

    @GetMapping({"/trainers"})
    public String trainers() {
        return "trainers";
    }

    @GetMapping({"/schedule"})
    public ModelAndView showTimetable() {
        ModelAndView mv = new ModelAndView("timetable");
        mv.addObject("timetable", gymClassService.getTimetable());
        return mv;
    }

    @GetMapping({"/contact"})
    public String contact() {
        return "contact";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/gymClasses")
    public ModelAndView gymClasses() {
        ModelAndView mv = new ModelAndView("gymClasses");
        mv.addObject("gymClasses", gymClassService.getAllGymClasses());
        return mv;
    }


    @GetMapping("/measurements")
    public ModelAndView measurements() {
        ModelAndView mv = new ModelAndView("measurements");
        mv.addObject("measurements", measurementService.getAllMeasurements());
        return mv;
    }

    @GetMapping("/users")
    public ModelAndView users() {
        ModelAndView mv = new ModelAndView("users");
        mv.addObject("users", userService.getAllUsers());
        return mv;
    }

    @GetMapping("/subscriptions")
    public ModelAndView subscriptions() {
        ModelAndView mv = new ModelAndView("subscriptions");
        mv.addObject("subscriptions", subscriptionService.getAllSubscriptions());
        return mv;
    }
}
