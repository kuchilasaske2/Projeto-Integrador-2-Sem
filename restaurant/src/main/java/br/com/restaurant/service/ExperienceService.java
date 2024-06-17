package br.com.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.restaurant.model.Experience;
import br.com.restaurant.repository.ExperienceRepository;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    public Experience save(Experience experience) {
        return experienceRepository.save(experience);
    }
}