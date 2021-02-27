package br.com.ifsul.pontoeletronico.service;

import br.com.ifsul.pontoeletronico.model.Usuario;
import br.com.ifsul.pontoeletronico.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public String salvarUsuario(String name, String email, String equipe) {
        usuarioRepository.save(buildUsuario(name, email, equipe));

        return "Salvo!";
    }

    public String excluirUsuario(Integer id) {
        usuarioRepository.deleteById(id);

        return "Deletado!";
    }

    public String editarUsuario(Integer id, String name, String email, String equipe) {
        Usuario usuario = buildUsuario(name, email, equipe);
        usuario.setId(id);

        usuarioRepository.save(usuario);

        return "Editado!";
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(new Usuario());
    }

    private Usuario buildUsuario(String name, String email, String equipe) {
        return Usuario.builder().name(name).email(email).equipe(equipe).build();
    }
}
