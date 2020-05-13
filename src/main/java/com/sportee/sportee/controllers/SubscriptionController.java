package com.sportee.sportee.controllers;

import com.sportee.sportee.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SubscriptionController {

    private SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping({"/subscriptions/showAll"})
    public ModelAndView showAllSubscriptions() {
        ModelAndView mv = new ModelAndView("subscriptions");
        mv.addObject("subscriptions", subscriptionService.getAllSubscriptions());
        return mv;
    }
}
