package br.com.restaurant.model;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurantes")
public class Restaurante {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String endereco;
    private String contato;
    private String tipoCozinha;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private List<Comment> comments;
    
    public Restaurante() {
		// TODO Auto-generated constructor stub
	}
    
    // Construtor
    public Restaurante(String nome, String endereco, String contato, String tipoCozinha, String email, String senha) {
        this.nome = nome;
        this.endereco = endereco;
        this.contato = contato;
        this.tipoCozinha = tipoCozinha;
        this.email = email;
        this.senha = senha;
    }
    
    // Getters e setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getTipoCozinha() {
		return tipoCozinha;
	}

	public void setTipoCozinha(String tipoCozinha) {
		this.tipoCozinha = tipoCozinha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}