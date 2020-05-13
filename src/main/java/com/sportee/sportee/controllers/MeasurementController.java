package com.sportee.sportee.controllers;

import com.sportee.sportee.data.DAO.MeasurementType;
import com.sportee.sportee.data.DAO.User;
import com.sportee.sportee.services.MeasurementService;
import com.sportee.sportee.services.MeasurementTypeService;
import com.sportee.sportee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.Optional;

@Controller
public class MeasurementController {

    private MeasurementService measurementService;
    private UserService userService;
    private MeasurementTypeService measurementTypeService;

    @Autowired
    public MeasurementController(MeasurementService measurementService, UserService userService, MeasurementTypeService measurementTypeService) {
        this.measurementService = measurementService;
        this.userService = userService;
        this.measurementTypeService = measurementTypeService;
    }

    @GetMapping({"/measurements/showAll"})
    public ModelAndView showAllMeasurements() {
        ModelAndView mv = new ModelAndView("measurements");
        mv.addObject("measurements", measurementService.getAllMeasurements());
        return mv;
    }

    @GetMapping("/measurements/insertMeasurement")
    public ModelAndView insertMeasurement() {
        ModelAndView mv = new ModelAndView("insertMeasurement");
        mv.addObject("users", userService.getAllUsers());
        mv.addObject("measurementTypes", measurementTypeService.getAllMeasurementTypes());

        mv.addObject("selectedMember", "");
        mv.addObject("selectedMeasurementType", "");
        return mv;
    }

    @PostMapping("/measurements/insertMeasurement")
    public ModelAndView insertMeasurement(Date date, int value, int measurementType, int user) {
//        System.out.println(sporteeMember);
        measurementService.insertMeasurement(date, value, measurementType, user);
        return showAllMeasurements();
    }

    @RequestMapping("/measurements/{id}/delete")
    public ModelAndView deleteMeasurement(@PathVariable Integer id) {
        measurementService.deleteMeasurement(id);
        return showAllMeasurements();

    }

    @GetMapping("/measurements/{id}/editMeasurement")
    public ModelAndView editMeasurement() {
        ModelAndView mv = new ModelAndView("editMeasurement");
        mv.addObject("users", userService.getAllUsers());
        mv.addObject("measurementTypes", measurementTypeService.getAllMeasurementTypes());

        mv.addObject("selectedMember", "");
        mv.addObject("selectedMeasurementType", "");
        return mv;

    }

    @PostMapping("/measurements/{id}/editMeasurement")
    public ModelAndView editMeasurement(@PathVariable Integer id, Optional<Date> date, Optional<Integer> value, Optional<MeasurementType> measurementType, Optional<User> user) {
        measurementService.editMeasurement(id, date, value, measurementType, user);
        return showAllMeasurements();
    }


}
