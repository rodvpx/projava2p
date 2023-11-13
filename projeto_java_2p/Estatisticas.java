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

    public static void verlancamentos() {
        StringBuilder mensagem = new StringBuilder();
        mensagem.append(
                "Data | Codigo | Nome | Raça | Variação | Comida Kg | Valor R$ | Leite ml | Venda do Leite R$\n");

        for (int i = 0; i < VarShare.id - 1; i++) {
            mensagem.append(formatarData(VarShare.data[i]) + " | " + VarShare.cod[i] + " | " + VarShare.nome[i] + " | "
                    + VarShare.raca[i] + " | " + VarShare.variacao[i] + " | " + VarShare.comidakg[i] + "Kg | R$"
                    + VarShare.custoComida[i] + " | " + VarShare.leite[i] + "ml | R$" + VarShare.valorVenda[i] + " | " + "\n");
        }

        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    public static String formatarData(int data) {
        int dia = data / 1000000;
        int mes = (data / 10000) % 100;
        int ano = data % 10000;
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    }

    public static void lancamentoDiario() {
    }

    public static void lancamentoMensal() {
    }

    public static void lancamentoAnual() {
    }
}
