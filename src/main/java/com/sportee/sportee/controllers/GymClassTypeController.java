package com.sportee.sportee.controllers;

import com.sportee.sportee.services.GymClassTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@PreAuthorize("hasRole('ROLE_admin')")
@Controller
@RequestMapping("/gymClassTypes")
public class GymClassTypeController {

    private GymClassTypeService gymClassTypeService;

    @Autowired
    public GymClassTypeController(GymClassTypeService gymClassTypeService) {
        this.gymClassTypeService = gymClassTypeService;
    }

    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView showAllGymClassTypes() {
        ModelAndView mv = new ModelAndView("gymClassTypes");
        mv.addObject("gymClassTypes", gymClassTypeService.getAllGymClassTypes());
        return mv;
    }

    @GetMapping("/insertGymClassType")
    public String insertGymClassType() {
        return "insertGymClassType";

    }

    @PostMapping("/insertGymClassType")
    public String insertGymClassType(String name, int duration ) {
       gymClassTypeService.insertGymClassType(name, duration);
        return "redirect:/gymClasses/insertGymClass";
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteGymClassTypes(@PathVariable Integer id) {
        gymClassTypeService.deleteGymClassType(id);
        return showAllGymClassTypes();

    }
}
