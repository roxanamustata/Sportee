package com.sportee.sportee.controllers;

import com.sportee.sportee.services.GymClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;


@Controller
@RequestMapping("/gymClasses")
public class GymClassController {
    private GymClassService gymClassService;

    @Autowired
    public GymClassController(GymClassService gymClassService) {
        this.gymClassService = gymClassService;
    }

    @GetMapping("/showAll")
    public ModelAndView showAllGymClasses() {
        ModelAndView mv = new ModelAndView("gymClasses");
        mv.addObject("gymClasses", gymClassService.getAllGymClasses());
        return mv;
    }

    @GetMapping("/insertGymClass")
    public String insertGymClass() {
        return "insertGymClass";

    }

    @PostMapping("/insertGymClass")

    public ModelAndView insertGymClass(@RequestParam(value = "date")
                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,

                                       @RequestParam(value = "time")
                                       @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time, int gymClassType, int room) {

        LocalDateTime dateTime = LocalDateTime.of(date, time);
        gymClassService.insertGymClass(dateTime, gymClassType, room);
        return showAllGymClasses();
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteGymClass(@PathVariable Integer id) {
        gymClassService.deleteGymClass(id);
        return showAllGymClasses();

    }


    @GetMapping("/{id}/editGymClass")
    public String editGymClass() {
        return "editGymClass";

    }

    @PostMapping("/{id}/editGymClass")
    public ModelAndView editGymClass(@PathVariable Integer id,
                                     @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> date,
                                     @RequestParam(value = "time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) Optional<LocalTime> time,
                                     Optional<Integer> gymClassType, Optional<Integer> room) {

        Optional<LocalDateTime> dateTime= Optional.of(LocalDateTime.of(date.get(), time.get()));
        gymClassService.editGymClass(id, dateTime, gymClassType, room);
        return showAllGymClasses();
    }


}
