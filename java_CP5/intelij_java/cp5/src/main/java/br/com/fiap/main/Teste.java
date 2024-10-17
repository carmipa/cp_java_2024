package br.com.fiap.main;

import br.com.fiap.UI.ClientesInputHandler;
import br.com.fiap.UI.SegurosInputHandler;
import br.com.fiap.UI.PagamentosInputHandler;
import br.com.fiap.service.ClientesService;
import br.com.fiap.service.SegurosService;
import br.com.fiap.service.PagamentosService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Teste {

    // Constantes para cores e estilos
    private static final String RESET = "\033[0m";
    private static final String BOLD = "\033[1m";
    private static final String GREEN = "\033[32m";
    private static final String RED = "\033[31m";
    private static final String BLUE = "\033[34m";
    private static final String CYAN = "\033[36m";
    private static final String YELLOW = "\033[33m";
    private static final String PINK = "\033[35m";

    // Animação de carregamento
    private static void loadingAnimation() {
        String loading = BOLD + PINK + "Carregando";
        for (int i = 0; i < 3; i++) {
            System.out.print(loading);
            try {
                Thread.sleep(500);
                System.out.print(".");
                Thread.sleep(500);
                System.out.print(".");
                Thread.sleep(500);
                System.out.println(".");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(RESET);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instanciando os serviços
        ClientesService clientesService = new ClientesService();
        SegurosService segurosService = new SegurosService();
        PagamentosService pagamentosService = new PagamentosService();

        // Instanciando os handlers de entrada
        ClientesInputHandler clienteInputHandler = new ClientesInputHandler(clientesService);
        SegurosInputHandler segurosInputHandler = new SegurosInputHandler(segurosService);
        PagamentosInputHandler pagamentosInputHandler = new PagamentosInputHandler(pagamentosService);

        loadingAnimation(); // Adicionando a animação no início

        while (true) {
            System.out.println(BOLD + GREEN + "╔═══════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                      SEGUROS                                          ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════╝" + RESET);

            System.out.println(
                    BOLD + CYAN +
                            "1 - CLIENTES" + "\n" +
                            "2 - SEGUROS" + "\n" +
                            "3 - ORÇAMENTO E PAGAMENTOS" + "\n" +
                            "0 - SAIR" + RESET
            );

            System.out.println(BOLD + GREEN + "╔═══════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                           DIGITE UMA DAS OPÇÕES ACIMA:                                ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════╝" + RESET);

            int opcao;
            try {
                System.out.print(BOLD + BLUE + "Sua escolha: " + RESET);
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
            } catch (InputMismatchException e) {
                System.err.println(RED + "Erro: Por favor, digite um número válido para a opção do menu." + RESET);
                scanner.nextLine(); // Limpar o buffer
                continue;
            }

            switch (opcao) {
                case 1:
                    menuClientes(scanner, clienteInputHandler);
                    break;
                case 2:
                    menuSeguros(scanner, segurosInputHandler);
                    break;
                case 3:
                    menuPagamentos(scanner, pagamentosInputHandler);
                    break;
                case 0:
                    System.out.println(GREEN + "Saindo do programa..." + RESET);
                    scanner.close();
                    return;
                default:
                    System.err.println(RED + "Opção inválida!" + RESET);
            }
        }
    }

    private static void menuClientes(Scanner scanner, ClientesInputHandler clienteInputHandler) {
        while (true) {
            System.out.println(BOLD + CYAN + "╔════════════════ MENU CLIENTES ════════════════╗" + RESET);
            System.out.println(BOLD + CYAN +
                    "1 - CADASTRAR CLIENTE" + "\n" +
                    "2 - LISTAR CLIENTES" + "\n" +
                    "3 - ATUALIZAR CLIENTE" + "\n" +
                    "4 - DELETAR CLIENTE" + "\n" +
                    "5 - BUSCAR CLIENTE POR ID" + "\n" +
                    "0 - VOLTAR" + RESET);
            System.out.println(BOLD + CYAN + "╚═══════════════════════════════════════════════╝" + RESET);
            System.out.println(BOLD + BLUE + "Escolha uma das opções: " + RESET);

            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        clienteInputHandler.cadastrarNovoCliente(scanner);
                        break;
                    case 2:
                        clienteInputHandler.listarClientes();
                        break;
                    case 3:
                        clienteInputHandler.atualizarCliente(scanner);
                        break;
                    case 4:
                        clienteInputHandler.deletarCliente(scanner);
                        break;
                    case 5:
                        clienteInputHandler.buscarClientePorId(scanner);
                        break;
                    case 0:
                        return;
                    default:
                        System.err.println(RED + "Opção inválida!" + RESET);
                }
            } catch (InputMismatchException e) {
                System.err.println(RED + "Erro: Por favor, digite um número válido." + RESET);
                scanner.nextLine(); // Limpar o buffer
            }
        }
    }

    private static void menuSeguros(Scanner scanner, SegurosInputHandler segurosInputHandler) {
        while (true) {
            System.out.println(BOLD + CYAN + "╔═════════════ MENU SEGUROS ═════════════╗" + RESET);
            System.out.println(BOLD + CYAN +
                    "1 - CADASTRAR SEGURO" + "\n" +
                    "2 - LISTAR SEGUROS" + "\n" +
                    "3 - ATUALIZAR SEGURO" + "\n" +
                    "4 - DELETAR SEGURO" + "\n" +
                    "5 - BUSCAR SEGURO POR ID" + "\n" +
                    "0 - VOLTAR" + RESET);
            System.out.println(BOLD + CYAN + "╚════════════════════════════════════════╝" + RESET);
            System.out.println(BOLD + BLUE + "Escolha uma das opções: " + RESET);

            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        segurosInputHandler.cadastrarNovoSeguro(scanner);
                        break;
                    case 2:
                        segurosInputHandler.listarSeguros();
                        break;
                    case 3:
                        segurosInputHandler.atualizarSeguro(scanner);
                        break;
                    case 4:
                        segurosInputHandler.deletarSeguro(scanner);
                        break;
                    case 5:
                        segurosInputHandler.buscarSeguroPorId(scanner);
                        break;
                    case 0:
                        return;
                    default:
                        System.err.println(RED + "Opção inválida!" + RESET);
                }
            } catch (InputMismatchException e) {
                System.err.println(RED + "Erro: Por favor, digite um número válido." + RESET);
                scanner.nextLine(); // Limpar o buffer
            }
        }
    }

    private static void menuPagamentos(Scanner scanner, PagamentosInputHandler pagamentosInputHandler) {
        while (true) {
            System.out.println(BOLD + CYAN + "╔═══════ MENU ORÇAMENTO E PAGAMENTOS ══════╗" + RESET);
            System.out.println(BOLD + CYAN +
                    "1 - CADASTRAR NOVO ORÇAMENTO/PAGAMENTO" + "\n" +
                    "2 - LISTAR ORÇAMENTOS/PAGAMENTOS" + "\n" +
                    "3 - ATUALIZAR PAGAMENTO/ORÇAMENTO" + "\n" +
                    "4 - DELETAR ORÇAMENTO/PAGAMENTO" + "\n" +
                    "5 - BUSCAR ORÇAMENTO/PAGAMENTO POR ID" + "\n" +
                    "0 - VOLTAR" + RESET);
            System.out.println(BOLD + CYAN + "╚══════════════════════════════════════════╝" + RESET);
            System.out.println(BOLD + BLUE + "Escolha uma das opções: " + RESET);

            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        pagamentosInputHandler.cadastrarNovoPagamento(scanner);
                        break;
                    case 2:
                        pagamentosInputHandler.listarPagamentos();
                        break;
                    case 3:
                        pagamentosInputHandler.atualizarPagamento(scanner);
                        break;
                    case 4:
                        pagamentosInputHandler.deletarPagamento(scanner);
                        break;
                    case 5:
                        pagamentosInputHandler.buscarPagamentoPorId(scanner);
                        break;
                    case 0:
                        return;
                    default:
                        System.err.println(RED + "Opção inválida!" + RESET);
                }
            } catch (InputMismatchException e) {
                System.err.println(RED + "Erro: Por favor, digite um número válido." + RESET);
                scanner.nextLine(); // Limpar o buffer
            }
        }
    }
}
