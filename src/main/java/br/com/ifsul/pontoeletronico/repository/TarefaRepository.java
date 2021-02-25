package br.com.ifsul.pontoeletronico.repository;

import br.com.ifsul.pontoeletronico.model.Ponto;
import br.com.ifsul.pontoeletronico.model.Tarefa;
import br.com.ifsul.pontoeletronico.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    List<Tarefa> findTarefasByUsuariosContains(Usuario usuario);

    List<Tarefa> findTarefasByTrabalhoCompletoIsLessThanEqual(LocalTime localTime);

}
