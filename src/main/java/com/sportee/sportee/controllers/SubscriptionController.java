package com.sportee.sportee.controllers;

import com.sportee.sportee.data.DAO.MeasurementType;
import com.sportee.sportee.data.DAO.SubscriptionType;
import com.sportee.sportee.data.DAO.User;
import com.sportee.sportee.services.SubscriptionService;
import com.sportee.sportee.services.SubscriptionTypeService;
import com.sportee.sportee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.Optional;
@PreAuthorize("hasRole('ROLE_admin')")
@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private SubscriptionService subscriptionService;
    private UserService userService;
    private SubscriptionTypeService subscriptionTypeService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, UserService userService, SubscriptionTypeService subscriptionTypeService) {
        this.subscriptionService = subscriptionService;
        this.userService = userService;
        this.subscriptionTypeService = subscriptionTypeService;
    }

    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView showAllSubscriptions() {
        ModelAndView mv = new ModelAndView("subscriptions");
        mv.addObject("subscriptions", subscriptionService.getAllSubscriptions());
        return mv;
    }

    @GetMapping("/insertSubscription")
    public ModelAndView insertSubscription() {
        ModelAndView mv = new ModelAndView("insertSubscription");
        mv.addObject("users", userService.getAllUsers());
        mv.addObject("subscriptionTypes", subscriptionTypeService.getAllSubscriptionType());

        mv.addObject("selectedMember", "");
        mv.addObject("selectedSubscriptionType", "");
        return mv;
    }

    @RequestMapping(value = "/insertSubscription", method = RequestMethod.POST)
    public String insertSubscription(Date date, boolean valid, int subscriptionType, int user) {
        subscriptionService.insertSubscription(date, valid, subscriptionType, user);
        return "redirect:/subscriptions";

    }

    @RequestMapping("/{id}/delete")
    public String deleteMeasurement(@PathVariable Integer id) {
        subscriptionService.deleteSubscription(id);
        return "redirect:/subscriptions";

    }


    @GetMapping("/{id}/editSubscription")
    public ModelAndView editSubscription() {
        ModelAndView mv = new ModelAndView("editSubscription");
        return mv;

    }

    @PostMapping("/{id}/editSubscription")
    public String editSubscription(@PathVariable Integer id, Optional<Date> date,
                                   Optional<Boolean> valid) {
        subscriptionService.editSubscription(id, date, valid);
        return "redirect:/subscriptions";
    }

}
