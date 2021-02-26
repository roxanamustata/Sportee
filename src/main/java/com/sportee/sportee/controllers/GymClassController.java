package com.sportee.sportee.controllers;

import com.sportee.sportee.data.DAO.GymClass;
import com.sportee.sportee.services.GymClassService;
import com.sportee.sportee.services.GymClassTypeService;
import com.sportee.sportee.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@PreAuthorize("hasRole('ROLE_admin')")
@Controller
@RequestMapping("/gymClasses")
public class GymClassController {
    private GymClassService gymClassService;
    private GymClassTypeService gymClassTypeService;
    private RoomService roomService;

    @Autowired
    public GymClassController(GymClassService gymClassService, GymClassTypeService gymClassTypeService, RoomService roomService) {
        this.gymClassService = gymClassService;
        this.gymClassTypeService = gymClassTypeService;
        this.roomService = roomService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showAllGymClasses() {
        ModelAndView mv = new ModelAndView("gymClasses");
        mv.addObject("gymClasses", gymClassService.getAllGymClasses());
        return mv;
    }

    @GetMapping("/insertGymClass")
    public ModelAndView insertGymClass() {

        ModelAndView mv = new ModelAndView("insertGymClass");
        mv.addObject("gymClassTypes", gymClassTypeService.getAllGymClassTypes());
        mv.addObject("rooms", roomService.getAllRooms());

        mv.addObject("selectedGymClassType", "");
        mv.addObject("selectedRoom", "");
        return mv;
    }

    @PostMapping("/insertGymClass")

    public String insertGymClass(@RequestParam(value = "date")
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,

                                 @RequestParam(value = "time")
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time, int gymClassType, int room) {

        LocalDateTime dateTime = LocalDateTime.of(date, time);
        gymClassService.insertGymClass(dateTime, gymClassType, room);
        return "redirect:/gymClasses";
    }

    @RequestMapping("/{id}/delete")
    public String deleteGymClass(@PathVariable Integer id) {
        gymClassService.deleteGymClass(id);
        return "redirect:/gymClasses";

    }

    @RequestMapping(value = "/editGymClass/{id}", method = RequestMethod.GET)
    public ModelAndView showUpdateForm(@PathVariable("id") int id) {
        GymClass gymClass = gymClassService.findGymClass(id);
        ModelAndView mv = new ModelAndView("editGymClass");
        mv.addObject("gymClass", gymClass);
        mv.addObject("gymClassTypes", gymClassTypeService.getAllGymClassTypes());
        mv.addObject("rooms", roomService.getAllRooms());
        mv.addObject("selectedGymClassType", "");
        mv.addObject("selectedRoom", "");
        return mv;

    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateGymClass(@PathVariable("id") int id,
                                 @RequestParam(value = "date")
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                                 int gymClassType, int room) {
        GymClass gymClass = gymClassService.findGymClass(id);
        gymClassService.editGymClass(gymClass.getId(), dateTime, gymClassType, room);
        return "redirect:/gymClasses";
    }
}
