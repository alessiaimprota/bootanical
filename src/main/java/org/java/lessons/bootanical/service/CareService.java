package org.java.lessons.bootanical.service;

import org.java.lessons.bootanical.model.Care;
import org.java.lessons.bootanical.repository.CareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CareService {

    @Autowired
    CareRepository careRepository;

    public Care getById(Integer id) {
        return careRepository.findById(id).get();
    }

    public Care save(Care care) {
        return careRepository.save(care);
    }

    public void deleteById(Integer id) {
        careRepository.deleteById(id);
    }

}
