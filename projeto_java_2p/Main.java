package projeto_java_2p;

import javax.swing.JOptionPane;

public class Main {

    /*Projeto de programação 2, dia 30/11/2023, IF GOIANO - CAMPUS URUTAÍ
        Alunos: Rodrigo Simão, Paulo Sergio
        Professora: Cristiane
        GIT HUB: https://github.com/rodvpx/projeto_java_2p
     */

    public static void main(String[] args) {

        // Carrega os cadastros e lançamentos previamente salvos
        Cadastro.carregarCadastros();
        Lancamento.carregarLancamentos();

        try {
            int option;

            do {
                // Exibe o menu principal para o usuário
                String input = JOptionPane.showInputDialog(null,
                        "GERENCIADOR DE CURRAL |\n\nEscolha uma opção:\n\n1. Ver Cadastro\n2. Cadastro\n3. Lançamentos\n4. Relatórios\n0. Sair",
                        "Menu",
                        JOptionPane.PLAIN_MESSAGE);

                // Obtém a opção escolhida pelo usuário
                option = obterOpcao(input);

                switch (option) {
                    case 0:
                        JOptionPane.showMessageDialog(null, "Saindo do programa.");
                        break;
                    case 1:
                        // Exibe a lista de cadastros
                        Cadastro.listarCadastros();
                        break;
                    case 2:
                        // Realiza o cadastro de um novo item
                        Cadastro.cadastro();
                        break;
                    case 3:
                        // Realiza o lançamento de um novo item
                        Lancamento.lancamento();
                        break;
                    case 4:
                        // Exibe o submenu de relatórios
                        subMenu();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                        break;
                }
            } while (option != 0);
        } catch (Exception e) {
            // Trata erros inesperados exibindo uma mensagem ao usuário
            exibirErro(e);
        }
    }

    private static void subMenu() {
        try {
            int option;

            do {
                // Exibe o submenu de relatórios para o usuário
                String input = JOptionPane.showInputDialog(null,
                        "Gerenciamento de curral\n\nEscolha uma opção:\n\n1. Lançamento Diário\n2. Lançamentos Mensais\n3. Lançamentos Anuais\n0. Sair",
                        "Menu",
                        JOptionPane.PLAIN_MESSAGE);

                // Obtém a opção escolhida pelo usuário no submenu
                option = obterOpcao(input);

                switch (option) {
                    case 0:
                        break;
                    case 1:
                        // Gera e exibe o relatório de lançamento diário
                        Estatisticas.lancamentoDiario();
                        break;
                    case 2:
                        // Gera e exibe o relatório de lançamentos mensais
                        Estatisticas.lancamentoMensal();
                        break;
                    case 3:
                        // Gera e exibe o relatório de lançamentos anuais
                        Estatisticas.lancamentoAnual();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                        break;
                }
            } while (option != 0);
        } catch (Exception e) {
            // Trata erros inesperados exibindo uma mensagem ao usuário
            exibirErro(e);
        }
    }

    private static int obterOpcao(String input) {
        // Método para converter a entrada do usuário em um número inteiro
        if (input == null) {
            return 0;
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1; // Tratamento de entrada inválida
        }
    }

    private static void exibirErro(Exception e) {
        // Exibe uma mensagem de erro ao usuário e imprime o stack trace para debug
        JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
        e.printStackTrace();
    }
}
