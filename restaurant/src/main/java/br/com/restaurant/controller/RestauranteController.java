package br.com.restaurant.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.restaurant.model.Comment;
import br.com.restaurant.model.Restaurante;
import br.com.restaurant.service.CommentService;
import br.com.restaurant.service.RestauranteService;

@RestController
@RequestMapping("/api")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/restaurants")
    public List<Restaurante> getAllRestaurantes() {
        return restauranteService.getAllRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurante getRestaurantById(@PathVariable Long id) {
        return restauranteService.getRestaurantById(id);
    }

    @PostMapping("/restaurants/{id}/comments")
    public Comment addComment(@PathVariable Long id, @RequestBody Comment comment) {
        return commentService.addComment(id, comment);
    }
}