package br.com.ifsul.pontoeletronico.repository;

import br.com.ifsul.pontoeletronico.model.Ponto;
import br.com.ifsul.pontoeletronico.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface PontoRepository extends JpaRepository<Ponto, Integer> {

    List<Ponto> findPontosByData(Date date);

    List<Ponto> findPontosByTarefas(Tarefa tarefa);
}
