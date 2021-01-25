package com.sportee.sportee.controllers;

import com.sportee.sportee.services.SubscriptionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@PreAuthorize("hasRole('ROLE_admin')")
@Controller
@RequestMapping("/subscriptionTypes")
public class SubscriptionTypeController {
    private SubscriptionTypeService subscriptionTypeService;

    @Autowired
    public SubscriptionTypeController(SubscriptionTypeService subscriptionTypeService) {
        this.subscriptionTypeService = subscriptionTypeService;
    }
    @GetMapping({"/showAll"})
    public ModelAndView showAllMembers() {
        ModelAndView mv = new ModelAndView("subscriptionTypes");
        mv.addObject("subscriptionTypes", subscriptionTypeService.getAllSubscriptionType());
        return mv;
    }


    @GetMapping("/insertSubscriptionType")
    public String insertSubscriptionType() {
        return "insertSubscriptionType";

    }

    @PostMapping("/insertSubscriptionType")
    public String insertSubscriptionType(String name, int duration, int price) {
       subscriptionTypeService.insertSubscriptionType(name, duration, price);
        return "redirect:/subscriptions/insertSubscription";
    }

}
