package br.com.ifsul.pontoeletronico.controller;

import br.com.ifsul.pontoeletronico.dto.CriarTarefaDto;
import br.com.ifsul.pontoeletronico.model.Ponto;
import br.com.ifsul.pontoeletronico.model.Tarefa;
import br.com.ifsul.pontoeletronico.model.Usuario;
import br.com.ifsul.pontoeletronico.service.PontoService;
import br.com.ifsul.pontoeletronico.service.TarefaService;
import br.com.ifsul.pontoeletronico.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/tarefa")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    private final PontoService pontoService;

    private final UsuarioService usuarioService;

    @PostMapping
    public @ResponseBody
    String criarTarefa(@RequestBody CriarTarefaDto criarTarefaDto) {
        List<Usuario> usuarios = criarTarefaDto.getIdUsuarios()
                .stream()
                .map(usuarioService::buscarPorId)
                .collect(Collectors.toList());

        List<Ponto> pontos = criarTarefaDto.getIdPontos()
                .stream()
                .map(pontoService::encontrarPontoPorId)
                .collect(Collectors.toList());

        tarefaService.cadastrarTarefa(criarTarefaDto.getNome(), criarTarefaDto.getLocalTime(), usuarios, pontos);

        return "Tarefa criada!";
    }

    @PutMapping
    public @ResponseBody
    String editarTarefa(@RequestBody Tarefa tarefa) {
        tarefaService.editarTarefa(tarefa.getId(), tarefa);

        return "Tarefa editada!";
    }

    @DeleteMapping
    public @ResponseBody
    String excluirTarefa(@RequestParam Integer id) {
        tarefaService.excluirTarefa(id);

        return "Tarefa excluida!";
    }

    @GetMapping
    public @ResponseBody
    Iterable<Tarefa> getTarefas() {
        return tarefaService.buscarTarefas();
    }
}
