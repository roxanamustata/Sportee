package com.sportee.sportee.controllers;

import com.sportee.sportee.data.DAO.MeasurementType;
import com.sportee.sportee.data.DAO.User;
import com.sportee.sportee.services.MeasurementService;
import com.sportee.sportee.services.MeasurementTypeService;
import com.sportee.sportee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.Optional;
@PreAuthorize("hasRole('ROLE_TRAINER')")
@Controller
@RequestMapping("/measurements")
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

    @GetMapping("/")
    public ModelAndView showAllMeasurements() {
        ModelAndView mv = new ModelAndView("measurements");
        mv.addObject("measurements", measurementService.getAllMeasurements());
        return mv;
    }

    @GetMapping("/insertMeasurement")
    public ModelAndView insertMeasurement() {
        ModelAndView mv = new ModelAndView("insertMeasurement");
        mv.addObject("users", userService.getAllUsers());
        mv.addObject("measurementTypes", measurementTypeService.getAllMeasurementTypes());

        mv.addObject("selectedMember", "");
        mv.addObject("selectedMeasurementType", "");
        return mv;
    }

    @RequestMapping(value = "/insertMeasurement", method = RequestMethod.POST)
    public String insertMeasurement(Date date, int value, int measurementType, int user) {
        measurementService.insertMeasurement(date, value, measurementType, user);
        return "redirect:/measurements";

    }

    @RequestMapping("/{id}/delete")
    public String deleteMeasurement(@PathVariable Integer id) {
        measurementService.deleteMeasurement(id);
        return "redirect:/measurements";

    }

    @GetMapping("/{id}/editMeasurement")
    public ModelAndView editMeasurement() {
        ModelAndView mv = new ModelAndView("editMeasurement");
        mv.addObject("users", userService.getAllUsers());
        mv.addObject("measurementTypes", measurementTypeService.getAllMeasurementTypes());

        mv.addObject("selectedMember", "");
        mv.addObject("selectedMeasurementType", "");
        return mv;

    }

    @PostMapping("/{id}/editMeasurement")
    public String editMeasurement(@PathVariable Integer id, Optional<Date> date,
                                        Optional<Integer> value, Optional<MeasurementType> measurementType,
                                        Optional<User> user) {
        measurementService.editMeasurement(id, date, value, measurementType, user);
        return "redirect:/measurements";
    }


}
