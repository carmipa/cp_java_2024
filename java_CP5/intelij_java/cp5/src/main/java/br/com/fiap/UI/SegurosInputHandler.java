package br.com.fiap.UI;

import br.com.fiap.model.Seguros;
import br.com.fiap.service.SegurosService;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class SegurosInputHandler {

    // Cores e formatação
    private static final String RESET = "\033[0m";
    private static final String BOLD = "\033[1m";
    private static final String GREEN = "\033[32m";
    private static final String RED = "\033[31m";
    private static final String BLUE = "\033[34m";
    private static final String CYAN = "\033[36m";

    private SegurosService segurosService;
    private static final DecimalFormat df = new DecimalFormat("#,##0.00"); // Formatação para valores monetários

    public SegurosInputHandler(SegurosService segurosService) {
        this.segurosService = segurosService;
    }

    public void cadastrarNovoSeguro(Scanner scanner) {
        try {
            Seguros seguro = coletarDadosSeguro(scanner);
            segurosService.cadastrarSeguros(seguro);
            System.out.println(GREEN + BOLD + "Seguro cadastrado com sucesso!" + RESET);
        } catch (IllegalArgumentException e) {
            System.err.println(RED + "Erro ao cadastrar seguro: " + e.getMessage() + RESET);
        } catch (SQLException e) {
            System.err.println(RED + "Erro ao cadastrar seguro no banco de dados: " + e.getMessage() + RESET);
        }
    }

    private Seguros coletarDadosSeguro(Scanner scanner) {
        Seguros seguro = new Seguros();

        System.out.println(BLUE + BOLD + "╔═════════════════════ 1 - CADASTRO DE SEGURO ═══════════════════╗" + RESET);

        // Tipo de Seguro
        while (true) {
            System.out.print("Tipo de Seguro (Auto/Vida): ");
            String tipoSeguro = scanner.nextLine().trim();
            if (!tipoSeguro.isEmpty() && (tipoSeguro.equalsIgnoreCase("Auto") ||
                    tipoSeguro.equalsIgnoreCase("Vida"))) {
                seguro.setTipoSeguro(tipoSeguro);
                break;
            } else {
                System.err.println(RED + "Tipo de Seguro inválido! Escolha entre 'Auto' ou 'Vida'." + RESET);
            }
        }

        // Perfil do Seguro
        while (true) {
            System.out.print("Perfil do Seguro (Básico/Completo): ");
            String perfil = scanner.nextLine().trim();
            if (!perfil.isEmpty() && (perfil.equalsIgnoreCase("Básico") ||
                    perfil.equalsIgnoreCase("Completo"))) {
                seguro.setPerfil(perfil);
                break;
            } else {
                System.err.println(RED + "Perfil do Seguro inválido! Escolha entre 'Básico' ou 'Completo'." + RESET);
            }
        }

        // Valor do Seguro (com máscara para entrada numérica e formatação)
        while (true) {
            System.out.print("Valor do Seguro (ex: 1000,00): ");
            String valorStr = scanner.nextLine().trim();
            if (!valorStr.isEmpty()) {
                try {
                    double valor = Double.parseDouble(valorStr.replace(",", "."));
                    if (valor >= 0) {
                        seguro.setValor(valor);
                        break;
                    } else {
                        System.err.println(RED + "O valor do seguro não pode ser negativo." + RESET);
                    }
                } catch (NumberFormatException e) {
                    System.err.println(RED + "Valor do Seguro inválido! Deve ser um número." + RESET);
                }
            } else {
                System.err.println(RED + "Valor do Seguro não pode ser vazio." + RESET);
            }
        }

        return seguro;
    }

    public void atualizarSeguro(Scanner scanner) {
        try {
            while (true) {
                System.out.print(BOLD + "Digite o ID do seguro que deseja atualizar: " + RESET);
                String idStr = scanner.nextLine().trim();
                if (!idStr.isEmpty()) {
                    if (idStr.matches("\\d+")) {
                        int id = Integer.parseInt(idStr);
                        Seguros seguroExistente = segurosService.buscarSegurosPorId(id);

                        if (seguroExistente != null) {
                            Seguros seguroAtualizado = coletarDadosSeguro(scanner);
                            seguroAtualizado.setIdSeguro(id); // Mantém o mesmo ID
                            segurosService.atualizarSeguros(seguroAtualizado);
                            System.out.println(GREEN + BOLD + "Seguro atualizado com sucesso!" + RESET);
                        } else {
                            System.err.println(RED + "Seguro com ID " + id + " não encontrado." + RESET);
                        }
                        break;
                    } else {
                        System.err.println(RED + "ID inválido! Deve ser um número inteiro." + RESET);
                    }
                } else {
                    System.err.println(RED + "ID não pode ser vazio." + RESET);
                }
            }
        } catch (SQLException e) {
            System.err.println(RED + "Erro ao atualizar seguro no banco de dados: " + e.getMessage() + RESET);
        } catch (IllegalArgumentException e) {
            System.err.println(RED + "Erro ao atualizar seguro: " + e.getMessage() + RESET);
        }
    }

    public void listarSeguros() {
        try {
            System.out.println(BLUE + BOLD + "===== LISTA DE SEGUROS =====" + RESET);
            segurosService.listarTodosSeguros().forEach(seguros -> {
                exibirDadosSeguro(seguros);
                System.out.println(BLUE + "-----------------------------------" + RESET);
            });
        } catch (SQLException e) {
            System.err.println(RED + "Erro ao listar seguros: " + e.getMessage() + RESET);
        }
    }

    public void buscarSeguroPorId(Scanner scanner) {
        try {
            while (true) {
                System.out.print(BOLD + "Digite o ID do seguro: " + RESET);
                String idStr = scanner.nextLine().trim();
                if (!idStr.isEmpty()) {
                    if (idStr.matches("\\d+")) {
                        int id = Integer.parseInt(idStr);
                        Seguros seguro = segurosService.buscarSegurosPorId(id);
                        if (seguro != null) {
                            exibirDadosSeguro(seguro);
                        } else {
                            System.err.println(RED + "Seguro com ID " + id + " não encontrado." + RESET);
                        }
                        break;
                    } else {
                        System.err.println(RED + "ID inválido! Deve ser um número inteiro." + RESET);
                    }
                } else {
                    System.err.println(RED + "ID não pode ser vazio." + RESET);
                }
            }
        } catch (SQLException e) {
            System.err.println(RED + "Erro ao buscar seguro: " + e.getMessage() + RESET);
        }
    }

    public void deletarSeguro(Scanner scanner) {
        try {
            while (true) {
                System.out.print(BOLD + "Digite o ID do seguro que deseja deletar: " + RESET);
                String idStr = scanner.nextLine().trim();
                if (!idStr.isEmpty()) {
                    if (idStr.matches("\\d+")) {
                        int idSeguro = Integer.parseInt(idStr);
                        segurosService.deletarSeguros(idSeguro);
                        System.out.println(GREEN + BOLD + "Seguro deletado com sucesso!" + RESET);
                        break;
                    } else {
                        System.err.println(RED + "ID inválido! Deve ser um número inteiro." + RESET);
                    }
                } else {
                    System.err.println(RED + "ID não pode ser vazio." + RESET);
                }
            }
        } catch (SQLException e) {
            System.err.println(RED + "Erro ao deletar seguro: " + e.getMessage() + RESET);
        }
    }

    private void exibirDadosSeguro(Seguros seguro) {
        System.out.println(BLUE + BOLD + "===== DADOS DO SEGURO =====" + RESET);
        System.out.println("ID do Seguro........: " + seguro.getIdSeguro());
        System.out.println("Tipo de Seguro......: " + seguro.getTipoSeguro());
        System.out.println("Perfil do Seguro....: " + seguro.getPerfil());
        System.out.println("Valor do Seguro.....: R$ " + df.format(seguro.getValor())); // Formatação com 2 casas decimais
    }
}
