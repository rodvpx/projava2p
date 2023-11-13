package projeto_java_2p;

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
                    mensagem.append("O código: " + codigo + ", Pertence a vaca: " + VarShare.cod[index] + " | "
                            + VarShare.nome[index] + " | " + VarShare.raca[index] + " | " + VarShare.variacao[index]);
                    adicionarLancamento(index);
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

    public static void adicionarLancamento(int index) {
        String input = JOptionPane
                .showInputDialog(
                        "Informe o lançamento: \n- Data | Comida Kg | Valor R$ | Leite L | Venda do Leite R$");

        if (input != null) {
            String[] div = input.split(", "); 
            if (div.length == 5) {

                //int i = (int) (VarShare.id - 1);

                try {
                    VarShare.data[index] = Integer.parseInt(div[0].trim());
                    VarShare.comidakg[index] = Double.parseDouble(div[1].trim());
                    VarShare.custoComida[index] = Double.parseDouble(div[2].trim());
                    VarShare.leite[index] = Double.parseDouble(div[3].trim());
                    VarShare.valorVenda[index] = Double.parseDouble(div[4].trim());

                    // Incrementar a variável id para o próximo lançamento
                    // VarShare.id++;
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
}
