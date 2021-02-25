package br.com.ifsul.pontoeletronico.controller;

import br.com.ifsul.pontoeletronico.model.Usuario;
import br.com.ifsul.pontoeletronico.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UsuarioService usuarioService;

    @PostMapping
    public @ResponseBody
    String addUser(@RequestParam String name, @RequestParam String email, @RequestParam String equipe) {
        return usuarioService.salvarUsuario(name, email, equipe);
    }

    @GetMapping
    public @ResponseBody
    Iterable<Usuario> getUsers() {
        return usuarioService.buscarTodos();
    }
}
