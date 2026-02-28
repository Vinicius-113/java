package com.biblioteca.app.controller;

import com.biblioteca.app.model.Livro;
import com.biblioteca.app.repository.LivroRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroRepository repository;

    public LivroController(LivroRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("livros", repository.findAll());
        return "livros";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("livro", new Livro());
        return "formLivro";
    }

    @PostMapping
    public String salvar(@ModelAttribute Livro livro) {
        repository.save(livro);
        return "redirect:/livros";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Livro livro = repository.findById(id).orElseThrow();
        model.addAttribute("livro", livro);
        return "formLivro";
    }

    @PostMapping("/{id}/editar")
    public String atualizar(@PathVariable Long id, @ModelAttribute Livro livroAtualizado) {
        Livro livro = repository.findById(id).orElseThrow();
        livro.setTitulo(livroAtualizado.getTitulo());
        livro.setAutor(livroAtualizado.getAutor());
        livro.setDisponivel(livroAtualizado.getDisponivel());
        repository.save(livro);
        return "redirect:/livros";
    }

    @GetMapping("/{id}/delete")
    public String deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/livros";
    }
}