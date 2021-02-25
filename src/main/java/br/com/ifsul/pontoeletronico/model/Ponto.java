package br.com.ifsul.pontoeletronico.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ponto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private LocalTime primeiroPonto;

    private LocalTime segundoPonto;

    private LocalTime terceiroPonto;

    private LocalTime quartoPonto;

    private Date data;

    @ManyToMany
    private List<Tarefa> tarefas;
}
