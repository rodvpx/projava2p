package projeto_java_2p;

public class VarShare {
    /* como vou utilizar as mesmas variaveis em varios arquivos no decorrer do projeto
    tive que instanciar as variaveis em um arquivo separado para poder puxar elas nos arquivos */
    public static long id = 1;
    public static int maxProdutos = 1;
    public static long[] produtoId = new long[maxProdutos];
    public static String[] tipo = new String[maxProdutos];
    public static String[] marca = new String[maxProdutos];
    public static String[] variacao = new String[maxProdutos];
    public static int[] quantidade = new int[maxProdutos];
    public static double[] valorCusto = new double[maxProdutos];
    public static double[] valorVenda = new double[maxProdutos];
}
