package br.com.ifsul.pontoeletronico.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;

    private LocalTime trabalhoCompleto;

    @ManyToMany
    private List<Usuario> usuarios;

    @ManyToMany
    private List<Ponto> pontosTrabalhados;
}
