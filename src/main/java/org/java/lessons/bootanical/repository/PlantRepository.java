package org.java.lessons.bootanical.repository;

import org.java.lessons.bootanical.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {

}
