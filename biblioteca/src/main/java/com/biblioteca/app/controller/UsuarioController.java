package com.biblioteca.app.controller;

import com.biblioteca.app.model.Usuario;
import com.biblioteca.app.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", repository.findAll());
        return "usuarios";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "formUsuario";
    }

    @PostMapping
    public String salvar(@ModelAttribute Usuario usuario) {
        repository.save(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário inválido: " + id));
        model.addAttribute("usuario", usuario);
        return "formUsuario";
    }

    @PostMapping("/{id}/editar")
    public String atualizar(@PathVariable Long id, @ModelAttribute Usuario usuarioAtualizado) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário inválido: " + id));
        usuario.setNome(usuarioAtualizado.getNome());
        repository.save(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/{id}/delete")
    public String deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/usuarios";
    }
}