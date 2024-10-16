package br.com.fiap.UI;

import br.com.fiap.model.Seguros;
import br.com.fiap.service.SegurosService;

import java.sql.SQLException;
import java.util.Scanner;

public class SegurosInputHandler {

    private SegurosService segurosService;

    public SegurosInputHandler(SegurosService segurosService) {
        this.segurosService = segurosService;
    }

    public void cadastrarNovoSeguro(Scanner scanner) {
        try {
            Seguros seguro = coletarDadosSeguro(scanner);
            segurosService.cadastrarSeguros(seguro);
            System.out.println("\033[32m\033[1mSeguro cadastrado com sucesso!\033[0m");
        } catch (IllegalArgumentException e) {
            System.err.println("\033[31mErro ao cadastrar seguro: " + e.getMessage() + "\033[0m");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Seguros coletarDadosSeguro(Scanner scanner) {
        Seguros seguro = new Seguros();

        System.out.println("\033[34m\033[1m===== 1 - CADASTRO DE SEGURO =====\033[0m");

        // Tipo de Seguro
        while (true) {
            System.out.print("Tipo de Seguro (Auto/Vida): ");
            String tipoSeguro = scanner.nextLine().trim();
            if (!tipoSeguro.isEmpty() && (tipoSeguro.equalsIgnoreCase("Auto") ||
                    tipoSeguro.equalsIgnoreCase("Vida"))) {
                seguro.setTipoSeguro(tipoSeguro);
                break;
            } else {
                System.err.println("Tipo de Seguro inválido! Escolha entre 'Auto' ou 'Vida'.");
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
                System.err.println("Perfil do Seguro inválido! Escolha entre 'Básico' ou 'Completo'.");
            }
        }

        // Valor do Seguro
        while (true) {
            System.out.print("Valor do Seguro: ");
            String valorStr = scanner.nextLine().trim();
            if (!valorStr.isEmpty()) {
                try {
                    double valor = Double.parseDouble(valorStr.replace(',', '.'));
                    if (valor >= 0) {
                        seguro.setValor(valor);
                        break;
                    } else {
                        System.err.println("O valor do seguro não pode ser negativo.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Valor do Seguro inválido! Deve ser um número.");
                }
            } else {
                System.err.println("Valor do Seguro não pode ser vazio.");
            }
        }

        return seguro;
    }

    public void atualizarSeguro(Scanner scanner) {
        try {
            while (true) {
                System.out.print("Digite o ID do seguro que deseja atualizar: ");
                String idStr = scanner.nextLine().trim();
                if (!idStr.isEmpty()) {
                    if (idStr.matches("\\d+")) {
                        int id = Integer.parseInt(idStr);
                        Seguros seguroExistente = segurosService.buscarSegurosPorId(id);

                        if (seguroExistente != null) {
                            Seguros seguroAtualizado = coletarDadosSeguro(scanner);
                            seguroAtualizado.setIdSeguro(id); // Mantém o mesmo ID
                            segurosService.atualizarSeguros(seguroAtualizado);
                            System.out.println("\033[32m\033[1mSeguro atualizado com sucesso!\033[0m");
                        } else {
                            System.err.println("Seguro com ID " + id + " não encontrado.");
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
            System.err.println("\033[31mErro ao atualizar seguro: " + e.getMessage() + "\033[0m");
        }
    }

    public void listarSeguros() {
        System.out.println("\033[34m\033[1m===== LISTA DE SEGUROS =====\033[0m");
        segurosService.listarTodosSeguros().forEach(seguros -> {
            exibirDadosSeguro(seguros);
            System.out.println("-----------------------------------");
        });
    }

    public void buscarSeguroPorId(Scanner scanner) {
        try {
            while (true) {
                System.out.print("Digite o ID do seguro: ");
                String idStr = scanner.nextLine().trim();
                if (!idStr.isEmpty()) {
                    if (idStr.matches("\\d+")) {
                        int id = Integer.parseInt(idStr);
                        Seguros seguro = segurosService.buscarSegurosPorId(id);
                        if (seguro != null) {
                            exibirDadosSeguro(seguro);
                        } else {
                            System.err.println("Seguro com ID " + id + " não encontrado.");
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
            System.err.println("\033[31mErro ao buscar seguro: " + e.getMessage() + "\033[0m");
        }
    }

    public void deletarSeguro(Scanner scanner) {
        try {
            while (true) {
                System.out.print("Digite o ID do seguro que deseja deletar: ");
                String idStr = scanner.nextLine().trim();
                if (!idStr.isEmpty()) {
                    if (idStr.matches("\\d+")) {
                        int idSeguro = Integer.parseInt(idStr);
                        segurosService.deletarSeguros(idSeguro);
                        System.out.println("\033[32m\033[1mSeguro deletado com sucesso!\033[0m");
                        break;
                    } else {
                        System.err.println("ID inválido! Deve ser um número inteiro.");
                    }
                } else {
                    System.err.println("ID não pode ser vazio.");
                }
            }
        } catch (Exception e) {
            System.err.println("\033[31mErro ao deletar seguro: " + e.getMessage() + "\033[0m");
        }
    }

    private void exibirDadosSeguro(Seguros seguro) {
        System.out.println("\033[34m\033[1m===== DADOS DO SEGURO =====\033[0m");
        System.out.println("ID do Seguro........: " + seguro.getIdSeguro());
        System.out.println("Tipo de Seguro......: " + seguro.getTipoSeguro());
        System.out.println("Perfil do Seguro....: " + seguro.getPerfil());
        System.out.println("Valor do Seguro.....: " + seguro.getValor());
    }
}
