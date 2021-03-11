package com.sportee.sportee.controllers;

import com.sportee.sportee.services.GymClassBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@PreAuthorize("hasRole('ROLE_client')")
@Controller
//@RequestMapping("/client")
public class ClientController {

    GymClassBookingService gymClassBookingService;

    @Autowired
    public ClientController(GymClassBookingService gymClassBookingService) {
        this.gymClassBookingService = gymClassBookingService;
    }

    @GetMapping("/myProgress")
    public String myProgress() {
        return "myProgress";
    }


    @GetMapping("/myAccount")
    public String myAccount() {
        return "myAccount";
    }

    @RequestMapping(value = "/mySchedule", method = RequestMethod.GET)
    public ModelAndView mySchedule() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        ModelAndView mv = new ModelAndView("mySchedule");
        mv.addObject("gymClassBookings", gymClassBookingService.getAllGymClassBookingsByUserName(currentPrincipalName));
        return mv;


    }
}
