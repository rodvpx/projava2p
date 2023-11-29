package projeto_java_2p;

// Classe responsável por armazenar variáveis compartilhadas entre diferentes partes do programa.
public class VarShare {

    // Identificador único para cada vaca cadastrada.
    public static long id = 1;

    // Número máximo de produtos (vagas para vacas) permitido no sistema.
    public static int maxProdutos = 1000;

    // Arrays para armazenar informações das vacas.
    public static int[] cod = new int[maxProdutos]; // Código de cada vaca.
    public static String[] nome = new String[maxProdutos]; // Nome de cada vaca.
    public static String[] raca = new String[maxProdutos]; // Raça de cada vaca.
    public static String[] variacao = new String[maxProdutos]; // Variação de cada vaca.
    public static int[] numL = new int[maxProdutos]; // Número de lançamentos para cada vaca.

    // Array para armazenar os lançamentos relacionados a cada vaca.
    public static LancamentoVaca[] lancamento = new LancamentoVaca[maxProdutos];
}
