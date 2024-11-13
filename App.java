import java.util.Scanner;

public class App {
    public static String[] listaConvidados = new String[10];
    public static boolean isExecutando = true;
    public static int uPos = -1;

    public static void exibirLista() {
        if (uPos < 0) {
            System.out.println("Lista de convidados vazia");
        } else {
            System.out.println("Lista de convidados:");
            for (int i = 0; i < uPos + 1; i++) {
                if (!listaConvidados[i].equals(null))
                    System.out.println((i + 1) + " - " + listaConvidados[i]);
            }
        }

    }

    public static void inserirConvidado(String nome) {
        ++uPos;
        listaConvidados[uPos] = nome;
    }

    public static void excluirConvidado(int posicao) {
        for (int i = posicao - 1; i < uPos; ++i) {
            listaConvidados[i] = listaConvidados[i + 1];
        }
        listaConvidados[uPos] = null;
        --uPos;
    }

    public static int qtdVagasDisp() {
        return (listaConvidados.length - (uPos + 1));
    }

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        String opcao;

        limparTela();
        System.out.println("\n ----------------------------------------------");
        System.out.println("|  Bem-vindo(a) ao App de Lista de Convidados  |");
        System.out.println(" ----------------------------------------------");

        while (isExecutando) {
            System.out.println("\nOpções disponíveis: [A, B, C, D, S]\n");
            System.out.println("Escolha uma opção:");
            System.out.println("[A] - Incluir novo convidado.");
            System.out.println("[B] - Excluir convidado.");
            System.out.println("[C] - Ver lista de convidados.");
            System.out.println("[D] - Ver quantidade de vagas restantes.");
            System.out.println("[S] - Sair.");

            opcao = teclado.next();
            opcao = opcao.toUpperCase();

            switch (opcao) {
                case "A":
                    try {
                        limparTela();
                        System.out.println("Informe o nome do convidado, por favor:");
                        teclado.nextLine();
                        String nome = teclado.nextLine();

                        if (nome.split(" ").length != 0) {
                            if (qtdVagasDisp() != 0) {
                                inserirConvidado(nome);
                                System.out.println("Convidado inserido com sucesso!");
                            } else {
                                System.out.println("Lista cheia.");
                            }
                        } else {
                            System.out.println("Campo de nome vazio. Preencha com algum nome.");
                        }
                    } catch (Exception e) {
                        System.out.println("Exceção ao incluir novo convidado. Mensagem da exceção: " + e.getMessage());
                    }
                    break;

                case "B":
                    try {
                        limparTela();
                        System.out.println("Informe a posição (de 1 à 10) do convidado que deseja remover, por favor:");
                        int posicao = teclado.nextInt();
                        if (posicao <= 0 || posicao > 10) {
                            System.out.println(
                                    "Posição informada não está na faixa disponível. Apenas números de 1 à 10.");

                        } else if (posicao - 1 > uPos) {
                            System.out.println("Posição sem nenhum convidado correspondente. Tente outra posição.");

                        } else {
                            String nomeExcluido = listaConvidados[posicao - 1];
                            excluirConvidado(posicao);
                            System.out.println("Convidado " + nomeExcluido + " excluído com sucesso!");
                        }
                    } catch (Exception e) {
                        System.out.println(
                                "Exceção ao informar valor para a posição. Mensagem de exceção: " + e.getMessage());
                    }

                    break;

                case "C":
                    limparTela();
                    exibirLista();
                    break;

                case "D":
                    limparTela();
                    System.out.println("Quantidade de vagas restantes: " + qtdVagasDisp());
                    break;

                case "S":
                    limparTela();
                    System.out.println("Obrigado por usar este App. Adeus!");
                    isExecutando = false;
                    break;

                default:
                    limparTela();
                    System.out.println("Opção inválida. Selecione uma das opções disponíveis.");

                    break;
            }
        }

        System.exit(0);
    }
}
