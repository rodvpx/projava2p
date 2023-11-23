package projeto_java_2p;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Lancamento {

    static StringBuilder mensagem = new StringBuilder();

    public static void lancamento() {

        String input = JOptionPane.showInputDialog("Informe o código da vaca:");

        if (input != null) {
            try {
                int codigo = Integer.parseInt(input);

                // Verifique se o código já existe
                int index = encontrarIndicePorCodigo(codigo);
                if (index != -1) {
                    adicionarLancamento(index, codigo);
                } else {
                    JOptionPane.showMessageDialog(null, "Vaca não encontrada.");
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

    public static void adicionarLancamento(int index, int codigo) {
        String input = JOptionPane.showInputDialog("O código digitado pertence ao seguinte cadastro:\n" +
                VarShare.cod[index] + " | " + VarShare.nome[index] + " | " + VarShare.raca[index] + " | " +
                VarShare.variacao[index] + "\n\nInforme o lançamento: \n- Data | Comida Kg |  Leite L ");
    
        if (input != null) {
            String[] div = input.split(", ");
            if (div.length == 3) {
                try {
                    VarShare.data[index] = Integer.parseInt(div[0].trim());
                    VarShare.comidakg[index] = Double.parseDouble(div[1].trim());
                    VarShare.leite[index] = Double.parseDouble(div[2].trim());
                
    
                    // Salvar os dados do lançamento em arquivo
                    salvarLancamentoEmArquivo(codigo);
    
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
    
    

    private static int encontrarIndicePorCodigo(int codigo) {
        for (int i = 0; i < VarShare.cod.length; i++) {
            if (VarShare.cod[i] == codigo) {
                return i;
            }
        }
        return -1; // Código não encontrado
    }

    public static void salvarLancamentoEmArquivo(int codigo) {
        int index = encontrarIndicePorCodigo(codigo);
    
        if (index != -1) {
            String diretorio = "lancamentos_vacas";
            File diretorioFile = new File(diretorio);
    
            try {
                if (!diretorioFile.exists()) {
                    diretorioFile.mkdirs();
                }
    
                // Criar o caminho completo do arquivo
                String caminhoArquivo = diretorio + "/lancamento_" + codigo + ".txt";
    
                // Adiciona os dados de lançamento ao arquivo, sem sobrescrever o conteúdo existente
                try (BufferedWriter w = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
                    w.newLine();  // Adiciona uma linha em branco para separar os lançamentos
                    w.write("Código: " + VarShare.cod[index]);
                    w.newLine();
                    w.write("Data: " + VarShare.data[index]);
                    w.newLine();
                    w.write("Comida Kg: " + VarShare.comidakg[index]);
                    w.newLine();
                    w.write("Custo Comida R$: " + VarShare.custoComida);
                    w.newLine();
                    w.write("Leite L: " + VarShare.leite[index]);
                    w.newLine();
                    w.write("Venda do Leite R$: " + VarShare.valorVenda);
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
    

public static void carregarLancamentos() {
    String diretorio = "lancamentos_vacas";
    File diretorioFile = new File(diretorio);

    if (diretorioFile.exists() && diretorioFile.isDirectory()) {
        File[] arquivos = diretorioFile.listFiles();

        if (arquivos != null && arquivos.length > 0) {
            for (File arquivo : arquivos) {
                if (arquivo.isFile()) {
                    lerLancamentoEAdicionar(arquivo);
                }
            }
        }
    }
}

private static void lerLancamentoEAdicionar(File arquivo) {
    try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
        String linha;
        int codigo = -1;

        while ((linha = reader.readLine()) != null) {
            String[] partes = linha.split(":");
            if (partes.length == 2) {
                switch (partes[0].trim()) {
                    case "Código":
                        codigo = Integer.parseInt(partes[1].trim());
                        break;
                    case "Data":
                        VarShare.data[encontrarIndicePorCodigo(codigo)] = Integer.parseInt(partes[1].trim());
                        break;
                    case "Comida Kg":
                        VarShare.comidakg[encontrarIndicePorCodigo(codigo)] = Double.parseDouble(partes[1].trim());
                        break;
                    case "Custo Comida R$":
                        VarShare.custoComida = Double.parseDouble(partes[1].trim());
                        break;
                    case "Leite L":
                        VarShare.leite[encontrarIndicePorCodigo(codigo)] = Double.parseDouble(partes[1].trim());
                        break;
                    case "Venda do Leite R$":
                        VarShare.valorVenda = Double.parseDouble(partes[1].trim());
                        break;
                }
            }
        }
    } catch (IOException | NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Erro ao ler o lançamento do arquivo: " + arquivo.getName());
        e.printStackTrace();
    }
}


}
