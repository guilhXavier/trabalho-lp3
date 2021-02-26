package br.com.ifsul.pontoeletronico.controller;

import br.com.ifsul.pontoeletronico.model.Usuario;
import br.com.ifsul.pontoeletronico.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public @ResponseBody
    String addUsuario(@RequestParam String name, @RequestParam String email, @RequestParam String equipe) {
        return usuarioService.salvarUsuario(name, email, equipe);
    }

    @DeleteMapping
    public @ResponseBody
    String deleteUsuario(@RequestParam Integer id) {
        return usuarioService.excluirUsuario(id);
    }

    @PutMapping
    public @ResponseBody
    String atualizarUsuario(@RequestParam Integer id, @RequestParam String name, @RequestParam String email, @RequestParam String equipe) {
        return usuarioService.editarUsuario(id, name, email, equipe);
    }

    @GetMapping
    public @ResponseBody
    Iterable<Usuario> getUsuarios() {
        return usuarioService.buscarTodos();
    }
}
