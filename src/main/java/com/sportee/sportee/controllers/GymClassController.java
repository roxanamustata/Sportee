package com.sportee.sportee.controllers;

import com.sportee.sportee.services.GymClassService;
import com.sportee.sportee.services.GymClassTypeService;
import com.sportee.sportee.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;


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

    @GetMapping("/")
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


    @GetMapping("/{id}/editGymClass")
    public String editGymClass() {
        return "editGymClass";

    }

    @PostMapping("/{id}/editGymClass")
    public String editGymClass(@PathVariable Integer id,
                                     @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> date,
                                     @RequestParam(value = "time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) Optional<LocalTime> time,
                                     Optional<Integer> gymClassType, Optional<Integer> room) {

        Optional<LocalDateTime> dateTime= Optional.of(LocalDateTime.of(date.get(), time.get()));
        gymClassService.editGymClass(id, dateTime, gymClassType, room);
        return "redirect:/gymClasses";
    }


}
