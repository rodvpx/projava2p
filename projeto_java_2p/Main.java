package projeto_java_2p;

import javax.swing.JOptionPane;

public class Main {
    
    public static void main(String[] args) {
        int option; // Inicialize a opção com um valor inválido

        do {
            // Exibir o menu e permitir ao usuário escolher uma opção
            String input = (String) JOptionPane.showInputDialog(null, 
            "Escolha uma opção:\n1. Estoque\n2. Venda\n3. Relatorios\n0. Sair", "Menu", JOptionPane.PLAIN_MESSAGE);

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
                case 1:
                    Estoque.verEstoque();

                    String cadastrarOpcao = JOptionPane.showInputDialog("Deseja cadastrar um novo produto? (s/n)");
                    if (cadastrarOpcao != null && cadastrarOpcao.equalsIgnoreCase("S")) {
                    Estoque.cadastroProduto();
                }
                    break;
                case 2:
                    // Implemente a lógica para a opção de venda
                    break;
                case 3:
                    // Implemente a lógica para a opção de relatórios
                    break;
                case 0:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (option != 0);
    }
}
