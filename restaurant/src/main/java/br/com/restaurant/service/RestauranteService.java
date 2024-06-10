package br.com.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.restaurant.model.Restaurante;
import br.com.restaurant.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
    @Autowired
    private RestauranteRepository restauranteRepository;

    public List<Restaurante> getAllRestaurants() {
        return restauranteRepository.findAll();
    }

    public Restaurante getRestaurantById(Long id) {
        return restauranteRepository.findById(id).orElse(null);
    }
}
