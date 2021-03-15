package com.sportee.sportee.controllers;

import com.sportee.sportee.data.DAO.GymClassBooking;
import com.sportee.sportee.data.DTO.GymClassBookingDTO;
import com.sportee.sportee.services.GymClassBookingService;
import com.sportee.sportee.services.GymClassService;
import com.sportee.sportee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_trainer', 'ROLE_client')")
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

    @RequestMapping(method = RequestMethod.GET)
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
    public String insertGymClassBooking(GymClassBooking gymClassBooking) {

        if (gymClassBooking.getGymClass().getAvailable() < 1) {
            return "redirect:/error";
        }

        gymClassBookingService.insertGymClassBooking(gymClassBooking);
        return "redirect:/gymClassBookings";
    }

    @RequestMapping("/{userId},{gymClassId}/delete")
    public String deleteGymClassBooking(@PathVariable Integer userId, @PathVariable Integer gymClassId) {

        gymClassBookingService.deleteGymClassBooking(userId, gymClassId);
        return "redirect:/gymClassBookings";

    }

    @RequestMapping("/{userId},{gymClassId}/cancel")
    public String cancelBooking(@PathVariable Integer userId, @PathVariable Integer gymClassId) {

        gymClassBookingService.deleteGymClassBooking(userId, gymClassId);
        return "redirect:/mySchedule";

    }

    @RequestMapping(value = "/{gymClass}/{remoteUserName}/bookGymClass", method = RequestMethod.POST)
    public String bookGymClass(@PathVariable("gymClass") Integer gymClass, @PathVariable("remoteUserName") String remoteUserName) {

        gymClassBookingService.bookGymClassBooking(gymClass, remoteUserName);
        return "redirect:/mySchedule";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchGymClassBookings(@Param("keyword") String keyword) {

        ModelAndView mv = new ModelAndView("gymClassBookings");
        List<GymClassBookingDTO> gymClassBookings = gymClassBookingService.searchGymClassBookings(keyword);
        mv.addObject("gymClassBookings", gymClassBookings);
        return mv;
    }

}
