package br.com.ifsul.pontoeletronico.dto;

import br.com.ifsul.pontoeletronico.model.Tarefa;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdicionarTarefaDto {

    private Integer idPonto;

    private Tarefa tarefa;
}
