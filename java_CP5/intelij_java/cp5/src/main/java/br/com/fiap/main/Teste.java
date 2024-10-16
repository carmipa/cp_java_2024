package br.com.fiap.main;

import br.com.fiap.UI.ClientesInputHandler;  // Classe que lida com a entrada de dados
import br.com.fiap.service.ClientesService;
import br.com.fiap.service.SegurosService;
import br.com.fiap.service.PagamentosService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Teste {

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
        ClientesInputHandler clienteInputHandler = new ClientesInputHandler(clientesService);  // Handler de entrada
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
                    menuClientes(scanner, clienteInputHandler);
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

    private static void menuClientes(Scanner scanner, ClientesInputHandler clienteInputHandler) {
        while (true) {
            System.out.println(BOLD + CYAN + "========== MENU CLIENTES ==========" + RESET + "\n" +
                    "\n" + BOLD + "1 - CADASTRAR CLIENTE" + "\n" +
                    "2 - LISTAR CLIENTES" + "\n" +
                    "3 - ATUALIZAR CLIENTE" + "\n" +
                    "4 - DELETAR CLIENTE" + "\n" +
                    "5 - BUSCAR CLIENTE POR ID" + "\n" +
                    "0 - VOLTAR" + RESET + "\n");

            System.out.println(BOLD + BLUE + "Escolha uma das opções: " + RESET);

            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        clienteInputHandler.cadastrarNovoCliente(scanner);  // Coleta os dados e cadastra o cliente
                        break;
                    case 2:
                        clienteInputHandler.listarClientes();  // Lista os clientes
                        break;
                    case 3:
                        clienteInputHandler.atualizarCliente(scanner);  // Atualiza cliente
                        break;
                    case 4:
                        clienteInputHandler.deletarCliente(scanner);  // Deleta cliente por ID
                        break;
                    case 5: // Busca cliente por ID
                        clienteInputHandler.buscarClientePorId(scanner);
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
                    "5 - BUSCAR SEGURO POR ID" + "\n" +
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
                    case 5:
                        segurosService.buscarSeguroPorId(scanner);
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
                    "5 - BUSCAR PAGAMENTO POR ID" + "\n" +
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
                    case 5:
                        pagamentosService.buscarPagamentoPorId(scanner);
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
