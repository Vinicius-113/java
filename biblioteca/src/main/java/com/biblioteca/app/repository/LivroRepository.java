package com.biblioteca.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.biblioteca.app.model.Livro;
public interface LivroRepository extends JpaRepository<Livro, Long> {

}
