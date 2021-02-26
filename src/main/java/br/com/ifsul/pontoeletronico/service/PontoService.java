package br.com.ifsul.pontoeletronico.service;

import br.com.ifsul.pontoeletronico.model.Ponto;
import br.com.ifsul.pontoeletronico.model.Tarefa;
import br.com.ifsul.pontoeletronico.repository.PontoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PontoService {

    private final PontoRepository pontoRepository;

    public String cadastrarPonto(List<Tarefa> tarefas) {
        Ponto ponto = Ponto.builder().primeiroPonto(LocalTime.now()).data(new Date(System.currentTimeMillis())).build();

        pontoRepository.save(ponto);

        return "Salvo!";
    }

    public String registrarPonto(Integer id) {
        Ponto ponto = pontoRepository.findById(id).orElse(new Ponto());

        pontoRepository.save(registrarProximoPonto(ponto));

        return "Novo ponto registrado.";
    }

    public String adicionarTarefa(Integer id, Tarefa tarefa) {
        Ponto ponto = pontoRepository.findById(id).orElse(new Ponto());
        List<Tarefa> tarefaList = ponto.getTarefas();
        tarefaList.add(tarefa);
        ponto.setTarefas(tarefaList);

        pontoRepository.save(ponto);

        return "Nova tarefa registrada.";
    }

    public String excluirPonto(Integer id) {
        pontoRepository.deleteById(id);

        return "Ponto deletado";
    }

    public Ponto encontrarPontoPorId(Integer id) {
        return pontoRepository.findById(id).orElse(new Ponto());
    }

    public Iterable<Ponto> buscarPontos() {
        return pontoRepository.findAll();
    }

    private Ponto registrarProximoPonto(Ponto ponto) {
        if (ponto.getSegundoPonto() == null) {
            ponto.setSegundoPonto(LocalTime.now());
            return ponto;
        }

        if (ponto.getTerceiroPonto() == null) {
            ponto.setTerceiroPonto(LocalTime.now());
            return ponto;
        }

        if (ponto.getQuartoPonto() == null) {
            ponto.setQuartoPonto(LocalTime.now());
            return ponto;
        }

        return ponto;
    }
}
