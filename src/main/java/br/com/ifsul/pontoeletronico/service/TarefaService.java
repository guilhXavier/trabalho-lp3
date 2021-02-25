package br.com.ifsul.pontoeletronico.service;

import br.com.ifsul.pontoeletronico.model.Tarefa;
import br.com.ifsul.pontoeletronico.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public String cadastrarTarefa(String nome, LocalTime localTime, List<Integer> idUsuarios, List<Integer> idPontos) {
        return "azar";
    }
}
