package br.com.ifsul.pontoeletronico.service;

import br.com.ifsul.pontoeletronico.model.Tarefa;
import br.com.ifsul.pontoeletronico.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;

}
