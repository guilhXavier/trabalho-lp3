package br.com.ifsul.pontoeletronico.gui;

import br.com.ifsul.pontoeletronico.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
@RequiredArgsConstructor
public class MainGui {

    private final UsuarioGui usuarioGui;

    private final TarefaGui tarefaGui;

    public JFrame criarMenuPrincipal() {
        JFrame janela = new JFrame("Trabalho");
        janela.setSize(480, 320);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);

        JButton btUsuarios = new JButton("Usuarios");
        JButton btTarefas = new JButton("Tarefas");
        JButton btPontos = new JButton("Pontos");

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(0, 2));

        painel.add(btPontos);
        painel.add(btTarefas);
        painel.add(btUsuarios);

        janela.getContentPane().setLayout(new BorderLayout());

        janela.getContentPane().add(painel, BorderLayout.CENTER);

        janela.revalidate();

        btUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioGui.runUsuarioGui();
            }
        });

        btTarefas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarefaGui.run();
            }
        });

        return janela;
    }
}
