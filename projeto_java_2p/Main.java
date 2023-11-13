package projeto_java_2p;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        Cadastro.carregarCadastros();
        Lancamento.carregarLancamentos();

        try {
            int option;

            do {
                String input = JOptionPane.showInputDialog(null,
                        "GERENCIADOR DE CURRAL |\n\nEscolha uma opção:\n\n1. Ver Cadastro\n2. Cadastro\n3. Lançamentos\n4. Relatórios\n0. Sair",
                        "Menu",
                        JOptionPane.PLAIN_MESSAGE);

                option = obterOpcao(input);

                switch (option) {
                    case 0:
                        JOptionPane.showMessageDialog(null, "Saindo do programa.");
                        break;
                    case 1:
                        Cadastro.listarCadastros();
                        break;
                    case 2:
                        Cadastro.cadastro();
                        break;
                    case 3:
                        Lancamento.lancamento();
                        break;
                    case 4:
                        subMenu();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                        break;
                }
            } while (option != 0);
        } catch (Exception e) {
            exibirErro(e);
        }
    }

    private static void subMenu() {
        try {
            int option;

            do {
                String input = JOptionPane.showInputDialog(null,
                        "Gerenciamento de curral\n\nEscolha uma opção:\n\n1. Lançamento Diário\n2. Lançamentos Mensais\n3. Lançamentos Anuais\n4. Relatórios\n0. Sair",
                        "Menu",
                        JOptionPane.PLAIN_MESSAGE);

                option = obterOpcao(input);

                switch (option) {
                    case 0:
                        break;
                    case 1:
                        Estatisticas.lancamentoDiario();
                        break;
                    case 2:
                        Estatisticas.lancamentoMensal();
                        break;
                    case 3:
                        Estatisticas.lancamentoAnual();
                        break;
                    case 4:
                        Estatisticas.verLancamentos();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                        break;
                }
            } while (option != 0);
        } catch (Exception e) {
            exibirErro(e);
        }
    }

    private static int obterOpcao(String input) {
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
        JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
        e.printStackTrace();
    }
}