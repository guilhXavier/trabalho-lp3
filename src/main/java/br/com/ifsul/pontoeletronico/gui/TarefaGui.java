package br.com.ifsul.pontoeletronico.gui;

import br.com.ifsul.pontoeletronico.model.Ponto;
import br.com.ifsul.pontoeletronico.model.Tarefa;
import br.com.ifsul.pontoeletronico.model.Usuario;
import br.com.ifsul.pontoeletronico.service.PontoService;
import br.com.ifsul.pontoeletronico.service.TarefaService;
import br.com.ifsul.pontoeletronico.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TarefaGui {

    private final TarefaService tarefaService;

    private final UsuarioService usuarioService;

    private final PontoService pontoService;

    JButton btCadastrar = new JButton("Cadastrar");
    JButton btListar = new JButton("Listar");

    private JFrame criarUiCadastro() {
        List<Integer> usuarioIdList = usuarioService.buscarTodos().stream().mapToInt(Usuario::getId).boxed().collect(Collectors.toList());
        List<Integer> pontoIdList = pontoService.buscarPontos().stream().mapToInt(Ponto::getId).boxed().collect(Collectors.toList());

        JFrame janela = new JFrame("Trabalho");
        janela.setSize(480, 320);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(0, 2));

        JLabel lblNome = new JLabel("Nome");
        JLabel lblTempo = new JLabel("Tempo");
        JLabel lblUsuarios = new JLabel("Usuarios");
        JLabel lblPontos = new JLabel("Pontos");

        JTextField txtNome = new JTextField("Nome");
        JTextField txtTempo = new JTextField("Tempo");
        JList<Integer> listUsuarios = new JList<Integer>(new Vector<Integer>(usuarioIdList));
        JList<Integer> listPontos = new JList<Integer>(new Vector<Integer>(pontoIdList));

        painel.add(lblNome);
        painel.add(txtNome);

        painel.add(lblTempo);
        painel.add(txtTempo);

        painel.add(lblUsuarios);
        painel.add(listUsuarios);

        painel.add(new JLabel());
        painel.add(new JLabel());

        painel.add(lblPontos);
        painel.add(listPontos);

        painel.add(new JLabel());
        painel.add(new JLabel());

        painel.add(btCadastrar);
        painel.add(btListar);

        btCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = tarefaService.cadastrarTarefa(txtNome.getText(), LocalTime.parse(txtTempo.getText()), idsToUsuario(listUsuarios.getSelectedValuesList()), idsToPonto(listPontos.getSelectedValuesList()));

                JOptionPane.showMessageDialog(null, s);
            }
        });

        janela.getContentPane().setLayout(new BorderLayout());

        janela.getContentPane().add(painel, BorderLayout.CENTER);

        janela.revalidate();

        return janela;
    }

    private JFrame criarUiListagem() {
        List<Tarefa> tarefas = tarefaService.buscarTarefas();

        JFrame janela = new JFrame("Usuarios");
        janela.setSize(768, 320);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(tarefas.size(), 3));

        tarefas.forEach(elem -> {
            JLabel lblTarefa = new JLabel("Tarefa: " + elem.getNome() + "| Tempo: " + elem.getTrabalhoCompleto());
            JButton btDeletar = new JButton("Deletar");
            JButton btEditar = new JButton("Editar");

            painel.add(lblTarefa);
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

    private List<Usuario> idsToUsuario(List<Integer> ids) {
        return ids
                .stream()
                .map(usuarioService::buscarPorId)
                .collect(Collectors.toList());
    }

    private List<Ponto> idsToPonto(List<Integer> ids) {
        return ids
                .stream()
                .map(pontoService::encontrarPontoPorId)
                .collect(Collectors.toList());
    }

}
