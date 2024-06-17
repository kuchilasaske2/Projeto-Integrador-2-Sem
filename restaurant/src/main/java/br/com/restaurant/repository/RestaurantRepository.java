package br.com.restaurant.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.restaurant.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}

