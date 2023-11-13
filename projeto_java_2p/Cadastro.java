package projeto_java_2p;

import javax.swing.JOptionPane;

public class Cadastro {

    public static void cadastro() {
        String input = JOptionPane
                .showInputDialog("Informe a vaca para cadastro: \n- Data | Código | Nome | Raça | Variação");

        if (input != null) {
            String[] div = input.split(",");
            if (div.length == 5) {
                try {
                    int codigo = Integer.parseInt(div[1].trim());

                    // Verifique se o código já existe
                    if (codigoExistente(codigo)) {
                        JOptionPane.showMessageDialog(null, "Erro: Código já existe. Escolha outro código.");
                    } else {
                        // Continue com a adição dos dados se o código for válido
                        adicionarDadosVaca(div);
                        JOptionPane.showMessageDialog(null, "Vaca cadastrada com sucesso.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro: Código não é um número válido.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "A entrada não foi dividida corretamente em 5 partes.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Entrada nula. Por favor, forneça uma entrada válida.");
        }
    }

    public static boolean codigoExistente(int codigo) {
        // Verifique se o código já existe no array de códigos
        for (int i = 0; i < VarShare.cod.length; i++) {
            if (VarShare.cod[i] == codigo) {
                return true;
            }
        }
        return false;
    }

    private static void adicionarDadosVaca(String[] div) {

        int i = (int) (VarShare.id - 1);

        try {
            VarShare.data[i] = Integer.parseInt(div[0].trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: Data inválida");
            return;
        }

        try {
            VarShare.cod[i] = Integer.parseInt(div[1].trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: Código não é um número válido.");
            return;
        }

        VarShare.nome[i] = div[2].trim();
        VarShare.raca[i] = div[3].trim();
        VarShare.variacao[i] = div[4].trim();

        // Incrementar a variável id para o próximo cadastro
        VarShare.id++;
    }
}
