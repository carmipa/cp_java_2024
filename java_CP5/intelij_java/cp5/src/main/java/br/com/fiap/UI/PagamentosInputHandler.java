package br.com.fiap.UI;

import br.com.fiap.model.Pagamentos;
import br.com.fiap.service.PagamentosService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PagamentosInputHandler {

    private PagamentosService pagamentosService;

    // Códigos de cor ANSI
    private static final String RESET = "\033[0m";
    private static final String BOLD = "\033[1m";
    private static final String GREEN = "\033[32m";
    private static final String RED = "\033[31m";
    private static final String CYAN = "\033[36m";

    public PagamentosInputHandler(PagamentosService pagamentosService) {
        this.pagamentosService = pagamentosService;
    }

    // Método para cadastrar um novo pagamento
    public void cadastrarNovoPagamento(Scanner scanner) {
        try {
            // Coleta os dados do pagamento
            Pagamentos pagamento = coletarDadosPagamento(scanner);

            // Chama o método do serviço para cadastrar o pagamento no banco de dados
            pagamentosService.cadastrarPagamentos(pagamento);
            System.out.println(GREEN + "Pagamento cadastrado com sucesso!" + RESET);

        } catch (IllegalArgumentException e) {
            System.err.println(RED + e.getMessage() + RESET);
        }
    }

    // Método para coletar dados de pagamento com uso de Builder
    private Pagamentos coletarDadosPagamento(Scanner scanner) {

        Pagamentos.PagamentosBuilder pagamentosBuilder = Pagamentos.builder();

        pagamentosBuilder.dataPagamento(Pagamentos.definirDataPagamentoAtual());

        System.out.println(CYAN + "===== 1 - CADASTRO DE PAGAMENTO =====" + RESET);
        System.out.println();
        System.out.println(BOLD + "Data de Pagamento: " + RESET + Pagamentos.definirDataPagamentoAtual());

        System.out.print("Tipo de Pagamento (Dinheiro, Cartão, Pix): ");
        pagamentosBuilder.tipoPagamento(scanner.nextLine());

        System.out.print("Forma de Pagamento (à vista, parcelado): ");
        pagamentosBuilder.formaPagamento(scanner.nextLine());

        // Validação para garantir que o número de parcelas seja positivo
        int parcelas;
        while (true) {
            try {
                System.out.print("Quantidade de Parcelas: ");
                parcelas = scanner.nextInt();
                if (parcelas <= 0) {
                    throw new IllegalArgumentException("Número de parcelas deve ser maior que zero.");
                }
                pagamentosBuilder.parcelas(parcelas);
                break;
            } catch (InputMismatchException e) {
                System.err.println(RED + "Erro: Por favor, insira um número válido para as parcelas." + RESET);
                scanner.nextLine();  // Limpa o buffer
            }
        }

        // Validação do valor da parcela
        double valorParcela;
        while (true) {
            try {
                System.out.print("Valor da Parcela R$: ");
                valorParcela = scanner.nextDouble();
                if (valorParcela < 0) {
                    throw new IllegalArgumentException("O valor da parcela não pode ser negativo.");
                }
                pagamentosBuilder.valorParcela(valorParcela);
                break;
            } catch (InputMismatchException e) {
                System.err.println(RED + "Erro: Por favor, insira um valor numérico válido para a parcela." + RESET);
                scanner.nextLine();  // Limpa o buffer
            }
        }

        // Validação do desconto
        double desconto;
        while (true) {
            try {
                System.out.print("Desconto %: ");
                desconto = scanner.nextDouble();
                if (desconto < 0) {
                    throw new IllegalArgumentException("O desconto não pode ser negativo.");
                }
                pagamentosBuilder.desconto(desconto);
                break;
            } catch (InputMismatchException e) {
                System.err.println(RED + "Erro: Por favor, insira um valor numérico válido para o desconto." + RESET);
                scanner.nextLine();  // Limpa o buffer
            }
        }

        // Validação do valor total
        double valorTotal;
        while (true) {
            try {
                System.out.print("Valor Total R$: ");
                valorTotal = scanner.nextDouble();
                if (valorTotal < 0) {
                    throw new IllegalArgumentException("O valor total não pode ser negativo.");
                }
                pagamentosBuilder.valorTotal(valorTotal);
                break;
            } catch (InputMismatchException e) {
                System.err.println(RED + "Erro: Por favor, insira um valor numérico válido para o valor total." + RESET);
                scanner.nextLine();  // Limpa o buffer
            }
        }

        scanner.nextLine(); // Limpar o buffer
        return pagamentosBuilder.build(); // Retorna o objeto `Pagamentos` construído
    }

    // Método para exibir detalhes de um pagamento
    public void exibirDadosPagamento(Pagamentos pagamento) {
        System.out.println(CYAN + BOLD + "===== DADOS DO PAGAMENTO =====" + RESET);
        System.out.println(BOLD + "ID do Pagamento..............: " + RESET + pagamento.getIdPagamento());
        System.out.println(BOLD + "Data do Pagamento............: " + RESET + pagamento.getDataPagamento());
        System.out.println(BOLD + "Tipo de Pagamento............: " + RESET + pagamento.getTipoPagamento());
        System.out.println(BOLD + "Forma de Pagamento...........: " + RESET + pagamento.getFormaPagamento());
        System.out.println(BOLD + "Quantidade de Parcelas.......: " + RESET + pagamento.getParcelas() + "x");
        System.out.println(BOLD + "Valor da Parcela.............: R$ " + RESET + pagamento.getValorParcela());
        System.out.println(BOLD + "Desconto.....................: " + RESET + pagamento.getDesconto() + "%");
        System.out.println(BOLD + "Valor Total..................: R$ " + RESET + pagamento.getValorTotal());
    }
}
