package projeto_java_2p;

import java.io.File;

import javax.swing.JOptionPane;

public class Main {

    // Caminho do diretório de armazenamento
    private static final String REPOSITORY_PATH = "repository";

    public static void main(String[] args) {
        // Cria o diretório de repositório se não existir
        createRepositoryIfNotExists();

        // Variável para armazenar a opção do usuário
        int option;

        // Loop principal do programa
        do {
            // Exibe o menu e obtém a opção do usuário
            String input = JOptionPane.showInputDialog(null,
                    "+-----------------------------------+\n| GERENCIADOR DE CURRAL |\n+-----------------------------------+\nEscolha uma opção:\n\n1. Banco De Dados\n2. Cadastro\n3. Lançamentos\n4. Relatórios\n0. Sair",
                    "Menu",
                    JOptionPane.PLAIN_MESSAGE);

            // Verifica se o usuário clicou em "Cancelar" ou fechou a janela
            if (input == null) {
                option = 0;
            } else {
                try {
                    // Converte a entrada do usuário para um número inteiro
                    option = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    // Se a conversão falhar, define a opção como -1 (inválida)
                    option = -1;
                }
            }

            // Executa a lógica correspondente à opção escolhida pelo usuário
            switch (option) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Saindo do programa.");
                    break;
                case 1:
                    Estatisticas.verCadastros();
                    break;
                case 2:
                    Cadastro.cadastrarVaca();
                    break;
                case 3:
                    Lancamento.lancamento();
                    break;
                case 4:
                    Estatisticas.verEstatisticas();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                    break;
            }
        } while (option != 0); // Continua o loop enquanto a opção do usuário não for 0 (Sair)
    }

    // Método para criar o diretório de repositório se não existir
    private static void createRepositoryIfNotExists() {
        // Cria um objeto File representando o diretório de repositório
        File repositoryDir = new File(REPOSITORY_PATH);
        
        // Verifica se o diretório não existe e, se necessário, cria-o
        if (!repositoryDir.exists()) {
            repositoryDir.mkdirs();
        }
    }
}
