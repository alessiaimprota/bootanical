package org.java.lessons.bootanical.controller;

import java.util.List;
import java.util.Optional;

import org.java.lessons.bootanical.model.Plant;
import org.java.lessons.bootanical.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;

@RestController
@RequestMapping("/api/plants")
public class PlantRestController {

    @Autowired
    private PlantService plantService;

    @GetMapping
    public List<Plant> index() {
        List<Plant> plants = plantService.findAll();
        return plants;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plant> show(@PathVariable Integer id) {
        Optional<Plant> plant = plantService.findById(id);
        if (plant.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(plant.get(), HttpStatus.OK);
    }
}
