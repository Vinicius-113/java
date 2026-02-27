package com.biblioteca.app.controller;

import com.biblioteca.app.model.Emprestimo;
import com.biblioteca.app.repository.EmprestimoRepository;
import com.biblioteca.app.repository.LivroRepository;
import com.biblioteca.app.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoRepository emprestimoRepo;
    private final LivroRepository livroRepo;
    private final UsuarioRepository usuarioRepo;

    public EmprestimoController(EmprestimoRepository emprestimoRepo,
                                LivroRepository livroRepo,
                                UsuarioRepository usuarioRepo) {
        this.emprestimoRepo = emprestimoRepo;
        this.livroRepo = livroRepo;
        this.usuarioRepo = usuarioRepo;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("emprestimos", emprestimoRepo.findAll());
        return "emprestimos";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("emprestimo", new Emprestimo());
        model.addAttribute("livros", livroRepo.findAll());
        model.addAttribute("usuarios", usuarioRepo.findAll());
        return "formEmprestimos";
    }

    @PostMapping
    public String salvar(@ModelAttribute Emprestimo emprestimo) {
        emprestimoRepo.save(emprestimo);
        return "redirect:/emprestimos";
    }

    @GetMapping("/{id}/delete")
    public String deletar(@PathVariable Long id) {
        emprestimoRepo.deleteById(id);
        return "redirect:/emprestimos";
    }
}