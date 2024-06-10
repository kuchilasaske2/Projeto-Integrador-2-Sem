package br.com.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.restaurant.model.Usuario;
import br.com.restaurant.repository.UsuarioRepository;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        Usuario existingUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (existingUsuario != null && existingUsuario.getSenha().equals(usuario.getSenha())) {
        	return ResponseEntity.ok(existingUsuario.getNome());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos.");
        }
    }
}
