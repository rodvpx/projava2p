package projeto_java_2p;

import javax.swing.JOptionPane;

public class Estoque {

    public static void cadastroProduto() {

        String input = JOptionPane.showInputDialog(
                "Informe o produto para Cadastro: (separado por , )\n- Tipo | Marca | Variação | Quantidade | Valor Custo | Valor Venda");

        String div[] = input.split(",");

        if (div.length == 6) {
            if (VarShare.id >= VarShare.maxProdutos) {
                // Os vetores atingiram o tamanho máximo; crie novos vetores maiores
                int newMaxProdutos = VarShare.maxProdutos * 2; // Dobre o tamanho
                long[] newProdutoId = new long[newMaxProdutos];
                String[] newTipo = new String[newMaxProdutos];
                String[] newMarca = new String[newMaxProdutos];
                String[] newVariacao = new String[newMaxProdutos];
                int[] newQuantidade = new int[newMaxProdutos];
                double[] newValorCusto = new double[newMaxProdutos];
                double[] newValorVenda = new double[newMaxProdutos];

                // Copie os dados do vetor antigo para o novo
                System.arraycopy(VarShare.produtoId, 0, newProdutoId, 0, VarShare.maxProdutos);
                System.arraycopy(VarShare.tipo, 0, newTipo, 0, VarShare.maxProdutos);
                System.arraycopy(VarShare.marca, 0, newMarca, 0, VarShare.maxProdutos);
                System.arraycopy(VarShare.variacao, 0, newVariacao, 0, VarShare.maxProdutos);
                System.arraycopy(VarShare.quantidade, 0, newQuantidade, 0, VarShare.maxProdutos);
                System.arraycopy(VarShare.valorCusto, 0, newValorCusto, 0, VarShare.maxProdutos);
                System.arraycopy(VarShare.valorVenda, 0, newValorVenda, 0, VarShare.maxProdutos);

                // Atualize os vetores para os novos
                VarShare.maxProdutos = newMaxProdutos;
                VarShare.produtoId = newProdutoId;
                VarShare.tipo = newTipo;
                VarShare.marca = newMarca;
                VarShare.variacao = newVariacao;
                VarShare.quantidade = newQuantidade;
                VarShare.valorCusto = newValorCusto;
                VarShare.valorVenda = newValorVenda;
            }
            
            int i = (int) (VarShare.id - 1);
            VarShare.produtoId[i] = VarShare.id;
            VarShare.tipo[i] = div[0];
            VarShare.marca[i] = div[1];
            VarShare.variacao[i] = div[2];
            VarShare.quantidade[i] = Integer.parseInt(div[3]);
            VarShare.valorCusto[i] = Double.parseDouble(div[4]);
            VarShare.valorVenda[i] = Double.parseDouble(div[5]);
            VarShare.id++;

        } else {
            JOptionPane.showConfirmDialog(null, "A entrada não foi dividida corretamente em 6 partes.");
        }

    }

    public static void verEstoque() {
        StringBuilder mensagem = new StringBuilder();

        mensagem.append("Tipo | Marca | Variação | Quantidade | Valor Custo | Valor Venda | % Lucro\n");

        for (int i = 0; i < VarShare.id - 1; i++) {
            mensagem.append(VarShare.tipo[i] + " | " + VarShare.marca[i] + " | " + VarShare.variacao[i] + " | " + VarShare.quantidade[i] + " | "
                    + VarShare.valorCusto[i] + " | " + VarShare.valorVenda[i] + " | " + margemLucro(VarShare.valorCusto[i], VarShare.valorVenda[i]) + "\n");
        }

        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    public static double margemLucro(double custo, double venda) {
        double faturamento = custo + venda;
        double lucro = (venda - custo) / faturamento;
        return lucro;
    }
}
