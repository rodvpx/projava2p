package projeto_java_2p;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        int option; // Inicialize a opção com um valor inválido

        do {
            // Exibir o menu e permitir ao usuário escolher uma opção
            String input = JOptionPane.showInputDialog(null,
                    "+-----------------------------------+\n| GERENCIADOR DE CURRAL |\n+-----------------------------------+\nEscolha uma opção:\n\n1. Banco De Dados\n2. Cadastro\n3. Lançamentos\n4. Relatórios\n0. Sair",
                    "Menu",
                    JOptionPane.PLAIN_MESSAGE);

            if (input == null) {
                option = 0; // Usuário clicou em Cancelar ou fechou a janela
            } else {
                try {
                    // Converter a entrada para um número inteiro
                    option = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    option = -1; // Tratamento de entrada inválida
                }
            }

            switch (option) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Saindo do programa.");
                    break;
                case 1:
                    Estatisticas.verCadastros();
                    break;
                case 2:
                    Cadastro.cadastro();
                    String cadastrarOpcao = JOptionPane.showInputDialog("Deseja cadastrar uma nova vaca? (s/n)");
                    if (cadastrarOpcao != null && cadastrarOpcao.equalsIgnoreCase("s")) {
                        Cadastro.cadastro();
                    }
                    break;
                case 3:
                    Lancamento.lancamento();
                    break;
                case 4:
                    Estatisticas.verlancamentos();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                    break;
            }
        } while (option != 0);
    }
}

 // 05112023, 5678, Maria, Holandesa, Marrom
                // 06112023, 8000, 3.0, 4000, 2.68