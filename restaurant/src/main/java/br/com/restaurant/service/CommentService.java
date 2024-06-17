package br.com.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.restaurant.model.Comment;
import br.com.restaurant.model.Usuario;
import br.com.restaurant.repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

	public List<Comment> findAll() {
		return commentRepository.findAll();
	}
	
	public List<Comment> findAll(Usuario usuario) {
		return commentRepository.findFirstByUsuario(usuario);
	}
	
	public Optional<Comment> edit(Long id, Comment comment) {
        return commentRepository.findById(id).map(existingComment -> {
            existingComment.setText(comment.getText());
            existingComment.setStatus(comment.getStatus());
            existingComment.setRating(comment.getRating());
            return commentRepository.save(existingComment);
        });
    }
	
	public void delete(Long id) {
		commentRepository.deleteById(id);
	}
}