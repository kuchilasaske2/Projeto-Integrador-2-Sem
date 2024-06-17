package br.com.restaurant.service;

import java.time.Instant;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.restaurant.model.Usuario;
import br.com.restaurant.repository.UsuarioRepository;

@Service
public class AuthService {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private UsuarioRepository usuarioRepository;
	
	public String authenticate(String email, String senha) {
		Optional<Usuario> usuario = usuarioService.findByEmail(email);
		if(usuario.isPresent() && usuario.get().getSenha().equals(senha)) {
			String token = generateToken(usuario.get());
			return String.format("{\"token\": \"%s\", \"nome\": \"%s\"}", token, usuario.get().getNome());
		}
		return null;
	}
	private String generateToken(Usuario usuario) {
		String token = String.format("%s|%s|%d|%d",usuario.getEmail(), usuario.getNome(),usuario.getId(), Instant.now().toEpochMilli());
		
		return Base64.getEncoder().encodeToString(token.getBytes());
	}
	
	public boolean validateToken(String token) {
		String[] split = toUserArray(token);
		Instant instant = Instant.ofEpochMilli(Long.valueOf(split[3]));
		return instant.isAfter(Instant.now().minusSeconds(240*10));
		}
	private String[] toUserArray(String token) {
		byte[] decode = Base64.getDecoder().decode(token);
		String auth = new String(decode);
		return auth.split("\\|");
	}
	
	public Usuario toUser(String token) {
		String[] split = toUserArray(token);
		Usuario u = new Usuario();
		
		u.setId(Long.valueOf(split[2]));
		u.setEmail(split[0]);
		u.setNome(split[1]);
		
		return u;
	}
	
	public Usuario getUserFromToken(String token) {
        // Lógica para obter o usuário a partir do token
        // Exemplo: decodificar o token e buscar o usuário no banco de dados
        String usuarioEmail = decodeToken(token);
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(usuarioEmail);
        return usuarioOptional.orElse(null);
    }

    private String decodeToken(String token) {
        // Lógica para decodificar o token e obter o email do usuário
        return "user@example.com"; // Exemplo de email decodificado
    }
}
