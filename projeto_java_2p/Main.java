package projeto_java_2p;

import javax.swing.JOptionPane;

public class Main {
    
    public static void main(String[] args) {
        String menu[] = {"Estoque", "Venda", "Relatorios"};
        int option = -1; // Inicialize a opção com um valor inválido

        do {
            // Exibir o menu e permitir ao usuário escolher uma opção
            String input = JOptionPane.showInputDialog("Escolha uma opção:\n" + "1. Estoque\n" + "2. Venda\n" + "3. Relatorios\n" + "0. Sair");

            // Converter a entrada para um número inteiro
            option = Integer.parseInt(input);

            switch (option) {
                case 1:
                    Estoque.cadastroProduto();
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
