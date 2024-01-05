package com.example.corporateappdev_phones.controllers;

import com.example.corporateappdev_phones.models.Phone;
import com.example.corporateappdev_phones.services.PhonesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PhoneController {
    private final PhonesService service;
    private boolean isError;

    @Autowired
    public PhoneController(PhonesService service ) {
        this.service = service;
    }
    @GetMapping("/phones")
    public String getPhones(Model model) {
        if(isError) {
            model.addAttribute("error", "Неправильный ввод");
        }
        if (isError)isError=false;
        model.addAttribute("phones", service.getAllPhones());
        return "phones";
    }
    @GetMapping("/phones/{String}")
    public String getPhones1(@PathVariable("String") String String,Model model) {
        model.addAttribute("phones", service.fing(String));
        return "phones2";
    }
    @PostMapping("/addPhone")
    public String addPhone(Phone phone, Model model) {
        try{
            phone.validate();
        }catch (Exception  e){
            model.addAttribute("phones", service.getAllPhones());
            System.out.println("Invalid phone detected: " + phone + " Reason: " + e.getMessage());
            isError=true;
            return "redirect:/phones";
        }
        service.savePhone(phone);
        return "redirect:/phones";
    }

    @GetMapping("/FindPhone")
    public String find(String name, Model model) {
        return "redirect:/phones/";
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
