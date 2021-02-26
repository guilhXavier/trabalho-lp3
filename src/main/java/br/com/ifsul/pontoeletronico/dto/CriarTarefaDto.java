package br.com.ifsul.pontoeletronico.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CriarTarefaDto {

    private String nome;

    private LocalTime localTime;

    private List<Integer> idUsuarios;

    private List<Integer> idPontos;

}
