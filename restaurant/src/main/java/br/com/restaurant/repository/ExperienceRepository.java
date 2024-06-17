package br.com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.restaurant.model.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}

