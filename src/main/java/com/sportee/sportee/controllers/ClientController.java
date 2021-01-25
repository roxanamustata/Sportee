package com.sportee.sportee.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize("hasRole('ROLE_client')")
@Controller
@RequestMapping("/")
public class ClientController {

    @GetMapping( "myProgress")
    public String myProgress() {
        return "myProgress";
    }



    @GetMapping("myAccount")
    public String myAccount() {
        return "myAccount";
    }

    @GetMapping("mySchedule")
    public String mySchedule() {
        return "mySchedule";
    }
}
