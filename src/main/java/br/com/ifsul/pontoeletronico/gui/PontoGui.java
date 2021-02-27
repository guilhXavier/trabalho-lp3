package br.com.ifsul.pontoeletronico.gui;

import br.com.ifsul.pontoeletronico.model.Ponto;
import br.com.ifsul.pontoeletronico.model.Tarefa;
import br.com.ifsul.pontoeletronico.service.PontoService;
import br.com.ifsul.pontoeletronico.service.TarefaService;
import br.com.ifsul.pontoeletronico.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PontoGui {

    private final TarefaService tarefaService;

    private final UsuarioService usuarioService;

    private final PontoService pontoService;

    JButton btCadastrar = new JButton("Cadastrar");
    JButton btListar = new JButton("Listar");

    private JFrame criarUiCadastro() {
        List<Integer> tarefaIdList = tarefaService.buscarTarefas().stream().mapToInt(Tarefa::getId).boxed().collect(Collectors.toList());

        JFrame janela = new JFrame("Trabalho");
        janela.setSize(480, 320);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(0, 2));

        JLabel lblTarefas = new JLabel("Tarefas");
        JList<Integer> listTarefas = new JList<Integer>(new Vector<Integer>(tarefaIdList));

        painel.add(lblTarefas);
        painel.add(listTarefas);

        painel.add(new JLabel());
        painel.add(new JLabel());

        painel.add(btCadastrar);
        painel.add(btListar);

        btCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = pontoService.cadastrarPonto(idsToTarefa(listTarefas.getSelectedValuesList()));

                JOptionPane.showMessageDialog(null, s);
            }
        });

        janela.getContentPane().setLayout(new BorderLayout());

        janela.getContentPane().add(painel, BorderLayout.CENTER);

        janela.revalidate();

        return janela;
    }

    private JFrame criarUiListagem() {
        List<Ponto> pontos = pontoService.buscarPontos();

        JFrame janela = new JFrame("Usuarios");
        janela.setSize(768, 600);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(pontos.size(), 3));

        pontos.forEach(elem -> {
            JLabel lblPonto = new JLabel("Primeiro ponto: " + elem.getPrimeiroPonto() + "| Data: " + elem.getData());
            JButton btDeletar = new JButton("Deletar");
            JButton btEditar = new JButton("Editar");

            painel.add(lblPonto);
            painel.add(btDeletar);
            painel.add(btEditar);
        });

        janela.getContentPane().setLayout(new BorderLayout());

        janela.getContentPane().add(painel, BorderLayout.CENTER);

        janela.revalidate();

        return janela;
    }

    public void run() {
        criarUiCadastro();

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarUiListagem();
            }
        });
    }

    private List<Tarefa> idsToTarefa(List<Integer> ids) {
        return ids
                .stream()
                .map(tarefaService::buscarTarefaPorId)
                .collect(Collectors.toList());
    }

}
