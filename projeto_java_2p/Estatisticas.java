package projeto_java_2p;

import javax.swing.JOptionPane;

public class Estatisticas {

    public static void verCadastros() {
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Codigo | Nome | Raça | Variação\n");

        for (int i = 0; i < VarShare.id - 1; i++) {
            mensagem.append(VarShare.cod[i] + " | " + VarShare.nome[i] + " | "
                    + VarShare.raca[i] + " | " + VarShare.variacao[i] + "\n");
        }

        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    public static void lancamentoDiario() {

        StringBuilder mensagem = new StringBuilder();

        double quantComida = 0, custoComida = 0, leite = 0, valorVenda = 0;

        int data = Integer.parseInt(JOptionPane.showInputDialog("Informe a data (dd/mm/yyyy):"));

        mensagem.append(
                "Data | Codigo | Nome | Raça | Variação | Comida Kg | Custo Comida R$ | Leite L | Venda do Leite R$\n");

        for (int i = 0; i < VarShare.id - 1; i++) {
            if (data == VarShare.data[i]) {
                mensagem.append(formatarData(data)).append(" | " + VarShare.cod[i] + " | ")
                        .append(VarShare.nome[i] + " | ").append(VarShare.raca[i] + " | ")
                        .append(VarShare.variacao[i] + " | ").append(VarShare.comidakg[i] + " Kg | R$ ")
                        .append(VarShare.custoComida + " | ").append(VarShare.leite[i] + " L | R$ ")
                        .append(VarShare.valorVenda + "\n");

                quantComida += VarShare.comidakg[i];
                custoComida = VarShare.custoComida;
                leite += VarShare.leite[i];
                valorVenda = VarShare.valorVenda;
            }
        }

        double custo = quantComida * custoComida;
        double venda = leite * valorVenda;
        double balanco = venda - custo;

        mensagem.append(
                "\n Qt Comida Total | Valor Custo | Custo Comida Total | Produção de Leite Total | Valor Venda Leite | Balanço\n")
                .append((quantComida) + " Kg | R$ ").append((custoComida) + " | R$ ").append((custo) + " | ")
                .append((leite) + " L | R$ ").append((valorVenda) + "  | R$ ").append((balanco) + " | ");

        JOptionPane.showMessageDialog(null, mensagem.toString());

    }

    public static void lancamentoMensal() {
    StringBuilder mensagem = new StringBuilder();

    double quantComida = 0, custoComida = 0, leite = 0, valorVenda = 0;

    int data = Integer.parseInt(JOptionPane.showInputDialog("Informe a data (mm/yyyy):"));

    int dataMes = (data % 10000) / 100;
    int dataAno = data % 100;

    mensagem.append("Data | Codigo | Nome | Raça | Variação | Comida Kg | Custo Comida R$ | Leite L | Venda do Leite R$\n");

    for (int i = 0; i < VarShare.id - 1; i++) {
        int lancamentoMes = (VarShare.data[i] % 10000) / 100;
        int lancamentoAno = VarShare.data[i] % 100;
        
        System.out.println("Vacina: " + VarShare.cod[i] + " Data: " + VarShare.data[i]);

        if (lancamentoMes == dataMes && lancamentoAno == dataAno) {
            mensagem.append(formatarData(VarShare.data[i])).append(" | " + VarShare.cod[i] + " | ")
                    .append(VarShare.nome[i] + " | ").append(VarShare.raca[i] + " | ")
                    .append(VarShare.variacao[i] + " | ").append(VarShare.comidakg[i] + " Kg | R$ ")
                    .append(VarShare.custoComida + " | ").append(VarShare.leite[i] + " L | R$ ")
                    .append(VarShare.valorVenda + "\n");

            quantComida += VarShare.comidakg[i];
            custoComida = VarShare.custoComida;
            leite += VarShare.leite[i];
            valorVenda = VarShare.valorVenda;
        }
    }

    double custo = quantComida * custoComida;
    double venda = leite * valorVenda;
    double balanco = venda - custo;

    mensagem.append(
            "\n Qt Comida Total | Valor Custo | Custo Comida Total | Produção de Leite Total | Valor Venda Leite | Balanço\n")
            .append((quantComida) + " Kg | R$ ").append((custoComida) + " | R$ ").append((custo) + " | ")
            .append((leite) + " L | R$ ").append((valorVenda) + "  | R$ ").append((balanco) + " | ");

    JOptionPane.showMessageDialog(null, mensagem.toString());
}


    public static void lancamentoAnual() {
    }

    public static String formatarData(int data) {
        int dia = data / 1000000;
        int mes = (data / 10000) % 100;
        int ano = data % 10000;
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    }
}