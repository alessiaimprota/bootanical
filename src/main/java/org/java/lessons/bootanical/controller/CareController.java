package org.java.lessons.bootanical.controller;

import org.java.lessons.bootanical.model.Care;
import org.java.lessons.bootanical.model.Plant;
import org.java.lessons.bootanical.service.CareService;
import org.java.lessons.bootanical.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/care")
public class CareController {

    @Autowired
    private CareService careService;

    @Autowired
    private PlantService plantService;

    @GetMapping("/{id}")
    public String care(@PathVariable("id") Integer id, Model model) {
        Plant plant = plantService.getById(id);
        Care care = new Care();
        care.setPlant(plant);

        model.addAttribute("plant", plant);
        model.addAttribute("care", care);
        model.addAttribute("cares", plant.getCares());

        return "care/care";
    }

    @GetMapping("/create/{id}")
    public String create(@PathVariable("id") Integer id, Model model) {
        Plant plant = plantService.getById(id);
        Care care = new Care();
        care.setPlant(plant);

        model.addAttribute("plant", plant);
        model.addAttribute("care", care);

        return "care/create";
    }

    @PostMapping("/store")
    public String store(@Valid @ModelAttribute("care") Care formCare, BindingResult bindingResult, Model model) {

        Plant plant = plantService.getById(formCare.getPlant().getId());

        if (bindingResult.hasErrors()) {
            model.addAttribute("plant", plant);
            model.addAttribute("cares", plant.getCares());
            return "care/create";
        }

        formCare.setPlant(plant);
        careService.save(formCare);

        return "redirect:/care/" + plant.getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Care care = careService.getById(id);

        Integer plantId = care.getPlant().getId();

        careService.deleteById(id);

        return String.format("redirect:/care/%d", plantId);
    }
}