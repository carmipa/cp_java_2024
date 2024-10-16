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

    // Método para cadastrar novo cliente
    public void cadastrarNovoCliente(Scanner scanner) {
        try {
            // Coleta os dados do cliente
            Clientes cliente = coletarDadosCliente(scanner);

            // Chama o método do serviço para cadastrar o cliente no banco de dados
            clientesService.cadastrarClientes(cliente);
            System.out.println("Cliente cadastrado com sucesso!");

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    // Método para coletar dados de cliente
    private Clientes coletarDadosCliente(Scanner scanner) {
        Clientes cliente = new Clientes();
        Enderecos endereco = new Enderecos();
        Contatos contato = new Contatos();

        // Coleta de dados do cliente
        System.out.println("===== 1 - CADASTRO DE CLIENTE =====");
        System.out.print("Nome...........................: ");
        cliente.setNome(scanner.nextLine());
        System.out.print("Tipo de Cliente (PF/PJ)........: ");
        cliente.setTipoCliente(scanner.nextLine());
        System.out.print("Tipo de Documento (CPF/CNPJ)...: ");
        cliente.setTipoDocumento(scanner.nextLine());
        System.out.print("Número do Documento............: ");
        cliente.setNumeroDocumento(scanner.nextLine());
        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        cliente.setDataNacimento(scanner.nextLine());

        // Coleta de dados do endereço e consulta via CEP
        coletarEndereco(scanner, endereco);

        // Coleta de dados de contato
        coletarContato(scanner, contato);

        // Associa o endereço e o contato ao cliente
        cliente.setEnderecos(endereco);
        cliente.setContatos(contato);

        return cliente;
    }

    // Método para coletar e consultar endereço via CEP
    private void coletarEndereco(Scanner scanner, Enderecos endereco) {
        System.out.println("===== ENDEREÇO DO CLIENTE =====");

        System.out.print("CEP........................: ");
        String cep = scanner.nextLine();

        try {
            // Usa a API de CEP para preencher os dados de endereço
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
                System.out.println("CEP não encontrado. Preencha os dados manualmente.");
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

        System.out.print("Número.....................: ");
        endereco.setNumero(scanner.nextInt());
        scanner.nextLine(); // Limpa o buffer
        System.out.print("Complemento................: ");
        endereco.setComplemento(scanner.nextLine());
    }

    // Método para coletar dados de contato
    private void coletarContato(Scanner scanner, Contatos contato) {
        System.out.println("===== CONTATOS DO CLIENTE =====");
        System.out.print("Telefone....................: ");
        contato.setTelefone(scanner.nextLine());
        System.out.print("E-mail......................: ");
        contato.setEmail(scanner.nextLine());
        System.out.print("Contato.....................: ");
        contato.setContato(scanner.nextLine());
    }

    // Métodos adicionais para CRUD (opcional, dependendo das suas necessidades)
    public void atualizarCliente(Scanner scanner) {
        System.out.print("Digite o ID do cliente que deseja atualizar: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        Clientes cliente = coletarDadosCliente(scanner);
        cliente.setIdCliente(idCliente);
        clientesService.atualizarClientes(cliente);
        System.out.println("Cliente atualizado com sucesso!");
    }

    public void deletarCliente(Scanner scanner) {
        System.out.print("Digite o ID do cliente que deseja deletar: ");
        int idCliente = scanner.nextInt();
        clientesService.deletarClientes(idCliente);
        System.out.println("Cliente deletado com sucesso!");
    }

    public void listarClientes() {
        clientesService.listarTodosClientes().forEach(cliente -> {
            System.out.println(cliente);
        });
    }

    // Método para buscar e exibir cliente por ID
    public void buscarClientePorId(Scanner scanner) {
        System.out.print("Digite o ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        Clientes cliente = clientesService.buscarClientesPorId(id);

        if (cliente != null) {
            exibirDadosCliente(cliente); // Exibe os dados do cliente encontrado
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    // Método para exibir os dados de um cliente
    private void exibirDadosCliente(Clientes cliente) {
        System.out.println("===== DADOS DO CLIENTE =====");
        System.out.println("ID: " + cliente.getIdCliente());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Tipo de Cliente: " + cliente.getTipoCliente());
        System.out.println("Tipo de Documento: " + cliente.getTipoDocumento());
        System.out.println("Número de Documento: " + cliente.getNumeroDocumento());
        System.out.println("Data de Nascimento: " + cliente.getDataNacimento());

        // Exibe dados do endereço
        Enderecos endereco = cliente.getEnderecos();
        if (endereco != null) {
            System.out.println("===== ENDEREÇO =====");
            System.out.println("CEP: " + endereco.getCep());
            System.out.println("Logradouro: " + endereco.getLogadouro());
            System.out.println("Número: " + endereco.getNumero());
            System.out.println("Complemento: " + endereco.getComplemento());
            System.out.println("Cidade: " + endereco.getCidade());
            System.out.println("Estado: " + endereco.getEstado());
        }

        // Exibe dados de contato
        Contatos contato = cliente.getContatos();
        if (contato != null) {
            System.out.println("===== CONTATOS =====");
            System.out.println("Telefone: " + contato.getTelefone());
            System.out.println("E-mail: " + contato.getEmail());
            System.out.println("Contato: " + contato.getContato());
        }
    }
    
}
