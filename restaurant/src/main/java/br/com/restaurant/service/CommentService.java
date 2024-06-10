package br.com.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.restaurant.model.Comment;
import br.com.restaurant.model.Restaurante;
import br.com.restaurant.repository.CommentRepository;
import br.com.restaurant.repository.RestauranteRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    public Comment addComment(Long restaurantId, Comment comment) {
        Restaurante restaurant = restauranteRepository.findById(restaurantId).orElse(null);
        if (restaurant != null) {
            comment.setRestaurante(restaurant);
            return commentRepository.save(comment);
        }
        return null;
    }
}