package br.com.restaurant.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.restaurant.model.Comment;
import br.com.restaurant.model.Usuario;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	List<Comment> findFirstByUsuario(Usuario usuario);
	
}