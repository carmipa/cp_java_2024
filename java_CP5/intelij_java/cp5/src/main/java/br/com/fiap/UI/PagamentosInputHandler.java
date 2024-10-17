package br.com.fiap.UI;

import br.com.fiap.model.Pagamentos;
import br.com.fiap.service.PagamentosService;

import java.sql.SQLException;
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Pagamentos coletarDadosPagamento(Scanner scanner) {
        Pagamentos pagamentos = new Pagamentos();
        pagamentos.definirDataPagamentoAtual();
        System.out.println("\033[34m\033[1m===== 1 - CADASTRO DE PAGAMENTO =====\033[0m");
        System.out.println("\nData de Pagamento: \033[32m" + pagamentos.getDataPagamento() + "\033[0m");

        // Tipo de Pagamento
        while (true) {
            System.out.print("Tipo de Pagamento (Dinheiro, Cartão, Pix): ");
            String tipoPagamento = scanner.nextLine().trim();
            if (!tipoPagamento.isEmpty() && (tipoPagamento.equalsIgnoreCase("Dinheiro") ||
                    tipoPagamento.equalsIgnoreCase("Cartão") || tipoPagamento.equalsIgnoreCase("Pix"))) {
                pagamentos.setTipoPagamento(tipoPagamento);
                break;
            } else {
                System.err.println("Tipo de Pagamento inválido! Escolha entre 'Dinheiro', 'Cartão' ou 'Pix'.");
            }
        }

        // Forma de Pagamento
        while (true) {
            System.out.print("Forma de Pagamento (à vista, parcelado): ");
            String formaPagamento = scanner.nextLine().trim();
            if (!formaPagamento.isEmpty() && (formaPagamento.equalsIgnoreCase("à vista") ||
                    formaPagamento.equalsIgnoreCase("parcelado"))) {
                pagamentos.setFormaPagamento(formaPagamento);
                break;
            } else {
                System.err.println("Forma de Pagamento inválida! Escolha entre 'à vista' ou 'parcelado'.");
            }
        }

        // Quantidade de Parcelas
        while (true) {
            System.out.print("Quantidade de Parcelas: ");
            String parcelasStr = scanner.nextLine().trim();
            if (!parcelasStr.isEmpty()) {
                if (parcelasStr.matches("\\d+")) {
                    int parcelas = Integer.parseInt(parcelasStr);
                    if (parcelas > 0) {
                        pagamentos.setParcelas(parcelas);
                        break;
                    } else {
                        System.err.println("A quantidade de parcelas deve ser maior que zero.");
                    }
                } else {
                    System.err.println("Quantidade de Parcelas inválida! Deve ser um número inteiro positivo.");
                }
            } else {
                System.err.println("Quantidade de Parcelas não pode ser vazia.");
            }
        }

        // Valor da Parcela
        while (true) {
            System.out.print("Valor da Parcela R$: ");
            String valorParcelaStr = scanner.nextLine().trim();
            if (!valorParcelaStr.isEmpty()) {
                try {
                    double valorParcela = Double.parseDouble(valorParcelaStr.replace(',', '.'));
                    if (valorParcela >= 0) {
                        pagamentos.setValorParcela(valorParcela);
                        break;
                    } else {
                        System.err.println("O valor da parcela não pode ser negativo.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Valor da Parcela inválido! Deve ser um número.");
                }
            } else {
                System.err.println("Valor da Parcela não pode ser vazio.");
            }
        }

        // Desconto
        while (true) {
            System.out.print("Desconto %: ");
            String descontoStr = scanner.nextLine().trim();
            if (!descontoStr.isEmpty()) {
                try {
                    double desconto = Double.parseDouble(descontoStr.replace(',', '.'));
                    if (desconto >= 0) {
                        pagamentos.setDesconto(desconto);
                        break;
                    } else {
                        System.err.println("O desconto não pode ser negativo.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Desconto inválido! Deve ser um número.");
                }
            } else {
                System.err.println("Desconto não pode ser vazio.");
            }
        }

        // Valor Total
        while (true) {
            System.out.print("Valor Total R$: ");
            String valorTotalStr = scanner.nextLine().trim();
            if (!valorTotalStr.isEmpty()) {
                try {
                    double valorTotal = Double.parseDouble(valorTotalStr.replace(',', '.'));
                    if (valorTotal >= 0) {
                        pagamentos.setValorTotal(valorTotal);
                        break;
                    } else {
                        System.err.println("O valor total não pode ser negativo.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Valor Total inválido! Deve ser um número.");
                }
            } else {
                System.err.println("Valor Total não pode ser vazio.");
            }
        }

        return pagamentos;
    }

    public void atualizarPagamento(Scanner scanner) {
        try {
            while (true) {
                System.out.print("Digite o ID do pagamento que deseja atualizar: ");
                String idStr = scanner.nextLine().trim();
                if (!idStr.isEmpty()) {
                    if (idStr.matches("\\d+")) {
                        int id = Integer.parseInt(idStr);
                        Pagamentos pagamentoExistente = pagamentosService.buscarPagamentosPorId(id);

                        if (pagamentoExistente != null) {
                            Pagamentos pagamentoAtualizado = coletarDadosPagamento(scanner);
                            pagamentoAtualizado.setIdPagemnto(id); // Mantém o mesmo ID
                            pagamentosService.atualizarPagamentos(pagamentoAtualizado);
                            System.out.println("\033[32m\033[1mPagamento atualizado com sucesso!\033[0m");
                        } else {
                            System.err.println("Pagamento com ID " + id + " não encontrado.");
                        }
                        break;
                    } else {
                        System.err.println("ID inválido! Deve ser um número inteiro.");
                    }
                } else {
                    System.err.println("ID não pode ser vazio.");
                }
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
            while (true) {
                System.out.print("Digite o ID do pagamento: ");
                String idStr = scanner.nextLine().trim();
                if (!idStr.isEmpty()) {
                    if (idStr.matches("\\d+")) {
                        int id = Integer.parseInt(idStr);
                        Pagamentos pagamento = pagamentosService.buscarPagamentosPorId(id);
                        if (pagamento != null) {
                            exibirDadosPagamento(pagamento);
                        } else {
                            System.err.println("Pagamento com ID " + id + " não encontrado.");
                        }
                        break;
                    } else {
                        System.err.println("ID inválido! Deve ser um número inteiro.");
                    }
                } else {
                    System.err.println("ID não pode ser vazio.");
                }
            }
        } catch (Exception e) {
            System.err.println("\033[31mErro ao buscar pagamento: " + e.getMessage() + "\033[0m");
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
            while (true) {
                System.out.print("Digite o ID do pagamento que deseja deletar: ");
                String idStr = scanner.nextLine().trim();
                if (!idStr.isEmpty()) {
                    if (idStr.matches("\\d+")) {
                        int idPagamento = Integer.parseInt(idStr);
                        pagamentosService.deletarPagamentos(idPagamento);
                        System.out.println("\033[32m\033[1mPagamento deletado com sucesso!\033[0m");
                        break;
                    } else {
                        System.err.println("ID inválido! Deve ser um número inteiro.");
                    }
                } else {
                    System.err.println("ID não pode ser vazio.");
                }
            }
        } catch (Exception e) {
            System.err.println("\033[31mErro ao deletar pagamento: " + e.getMessage() + "\033[0m");
        }
    }

}
