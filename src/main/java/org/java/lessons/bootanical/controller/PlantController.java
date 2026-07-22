package org.java.lessons.bootanical.controller;

import java.util.List;

import org.java.lessons.bootanical.model.Plant;
import org.java.lessons.bootanical.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller

@RequestMapping("/plants")
public class PlantController {

    @Autowired
    private PlantRepository repository;

    @GetMapping
    public String index(Model model) {
        List<Plant> plants = repository.findAll();
        model.addAttribute("plants", plants);
        return "plants/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Plant plant = repository.findById(id).get();
        model.addAttribute("plant", plant);
        return "plants/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("plant", new Plant());
        return "plants/create";
    }

    @PostMapping("/store")
    public String store(@Valid @ModelAttribute("plant") Plant formPlant, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "plants/create";
        }
        repository.save(formPlant);
        return "redirect:/plants";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("plant", repository.findById(id).get());
        return "plants/edit";
    }

    @PostMapping("edit/{id}")
    public String update(@Valid @ModelAttribute("plant") Plant formPlant, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "plants/edit";
        }
        repository.save(formPlant);
        return "redirect:/plants";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        repository.deleteById(id);
        return "redirect:/plants";
    }
}
