package bsuir.labwork.Labwork.controllers;

import bsuir.labwork.Labwork.models.Cinema;
import bsuir.labwork.Labwork.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CinemaController {
    private final CinemaService service;

    @Autowired
    public CinemaController(CinemaService service) {
        this.service = service;
    }

    @GetMapping("/cinemas")
    public String getCinema(Model model) {
        model.addAttribute("cinemas", service.getAllCinemas());
        return "cinemas";
    }

    @PostMapping("/addCinema")
    public String addCinema(Cinema cinema) {
        service.saveCinema(cinema);
        return "redirect:/cinemas";
    }

    @GetMapping("/updateCinema/{id}")
    public String updateCinemaForm(@PathVariable("id") int id, Model model) {
        Cinema cinema = service.getCinemaById(id);
        model.addAttribute("cinema", cinema);
        return "updateCinema";
    }

    @PostMapping("/updateCinema")
    public String updateCinema(Cinema cinema) {
        service.updateCinema(cinema);
        return "redirect:/cinemas";
    }

    @GetMapping("/deleteCinema/{id}")
    public String deleteCinema(@PathVariable int id) {
        service.deleteCinema(id);
        return "redirect:/cinemas";
    }
}
