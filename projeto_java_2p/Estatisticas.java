package projeto_java_2p;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Estatisticas {

    // Caminho do diretório de armazenamento
    private static final String REPOSITORY_PATH = "repository";

    // Método para exibir os cadastros de vacas
    public static void verCadastros() {
        // StringBuilder para construir a mensagem a ser exibida
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Data | Codigo | Nome | Raça | Variação\n");

        // Obtém a lista de arquivos no diretório de repositório
        File repositoryDir = new File(REPOSITORY_PATH);
        File[] files = repositoryDir.listFiles();

        // Verifica se existem arquivos no diretório
        if (files != null) {
            // Itera sobre os arquivos no diretório
            for (File file : files) {
                // Verifica se o arquivo é um arquivo de vaca
                if (file.isFile() && file.getName().startsWith("vaca_")) {
                    // Lê os dados da vaca e adiciona à mensagem
                    lerDadosVaca(file, mensagem);
                }
            }
        }

        // Exibe a mensagem com os cadastros de vacas
        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    // Método para exibir os lançamentos de vacas
    public static void verLancamentos() {
        // StringBuilder para construir a mensagem a ser exibida
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Data | Codigo | Nome | Raça | Variação | Comida Kg | Valor R$ | Leite ml | Venda do Leite R$\n");

        // Obtém a lista de arquivos no diretório de repositório
        File repositoryDir = new File(REPOSITORY_PATH);
        File[] files = repositoryDir.listFiles();

        // Verifica se existem arquivos no diretório
        if (files != null) {
            // Itera sobre os arquivos no diretório
            for (File file : files) {
                // Verifica se o arquivo é um arquivo de vaca
                if (file.isFile() && file.getName().startsWith("vaca_")) {
                    // Lê os dados da vaca e adiciona à mensagem
                    lerDadosVaca(file, mensagem);
                    // Lê os lançamentos da vaca e adiciona à mensagem
                    lerLancamentos(file, mensagem);
                }
            }
        }

        // Exibe a mensagem com os lançamentos de vacas
        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    // Método privado para ler os dados de uma vaca de um arquivo
    private static void lerDadosVaca(File vacaFile, StringBuilder mensagem) {
        try (Scanner scanner = new Scanner(new FileReader(vacaFile))) {
            // Lê cada linha do arquivo e adiciona à mensagem
            while (scanner.hasNextLine()) {
                mensagem.append(scanner.nextLine()).append("\n");
            }
        } catch (IOException e) {
            // Imprime a rastreabilidade da exceção em caso de erro de E/S
            e.printStackTrace();
        }
    }

    // Método privado para ler os lançamentos de uma vaca de um arquivo
    private static void lerLancamentos(File vacaFile, StringBuilder mensagem) {
        try (Scanner scanner = new Scanner(new FileReader(vacaFile))) {
            // Lê cada linha do arquivo e identifica os lançamentos
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("Lançamento:")) {
                    // Adiciona a marca de início de lançamento à mensagem
                    mensagem.append("Lançamento:\n");
                    // Adiciona as linhas seguintes (dados do lançamento) à mensagem
                    mensagem.append(scanner.nextLine()).append("\n");
                    mensagem.append(scanner.nextLine()).append("\n");
                    mensagem.append(scanner.nextLine()).append("\n");
                    mensagem.append(scanner.nextLine()).append("\n");
                    mensagem.append(scanner.nextLine()).append("\n");
                }
            }
        } catch (IOException e) {
            // Imprime a rastreabilidade da exceção em caso de erro de E/S
            e.printStackTrace();
        }
    }

    // Método para formatar a data no formato "dd/MM/yyyy"
    public static String formatarData(int data) {
        int dia = data / 1000000;
        int mes = (data / 10000) % 100;
        int ano = data % 10000;
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    }
}
