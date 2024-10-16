package br.com.fiap.UI;

import br.com.fiap.model.Pagamentos;
import br.com.fiap.service.PagamentosService;

import java.io.IOException;
import java.util.Scanner;

public class PagamentosInputHandler {

    private PagamentosService pagamentosService;

    public PagamentosInputHandler(PagamentosService pagamentosService) {
        this.pagamentosService = pagamentosService;
    }

    public void cadastrarNovoPagamento(Scanner scanner) {

        try {
            // Coleta os dados do pagamento
            Pagamentos pagamento = coletarDadosPagamento(scanner);

            // Chama o método do serviço para cadastrar o pagamento no banco de dados
            pagamentosService.cadastrarPagamentos(pagamento);
            System.out.println("Pagamento cadastrado com sucesso!");

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

    }

    private Pagamentos coletarDadosPagamento(Scanner scanner){

        Pagamentos pagamentos = new Pagamentos();

        pagamentos.definirDataPagamentoAtual();

        System.out.println("===== 1 - CADASTRO DE PAGAMENTO =====");
        System.out.println();
        System.out.println("Data de Pagmento...........................: " + pagamentos.definirDataPagamentoAtual());
        System.out.println("Tipo de Pagamento (Dinheiro, Cartão, Pix)..:");
        pagamentos.setTipoPagamento(scanner.nextLine());
        System.out.println("Forma de Pagamento (a vista, parcelado)....:");
        pagamentos.setFormaPagamento(scanner.nextLine());
        System.out.println("Quantidade de Parcelas.....................: ");
        pagamentos.setParcelas(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Valor da Parcela R$........................: ");
        pagamentos.setValorParcela(scanner.nextDouble());
        scanner.nextLine();
        System.out.println("Desconto %.................................: ");
        pagamentos.setDesconto(scanner.nextDouble());
        scanner.nextLine();
        System.out.println("Valor Total R$.............................: ");
        pagamentos.setValorTotal(scanner.nextDouble());

        return pagamentos;
    }

    private void atualizarPagamento(Scanner scanner) {
        try {
            // Coleta os dados do pagamento
            Pagamentos pagamento = coletarDadosPagamento(scanner);

            // Chama o método do serviço para atualizar o pagamento no banco de dados
            pagamentosService.atualizarPagamentos(pagamento);
            System.out.println("Pagamento atualizado com sucesso!");

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public void listarPagamentos(){
        pagamentosService.listarTodosPagamentos().forEach(pagamentos -> {
            System.out.println(pagamentos);
        });
    }
}
