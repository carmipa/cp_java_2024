package br.com.fiap.UI;

import br.com.fiap.model.Seguros;
import br.com.fiap.service.SegurosService;

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
            System.out.println("Seguro cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private Seguros coletarDadosSeguro(Scanner scanner) {

        Seguros seguro = new Seguros();

        System.out.println("===== 1 - CADASTRO DE SEGURO =====");
        System.out.println();
        System.out.println("Tipo de Seguro (Auto/Vida)........: ");
        seguro.setTipoSeguro(scanner.nextLine());
        System.out.println("Prefil do seguro (Básico/Completo): ");
        seguro.setPerfil(scanner.nextLine());
        System.out.println("Valor do Seguro...................: ");
        seguro.setValor(Double.parseDouble(scanner.nextLine()));
        scanner.nextLine();

        return seguro;
    }

    public void atualizarSeguro(Scanner scanner) {
        try {
            Seguros seguro = coletarDadosSeguro(scanner);
            segurosService.atualizarSeguros(seguro);
            System.out.println("Seguro atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public void desetarSeguro(Scanner scanner){
        System.out.print("Digite o ID do seguro que deseja deletar: ");
        int idSeguro = scanner.nextInt();
        segurosService.deletarSeguros(idSeguro);
        System.out.println("Seguro deletado com sucesso!");

    }

    public void listarSeguros(){
        segurosService.listarTodosSeguros().forEach(seguros -> {System.out.println(seguros);});
    }

}
