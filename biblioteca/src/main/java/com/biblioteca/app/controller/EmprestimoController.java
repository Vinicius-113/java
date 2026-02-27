package com.biblioteca.app.controller;

import com.biblioteca.app.model.Emprestimo;
import com.biblioteca.app.model.Livro;
import com.biblioteca.app.model.Usuario;
import com.biblioteca.app.repository.EmprestimoRepository;
import com.biblioteca.app.repository.LivroRepository;
import com.biblioteca.app.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;

    public EmprestimoController(EmprestimoRepository emprestimoRepository,
                                LivroRepository livroRepository,
                                UsuarioRepository usuarioRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // LISTAR TODOS
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("emprestimos", emprestimoRepository.findAll());
        return "emprestimos"; // template da lista
    }

    // FORMULÁRIO DE NOVO
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("emprestimo", new Emprestimo());
        model.addAttribute("livros", livroRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "formEmprestimo"; // template do formulário singular
    }

    // SALVAR NOVO
    @PostMapping
    public String salvar(@ModelAttribute Emprestimo emprestimo) {
        emprestimoRepository.save(emprestimo);
        return "redirect:/emprestimos";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow();
        model.addAttribute("emprestimo", emprestimo);
        model.addAttribute("livros", livroRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "formEmprestimo";
    }

    // EXCLUIR
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        emprestimoRepository.deleteById(id);
        return "redirect:/emprestimos";
    }
}