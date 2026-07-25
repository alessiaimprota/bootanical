package org.java.lessons.bootanical.service;

import java.util.List;
import java.util.Optional;

import org.java.lessons.bootanical.model.Plant;
import org.java.lessons.bootanical.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

    public List<Plant> findAll() {
        return plantRepository.findAll();
    }

    public Plant getById(Integer id) {
        return plantRepository.findById(id).get();
    }

    public Plant create(Plant plant) {
        return plantRepository.save(plant);
    }

    public Plant save(Plant plant) {
        return plantRepository.save(plant);
    }

    public void deleteById(Integer id) {
        plantRepository.deleteById(id);
    }

    public Optional<Plant> findById(Integer id) {
        return plantRepository.findById(id);
    }

}
