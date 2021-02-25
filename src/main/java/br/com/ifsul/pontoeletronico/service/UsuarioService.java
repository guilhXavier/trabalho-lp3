package br.com.ifsul.pontoeletronico.service;

import br.com.ifsul.pontoeletronico.model.Usuario;
import br.com.ifsul.pontoeletronico.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UserRepository userRepository;

    public String salvarUsuario(String name, String email, String equipe) {
        userRepository.save(buildUsuario(name, email, equipe));

        return "Salvo!";
    }

    public String excluirUsuario(Integer id) {
        userRepository.deleteById(id);

        return "Deletado!";
    }

    public String editarUsuario(String name, String email, String equipe) {
        userRepository.save(buildUsuario(name, email, equipe));

        return "Editado!";
    }

    public List<Usuario> buscarTodos() {
        return userRepository.findAll();
    }

    private Usuario buildUsuario(String name, String email, String equipe) {
        return Usuario.builder().name(name).email(email).equipe(equipe).build();
    }
}
