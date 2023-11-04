package projeto_java_2p;

import javax.swing.JOptionPane;

public class Main {
    
    public static void main(String[] args) {
    int option; // Inicialize a opção com um valor inválido

    do {
        // Exibir o menu e permitir ao usuário escolher uma opção
        String input = JOptionPane.showInputDialog(null,
                "+-----------------------------------+\n| GERENCIADOR DE CURRAL |\n+-----------------------------------+\nEscolha uma opção:\n\n1. Banco De Dados\n2. Lançamentos\n3. Relatorios\n0. Sair", "Menu",
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
                BancoDados.verDados();

                String cadastrarOpcao = JOptionPane.showInputDialog("Deseja cadastrar uma nova vaca? (s/n)");
                if (cadastrarOpcao != null && cadastrarOpcao.equalsIgnoreCase("S")) {
                    BancoDados.lancamento();
                }
                break;
            case 2:
                BancoDados.lancamento();
                break;
            case 3:
                // Implemente a lógica para a opção de relatórios
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                break;
                // 04112023,6789,Mimosa,Holandese,Marrom,8.0,5.69,3.500,2.69
        }
    } while (option != 0);
}

}
