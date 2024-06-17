package br.com.restaurant.model;

import br.com.restaurant.comment.enums.CommentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comentarios")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Integer rating;
    private CommentStatus status = CommentStatus.PENDING;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;
    
    
    public Comment() {
		// TODO Auto-generated constructor stub
	}
    
    // Construtor
    public Comment(Long id, String text, Integer rating, CommentStatus status, Usuario usuario) {
    	super();
		this.id = id;
        this.text = text;
        this.rating = rating;
        this.status = status;
        this.usuario = usuario;
    }
    
    // Getters e setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public CommentStatus getStatus() {
		return status;
	}

	public void setStatus(CommentStatus status) {
		this.status = status;
	}
}