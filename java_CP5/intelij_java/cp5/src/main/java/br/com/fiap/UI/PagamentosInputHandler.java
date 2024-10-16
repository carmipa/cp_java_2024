package br.com.fiap.UI;

import br.com.fiap.model.Pagamentos;
import br.com.fiap.service.PagamentosService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PagamentosInputHandler {

    private PagamentosService pagamentosService;

    public PagamentosInputHandler(PagamentosService pagamentosService) {
        this.pagamentosService = pagamentosService;
    }

    public void cadastrarNovoPagamento(Scanner scanner) {
        try {
            Pagamentos pagamento = coletarDadosPagamento(scanner);
            pagamentosService.cadastrarPagamentos(pagamento);
            System.out.println("\033[32m\033[1mPagamento cadastrado com sucesso!\033[0m");
        } catch (IllegalArgumentException e) {
            System.err.println("\033[31mErro ao cadastrar pagamento: " + e.getMessage() + "\033[0m");
        }
    }

    private Pagamentos coletarDadosPagamento(Scanner scanner) {
        Pagamentos pagamentos = new Pagamentos();
        pagamentos.definirDataPagamentoAtual();
        System.out.println("\033[34m\033[1m===== 1 - CADASTRO DE PAGAMENTO =====\033[0m");
        System.out.println("\nData de Pagamento: \033[32m" + pagamentos.getDataPagamento() + "\033[0m");

        try {
            System.out.print("Tipo de Pagamento (Dinheiro, Cartão, Pix): ");
            pagamentos.setTipoPagamento(scanner.nextLine());

            System.out.print("Forma de Pagamento (à vista, parcelado): ");
            pagamentos.setFormaPagamento(scanner.nextLine());

            System.out.print("Quantidade de Parcelas: ");
            int parcelas = scanner.nextInt();
            if (parcelas < 0) throw new IllegalArgumentException("A quantidade de parcelas não pode ser negativa.");
            pagamentos.setParcelas(parcelas);

            System.out.print("Valor da Parcela R$: ");
            double valorParcela = scanner.nextDouble();
            if (valorParcela < 0) throw new IllegalArgumentException("O valor da parcela não pode ser negativo.");
            pagamentos.setValorParcela(valorParcela);

            System.out.print("Desconto %: ");
            double desconto = scanner.nextDouble();
            if (desconto < 0) throw new IllegalArgumentException("O desconto não pode ser negativo.");
            pagamentos.setDesconto(desconto);

            System.out.print("Valor Total R$: ");
            double valorTotal = scanner.nextDouble();
            if (valorTotal < 0) throw new IllegalArgumentException("O valor total não pode ser negativo.");
            pagamentos.setValorTotal(valorTotal);

        } catch (InputMismatchException e) {
            System.err.println("\033[31mErro: Entrada inválida. Por favor, insira os valores corretamente.\033[0m");
            scanner.nextLine(); // Limpa o buffer do scanner
            throw new IllegalArgumentException("Erro durante a coleta de dados do pagamento.");
        }
        scanner.nextLine(); // Limpa o buffer do scanner

        return pagamentos;
    }

    public void atualizarPagamento(Scanner scanner) {
        try {
            System.out.print("Digite o ID do pagamento que deseja atualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            Pagamentos pagamentoExistente = pagamentosService.buscarPagamentosPorId(id);

            if (pagamentoExistente != null) {
                Pagamentos pagamentoAtualizado = coletarDadosPagamento(scanner);
                pagamentoAtualizado.setIdPagemnto(id); // Mantém o mesmo ID
                pagamentosService.atualizarPagamentos(pagamentoAtualizado);
                System.out.println("\033[32m\033[1mPagamento atualizado com sucesso!\033[0m");
            } else {
                System.out.println("\033[31mPagamento com ID " + id + " não encontrado.\033[0m");
            }

        } catch (IllegalArgumentException e) {
            System.err.println("\033[31mErro ao atualizar pagamento: " + e.getMessage() + "\033[0m");
        }
    }

    public void listarPagamentos() {
        System.out.println("\033[34m\033[1m===== LISTA DE PAGAMENTOS =====\033[0m");
        pagamentosService.listarTodosPagamentos().forEach(pagamento -> {
            exibirDadosPagamento(pagamento);
            System.out.println("-----------------------------------");
        });
    }

    public void buscarPagamentoPorId(Scanner scanner) {
        try {
            System.out.print("Digite o ID do pagamento: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            Pagamentos pagamento = pagamentosService.buscarPagamentosPorId(id);
            if (pagamento != null) {
                exibirDadosPagamento(pagamento);
            } else {
                System.out.println("\033[31mPagamento com ID " + id + " não encontrado.\033[0m");
            }
        } catch (InputMismatchException e) {
            System.err.println("\033[31mErro: Entrada inválida. Por favor, insira os valores corretamente.\033[0m");
            scanner.nextLine(); // Limpa o buffer do scanner
        }
    }

    private void exibirDadosPagamento(Pagamentos pagamentos) {
        System.out.println("\033[34m\033[1m===== DADOS DO PAGAMENTO =====\033[0m");
        System.out.println("ID: " + pagamentos.getIdPagemnto());
        System.out.println("Data de Pagamento: " + pagamentos.getDataPagamento());
        System.out.println("Tipo de Pagamento: " + pagamentos.getTipoPagamento());
        System.out.println("Forma de Pagamento: " + pagamentos.getFormaPagamento());
        System.out.println("Parcelas: " + pagamentos.getParcelas());
        System.out.println("Valor da Parcela: " + pagamentos.getValorParcela());
        System.out.println("Desconto: " + pagamentos.getDesconto());
        System.out.println("Valor Total: " + pagamentos.getValorTotal());
    }

    public void deletarPagamento(Scanner scanner) {
        try {
            System.out.print("Digite o ID do pagamento que deseja deletar: ");
            int idPagamento = scanner.nextInt();
            pagamentosService.deletarPagamentos(idPagamento);
            System.out.println("\033[32m\033[1mPagamento deletado com sucesso!\033[0m");
        } catch (InputMismatchException e) {
            System.err.println("\033[31mErro: Entrada inválida. Por favor, insira os valores corretamente.\033[0m");
            scanner.nextLine(); // Limpa o buffer do scanner
        }
    }

}