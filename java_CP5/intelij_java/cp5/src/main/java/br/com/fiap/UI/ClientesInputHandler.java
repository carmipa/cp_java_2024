package br.com.fiap.UI;

import br.com.fiap.ExternalInterface.CpfCnpj;
import br.com.fiap.model.Clientes;
import br.com.fiap.model.Contatos;
import br.com.fiap.model.Enderecos;
import br.com.fiap.service.ClientesService;
import br.com.fiap.ExternalInterface.Cep;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientesInputHandler {

    private ClientesService clientesService;

    public ClientesInputHandler(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    public void cadastrarNovoCliente(Scanner scanner) {
        try {
            Clientes cliente = coletarDadosCliente(scanner);
            if (cliente != null) {
                clientesService.cadastrarClientes(cliente);
                System.out.println("\033[32m\033[1mCliente cadastrado com sucesso!\033[0m");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("\033[31mErro ao cadastrar cliente: " + e.getMessage() + "\033[0m");
        }
    }

    private Clientes coletarDadosCliente(Scanner scanner) {
        Clientes cliente = new Clientes();
        Enderecos endereco = new Enderecos();
        Contatos contato = new Contatos();

        System.out.println("\033[34m\033[1m===== 1 - CADASTRO DE CLIENTE =====\033[0m");
        System.out.println("\033[34m===== DADOS DO CLIENTE =====\033[0m");

        try {
            while (true) {
                System.out.print("Nome...........................: ");
                cliente.setNome(scanner.nextLine().trim());
                if (!cliente.getNome().isEmpty()) break;
                System.err.println("Nome não pode ser vazio.");
            }

            while (true) {
                System.out.print("Tipo de Cliente (PF/PJ)........: ");
                cliente.setTipoCliente(scanner.nextLine().trim());
                if (!cliente.getTipoCliente().isEmpty()) break;
                System.err.println("Tipo de Cliente não pode ser vazio.");
            }

            while (true) {
                System.out.print("Documento (CPF ou CNPJ)........: ");
                String documento = scanner.nextLine().trim();
                if (documento.matches("\\d+")) {
                    String tipoDocumento = CpfCnpj.validarDocumento(documento); // Valida o documento
                    if (tipoDocumento.equals("CPF") || tipoDocumento.equals("CNPJ")) {
                        cliente.setNumeroDocumento(documento);
                        cliente.setTipoDocumento(tipoDocumento); // Define o tipo de documento
                        System.out.println("Documento válido (" + tipoDocumento + ").");
                        break;
                    } else {
                        System.err.println(tipoDocumento); // Exibe o erro caso o documento seja inválido
                    }
                } else {
                    System.err.println("Documento inválido! Deve conter apenas dígitos.");
                }
            }

            while (true) {
                System.out.print("Data de Nascimento (dd/MM/yyyy): ");
                cliente.setDataNacimento(scanner.nextLine().trim());
                if (!cliente.getDataNacimento().isEmpty()) break;
                System.err.println("Data de Nascimento não pode ser vazia.");
            }
            System.out.println();

            coletarEndereco(scanner, endereco);
            coletarContato(scanner, contato);

            cliente.setEnderecos(endereco);
            cliente.setContatos(contato);

        } catch (InputMismatchException e) {
            System.err.println("\033[31mErro: Entrada inválida. Por favor, insira os valores corretamente.\033[0m");
            scanner.nextLine(); // Limpa o buffer do scanner
            throw new IllegalArgumentException("Erro durante a coleta de dados do cliente.");
        }

        return cliente;
    }

    private void coletarEndereco(Scanner scanner, Enderecos endereco) {
        System.out.println("\033[34m===== ENDEREÇO DO CLIENTE =====\033[0m");
        try {
            while (true) {
                System.out.print("Número.........................: ");
                String numero = scanner.nextLine().trim();
                if (numero.matches("\\d+")) {
                    endereco.setNumero(Integer.parseInt(numero));
                    break;
                }
                System.err.println("Número deve conter apenas dígitos.");
            }

            while (true) {
                System.out.print("CEP............................: ");
                String cep = scanner.nextLine().trim();
                if (cep.matches("\\d{5}-\\d{3}")) {
                    try {
                        Enderecos enderecoViaCep = Cep.buscarEnderecoPorCep(cep);
                        if (enderecoViaCep != null) {
                            endereco.setCep(cep);
                            endereco.setLogadouro(enderecoViaCep.getLogradouro());
                            endereco.setBairro(enderecoViaCep.getBairro());
                            endereco.setCidade(enderecoViaCep.getCidade());
                            endereco.setEstado(enderecoViaCep.getEstado());

                            System.out.println("Endereço encontrado:");
                            System.out.println("Logradouro: " + endereco.getLogradouro());
                            System.out.println("Bairro: " + endereco.getBairro());
                            System.out.println("Cidade: " + endereco.getCidade());
                            System.out.println("Estado: " + endereco.getEstado());
                        } else {
                            System.out.println();
                            System.err.println("CEP não encontrado. Preencha os dados manualmente.");
                            System.out.println();
                            System.out.print("Logradouro.....................: ");
                            endereco.setLogadouro(scanner.nextLine().trim());
                            System.out.print("Bairro.........................: ");
                            endereco.setBairro(scanner.nextLine().trim());
                            System.out.print("Cidade.........................: ");
                            endereco.setCidade(scanner.nextLine().trim());
                            System.out.print("Estado.........................: ");
                            endereco.setEstado(scanner.nextLine().trim());
                        }
                        break;
                    } catch (IOException | InterruptedException e) {
                        System.err.println("Erro ao buscar o CEP: " + e.getMessage());
                    }
                } else {
                    System.err.println("CEP deve estar no formato 12345-678.");
                }
            }

            while (true) {
                System.out.print("Complemento....................: ");
                String complemento = scanner.nextLine().trim();
                if (!complemento.isEmpty()) {
                    endereco.setComplemento(complemento);
                    break;
                }
                System.err.println("Complemento não pode ser vazio.");
            }

        } catch (InputMismatchException e) {
            System.err.println("\033[31mErro: Entrada inválida. Por favor, insira os valores corretamente.\033[0m");
            scanner.nextLine(); // Limpa o buffer do scanner
            throw new IllegalArgumentException("Erro durante a coleta de dados do endereço.");
        }
    }

    private void coletarContato(Scanner scanner, Contatos contato) {
        System.out.println("\033[34m===== CONTATOS DO CLIENTE =====\033[0m");
        try {
            while (true) {
                System.out.print("Telefone.......................: ");
                String telefone = scanner.nextLine().trim();
                if (telefone.matches("\\d+")) {
                    contato.setTelefone(telefone);
                    break;
                }
                System.err.println("Telefone deve conter apenas dígitos.");
            }

            while (true) {
                System.out.print("E-mail.........................: ");
                contato.setEmail(scanner.nextLine().trim());
                if (!contato.getEmail().isEmpty()) break;
                System.err.println("E-mail não pode ser vazio.");
            }

            while (true) {
                System.out.print("Contato........................: ");
                contato.setContato(scanner.nextLine().trim());
                if (!contato.getContato().isEmpty()) break;
                System.err.println("Contato não pode ser vazio.");
            }
        } catch (InputMismatchException e) {
            System.err.println("\033[31mErro: Entrada inválida. Por favor, insira os valores corretamente.\033[0m");
            scanner.nextLine(); // Limpa o buffer do scanner
            throw new IllegalArgumentException("Erro durante a coleta de dados de contato.");
        }
    }

    public void atualizarCliente(Scanner scanner) {
        try {
            System.out.print("Digite o ID do cliente que deseja atualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            Clientes clienteExistente = clientesService.buscarClientesPorId(id);

            if (clienteExistente != null) {
                Clientes clienteAtualizado = coletarDadosCliente(scanner);
                clienteAtualizado.setIdCliente(id); // Mantém o mesmo ID
                clientesService.atualizarClientes(clienteAtualizado);
                System.out.println("\033[32m\033[1mCliente atualizado com sucesso!\033[0m");
            } else {
                System.out.println("\033[31mCliente com ID " + id + " não encontrado.\033[0m");
            }

        } catch (IllegalArgumentException e) {
            System.err.println("\033[31mErro ao atualizar cliente: " + e.getMessage() + "\033[0m");
        }
    }

    public void listarClientes() {
        System.out.println("\033[34m\033[1m===== LISTA DE CLIENTES =====\033[0m");
        clientesService.listarTodosClientes().forEach(cliente -> {
            exibirDadosCliente(cliente);
            System.out.println("-----------------------------------");
        });
    }

    public void buscarClientePorId(Scanner scanner) {
        try {
            System.out.print("Digite o ID do cliente: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            Clientes cliente = clientesService.buscarClientesPorId(id);
            if (cliente != null) {
                exibirDadosCliente(cliente);
            } else {
                System.out.println("\033[31mCliente com ID " + id + " não encontrado.\033[0m");
            }
        } catch (InputMismatchException e) {
            System.err.println("\033[31mErro: Entrada inválida. Por favor, insira os valores corretamente.\033[0m");
            scanner.nextLine(); // Limpa o buffer do scanner
        }
    }

    public void deletarCliente(Scanner scanner) {
        try {
            System.out.print("Digite o ID do cliente que deseja deletar: ");
            int idCliente = scanner.nextInt();
            clientesService.deletarClientes(idCliente);
            System.out.println("\033[32m\033[1mCliente deletado com sucesso!\033[0m");
        } catch (InputMismatchException e) {
            System.err.println("\033[31mErro: Entrada inválida. Por favor, insira os valores corretamente.\033[0m");
            scanner.nextLine(); // Limpa o buffer do scanner
        }
    }

    private void exibirDadosCliente(Clientes cliente) {
        System.out.println("\033[34m\033[1m===== DADOS DO CLIENTE =====\033[0m");
        System.out.println("ID: " + cliente.getIdCliente());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Tipo de Cliente: " + cliente.getTipoCliente());
        System.out.println("Tipo de Documento: " + cliente.getTipoDocumento());
        System.out.println("Número do Documento: " + cliente.getNumeroDocumento());
        System.out.println("Data de Nascimento: " + cliente.getDataNacimento());

        Enderecos endereco = cliente.getEnderecos();
        if (endereco != null) {
            System.out.println("\033[34m===== ENDEREÇO =====\033[0m");
            System.out.println("CEP: " + endereco.getCep());
            System.out.println("Logradouro: " + endereco.getLogradouro());
            System.out.println("Número: " + endereco.getNumero());
            System.out.println("Complemento: " + endereco.getComplemento());
            System.out.println("Bairro: " + endereco.getBairro());
            System.out.println("Cidade: " + endereco.getCidade());
            System.out.println("Estado: " + endereco.getEstado());
        }

        Contatos contato = cliente.getContatos();
        if (contato != null) {
            System.out.println("\033[34m===== CONTATO =====\033[0m");
            System.out.println("Telefone: " + contato.getTelefone());
            System.out.println("E-mail: " + contato.getEmail());
            System.out.println("Contato: " + contato.getContato());
        }
    }

    private void preencherEnderecoManual(Scanner scanner, Enderecos endereco) {
        System.out.print("Logradouro.....................: ");
        endereco.setLogadouro(scanner.nextLine().trim());
        System.out.print("Bairro.........................: ");
        endereco.setBairro(scanner.nextLine().trim());
        System.out.print("Cidade.........................: ");
        endereco.setCidade(scanner.nextLine().trim());
        System.out.print("Estado.........................: ");
        endereco.setEstado(scanner.nextLine().trim());
    }
}
