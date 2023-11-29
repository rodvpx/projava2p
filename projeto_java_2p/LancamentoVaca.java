package projeto_java_2p;

// Classe que representa os lançamentos de uma vaca.
public class LancamentoVaca {

    // Número máximo de produtos (lançamentos) permitido para cada vaca.
    static int maxProdutos = 1000;

    // Arrays para armazenar os dados de cada lançamento.
    public int[] data = new int[maxProdutos]; // Data de cada lançamento.
    public double[] comidakg = new double[maxProdutos]; // Quantidade de comida (em Kg) consumida em cada lançamento.

    // Custo padrão da comida por Kg (pode ser modificado conforme necessidade).
    public double custoComida = 0.50;

    public double[] leite = new double[maxProdutos]; // Quantidade de leite produzida em cada lançamento.

    // Valor padrão de venda do leite por litro (pode ser modificado conforme
    // necessidade).
    public double valorVenda = 2.69;
}
