package br.com.fiap.UI;

import br.com.fiap.model.Seguros;
import br.com.fiap.service.SegurosService;

import java.util.InputMismatchException;
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
        }
    }

    private Seguros coletarDadosSeguro(Scanner scanner) {
        Seguros seguro = new Seguros();

        System.out.println("\033[34m\033[1m===== 1 - CADASTRO DE SEGURO =====\033[0m");

        try {
            System.out.print("Tipo de Seguro (Auto/Vida)........: ");
            seguro.setTipoSeguro(scanner.nextLine());

            System.out.print("Perfil do seguro (Básico/Completo): ");
            seguro.setPerfil(scanner.nextLine());

            System.out.print("Valor do Seguro...................: ");
            double valor = scanner.nextDouble();
            if (valor < 0) throw new IllegalArgumentException("O valor do seguro não pode ser negativo.");
            seguro.setValor(valor);

        } catch (InputMismatchException e) {
            System.err.println("\033[31mErro: Entrada inválida. Por favor, insira os valores corretamente.\033[0m");
            scanner.nextLine(); // Limpa o buffer do scanner
            throw new IllegalArgumentException("Erro durante a coleta de dados do seguro.");
        }
        scanner.nextLine(); // Limpa o buffer do scanner

        return seguro;
    }

    public void atualizarSeguro(Scanner scanner) {
        try {
            System.out.print("Digite o ID do seguro que deseja atualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            Seguros seguroExistente = segurosService.buscarSegurosPorId(id);

            if (seguroExistente != null) {
                Seguros seguroAtualizado = coletarDadosSeguro(scanner);
                seguroAtualizado.setIdSeguro(id); // Mantém o mesmo ID
                segurosService.atualizarSeguros(seguroAtualizado);
                System.out.println("\033[32m\033[1mSeguro atualizado com sucesso!\033[0m");
            } else {
                System.out.println("\033[31mSeguro com ID " + id + " não encontrado.\033[0m");
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
            System.out.print("Digite o ID do seguro: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            Seguros seguro = segurosService.buscarSegurosPorId(id);
            if (seguro != null) {
                exibirDadosSeguro(seguro);
            } else {
                System.out.println("\033[31mSeguro com ID " + id + " não encontrado.\033[0m");
            }
        } catch (InputMismatchException e) {
            System.err.println("\033[31mErro: Entrada inválida. Por favor, insira os valores corretamente.\033[0m");
            scanner.nextLine(); // Limpa o buffer do scanner
        }
    }

    public void deletarSeguro(Scanner scanner) {
        try {
            System.out.print("Digite o ID do seguro que deseja deletar: ");
            int idSeguro = scanner.nextInt();
            segurosService.deletarSeguros(idSeguro);
            System.out.println("\033[32m\033[1mSeguro deletado com sucesso!\033[0m");
        } catch (InputMismatchException e) {
            System.err.println("\033[31mErro: Entrada inválida. Por favor, insira os valores corretamente.\033[0m");
            scanner.nextLine(); // Limpa o buffer do scanner
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