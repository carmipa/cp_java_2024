package br.com.fiap.UI;

import br.com.fiap.model.Pagamentos;
import br.com.fiap.service.PagamentosService;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class PagamentosInputHandler {

    // Cores e formatação
    private static final String RESET = "\033[0m";
    private static final String BOLD = "\033[1m";
    private static final String GREEN = "\033[32m";
    private static final String RED = "\033[31m";
    private static final String BLUE = "\033[34m";
    private static final String CYAN = "\033[36m";
    private static final String YELLOW = "\033[33m";

    private PagamentosService pagamentosService;
    private static final DecimalFormat df = new DecimalFormat("#,##0.00"); // Formatação para valores monetários

    public PagamentosInputHandler(PagamentosService pagamentosService) {
        this.pagamentosService = pagamentosService;
    }

    public void cadastrarNovoPagamento(Scanner scanner) {
        try {
            Pagamentos pagamento = coletarDadosPagamento(scanner);
            pagamentosService.cadastrarPagamentos(pagamento);
            System.out.println(GREEN + BOLD + "Pagamento cadastrado com sucesso!" + RESET);
        } catch (IllegalArgumentException e) {
            System.err.println(RED + "Erro ao cadastrar pagamento: " + e.getMessage() + RESET);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Pagamentos coletarDadosPagamento(Scanner scanner) {
        Pagamentos pagamentos = new Pagamentos();
        pagamentos.definirDataPagamentoAtual();
        System.out.println(BLUE + BOLD + "╔═════════════════════ 1 - CADASTRO DE PAGAMENTO ═══════════════════╗" + RESET);
        System.out.println("Data de Pagamento: " + GREEN + pagamentos.getDataPagamento() + RESET);

        // Tipo de Pagamento com opções numéricas
        while (true) {
            System.out.println("Selecione o tipo de pagamento:");
            System.out.println("1 - Débito");
            System.out.println("2 - Pix");
            System.out.println("3 - Cartão de Crédito");
            System.out.print("Escolha uma opção (1, 2 ou 3): ");
            String opcaoPagamento = scanner.nextLine().trim();

            switch (opcaoPagamento) {
                case "1":
                    pagamentos.setTipoPagamento("Débito");
                    break;
                case "2":
                    pagamentos.setTipoPagamento("Pix");
                    break;
                case "3":
                    pagamentos.setTipoPagamento("Cartão de Crédito");
                    break;
                default:
                    System.err.println(RED + "Opção inválida! Escolha entre 1, 2 ou 3." + RESET);
                    continue;
            }
            break;
        }

        // Forma de Pagamento com opções numéricas
        while (true) {
            System.out.println("Selecione a forma de pagamento:");
            System.out.println("1 - À vista");
            System.out.println("2 - Parcelado");
            System.out.print("Escolha uma opção (1 ou 2): ");
            String opcaoFormaPagamento = scanner.nextLine().trim();

            switch (opcaoFormaPagamento) {
                case "1":
                    pagamentos.setFormaPagamento("À vista");
                    pagamentos.setParcelas(1); // Se for à vista, define automaticamente 1 parcela
                    break;
                case "2":
                    pagamentos.setFormaPagamento("Parcelado");
                    // Quantidade de Parcelas
                    while (true) {
                        System.out.print("Quantidade de Parcelas: ");
                        String parcelasStr = scanner.nextLine().trim();
                        if (!parcelasStr.isEmpty() && parcelasStr.matches("\\d+")) {
                            int parcelas = Integer.parseInt(parcelasStr);
                            if (parcelas > 0) {
                                pagamentos.setParcelas(parcelas);
                                break;
                            } else {
                                System.err.println(RED + "A quantidade de parcelas deve ser maior que zero." + RESET);
                            }
                        } else {
                            System.err.println(RED + "Quantidade de Parcelas inválida! Deve ser um número inteiro positivo." + RESET);
                        }
                    }
                    break;
                default:
                    System.err.println(RED + "Opção inválida! Escolha entre 1 ou 2." + RESET);
                    continue;
            }
            break;
        }

        // Valor do Serviço (usado apenas para cálculo)
        while (true) {
            System.out.print("Valor do Serviço R$: ");
            String valorServicoStr = scanner.nextLine().trim();
            if (!valorServicoStr.isEmpty()) {
                try {
                    double valorServico = Double.parseDouble(valorServicoStr.replace(',', '.'));
                    if (valorServico >= 0) {
                        pagamentos.setValorServico(valorServico);
                        break;
                    } else {
                        System.err.println(RED + "O valor do serviço não pode ser negativo." + RESET);
                    }
                } catch (NumberFormatException e) {
                    System.err.println(RED + "Valor do Serviço inválido! Deve ser um número." + RESET);
                }
            } else {
                System.err.println(RED + "Valor do Serviço não pode ser vazio." + RESET);
            }
        }

        // Desconto calculado automaticamente
        double desconto = calcularDesconto(pagamentos.getTipoPagamento());
        pagamentos.setDesconto(desconto);
        System.out.println(YELLOW + "Desconto aplicado: " + desconto + "%" + RESET);

        // Calcula o valor total e as parcelas com base no valor do serviço e desconto
        pagamentos.calcularValorTotalEParcelas();
        System.out.println(GREEN + "Valor total com desconto: R$ " + df.format(pagamentos.getValorTotal()) + RESET);
        System.out.println(GREEN + "Valor de cada parcela: R$ " + df.format(pagamentos.getValorParcela()) + RESET);

        return pagamentos;
    }

    private double calcularDesconto(String tipoPagamento) {
        switch (tipoPagamento.toLowerCase()) {
            case "pix":
                return 10.0;
            case "débito":
                return 5.0;
            case "cartão de crédito":
                return 2.0;
            default:
                return 0.0;
        }
    }




    public void atualizarPagamento(Scanner scanner) {
        try {
            while (true) {
                System.out.print(BOLD + "Digite o ID do pagamento que deseja atualizar: " + RESET);
                String idStr = scanner.nextLine().trim();
                if (!idStr.isEmpty()) {
                    if (idStr.matches("\\d+")) {
                        int id = Integer.parseInt(idStr);
                        Pagamentos pagamentoExistente = pagamentosService.buscarPagamentosPorId(id);

                        if (pagamentoExistente != null) {
                            Pagamentos pagamentoAtualizado = coletarDadosPagamento(scanner);
                            pagamentoAtualizado.setIdPagemnto(id); // Mantém o mesmo ID
                            pagamentosService.atualizarPagamentos(pagamentoAtualizado);
                            System.out.println(GREEN + BOLD + "Pagamento atualizado com sucesso!" + RESET);
                        } else {
                            System.err.println(RED + "Pagamento com ID " + id + " não encontrado." + RESET);
                        }
                        break;
                    } else {
                        System.err.println(RED + "ID inválido! Deve ser um número inteiro." + RESET);
                    }
                } else {
                    System.err.println(RED + "ID não pode ser vazio." + RESET);
                }
            }
        } catch (IllegalArgumentException e) {
            System.err.println(RED + "Erro ao atualizar pagamento: " + e.getMessage() + RESET);
        }
    }

    public void listarPagamentos() {
        System.out.println(BLUE + BOLD + "===== LISTA DE PAGAMENTOS =====" + RESET);
        pagamentosService.listarTodosPagamentos().forEach(pagamento -> {
            exibirDadosPagamento(pagamento);
            System.out.println(BLUE + "-----------------------------------" + RESET);
        });
    }

    public void buscarPagamentoPorId(Scanner scanner) {
        try {
            while (true) {
                System.out.print(BOLD + "Digite o ID do pagamento: " + RESET);
                String idStr = scanner.nextLine().trim();
                if (!idStr.isEmpty()) {
                    if (idStr.matches("\\d+")) {
                        int id = Integer.parseInt(idStr);
                        Pagamentos pagamento = pagamentosService.buscarPagamentosPorId(id);
                        if (pagamento != null) {
                            exibirDadosPagamento(pagamento);
                        } else {
                            System.err.println(RED + "Pagamento com ID " + id + " não encontrado." + RESET);
                        }
                        break;
                    } else {
                        System.err.println(RED + "ID inválido! Deve ser um número inteiro." + RESET);
                    }
                } else {
                    System.err.println(RED + "ID não pode ser vazio." + RESET);
                }
            }
        } catch (Exception e) {
            System.err.println(RED + "Erro ao buscar pagamento: " + e.getMessage() + RESET);
        }
    }

    private void exibirDadosPagamento(Pagamentos pagamentos) {
        System.out.println(BLUE + BOLD + "===== DADOS DO PAGAMENTO =====" + RESET);
        System.out.println("ID: " + pagamentos.getIdPagemnto());
        System.out.println("Data de Pagamento: " + pagamentos.getDataPagamento());
        System.out.println("Tipo de Pagamento: " + pagamentos.getTipoPagamento());
        System.out.println("Forma de Pagamento: " + pagamentos.getFormaPagamento());
        System.out.println("Parcelas: " + pagamentos.getParcelas());
        System.out.println("Valor da Parcela: " + df.format(pagamentos.getValorParcela()));
        System.out.println("Desconto: " + df.format(pagamentos.getDesconto()) + "%");
        System.out.println("Valor Total: " + df.format(pagamentos.getValorTotal()));
    }

    public void deletarPagamento(Scanner scanner) {
        try {
            while (true) {
                System.out.print(BOLD + "Digite o ID do pagamento que deseja deletar: " + RESET);
                String idStr = scanner.nextLine().trim();
                if (!idStr.isEmpty()) {
                    if (idStr.matches("\\d+")) {
                        int idPagamento = Integer.parseInt(idStr);
                        pagamentosService.deletarPagamentos(idPagamento);
                        System.out.println(GREEN + BOLD + "Pagamento deletado com sucesso!" + RESET);
                        break;
                    } else {
                        System.err.println(RED + "ID inválido! Deve ser um número inteiro." + RESET);
                    }
                } else {
                    System.err.println(RED + "ID não pode ser vazio." + RESET);
                }
            }
        } catch (Exception e) {
            System.err.println(RED + "Erro ao deletar pagamento: " + e.getMessage() + RESET);
        }
    }
}