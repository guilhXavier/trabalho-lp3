package br.com.ifsul.pontoeletronico.controller;

import br.com.ifsul.pontoeletronico.dto.AdicionarTarefaDto;
import br.com.ifsul.pontoeletronico.model.Ponto;
import br.com.ifsul.pontoeletronico.model.Tarefa;
import br.com.ifsul.pontoeletronico.service.PontoService;
import br.com.ifsul.pontoeletronico.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/ponto")
@RequiredArgsConstructor
public class PontoController {

    private final PontoService pontoService;

    private final TarefaService tarefaService;

    @GetMapping
    public @ResponseBody Iterable<Ponto> getPontos() {
        return pontoService.buscarPontos();
    }

    @PostMapping
    public @ResponseBody
    String cadastrarPonto(@RequestBody List<Integer> idTarefas) {
        List<Tarefa> tarefas = idTarefas
                .stream()
                .map(tarefaService::buscarTarefaPorId)
                .collect(Collectors.toList());

        return pontoService.cadastrarPonto(tarefas);
    }

    @PutMapping(path = "/registrar")
    public @ResponseBody
    String registrarPonto(@RequestParam Integer id) {
        return pontoService.registrarPonto(id);
    }

    @PutMapping(path = "/add-tarefa")
    public @ResponseBody
    String adicionarTarefaAoPonto(@RequestBody AdicionarTarefaDto adicionarTarefaDto) {
        return pontoService.adicionarTarefa(adicionarTarefaDto.getIdPonto(), adicionarTarefaDto.getTarefa());
    }

    @DeleteMapping
    public @ResponseBody
    String excluirPonto(@RequestParam Integer id) {
        return pontoService.excluirPonto(id);
    }
}
