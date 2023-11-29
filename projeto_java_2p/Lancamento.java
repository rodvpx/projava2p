package projeto_java_2p;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Lancamento {

    // StringBuilder para armazenar mensagens (não está sendo usado no código
    // fornecido)
    static StringBuilder mensagem = new StringBuilder();

    // Método para realizar o lançamento de dados relacionados a uma vaca
    public static void lancamento() {

        // Solicita o código da vaca para o lançamento
        String input = JOptionPane.showInputDialog("Informe o código da vaca:");

        if (input != null) {
            try {
                int codigo = Integer.parseInt(input);

                // Verifica se o código já existe
                int index = encontrarIndicePorCodigo(codigo);
                if (index != -1) {
                    // Adiciona o lançamento relacionado à vaca
                    adicionarLancamento(index, codigo);
                } else {
                    JOptionPane.showMessageDialog(null, "Vaca não encontrada.");
                    // Permite ao usuário fazer uma nova consulta
                    String opcao = JOptionPane.showInputDialog("Deseja fazer uma nova consulta? (s/n)");
                    if (opcao != null && opcao.equalsIgnoreCase("s")) {
                        lancamento();
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: Código não é um número válido.");
            }
        }
    }

    // Método para adicionar um lançamento relacionado a uma vaca
    public static void adicionarLancamento(int index, int codigo) {
        // Solicita informações do lançamento ao usuário
        String input = JOptionPane.showInputDialog("O código digitado pertence ao seguinte cadastro:\n" +
                VarShare.cod[index] + " | " + VarShare.nome[index] + " | " + VarShare.raca[index] + " | " +
                VarShare.variacao[index] + "\n\nInforme o lançamento: \n- Data | Comida Kg | Leite L ");

        if (input != null) {
            // Divide as informações inseridas pelo usuário
            String[] div = input.split(", ");

            // Verifica se as informações foram inseridas corretamente
            if (div.length == 3) {
                try {
                    // Inicializa a estrutura de dados para o lançamento, se ainda não estiver
                    if (VarShare.lancamento[index] == null)
                        VarShare.lancamento[index] = new LancamentoVaca();

                    // Preenche os dados do lançamento
                    VarShare.lancamento[index].data[VarShare.numL[index]] = Integer.parseInt(div[0].trim());
                    VarShare.lancamento[index].comidakg[VarShare.numL[index]] = Double.parseDouble(div[1].trim());
                    VarShare.lancamento[index].leite[VarShare.numL[index]] = Double.parseDouble(div[2].trim());

                    // Salva os dados do lançamento em arquivo
                    salvarLancamentoEmArquivo(codigo);
                    VarShare.numL[index]++;

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro: Valores inválidos.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "A entrada não foi dividida corretamente em 5 partes.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Entrada nula. Por favor, forneça uma entrada válida.");
        }
    }

    // Método para encontrar o índice de uma vaca no vetor pelo código
    private static int encontrarIndicePorCodigo(int codigo) {
        for (int i = 0; i < VarShare.cod.length; i++) {
            if (VarShare.cod[i] == codigo) {
                return i;
            }
        }
        return -1; // Código não encontrado
    }

    // Método para salvar os dados do lançamento em arquivo
    public static void salvarLancamentoEmArquivo(int codigo) {
        int index = encontrarIndicePorCodigo(codigo);

        if (index != -1) {
            String diretorio = "lancamentos_vacas";
            File diretorioFile = new File(diretorio);

            try {
                if (!diretorioFile.exists()) {
                    diretorioFile.mkdirs();
                }

                // Cria o caminho completo do arquivo
                String caminhoArquivo = diretorio + "/lancamento_" + codigo + ".txt";

                // Adiciona os dados de lançamento ao arquivo, sem sobrescrever o conteúdo
                // existente
                try (BufferedWriter w = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {

                    w.newLine(); // Adiciona uma linha em branco para separar os lançamentos
                    w.write("Código: " + VarShare.cod[index]);
                    w.newLine();
                    w.write("Data: " + VarShare.lancamento[index].data[VarShare.numL[index]]);
                    w.newLine();
                    w.write("Comida Kg: " + VarShare.lancamento[index].comidakg[VarShare.numL[index]]);
                    w.newLine();
                    w.write("Custo Comida R$: " + VarShare.lancamento[index].custoComida);
                    w.newLine();
                    w.write("Leite L: " + VarShare.lancamento[index].leite[VarShare.numL[index]]);
                    w.newLine();
                    w.write("Venda do Leite R$: " + VarShare.lancamento[index].valorVenda);
                    w.newLine();

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar o lançamento em arquivo: " + e.getMessage());
                    e.printStackTrace();
                }
            } catch (SecurityException e) {
                JOptionPane.showMessageDialog(null, "Erro de permissão ao criar o diretório: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vaca não encontrada para salvar o lançamento.");
        }
    }

    // Método para carregar os lançamentos a partir de arquivos
    public static void carregarLancamentos() {
        String diretorio = "lancamentos_vacas";
        File diretorioFile = new File(diretorio);

        if (diretorioFile.exists() && diretorioFile.isDirectory()) {
            File[] arquivos = diretorioFile.listFiles();

            if (arquivos != null && arquivos.length > 0) {
                for (File arquivo : arquivos) {
                    if (arquivo.isFile()) {
                        // Lê os lançamentos do arquivo e adiciona aos vetores
                        lerLancamentoEAdicionar(arquivo);
                    }
                }
            }
        }
    }

    // Método para ler os dados de lançamento de um arquivo e adicionar aos vetores
    private static void lerLancamentoEAdicionar(File arquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            int codigo = -1;
            int index = -1;
            int lancamento = -1;
            boolean encontrou = false;

            // Lê cada linha do arquivo
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(":");
                if (partes.length == 2) {
                    switch (partes[0].trim()) {
                        case "Código":
                            codigo = Integer.parseInt(partes[1].trim());
                            index = encontrarIndicePorCodigo(codigo);
                            lancamento++;
                            if (!encontrou) {
                                encontrou = true;
                                VarShare.lancamento[index] = new LancamentoVaca();
                            }
                            break;
                        case "Data":
                            VarShare.lancamento[index].data[lancamento] = Integer.parseInt(partes[1].trim());
                            break;
                        case "Comida Kg":
                            VarShare.lancamento[index].comidakg[lancamento] = Double.parseDouble(partes[1].trim());
                            break;
                        case "Custo Comida R$":
                            VarShare.lancamento[index].custoComida = Double.parseDouble(partes[1].trim());
                            break;
                        case "Leite L":
                            VarShare.lancamento[index].leite[lancamento] = Double.parseDouble(partes[1].trim());
                            break;
                        case "Venda do Leite R$":
                            VarShare.lancamento[index].valorVenda = Double.parseDouble(partes[1].trim());
                            break;
                    }
                }
            }

            VarShare.numL[index] = lancamento + 1;

        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o lançamento do arquivo: " + arquivo.getName());
            e.printStackTrace();
        }
    }
}
