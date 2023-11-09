package projeto_java_2p;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class Cadastro {

    // Caminho do diretório de armazenamento
    private static final String REPOSITORY_PATH = "repository";

    // Método para cadastrar uma vaca
    public static void cadastrarVaca() {
        // Solicita ao usuário que insira os dados da vaca
        String input = JOptionPane.showInputDialog("Informe a vaca para cadastro: \n- Código | Nome | Raça | Variação");

        if (input != null) {
            // Divide a entrada em partes usando a vírgula como delimitador
            String[] div = input.split(", ");
            // Verifica se foram fornecidas as cinco partes necessárias
            if (div.length == 4) {
                try {
                    // Extrai o código da vaca e converte para inteiro
                    int codigo = Integer.parseInt(div[0].trim());

                    // Verifica se o código já existe
                    if (codigoExistente(codigo)) {
                        // Exibe uma mensagem de erro se o código já existe
                        JOptionPane.showMessageDialog(null, "Erro: Código já existe. Escolha outro código.");
                    } else {
                        // Continua com a adição dos dados se o código for válido
                        adicionarDadosVaca(div, codigo);
                        // Exibe mensagem de sucesso
                        JOptionPane.showMessageDialog(null, "Vaca cadastrada com sucesso.");
                    }
                } catch (NumberFormatException e) {
                    // Exibe uma mensagem de erro se o código não for um número válido
                    JOptionPane.showMessageDialog(null, "Erro: Código não é um número válido.");
                }
            } else {
                // Exibe mensagem de erro se a entrada não foi dividida corretamente em 5 partes
                JOptionPane.showMessageDialog(null, "A entrada não foi dividida corretamente em 4 partes.");
            }
        } else {
            // Exibe mensagem se a entrada for nula
            JOptionPane.showMessageDialog(null, "Entrada nula. Por favor, forneça uma entrada válida.");
        }
    }

    // Método para adicionar os dados da vaca a um arquivo
    public static void adicionarDadosVaca(String[] div, int codigo) {
        // Cria um objeto File para representar o arquivo da vaca
        File vacaFile = new File(REPOSITORY_PATH + File.separator + "vaca_" + codigo + ".txt");

        try (PrintWriter writer = new PrintWriter(new FileWriter(vacaFile))) {
            // Escreve os dados da vaca no arquivo
            writer.println("Código: " + codigo);
            writer.println("Nome: " + div[1].trim());
            writer.println("Raça: " + div[2].trim());
            writer.println("Variação: " + div[3].trim());
        } catch (IOException e) {
            // Imprime a rastreabilidade da exceção em caso de erro de E/S
            e.printStackTrace();
        }
    }

    // Método para verificar se um código de vaca já existe
    public static boolean codigoExistente(int codigo) {
        // Cria um objeto File para representar o arquivo da vaca
        File vacaFile = new File(REPOSITORY_PATH + File.separator + "vaca_" + codigo + ".txt");
        // Verifica se o arquivo existe
        return vacaFile.exists();
    }
}
