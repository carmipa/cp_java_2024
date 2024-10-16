package br.com.fiap.UI;

import br.com.fiap.model.Clientes;
import br.com.fiap.model.Contatos;
import br.com.fiap.model.Enderecos;
import br.com.fiap.service.ClientesService;
import br.com.fiap.ExternalInterface.Cep;

import java.io.IOException;
import java.util.Scanner;

public class ClientesInputHandler {

    private ClientesService clientesService;

    public ClientesInputHandler(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    public void cadastrarNovoCliente(Scanner scanner) {
        try {

            Clientes cliente = coletarDadosCliente(scanner);
            clientesService.cadastrarClientes(cliente);
            System.out.println("Cliente cadastrado com sucesso!");

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private Clientes coletarDadosCliente(Scanner scanner) {
        Clientes cliente = new Clientes();
        Enderecos endereco = new Enderecos();
        Contatos contato = new Contatos();

        System.out.println("===== 1 - CADASTRO DE CLIENTE =====");
        System.out.println();
        System.out.println("===== ENDEREÇO DO CLIENTE =====");

        // Coleta de dados do cliente
        System.out.print("Nome...........................: ");
        cliente.setNome(scanner.nextLine());
        System.out.print("Tipo de Cliente (PF/PJ)........: ");
        cliente.setTipoCliente(scanner.nextLine());
        System.out.print("Tipo de Documento (CPF/CNP.....: ");
        cliente.setTipoDocumento(scanner.nextLine());
        System.out.print("Número do Documento............: ");
        cliente.setNumeroDocumento(scanner.nextLine());
        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        cliente.setDataNacimento(scanner.nextLine());
        System.out.println();

        // Coleta de dados do endereço com consulta ao serviço de CEP
        coletarEndereco(scanner, endereco);

        // Coleta de dados de contato
        coletarContato(scanner, contato);

        // Associa o endereço e o contato ao cliente
        cliente.setEnderecos(endereco);
        cliente.setContatos(contato);

        return cliente;
    }

    private void coletarEndereco(Scanner scanner, Enderecos endereco) {
        System.out.println("===== ENDEREÇO DO CLIENTE =====");
        System.out.println("Número...................:");
        endereco.setNumero(scanner.nextInt());
        scanner.nextLine(); // Limpa o buffer
        System.out.print("CEP........................: ");
        String cep = scanner.nextLine();

        try {
            // Utiliza a API de CEP para buscar o endereço
            Enderecos enderecoViaCep = Cep.buscarEnderecoPorCep(cep);

            if (enderecoViaCep != null) {
                endereco.setCep(cep);
                endereco.setLogadouro(enderecoViaCep.getLogadouro());
                endereco.setBairro(enderecoViaCep.getBairro());
                endereco.setCidade(enderecoViaCep.getCidade());
                endereco.setEstado(enderecoViaCep.getEstado());

                System.out.println("Endereço encontrado:");
                System.out.println("Logradouro: " + endereco.getLogadouro());
                System.out.println("Bairro: " + endereco.getBairro());
                System.out.println("Cidade: " + endereco.getCidade());
                System.out.println("Estado: " + endereco.getEstado());
            } else {
                System.out.println();
                System.err.println("CEP não encontrado. Preencha os dados manualmente.");
                System.out.println();
                System.out.print("Logradouro.................: ");
                endereco.setLogadouro(scanner.nextLine());
                System.out.print("Bairro.....................: ");
                endereco.setBairro(scanner.nextLine());
                System.out.print("Cidade.....................: ");
                endereco.setCidade(scanner.nextLine());
                System.out.print("Estado.....................: ");
                endereco.setEstado(scanner.nextLine());
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao buscar o CEP: " + e.getMessage());
        }

        System.out.print("Complemento................: ");
        endereco.setComplemento(scanner.nextLine());
    }

    private void coletarContato(Scanner scanner, Contatos contato) {
        System.out.println();
        System.out.println("===== CONTATOS DO CLIENTE =====");
        System.out.print("Telefone....................: ");
        contato.setTelefone(scanner.nextLine());
        System.out.print("E-mail......................: ");
        contato.setEmail(scanner.nextLine());
        System.out.print("Contato.....................: ");
        contato.setContato(scanner.nextLine());
    }
}
