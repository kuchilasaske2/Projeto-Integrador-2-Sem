package br.com.restaurant.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.restaurant.model.Comment;
import br.com.restaurant.model.Restaurante;
import br.com.restaurant.model.Usuario;
import br.com.restaurant.service.CommentService;
import br.com.restaurant.service.RestauranteService;
import br.com.restaurant.service.UsuarioService;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private CommentService commentService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @PostMapping
	public ResponseEntity<Restaurante> create(@RequestBody Restaurante restaurante){
		Restaurante created = restauranteService.save(restaurante);
		return ResponseEntity.ok(created);
	}

    @GetMapping("/restaurante")
    public List<Restaurante> getAllRestaurantes() {
        return restauranteService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurante getRestaurantById(@PathVariable Long id) {
        return restauranteService.getRestaurantById(id);
    }

    @PostMapping("/{id}/{userid}/comments")
    public Comment addComment(@PathVariable Long id, @PathVariable Long userid, @RequestBody Comment comment) {
    	Optional<Usuario> usuario = usuarioService.getUserById(userid);
    	comment.setUsuario(usuario.get());
        return commentService.addComment(id, comment);
    }
}