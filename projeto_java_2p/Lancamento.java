package projeto_java_2p;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Lancamento {

    // Caminho do diretório de armazenamento
    private static final String REPOSITORY_PATH = "repository";

    // Método para realizar um lançamento
    public static void lancamento() {
        // Solicita ao usuário que informe o código da vaca
        String input = JOptionPane.showInputDialog("Informe o código da vaca:");

        // Verifica se o usuário forneceu uma entrada
        if (input != null) {
            try {
                // Converte a entrada do usuário para um número inteiro (código da vaca)
                int codigo = Integer.parseInt(input);

                // Verifica se o arquivo da vaca existe
                File vacaFile = new File(REPOSITORY_PATH + File.separator + "vaca_" + codigo + ".txt");
                if (vacaFile.exists()) {
                    // Exibe os dados da vaca
                    lerDadosVaca(vacaFile);
                    // Adiciona um novo lançamento à vaca
                    adicionarLancamento(vacaFile);
                } else {
                    // Exibe mensagem se a vaca não for encontrada
                    JOptionPane.showMessageDialog(null, "Vaca não encontrada.");
                    
                    // Pergunta se o usuário deseja fazer uma nova consulta
                    String opcao = JOptionPane.showInputDialog("Deseja fazer uma nova consulta? (s/n)");
                    if (opcao != null && opcao.equalsIgnoreCase("s")) {
                        lancamento(); // Chama recursivamente o método para uma nova consulta
                    }
                }
            } catch (NumberFormatException e) {
                // Exibe mensagem de erro se o código não for um número válido
                JOptionPane.showMessageDialog(null, "Erro: Código não é um número válido.");
            }
        }
    }

    // Método para ler os dados da vaca de um arquivo
    public static void lerDadosVaca(File vacaFile) {
        try (Scanner scanner = new Scanner(new FileReader(vacaFile))) {
            // Lê cada linha do arquivo e imprime no console (pode ser alterado para JOptionPane)
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            // Imprime a rastreabilidade da exceção em caso de erro de E/S
            e.printStackTrace();
        }
    }

    // Método para adicionar um lançamento aos dados da vaca
    public static void adicionarLancamento(File vacaFile) {
        // Solicita ao usuário que informe os detalhes do lançamento
        String input = JOptionPane.showInputDialog(
                "Informe o lançamento: \n- Data | Comida Kg | Valor R$ | Leite ml | Venda do Leite R$");

        // Verifica se o usuário forneceu uma entrada
        if (input != null) {
            // Divide a entrada em partes usando a vírgula como delimitador
            String[] div = input.split(", "); // Use "|" para separar os campos

            // Verifica se a entrada foi dividida corretamente em 5 partes
            if (div.length == 5) {
                try (PrintWriter writer = new PrintWriter(new FileWriter(vacaFile, true))) {
                    // Adiciona os detalhes do lançamento ao arquivo da vaca
                    writer.println("Lançamento:");
                    writer.println("Data: " + div[0].trim());
                    writer.println("Comida Kg: " + div[1].trim());
                    writer.println("Valor R$: " + div[2].trim());
                    writer.println("Leite ml: " + div[3].trim());
                    writer.println("Venda do Leite R$: " + div[4].trim());
                } catch (IOException e) {
                    // Imprime a rastreabilidade da exceção em caso de erro de E/S
                    e.printStackTrace();
                }
            } else {
                // Exibe mensagem de erro se a entrada não foi dividida corretamente em 5 partes
                JOptionPane.showMessageDialog(null, "A entrada não foi dividida corretamente em 5 partes.");
            }
        } else {
            // Exibe mensagem se a entrada for nula
            JOptionPane.showMessageDialog(null, "Entrada nula. Por favor, forneça uma entrada válida.");
        }
    }
}
