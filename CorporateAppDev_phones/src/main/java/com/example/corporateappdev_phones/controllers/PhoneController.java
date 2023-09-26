package com.example.corporateappdev_phones.controllers;

import com.example.corporateappdev_phones.models.Phone;
import com.example.corporateappdev_phones.services.PhonesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PhoneController {
    private final PhonesService service;

    @Autowired
    public PhoneController(PhonesService service) {
        this.service = service;
    }

    @GetMapping("/phones")
    public String getPhones(Model model) {
        model.addAttribute("phones", service.getAllPhones());
        return "phones";
    }

    @PostMapping("/addPhone")
    public String addPhone(Phone phone) {
        service.savePhone(phone);
        return "redirect:/phones";
    }

    @GetMapping("/updatePhone/{id}")
    public String updatePhoneForm(@PathVariable("id") int id, Model model) {
        Phone phone = service.getPhoneById(id);
        model.addAttribute("phone", phone);
        return "updatePhone";
    }

    @PostMapping("/updatePhone")
    public String updatePhone(Phone phone) {
        service.updatePhone(phone);
        return "redirect:/phones";
    }

    @GetMapping("/deletePhone/{id}")
    public String deletePhone(@PathVariable int id) {
        service.deletePhone(id);
        return "redirect:/phones";
    }
}
