package br.com.ifsul.pontoeletronico;

import br.com.ifsul.pontoeletronico.gui.MainGui;
import br.com.ifsul.pontoeletronico.gui.UsuarioGui;
import br.com.ifsul.pontoeletronico.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
@EnableSwagger2
@RequiredArgsConstructor
public class PontoEletronicoApplication {

    private final MainGui mainGui;

    public static void main(String[] args) {
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(PontoEletronicoApplication.class);
        springApplicationBuilder.headless(false);
        springApplicationBuilder.run(args);
    }

    @Bean
    public UsuarioService run(UsuarioService usuarioService) {
        mainGui.criarMenuPrincipal();
        return usuarioService;
    }
}
