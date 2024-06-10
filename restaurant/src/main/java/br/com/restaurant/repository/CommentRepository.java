package br.com.restaurant.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.restaurant.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}