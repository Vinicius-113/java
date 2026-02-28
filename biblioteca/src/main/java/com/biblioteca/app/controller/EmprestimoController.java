package com.biblioteca.app.controller;

import com.biblioteca.app.model.Emprestimo;
import com.biblioteca.app.repository.EmprestimoRepository;
import com.biblioteca.app.repository.LivroRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroRepository livroRepository;

    public EmprestimoController(EmprestimoRepository emprestimoRepository,
                                LivroRepository livroRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("emprestimos", emprestimoRepository.findAll());
        return "emprestimos";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("emprestimo", new Emprestimo());
        model.addAttribute("livros", livroRepository.findAll());
        return "formEmprestimo";
    }

    @PostMapping
    public String salvar(@ModelAttribute Emprestimo emprestimo) {
        emprestimoRepository.save(emprestimo);
        return "redirect:/emprestimos";
    }

    // 🔴 ESSE MÉTODO É O QUE ESTAVA FALTANDO
    @GetMapping("/{id}/delete")
    public String deletar(@PathVariable Long id) {
        emprestimoRepository.deleteById(id);
        return "redirect:/emprestimos";
    }
}