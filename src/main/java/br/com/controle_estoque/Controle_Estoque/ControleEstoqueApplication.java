package br.com.controle_estoque.Controle_Estoque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal que inicializa a aplicação Spring Boot.
 *
 * A anotação {@link SpringBootApplication} é uma conveniência
 * que habilita a auto-configuração do Spring Boot, a varredura de
 * componentes (component-scan) e a configuração da aplicação
 * em uma única anotação.
 */
@SpringBootApplication
public class ControleEstoqueApplication {

    /**
     * Ponto de entrada (main method) para a aplicação Java.
     *
     * Este método delega a inicialização para o {@link SpringApplication#run(Class, String...)},
     * que inicia o contêiner Spring, executa a auto-configuração e
     * inicia o servidor web embutido (ex: Tomcat).
     *
     * @param args Argumentos de linha de comando passados na inicialização.
     */
    public static void main(String[] args) {
        SpringApplication.run(ControleEstoqueApplication.class, args);
    }

}