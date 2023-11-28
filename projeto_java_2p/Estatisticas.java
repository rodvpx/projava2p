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

        int dataDia = data % 100;
        int dataMes = (data % 10000) / 100;
        int dataAno = data / 10000;

        mensagem.append(
                "| Codigo | Nome | Raça | Variação |\n");

        for (int i = 0; i < VarShare.id - 1; i++) {
            mensagem.append(" | " + VarShare.cod[i] + " | ")
                    .append(VarShare.nome[i] + " | ").append(VarShare.raca[i] + " | ")
                    .append(VarShare.variacao[i] + "\n");
            mensagem.append("      Data | Comida Kg | Custo Comida R$ | Leite L | Venda do Leite R$ |\n");

            for (int j = 0; j < VarShare.numL[i]; j++) {

                int lancamentoDia = VarShare.lancamento[i].data[j] % 100;
                int lancamentoMes = (VarShare.lancamento[i].data[j] % 10000) / 100;
                int lancamentoAno = VarShare.lancamento[i].data[j] / 10000;

                if (lancamentoDia == dataDia && lancamentoMes == dataMes && lancamentoAno == dataAno) {
                    mensagem.append("      ");
                    mensagem.append(formatarData(VarShare.lancamento[i].data[j]) + " | ")
                            .append(VarShare.lancamento[i].comidakg[j] + " Kg | R$ ")
                            .append(VarShare.lancamento[i].custoComida + " | ")
                            .append(VarShare.lancamento[i].leite[j] + " L | R$ ")
                            .append(VarShare.lancamento[i].valorVenda + "\n");

                    quantComida += VarShare.lancamento[i].comidakg[j];
                    custoComida = VarShare.lancamento[i].custoComida;
                    leite += VarShare.lancamento[i].leite[j];
                    valorVenda = VarShare.lancamento[i].valorVenda;
                } else
                    mensagem.append("");
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
        StringBuilder mensagemVaca = new StringBuilder();
        StringBuilder mensagem = new StringBuilder();


        double quantComida = 0, custoComida = 0, leite = 0, valorVenda = 0;

        int data = Integer.parseInt(JOptionPane.showInputDialog("Informe a data (mm/yyyy):"));
        int dataMes = data / 10000;
        int dataAno = data % 10000;

        for (int i = 0; i < VarShare.id - 1; i++) {
            StringBuilder mensagemLancs = new StringBuilder();
            for (int j = 0; j < VarShare.numL[i]; j++) {
                int semDia = VarShare.lancamento[i].data[j] % 1000000;
                int lancamentoMes = semDia / 10000;
                int lancamentoAno = semDia % 10000;

                if (lancamentoMes == dataMes && lancamentoAno == dataAno) {

                    mensagemLancs.append(formatarData(VarShare.lancamento[i].data[j]) + " | ");

                mensagemLancs.append(" | " + VarShare.cod[i] + " | ")
                    .append(VarShare.nome[i] + " | ").append(VarShare.raca[i] + " | ")
                    .append(VarShare.variacao[i])
                            .append(VarShare.lancamento[i].comidakg[j] + " Kg | R$ ")
                            .append(VarShare.lancamento[i].custoComida + " | ")
                            .append(VarShare.lancamento[i].leite[j] + " L | R$ ")
                            .append(VarShare.lancamento[i].valorVenda + "\n");

                    quantComida += VarShare.lancamento[i].comidakg[j];
                    custoComida = VarShare.lancamento[i].custoComida;
                    leite += VarShare.lancamento[i].leite[j];
                    valorVenda = VarShare.lancamento[i].valorVenda;
                }
            }

            if (!mensagemLancs.toString().equals("")) {
                mensagemVaca.append(" Data | Codigo | Nome | Raça | Variação |");
                mensagemVaca.append(" Comida Kg | Custo Comida R$ | Leite L | Venda do Leite R$ |\n");
                
                mensagemVaca.append(mensagemLancs + "\n");
            }
        }


        double custo = quantComida * custoComida;
        double venda = leite * valorVenda;
        double balanco = venda - custo;

        if (mensagemVaca.toString().equals("")) {
            mensagem.append("Nenhum lançamento encontrado para o mês informado.\n");
        }else{
        mensagem.append(
                "\n Qt Comida Total | Valor Custo | Custo Comida Total | Produção de Leite Total | Valor Venda Leite | Balanço\n")
                .append((quantComida) + " Kg | R$ ").append((custoComida) + " | R$ ").append((custo) + " | ")
                .append((leite) + " L | R$ ").append((valorVenda) + "  | R$ ").append((balanco) + " | ");
        mensagem.append("\n" + mensagemVaca);
        }

        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    public static void lancamentoAnual() {
        StringBuilder mensagem = new StringBuilder();

        int ano = Integer.parseInt(JOptionPane.showInputDialog("Informe o ano (yyyy):"));

        mensagem.append("| Codigo | Nome | Raça | Variação |\n");

        boolean encontrouLancamentos = false;

        for (int i = 0; i < VarShare.id; i++) {
            mensagem.append(" | " + VarShare.cod[i] + " | ")
                    .append(VarShare.nome[i] + " | ").append(VarShare.raca[i] + " | ")
                    .append(VarShare.variacao[i] + "\n");

            mensagem.append("      Data | Comida Kg | Custo Comida R$ | Leite L | Venda do Leite R$ |\n");

            boolean encontrouLancamentosIndividuais = false;

            for (int j = 0; j < VarShare.numL[i]; j++) {
                int lancamentoAno = VarShare.lancamento[i].data[j] % 10000;

                if (lancamentoAno == ano) {
                    encontrouLancamentos = true;
                    encontrouLancamentosIndividuais = true;

                    mensagem.append("      ");
                    mensagem.append(formatarData(VarShare.lancamento[i].data[j]) + " | ")
                            .append(VarShare.lancamento[i].comidakg[j] + " Kg | R$ ")
                            .append(VarShare.lancamento[i].custoComida + " | ")
                            .append(VarShare.lancamento[i].leite[j] + " L | R$ ")
                            .append(VarShare.lancamento[i].valorVenda + "\n");
                }
            }

            if (encontrouLancamentosIndividuais) {
                mensagem.append("\n");
            }
        }

        if (!encontrouLancamentos) {
            mensagem.append("Nenhum lançamento encontrado para o ano informado.\n");
        }

        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    public static String formatarData(int data) {
        int dia = data / 1000000;
        int mes = (data / 10000) % 100;
        int ano = data % 10000;
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    }
}