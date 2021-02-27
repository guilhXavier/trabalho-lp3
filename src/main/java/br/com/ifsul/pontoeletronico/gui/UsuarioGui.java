package br.com.ifsul.pontoeletronico.gui;

import br.com.ifsul.pontoeletronico.model.Usuario;
import br.com.ifsul.pontoeletronico.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UsuarioGui {

    private final UsuarioService usuarioService;

    JButton btCadastrar = new JButton("Cadastrar");
    JButton btListar = new JButton("Listar");
    JTextField txtNome = new JTextField();
    JTextField txtEmail = new JTextField();
    JTextField txtEquipe = new JTextField();

    private JFrame criarUiUsuario() {
        JFrame janela = new JFrame("Trabalho");
        janela.setSize(480, 320);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(0, 2));

        JLabel lblNome = new JLabel("Nome");
        JLabel lblEmail = new JLabel("Email");
        JLabel lblEquipe = new JLabel("Equipe");

        painel.add(lblNome);
        painel.add(txtNome);

        painel.add(lblEmail);
        painel.add(txtEmail);

        painel.add(lblEquipe);
        painel.add(txtEquipe);

        painel.add(new JLabel());
        painel.add(new JLabel());

        painel.add(btCadastrar);
        painel.add(btListar);

        janela.getContentPane().setLayout(new BorderLayout());

        janela.getContentPane().add(painel, BorderLayout.CENTER);

        janela.revalidate();

        return janela;
    }

    private JFrame criarUiListagemUsuario(List<Usuario> list) {
        JFrame janela = new JFrame("Usuarios");
        janela.setSize(768, 320);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(list.size(), 3));

        list.forEach(elem -> {
            JLabel lblUsuario = new JLabel("Nome: " + elem.getName() + "| Equipe: " + elem.getEquipe());
            JButton btDeletar = new JButton("Deletar");
            JButton btEditar = new JButton("Editar");

            painel.add(lblUsuario);
            painel.add(btDeletar);
            painel.add(btEditar);
        });

        janela.getContentPane().setLayout(new BorderLayout());

        janela.getContentPane().add(painel, BorderLayout.CENTER);

        janela.revalidate();

        return janela;
    }

    public UsuarioService runUsuarioGui() {
        criarUiUsuario();

        btCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = usuarioService.salvarUsuario(txtNome.getText(), txtEmail.getText(), txtEquipe.getText());

                JOptionPane.showMessageDialog(null, s);
            }
        });

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Usuario> usuarios = usuarioService.buscarTodos();

                criarUiListagemUsuario(usuarios);
            }
        });

        return usuarioService;
    }
}
