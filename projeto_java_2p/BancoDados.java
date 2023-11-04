package projeto_java_2p;

import javax.swing.JOptionPane;

public class BancoDados {

    public static void lancamento() {
        String input = JOptionPane.showInputDialog(
                "Informe o produto para Cadastro: (separado por , )\n- Data | Codigo | Nome | Raça | Variação | Quantidade Comida kg | Valor Kg | Produção de Leite L | Valor L");

        if (input != null) {
            String[] div = input.split(",");
            if (div.length == 9) {
                if (VarShare.id >= VarShare.cod.length) {
                    // Crie novos arrays com tamanhos maiores
                    int newMaxProdutos = VarShare.cod.length * 2;
                    int[] newData = new int[newMaxProdutos];
                    int[] newCod = new int[newMaxProdutos];
                    String[] newNome = new String[newMaxProdutos];
                    String[] newRaca = new String[newMaxProdutos];
                    String[] newVariacao = new String[newMaxProdutos];
                    double[] newComidaKg = new double[newMaxProdutos];
                    double[] newCustoComida = new double[newMaxProdutos];
                    double[] newLeite = new double[newMaxProdutos];
                    double[] newValorVenda = new double[newMaxProdutos];

                    // Copie os dados do vetor antigo para o novo
                    System.arraycopy(VarShare.data, 0, newData, 0, VarShare.cod.length);
                    System.arraycopy(VarShare.cod, 0, newCod, 0, VarShare.cod.length);
                    System.arraycopy(VarShare.nome, 0, newNome, 0, VarShare.nome.length);
                    System.arraycopy(VarShare.raca, 0, newRaca, 0, VarShare.cod.length);
                    System.arraycopy(VarShare.variacao, 0, newVariacao, 0, VarShare.cod.length);
                    System.arraycopy(VarShare.comidakg, 0, newComidaKg, 0, VarShare.cod.length);
                    System.arraycopy(VarShare.custoComida, 0, newCustoComida, 0, VarShare.cod.length);
                    System.arraycopy(VarShare.leite, 0, newLeite, 0, VarShare.cod.length);
                    System.arraycopy(VarShare.valorVenda, 0, newValorVenda, 0, VarShare.cod.length);

                    // Atualize os vetores para os novos
                    VarShare.maxProdutos = newMaxProdutos;
                    VarShare.data = newData;
                    VarShare.cod = newCod;
                    VarShare.nome = newNome;
                    VarShare.raca = newRaca;
                    VarShare.variacao = newVariacao;
                    VarShare.comidakg = newComidaKg;
                    VarShare.custoComida = newCustoComida;
                    VarShare.leite = newLeite;
                    VarShare.valorVenda = newValorVenda;
                }

                int i = (int) (VarShare.id - 1);
                try {
                    VarShare.cod[i] = Integer.parseInt(div[0]);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro: Código não é um número válido.");
                    return;
                }
                VarShare.data[i] = Integer.parseInt(div[1]);
                VarShare.nome[i] = div[2];
                VarShare.raca[i] = div[3];
                VarShare.variacao[i] = div[4];
                VarShare.comidakg[i] = Double.parseDouble(div[5]);
                VarShare.custoComida[i] = Double.parseDouble(div[6]);
                VarShare.leite[i] = Double.parseDouble(div[7]);
                VarShare.valorVenda[i] = Double.parseDouble(div[8]);
                VarShare.id++;

            } else {
                JOptionPane.showMessageDialog(null, "A entrada não foi dividida corretamente em 9 partes.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Entrada nula. Por favor, forneça uma entrada válida.");
        }
    }

    public static void verDados() {
        StringBuilder mensagem = new StringBuilder();

        mensagem.append(
                "Data | Codigo | Nome | Raça | Variação | Quantidade Comida kg | Valor Kg | Produção de Leite L | Valor L\n");

        for (int i = 0; i < VarShare.id - 1; i++) {

            //String dataFormatada = formatarData();
            mensagem.append(formatarData() + " | " + VarShare.cod[i] + " | " + VarShare.nome[i] + " | "
                    + VarShare.raca[i] + " | " + VarShare.variacao[i] + " | " + VarShare.comidakg[i] + "Kg | R$"
                    + VarShare.custoComida[i] + " | " + (VarShare.leite[i] + "ml | R$" + VarShare.valorVenda[i] + "\n"));
        }

        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    public static String formatarData() {
        
        int data = 0;

        for (int i = 0; i < VarShare.id - 1; i++) {

            data = VarShare.data[i];

        }

        int dia = data / 1000000;
        int mes = (data / 10000) % 100;
        int ano = data % 10000;

        String dataFormatada = dia + "/" + mes + "/" + ano;

        return dataFormatada;

    }

}
