package projeto_java_2p;

import javax.swing.JOptionPane;

public class Estatisticas {

    // Método para exibir os cadastros de todas as vacas
    public static void verCadastros() {
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Codigo | Nome | Raça | Variação\n");

        // Itera sobre as vacas cadastradas
        for (int i = 0; i < VarShare.id - 1; i++) {
            // Adiciona informações da vaca à mensagem
            mensagem.append(VarShare.cod[i] + " | " + VarShare.nome[i] + " | "
                    + VarShare.raca[i] + " | " + VarShare.variacao[i] + "\n");
        }

        // Exibe os cadastros em uma caixa de diálogo
        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    // Método para gerar relatórios diários de lançamentos
    public static void lancamentoDiario() {
        // Inicialização de variáveis e solicitação da data ao usuário
        StringBuilder mensagemVaca = new StringBuilder();
        StringBuilder mensagem = new StringBuilder();
        double quantComida = 0, custoComida = 0, leite = 0, valorVenda = 0;

        // Solicitação da data ao usuário
        int data = Integer.parseInt(JOptionPane.showInputDialog("Informe a data (dd/mm/yyyy):"));

        mensagem.append("- Relatórios para" + formatarData(data) + "\n");

        // Obtém partes do dia, mês e ano da data
        int dataDia = data % 100;
        int dataMes = (data % 10000) / 100;
        int dataAno = data / 10000;

        mensagemVaca.append(
                " | Data | Codigo | Nome | Raça | Variação | Comida Kg | Custo Comida R$ | Leite L | Venda do Leite R$ |\n");

        // Itera sobre as vacas cadastradas
        for (int i = 0; i < VarShare.id - 1; i++) {
            StringBuilder mensagemLancs = new StringBuilder();

            // Itera sobre os lançamentos da vaca
            for (int j = 0; j < VarShare.numL[i]; j++) {
                int lancamentoDia = VarShare.lancamento[i].data[j] % 100;
                int lancamentoMes = (VarShare.lancamento[i].data[j] % 10000) / 100;
                int lancamentoAno = VarShare.lancamento[i].data[j] / 10000;

                // Verifica se o lançamento é na data informada
                if (lancamentoDia == dataDia && lancamentoMes == dataMes && lancamentoAno == dataAno) {
                    // Adiciona informações do lançamento à mensagem
                    mensagemVaca.append(String.format(" | %s | %s | %s | %s | %s | %s Kg | R$ %s | %s L | R$ %s |\n",
                            formatarData(VarShare.lancamento[i].data[j]),
                            VarShare.cod[i], VarShare.nome[i], VarShare.raca[i], VarShare.variacao[i],
                            formatarCasaDecimal(VarShare.lancamento[i].comidakg[j]),
                            formatarCasaDecimal(VarShare.lancamento[i].custoComida),
                            formatarCasaDecimal(VarShare.lancamento[i].leite[j]),
                            formatarCasaDecimal(VarShare.lancamento[i].valorVenda)));

                    // Atualiza totais
                    quantComida += VarShare.lancamento[i].comidakg[j];
                    custoComida = VarShare.lancamento[i].custoComida;
                    leite += VarShare.lancamento[i].leite[j];
                    valorVenda = VarShare.lancamento[i].valorVenda;
                }
            }

            // Se houver lançamentos, adiciona à mensagem
            if (!mensagemLancs.toString().equals("")) {
                mensagemVaca
                        .append(String.format(" |      Data     | Codigo |    Nome    |      Raça      | Variação |"));
                mensagemVaca.append(" Comida Kg | Custo Comida R$ | Leite L | Venda do Leite R$ |\n");
                mensagemVaca.append(mensagemLancs + "\n");
            }
        }

        // Calcula custo, venda e balanço
        double custo = quantComida * custoComida;
        double venda = leite * valorVenda;
        double balanco = venda - custo;

        // Se não houver lançamentos, informa ao usuário
        if (mensagemVaca.toString().equals("")) {
            mensagem.append("Nenhum lançamento encontrado para o mês informado.\n");
        } else {
            // Adiciona informações totais à mensagem
            mensagem.append(
                    "\n | Comida Total | Custo Comida Total | Produção de Leite Total | Venda Total de Leite | Balanço\n")
                    .append(" |    " + formatarCasaDecimal(quantComida) + " Kg    |    R$ ")
                    .append(formatarCasaDecimal(custo) + "    |   ")
                    .append(formatarCasaDecimal(leite) + " L    | R$   ")
                    .append(formatarCasaDecimal(venda) + "     | R$   ")
                    .append(formatarCasaDecimal(balanco) + "   |   ");
            mensagem.append("\n\n" + mensagemVaca);
        }

        // Exibe a mensagem final em uma caixa de diálogo
        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    // Método para gerar relatórios mensais de lançamentos
    public static void lancamentoMensal() {
        // Inicialização de variáveis e solicitação da data ao usuário
        StringBuilder mensagemVaca = new StringBuilder();
        StringBuilder mensagem = new StringBuilder();
        double quantComida = 0, custoComida = 0, leite = 0, valorVenda = 0;

        // Solicitação da data ao usuário no formato mm/yyyy
        int data = Integer.parseInt(JOptionPane.showInputDialog("Informe a data (mm/yyyy):"));

        // Obtém partes do mês e ano da data
        int dataMes = data / 10000;
        int dataAno = data % 10000;

        // Adiciona cabeçalho à mensagem com a informação do mês e ano
        mensagem.append(" - Relatórios para " + dataMes + "/" + dataAno + "\n");

        // Itera sobre as vacas cadastradas
        for (int i = 0; i < VarShare.id - 1; i++) {
            StringBuilder mensagemLancs = new StringBuilder();
            // Itera sobre os lançamentos da vaca
            for (int j = 0; j < VarShare.numL[i]; j++) {
                // Extrai o mês e ano do lançamento
                int semDia = VarShare.lancamento[i].data[j] % 1000000;
                int lancamentoMes = semDia / 10000;
                int lancamentoAno = semDia % 10000;

                // Verifica se o lançamento é no mês e ano informados
                if (lancamentoMes == dataMes && lancamentoAno == dataAno) {
                    // Adiciona informações do lançamento à mensagem
                    mensagemLancs.append(String.format(" | %s | %s | %s | %s | %s | %s Kg | R$ %s | %s L | R$ %s |\n",
                            formatarData(VarShare.lancamento[i].data[j]),
                            VarShare.cod[i], VarShare.nome[i], VarShare.raca[i], VarShare.variacao[i],
                            formatarCasaDecimal(VarShare.lancamento[i].comidakg[j]),
                            formatarCasaDecimal(VarShare.lancamento[i].custoComida),
                            formatarCasaDecimal(VarShare.lancamento[i].leite[j]),
                            formatarCasaDecimal(VarShare.lancamento[i].valorVenda)));

                    // Atualiza totais
                    quantComida += VarShare.lancamento[i].comidakg[j];
                    custoComida = VarShare.lancamento[i].custoComida;
                    leite += VarShare.lancamento[i].leite[j];
                    valorVenda = VarShare.lancamento[i].valorVenda;
                }
            }

            // Se houver lançamentos, adiciona à mensagem
            if (!mensagemLancs.toString().equals("")) {
                mensagemVaca
                        .append(String.format(" |      Data     | Codigo |    Nome    |      Raça      | Variação |"));
                mensagemVaca.append(" Comida Kg | Custo Comida R$ | Leite L | Venda do Leite R$ |\n");
                mensagemVaca.append(mensagemLancs + "\n");
            }
        }

        // Calcula custo, venda e balanço
        double custo = quantComida * custoComida;
        double venda = leite * valorVenda;
        double balanco = venda - custo;

        // Se não houver lançamentos, informa ao usuário
        if (mensagemVaca.toString().equals("")) {
            mensagem.append("Nenhum lançamento encontrado para o mês informado.\n");
        } else {
            // Adiciona informações totais à mensagem
            mensagem.append(
                    "\n | Comida Total | Custo Comida Total | Produção de Leite Total | Venda Total de Leite | Balanço\n")
                    .append(" |    " + formatarCasaDecimal(quantComida) + " Kg    |    R$ ")
                    .append(formatarCasaDecimal(custo) + "    |   ")
                    .append(formatarCasaDecimal(leite) + "    L | R$   ")
                    .append(formatarCasaDecimal(venda) + "    L | R$   ")
                    .append(formatarCasaDecimal(balanco) + "   |   ");
            mensagem.append("\n\n" + mensagemVaca);
        }

        // Exibe a mensagem final em uma caixa de diálogo
        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    // Método para gerar relatórios anuais de lançamentos
    public static void lancamentoAnual() {
        // Inicialização de variáveis e solicitação da data ao usuário
        StringBuilder mensagemVaca = new StringBuilder();
        StringBuilder mensagem = new StringBuilder();
        double totalQuantComida = 0, totalCustoComida = 0, totalLeite = 0, totalValorVenda = 0;

        // Solicitação da data ao usuário no formato yyyy
        int ano = Integer.parseInt(JOptionPane.showInputDialog("Informe a data (yyyy):"));

        // Adiciona cabeçalho à mensagem com a informação do ano
        mensagem.append(String.format("- Relatórios para o ano de %d:\n", ano));

        // Flag para verificar se houve lançamentos
        boolean encontrouLancamentos = false;

        // Adiciona cabeçalho da tabela de lançamentos à mensagem
        mensagemVaca.append(
                " | Data | Codigo | Nome | Raça | Variação | Comida Kg | Custo Comida R$ | Leite L | Venda do Leite R$ |\n");

        // Itera sobre as vacas cadastradas
        for (int i = 0; i < VarShare.id - 1; i++) {
            double quantComidaVaca = 0, custoComidaVaca = 0, leiteVaca = 0, valorVendaVaca = 0;

            // Flag para verificar se houve lançamentos individuais para a vaca
            boolean encontrouLancamentosIndividuais = false;

            // Itera sobre os lançamentos da vaca
            for (int j = 0; j < VarShare.numL[i]; j++) {
                // Extrai o ano do lançamento
                int lancamentoAno = VarShare.lancamento[i].data[j] % 10000;

                // Verifica se o lançamento é no ano informado
                if (lancamentoAno == ano) {
                    // Atualiza flags e totais
                    encontrouLancamentos = true;
                    encontrouLancamentosIndividuais = true;

                    quantComidaVaca += VarShare.lancamento[i].comidakg[j];
                    custoComidaVaca += VarShare.lancamento[i].custoComida;
                    leiteVaca += VarShare.lancamento[i].leite[j];
                    valorVendaVaca += VarShare.lancamento[i].valorVenda;
                }
            }

            // Se houver lançamentos individuais, adiciona à mensagem
            if (encontrouLancamentosIndividuais) {
                mensagemVaca.append(String.format(" | %s | %s | %s | %s | %s | %s Kg | R$ %s | %s L | R$ %s |\n",
                        formatarData(VarShare.lancamento[i].data[0]),
                        VarShare.cod[i], VarShare.nome[i], VarShare.raca[i], VarShare.variacao[i],
                        formatarCasaDecimal(quantComidaVaca), formatarCasaDecimal(custoComidaVaca),
                        formatarCasaDecimal(leiteVaca), formatarCasaDecimal(valorVendaVaca)));

                // Atualiza totais gerais
                totalQuantComida += quantComidaVaca;
                totalCustoComida = VarShare.lancamento[i].custoComida;
                totalLeite += leiteVaca;
                totalValorVenda = VarShare.lancamento[i].valorVenda;
            }
        }

        // Se não houver lançamentos, informa ao usuário
        if (!encontrouLancamentos || mensagemVaca.toString().equals("")) {
            mensagem.append("Nenhum lançamento encontrado para o ano informado.\n");
        } else {
            // Calcula custo, venda e balanço gerais
            double totalCusto = totalQuantComida * totalCustoComida;
            double totalVenda = totalLeite * totalValorVenda;
            double totalBalanço = totalVenda - totalCusto;

            // Adiciona informações totais à mensagem
            mensagem.append(
                    "\n | Comida Total | Custo Comida Total | Produção de Leite Total | Venda Total de Leite | Balanço\n")
                    .append(" |    " + formatarCasaDecimal(totalQuantComida) + " Kg    |    R$ ")
                    .append(formatarCasaDecimal(totalCusto) + "    |   ")
                    .append(formatarCasaDecimal(totalLeite) + "    L | R$   ")
                    .append(formatarCasaDecimal(totalVenda) + "    L | R$   ")
                    .append(formatarCasaDecimal(totalBalanço) + "   |   ");
            mensagem.append("\n\n" + mensagemVaca);
        }

        // Exibe a mensagem final em uma caixa de diálogo
        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    public static String formatarData(int data) {
        int dia = data / 1000000;
        int mes = (data / 10000) % 100;
        int ano = data % 10000;
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    }

    public static String formatarCasaDecimal(double valor) {
        return String.format("%.2f", valor);
    }
}