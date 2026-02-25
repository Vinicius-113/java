package com.biblioteca.app.model;

import jakarta.persistence.*;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private Boolean disponivel = true;

    public Long getId() { return id; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }

    public void setAutor(String autor) { this.autor = autor; }

    public Boolean getDisponivel() { return disponivel; }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
}