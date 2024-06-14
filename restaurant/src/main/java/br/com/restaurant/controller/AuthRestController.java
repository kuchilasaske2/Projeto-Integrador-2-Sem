package br.com.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.restaurant.model.Restaurante;
import br.com.restaurant.repository.RestauranteRepository;

@RestController
@RequestMapping("/api")
public class AuthRestController {
	
	@Autowired
    private RestauranteRepository restauranteRepository;
    
    @PostMapping("/loginRestaurante")
    public ResponseEntity<String> login(@RequestBody Restaurante restaurante) {
        Restaurante existingUsuario = restauranteRepository.findByEmail(restaurante.getEmail());
        if (existingUsuario != null && existingUsuario.getSenha().equals(restaurante.getSenha())) {
        	return ResponseEntity.ok(existingUsuario.getId().toString());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos.");
        }
    }

}
