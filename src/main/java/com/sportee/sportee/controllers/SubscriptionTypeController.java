package com.sportee.sportee.controllers;

import com.sportee.sportee.services.SubscriptionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SubscriptionTypeController {
    private SubscriptionTypeService subscriptionTypeService;

    @Autowired
    public SubscriptionTypeController(SubscriptionTypeService subscriptionTypeService) {
        this.subscriptionTypeService = subscriptionTypeService;
    }
    @GetMapping({"/subscriptionTypes/showAll"})
    public ModelAndView showAllMembers() {
        ModelAndView mv = new ModelAndView("subscriptionTypes");
        mv.addObject("subscriptionTypes", subscriptionTypeService.getAllSubscriptionType());
        return mv;
    }


}
