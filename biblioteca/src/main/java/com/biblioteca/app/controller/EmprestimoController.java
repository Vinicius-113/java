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

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("emprestimos", emprestimoRepository.findAll());
        return "emprestimos";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("emprestimo", new Emprestimo());
        model.addAttribute("livros", livroRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll()); // adiciona usuários
        return "formEmprestimo";
    }

    @PostMapping
    public String salvar(@ModelAttribute Emprestimo emprestimo) {

        // Carregar o livro completo do banco
        if (emprestimo.getLivro() != null && emprestimo.getLivro().getId() != null) {
            emprestimo.setLivro(livroRepository.findById(emprestimo.getLivro().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Livro inválido")));
        }

        // Carregar o usuário completo do banco
        if (emprestimo.getUsuario() != null && emprestimo.getUsuario().getId() != null) {
            emprestimo.setUsuario(usuarioRepository.findById(emprestimo.getUsuario().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário inválido")));
        }

        emprestimoRepository.save(emprestimo);
        return "redirect:/emprestimos";
    }

    @GetMapping("/{id}/delete")
    public String deletar(@PathVariable Long id) {
        emprestimoRepository.deleteById(id);
        return "redirect:/emprestimos";
    }
}