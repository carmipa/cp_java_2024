package br.com.fiap.main;

import br.com.fiap.service.*; // Importando as classes de serviço
import java.util.InputMismatchException;
import java.util.Scanner;

public class Teste {

    // Códigos de cor ANSI para o terminal
    private static final String RESET = "\033[0m";
    private static final String BOLD = "\033[1m";
    private static final String GREEN = "\033[32m";
    private static final String RED = "\033[31m";
    private static final String BLUE = "\033[34m";
    private static final String CYAN = "\033[36m";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Instanciando os serviços
        ClientesService clientesService = new ClientesService();
        SegurosService segurosService = new SegurosService();
        PagamentosService pagamentosService = new PagamentosService();

        while (true) {
            System.out.println(BOLD + GREEN + "****************************************************************************************" +
                    "\nSEGURO AUTOMOTIVO" + RESET);
            System.out.println(
                    "\n" + BOLD + CYAN +
                            "1 - CLIENTES" + "\n" +
                            "2 - SEGURO" + "\n" +
                            "3 - ORÇAMENTO E PAGAMENTO" + "\n" +
                            "0 - SAIR" + RESET +
                            "\n" + BOLD + GREEN + "****************************************************************************************" + RESET);

            System.out.println("\n" + BOLD + BLUE + "DIGITE UMA DAS OPÇÕES ACIMA: " + RESET);

            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
            } catch (InputMismatchException e) {
                System.err.println(RED + "Erro: Por favor, digite um número válido para a opção do menu." + RESET);
                scanner.nextLine(); // Limpar o buffer
                continue;
            }

            switch (opcao) {
                case 1:
                    menuClientes(scanner, clientesService);
                    break;

                case 2:
                    menuSeguros(scanner, segurosService);
                    break;

                case 3:
                    menuPagamentos(scanner, pagamentosService);
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

    private static void menuClientes(Scanner scanner, ClientesService clientesService) {
        while (true) {
            System.out.println(BOLD + CYAN + "========== MENU CLIENTES ==========" + RESET + "\n" +
                    "\n" + BOLD + "1 - CADASTRAR CLIENTE" + "\n" +
                    "2 - LISTAR CLIENTES" + "\n" +
                    "3 - ATUALIZAR CLIENTE" + "\n" +
                    "4 - DELETAR CLIENTE" + "\n" +
                    "0 - VOLTAR" + RESET + "\n");

            System.out.println(BOLD + BLUE + "Escolha uma das opções: " + RESET);

            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        clientesService.cadastrarClientes(scanner);
                        break;
                    case 2:
                        clientesService.listarTodosClientes();
                        break;
                    case 3:
                        clientesService.atualizarClientes();
                        break;
                    case 4:
                        clientesService.deletarClientes();
                        break;
                    case 0:
                        System.out.println(GREEN + "Voltando ao menu principal..." + RESET);
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

    private static void menuSeguros(Scanner scanner, SegurosService segurosService) {
        while (true) {
            System.out.println(BOLD + CYAN + "========== MENU SEGUROS ==========" + RESET + "\n" +
                    "\n" + BOLD + "1 - CADASTRAR SEGURO" + "\n" +
                    "2 - LISTAR SEGUROS" + "\n" +
                    "3 - ATUALIZAR SEGURO" + "\n" +
                    "4 - DELETAR SEGURO" + "\n" +
                    "0 - VOLTAR" + RESET + "\n");

            System.out.println(BOLD + BLUE + "Escolha uma das opções: " + RESET);

            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        segurosService.cadastrarSeguro(scanner);
                        break;
                    case 2:
                        segurosService.listarSeguros();
                        break;
                    case 3:
                        segurosService.atualizarSeguro(scanner);
                        break;
                    case 4:
                        segurosService.deletarSeguro(scanner);
                        break;
                    case 0:
                        System.out.println(GREEN + "Voltando ao menu principal..." + RESET);
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

    private static void menuPagamentos(Scanner scanner, PagamentosService pagamentosService) {
        while (true) {
            System.out.println(BOLD + CYAN + "========== MENU ORÇAMENTO E PAGAMENTO ==========" + RESET + "\n" +
                    "\n" + BOLD + "1 - CADASTRAR NOVO PAGAMENTO" + "\n" +
                    "2 - LISTAR PAGAMENTOS" + "\n" +
                    "3 - ATUALIZAR PAGAMENTO" + "\n" +
                    "4 - DELETAR PAGAMENTO" + "\n" +
                    "0 - VOLTAR" + RESET + "\n");

            System.out.println(BOLD + BLUE + "Escolha uma das opções: " + RESET);

            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        pagamentosService.cadastrarPagamento(scanner);
                        break;
                    case 2:
                        pagamentosService.listarPagamentos();
                        break;
                    case 3:
                        pagamentosService.atualizarPagamento(scanner);
                        break;
                    case 4:
                        pagamentosService.deletarPagamento(scanner);
                        break;
                    case 0:
                        System.out.println(GREEN + "Voltando ao menu principal..." + RESET);
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
