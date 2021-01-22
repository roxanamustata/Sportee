package com.sportee.sportee.controllers;

import com.sportee.sportee.services.GymClassBookingService;
import com.sportee.sportee.services.GymClassService;
import com.sportee.sportee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_trainer')")
@Controller
@RequestMapping("/gymClassBookings")
public class GymClassBookingController {
    private GymClassService gymClassService;
    private UserService userService;
    private GymClassBookingService gymClassBookingService;

    @Autowired
    public GymClassBookingController(GymClassService gymClassService, UserService userService, GymClassBookingService gymClassBookingService) {
        this.gymClassService = gymClassService;
        this.userService = userService;
        this.gymClassBookingService = gymClassBookingService;
}

    @GetMapping("/")
    public ModelAndView showAllGymClassBookings() {
        ModelAndView mv = new ModelAndView("gymClassBookings");
        mv.addObject("gymClassBookings", gymClassBookingService.getAllGymClassBookings());
        return mv;
    }

    @GetMapping("/insertGymClassBooking")
    public ModelAndView insertGymClassBooking() {

        ModelAndView mv = new ModelAndView("insertGymClassBooking");
        mv.addObject("gymClasses", gymClassService.getAllGymClasses());
        mv.addObject("users", userService.getAllUsers());

        mv.addObject("selectedGymClass", "");
        mv.addObject("selectedUser", "");
        return mv;
    }

    @RequestMapping(value = "/insertGymClassBooking", method = RequestMethod.POST)
        public String insertGymClassBooking(Integer gymClass, Integer user) {

       gymClassBookingService.insertGymClassBooking(gymClass, user);
        return "redirect:/gymClassBookings";
    }


}
