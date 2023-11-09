package projeto_java_2p;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Estatisticas {

    // Caminho do diretório de armazenamento
    private static final String REPOSITORY_PATH = "repository";

    // Método para exibir os dados de cadastro de vacas
    public static void verCadastros() {
        // StringBuilder para construir a mensagem a ser exibida
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Código | Nome | Raça | Variação\n");

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
                    mensagem.append("\n");
                }
            }
        }

        // Exibe a mensagem com os dados de cadastro de vacas
        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    // Método para exibir as estatísticas de vacas (dados de cadastro + lançamentos)
    public static void verEstatisticas() {
        // StringBuilder para construir a mensagem a ser exibida
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Código | Nome | Raça | Variação\n");

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
                    mensagem.append("\nLançamentos:\n");
                    // Lê os lançamentos da vaca e adiciona à mensagem
                    lerLancamentos(file, mensagem);
                    mensagem.append("\n");
                }
            }
        }

        // Exibe a mensagem com as estatísticas de vacas
        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    // Método privado para ler os dados de uma vaca de um arquivo
    private static void lerDadosVaca(File vacaFile, StringBuilder mensagem) {
        try (Scanner scanner = new Scanner(new FileReader(vacaFile))) {
            // Itera até encontrar a linha "Lançamento:"
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("Lançamento:")) {
                    break;
                }
                // Adiciona os dados de cadastro à mensagem
                String[] parts = line.split(":");
                if (parts.length > 1) {
                    mensagem.append(parts[1].trim()).append(" | ");
                } else {
                    // Lidar com o caso em que não há ':' na linha (mensagem de aviso, por exemplo)
                    mensagem.append("Aviso: Dados ausentes").append(" | ");
                }
            }
        } catch (IOException e) {
            // Imprime a rastreabilidade da exceção em caso de erro de E/S
            e.printStackTrace();
        }
    }

    // Método privado para ler os lançamentos de uma vaca de um arquivo
    private static void lerLancamentos(File vacaFile, StringBuilder mensagem) {
        try (Scanner scanner = new Scanner(new FileReader(vacaFile))) {
            boolean isLancamentoSection = false;

            // Itera até encontrar o próximo dado
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.equals("Lançamento:")) {
                    // Adiciona uma quebra de linha antes de cada novo lançamento
                    if (isLancamentoSection) {
                        mensagem.append("\n");
                    }
                    isLancamentoSection = true;
                    continue;
                }

                if (isLancamentoSection) {
                    // Adiciona os dados de lançamento à mensagem com espaçamento fixo
                    String[] parts = line.split(":");
                    if (parts.length > 1) {
                        mensagem.append(String.format("%-10s", parts[1].trim())).append(" | ");
                    } else {
                        // Lidar com o caso em que não há ':' na linha (mensagem de aviso, por exemplo)
                        mensagem.append("Aviso: Dados ausentes").append(" | ");
                    }
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
