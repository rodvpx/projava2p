package projeto_java_2p;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Cadastro {

    public static void cadastro() {
        String input = JOptionPane
                .showInputDialog("Informe a vaca para cadastro: \n- Código | Nome | Raça | Variação");

        if (input != null) {
            String[] div = input.split(", ");
            if (div.length == 4) {
                try {
                    int codigo = Integer.parseInt(div[0].trim());

                    // Verifique se o código já existe
                    if (codigoExistente(codigo)) {
                        JOptionPane.showMessageDialog(null, "Erro: Código já existe. Escolha outro código.");
                    } else {
                        // Continue com a adição dos dados se o código for válido
                        adicionarDadosVaca(div);
                        salvarCadastroEmArquivo(codigo, div);
                        JOptionPane.showMessageDialog(null, "Vaca cadastrada com sucesso.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro: Código não é um número válido.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "A entrada não foi dividida corretamente em 4 partes.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Entrada nula. Por favor, forneça uma entrada válida.");
        }
    }

    public static boolean codigoExistente(int codigo) {
        // Verifique se o código já existe no array de códigos
        for (int i = 0; i < VarShare.cod.length; i++) {
            if (VarShare.cod[i] == codigo) {
                return true;
            }
        }
        return false;
    }

    private static void adicionarDadosVaca(String[] div) {

        int i = (int) (VarShare.id - 1);

        try {
            VarShare.cod[i] = Integer.parseInt(div[0].trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: Código não é um número válido.");
            return;
        }

        VarShare.nome[i] = div[1].trim();
        VarShare.raca[i] = div[2].trim();
        VarShare.variacao[i] = div[3].trim();

        // Incrementar a variável id para o próximo cadastro
        VarShare.id++;
    }

    private static void salvarCadastroEmArquivo(int codigo, String[] div) {
        // Criar o diretório se não existir
        String diretorio = "cadastros_vacas";
        File diretorioFile = new File(diretorio);
        
        try {
            if (!diretorioFile.exists()) {
                diretorioFile.mkdirs();
            }
    
            // Criar o caminho completo do arquivo
            String caminhoArquivo = diretorio + "/vaca_" + codigo + ".txt";
    
            try (BufferedWriter w = new BufferedWriter(new FileWriter(caminhoArquivo))) {
                w.write("Código: " + div[0].trim());
                w.newLine();
                w.write("Nome: " + div[1].trim());
                w.newLine();
                w.write("Raça: " + div[2].trim());
                w.newLine();
                w.write("Variação: " + div[3].trim());
                w.newLine();
                
                JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso em: " + caminhoArquivo);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar o cadastro em arquivo: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SecurityException e) {
            JOptionPane.showMessageDialog(null, "Erro de permissão ao criar o diretório: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void listarCadastros() {
        // Verifique se o diretório de cadastros existe
        String diretorio = "cadastros_vacas";
        File diretorioFile = new File(diretorio);

        if (diretorioFile.exists() && diretorioFile.isDirectory()) {
            // Obtenha a lista de arquivos no diretório
            File[] arquivos = diretorioFile.listFiles();

            if (arquivos != null && arquivos.length > 0) {
                StringBuilder cadastrosFormatados = new StringBuilder();

                // Adicione o cabeçalho
                cadastrosFormatados.append("Código | Nome | Raça | Variação\n");

                for (File arquivo : arquivos) {
                    if (arquivo.isFile()) {
                        // Leia as informações do arquivo e adicione à string formatada
                        String infoCadastro = lerCadastro(arquivo);
                        cadastrosFormatados.append(infoCadastro).append("\n");
                    }
                }

                // Exiba os cadastros formatados usando JOptionPane
                JOptionPane.showMessageDialog(null, "Cadastros:\n" + cadastrosFormatados.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Não há cadastros disponíveis.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Diretório de cadastros não encontrado.");
        }
    }

    private static String lerCadastro(File arquivo) {
        StringBuilder infoCadastro = new StringBuilder();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                // Adicione cada linha do arquivo à string formatada
                infoCadastro.append(linha).append("\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o cadastro do arquivo: " + arquivo.getName());
            e.printStackTrace();
        }
    
        return formatarCadastro(infoCadastro.toString());
    }
    
    private static String formatarCadastro(String cadastro) {
        // Modificar conforme necessário para o formato específico do seu arquivo
        String[] linhas = cadastro.split("\n");
        StringBuilder cadastroFormatado = new StringBuilder();
    
        for (String linha : linhas) {
            String[] partes = linha.split(":");
            if (partes.length == 2) {
                cadastroFormatado.append(partes[1].trim()).append(" | ");
            }
        }
    
        // Remover o último " | " se existir
        if (cadastroFormatado.length() > 3) {
            cadastroFormatado.setLength(cadastroFormatado.length() - 3);
        }
    
        return cadastroFormatado.toString();
    }
        
}   
/*Código | Nome | Raça | Variação
5554 | Mimosa | Holandesa | Marrom
6969 | Fatue | Holandesa | Preta */