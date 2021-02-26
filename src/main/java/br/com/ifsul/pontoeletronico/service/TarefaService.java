package br.com.ifsul.pontoeletronico.service;

import br.com.ifsul.pontoeletronico.model.Ponto;
import br.com.ifsul.pontoeletronico.model.Tarefa;
import br.com.ifsul.pontoeletronico.model.Usuario;
import br.com.ifsul.pontoeletronico.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public String cadastrarTarefa(String nome, LocalTime localTime, List<Usuario> usuarios, List<Ponto> pontos) {
        tarefaRepository.save(buildTarefa(nome, localTime, usuarios, pontos));

        return "Salvo!";
    }

    public String editarTarefa(Integer id, Tarefa tarefa) {
        Tarefa tarefa1 = buildTarefa(tarefa.getNome(), tarefa.getTrabalhoCompleto(), tarefa.getUsuarios(), tarefa.getPontosTrabalhados());
        tarefa1.setId(id);

        tarefaRepository.save(tarefa1);

        return "Editado!";
    }

    public String excluirTarefa(Integer id) {
        tarefaRepository.deleteById(id);

        return "Excluido!";
    }

    public List<Tarefa> buscarTarefas() {
        return tarefaRepository.findAll();
    }

    public Tarefa buscarTarefaPorId(Integer id) {
        return tarefaRepository.findById(id).orElse(new Tarefa());
    }

    private Tarefa buildTarefa(String nome, LocalTime localTime, List<Usuario> idUsuarios, List<Ponto> idPontos) {
        return Tarefa.builder().nome(nome).trabalhoCompleto(localTime).usuarios(idUsuarios).pontosTrabalhados(idPontos).build();
    }
}
