package com.sportee.sportee.controllers;

import com.sportee.sportee.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@PreAuthorize("hasRole('ROLE_admin')")
@Controller
@RequestMapping("/rooms")
public class RoomController {
    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService ;
    }

    @GetMapping("/")
    public ModelAndView showAllRooms() {
        ModelAndView mv = new ModelAndView("rooms");
        mv.addObject("rooms", roomService.getAllRooms());
        return mv;
    }
    @GetMapping("/insertRoom")
    public String insertRoom() {
        return "insertRoom";

    }

    @PostMapping("/insertRoom")
    public String insertRoom(String name, Integer capacity) {
        roomService.insertRoom(name, capacity);
        return "redirect:/gymClasses/insertGymClass";
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteRooms(@PathVariable Integer id) {
        roomService.deleteRoom(id);
        return showAllRooms();

    }
}
