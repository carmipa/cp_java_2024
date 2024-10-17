package br.com.fiap.UI;

import br.com.fiap.ExternalInterface.CpfCnpj;
import br.com.fiap.model.Clientes;
import br.com.fiap.model.Contatos;
import br.com.fiap.model.Enderecos;
import br.com.fiap.service.ClientesService;
import br.com.fiap.ExternalInterface.Cep;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientesInputHandler {

    // Cores e formatação
    private static final String RESET = "\033[0m";
    private static final String BOLD = "\033[1m";
    private static final String GREEN = "\033[32m";
    private static final String RED = "\033[31m";
    private static final String BLUE = "\033[34m";
    private static final String CYAN = "\033[36m";
    private static final String YELLOW = "\033[33m";
    private static final String PINK = "\033[35m";

    private ClientesService clientesService;

    public ClientesInputHandler(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    public void cadastrarNovoCliente(Scanner scanner) {
        try {
            Clientes cliente = coletarDadosCliente(scanner);
            clientesService.cadastrarClientes(cliente);
            System.out.println(GREEN + BOLD + "Cliente cadastrado com sucesso!" + RESET);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(RED + "Erro ao cadastrar cliente: " + e.getMessage() + RESET);
        }
    }

    private Clientes coletarDadosCliente(Scanner scanner) {
        Clientes cliente = new Clientes();
        Enderecos endereco = new Enderecos();
        Contatos contato = new Contatos();

        System.out.println(BLUE + BOLD + "╔═══════════════════ 1 - CADASTRO DE CLIENTE ═══════════════════╗" + RESET);
        System.out.println(BLUE + "===== DADOS DO CLIENTE =====" + RESET);

        try {
            while (true) {
                System.out.print("Nome...........................: ");
                cliente.setNome(scanner.nextLine().trim());
                if (!cliente.getNome().isEmpty()) break;
                System.err.println(RED + "Nome não pode ser vazio." + RESET);
            }

            while (true) {
                System.out.print("Tipo de Cliente (PF/PJ)........: ");
                cliente.setTipoCliente(scanner.nextLine().trim());
                if (!cliente.getTipoCliente().isEmpty()) break;
                System.err.println(RED + "Tipo de Cliente não pode ser vazio." + RESET);
            }

            while (true) {
                System.out.print("Documento (CPF ou CNPJ)........: ");
                String documento = scanner.nextLine().trim();
                if (documento.matches("\\d{11}|\\d{14}")) {
                    String tipoDocumento = CpfCnpj.validarDocumento(documento);
                    if (tipoDocumento.equals("CPF") || tipoDocumento.equals("CNPJ")) {
                        cliente.setNumeroDocumento(documento);
                        cliente.setTipoDocumento(tipoDocumento);
                        System.out.println(GREEN + "Documento válido (" + tipoDocumento + ")." + RESET);
                        break;
                    } else {
                        System.err.println(RED + tipoDocumento + RESET);
                    }
                } else {
                    System.err.println(RED + "Documento inválido! CPF deve ter 11 dígitos e CNPJ 14 dígitos." + RESET);
                }
            }

            while (true) {
                System.out.print("Data de Nascimento (dd/MM/yyyy): ");
                String dataNascimento = scanner.nextLine().trim();
                if (dataNascimento.matches("\\d{2}/\\d{2}/\\d{4}")) {
                    cliente.setDataNacimento(dataNascimento);
                    break;
                } else {
                    System.err.println(RED + "Data de Nascimento inválida! Use o formato dd/MM/yyyy." + RESET);
                }
            }
            System.out.println();

            coletarEndereco(scanner, endereco);
            coletarContato(scanner, contato);

            cliente.setEnderecos(endereco);
            cliente.setContatos(contato);

        } catch (InputMismatchException e) {
            System.err.println(RED + "Erro: Entrada inválida. Por favor, insira os valores corretamente." + RESET);
            scanner.nextLine();
            throw new IllegalArgumentException("Erro durante a coleta de dados do cliente.");
        }

        return cliente;
    }

    private void coletarEndereco(Scanner scanner, Enderecos endereco) {
        System.out.println(BLUE + "===== ENDEREÇO DO CLIENTE =====" + RESET);
        try {
            while (true) {
                System.out.print("Número.........................: ");
                String numero = scanner.nextLine().trim();
                if (numero.matches("\\d+")) {
                    endereco.setNumero(Integer.parseInt(numero));
                    break;
                }
                System.err.println(RED + "Número deve conter apenas dígitos." + RESET);
            }

            while (true) {
                System.out.print("CEP (99999-999):...............: ");
                String cep = scanner.nextLine().trim();
                if (cep.matches("\\d{5}-\\d{3}")) {
                    try {
                        Enderecos enderecoViaCep = Cep.buscarEnderecoPorCep(cep);
                        if (enderecoViaCep != null) {
                            endereco.setCep(cep);
                            endereco.setLogradouro(enderecoViaCep.getLogradouro());
                            endereco.setBairro(enderecoViaCep.getBairro());
                            endereco.setCidade(enderecoViaCep.getCidade());
                            endereco.setEstado(enderecoViaCep.getEstado());

                            System.out.println(GREEN + "Endereço encontrado:" );
                            System.out.println("Logradouro.....................: " + endereco.getLogradouro());
                            System.out.println("Bairro.........................: " + endereco.getBairro());
                            System.out.println("Cidade.........................: " + endereco.getCidade());
                            System.out.println("Estado.........................: " + endereco.getEstado() + RESET);
                        } else {
                            System.out.println();
                            System.err.println(RED + "CEP não encontrado. Preencha os dados manualmente." + RESET);
                            preencherEnderecoManual(scanner, endereco);
                        }
                        break;
                    } catch (IOException | InterruptedException e) {
                        System.err.println(RED + "Erro ao buscar o CEP: " + e.getMessage() + RESET);
                    }
                } else {
                    System.err.println(RED + "CEP deve estar no formato 12345-678." + RESET);
                }
            }

            while (true) {
                System.out.print("Complemento....................: ");
                String complemento = scanner.nextLine().trim();
                if (!complemento.isEmpty()) {
                    endereco.setComplemento(complemento);
                    break;
                }
                System.err.println(RED + "Complemento não pode ser vazio." + RESET);
            }

        } catch (InputMismatchException e) {
            System.err.println(RED + "Erro: Entrada inválida. Por favor, insira os valores corretamente." + RESET);
            scanner.nextLine();
            throw new IllegalArgumentException("Erro durante a coleta de dados do endereço.");
        }
    }

    private void coletarContato(Scanner scanner, Contatos contato) {
        System.out.println(BLUE + "===== CONTATOS DO CLIENTE =====" + RESET);
        try {
            while (true) {
                System.out.print("Telefone ((99) 99999-9999):....: ");
                String telefone = scanner.nextLine().trim();
                if (telefone.matches("\\(\\d{2}\\) \\d{5}-\\d{4}")) {
                    contato.setTelefone(telefone);
                    break;
                }
                System.err.println(RED + "Telefone inválido! Use o formato (99) 99999-9999." + RESET);
            }

            while (true) {
                System.out.print("E-mail.........................: ");
                contato.setEmail(scanner.nextLine().trim());
                if (!contato.getEmail().isEmpty()) break;
                System.err.println(RED + "E-mail não pode ser vazio." + RESET);
            }

            while (true) {
                System.out.print("Contato........................: ");
                contato.setContato(scanner.nextLine().trim());
                if (!contato.getContato().isEmpty()) break;
                System.err.println(RED + "Contato não pode ser vazio." + RESET);
            }
        } catch (InputMismatchException e) {
            System.err.println(RED + "Erro: Entrada inválida. Por favor, insira os valores corretamente." + RESET);
            scanner.nextLine();
            throw new IllegalArgumentException("Erro durante a coleta de dados de contato.");
        }
    }

    public void atualizarCliente(Scanner scanner) {
        try {
            System.out.print(BOLD + "Digite o ID do cliente que deseja atualizar: " + RESET);
            int id = scanner.nextInt();
            scanner.nextLine();

            Clientes clienteExistente = clientesService.buscarClientesPorId(id);

            if (clienteExistente != null) {
                Clientes clienteAtualizado = coletarDadosCliente(scanner);
                clienteAtualizado.setIdCliente(id);
                clientesService.atualizarClientes(clienteAtualizado);
                System.out.println(GREEN + BOLD + "Cliente atualizado com sucesso!" + RESET);
            } else {
                System.out.println(RED + "Cliente com ID " + id + " não encontrado." + RESET);
            }

        } catch (IllegalArgumentException e) {
            System.err.println(RED + "Erro ao atualizar cliente: " + e.getMessage() + RESET);
        }
    }

    public void listarClientes() {
        System.out.println(BLUE + BOLD + "===== LISTA DE CLIENTES =====" + RESET);
        clientesService.listarTodosClientes().forEach(cliente -> {
            exibirDadosCliente(cliente);
            System.out.println(BLUE + "-----------------------------------" + RESET);
        });
    }

    public void buscarClientePorId(Scanner scanner) {
        try {
            System.out.print(BOLD + "Digite o ID do cliente: " + RESET);
            int id = scanner.nextInt();
            scanner.nextLine();

            Clientes cliente = clientesService.buscarClientesPorId(id);
            if (cliente != null) {
                exibirDadosCliente(cliente);
            } else {
                System.out.println(RED + "Cliente com ID " + id + " não encontrado." + RESET);
            }
        } catch (InputMismatchException e) {
            System.err.println(RED + "Erro: Entrada inválida. Por favor, insira os valores corretamente." + RESET);
            scanner.nextLine();
        }
    }

    public void deletarCliente(Scanner scanner) {
        try {
            System.out.print(BOLD + "Digite o ID do cliente que deseja deletar: " + RESET);
            int idCliente = scanner.nextInt();
            clientesService.deletarClientes(idCliente);
            System.out.println(GREEN + BOLD + "Cliente deletado com sucesso!" + RESET);
        } catch (InputMismatchException e) {
            System.err.println(RED + "Erro: Entrada inválida. Por favor, insira os valores corretamente." + RESET);
            scanner.nextLine();
        }
    }

    private void exibirDadosCliente(Clientes cliente) {
        System.out.println(BLUE + BOLD + "===== DADOS DO CLIENTE =====" + RESET);
        System.out.println("ID..................: " + cliente.getIdCliente());
        System.out.println("Nome................: " + cliente.getNome());
        System.out.println("Tipo de Cliente.....: " + cliente.getTipoCliente());
        System.out.println("Tipo de Documento...: " + cliente.getTipoDocumento());
        System.out.println("Número do Documento.: " + cliente.getNumeroDocumento());
        System.out.println("Data de Nascimento..: " + cliente.getDataNacimento());

        Enderecos endereco = cliente.getEnderecos();
        if (endereco != null) {
            System.out.println(BLUE + "===== ENDEREÇO =====" + RESET);
            System.out.println("CEP.............: " + endereco.getCep());
            System.out.println("Logradouro......: " + endereco.getLogradouro());
            System.out.println("Número..........: " + endereco.getNumero());
            System.out.println("Complemento.....: " + endereco.getComplemento());
            System.out.println("Bairro..........: " + endereco.getBairro());
            System.out.println("Cidade..........: " + endereco.getCidade());
            System.out.println("Estado..........: " + endereco.getEstado());
        }

        Contatos contato = cliente.getContatos();
        if (contato != null) {
            System.out.println(BLUE + "===== CONTATO =====" + RESET);
            System.out.println("Telefone........: " + contato.getTelefone());
            System.out.println("E-mail..........: " + contato.getEmail());
            System.out.println("Contato.........: " + contato.getContato());
        }
    }

    private void preencherEnderecoManual(Scanner scanner, Enderecos endereco) {
        System.out.print("Logradouro.....................: ");
        endereco.setLogradouro(scanner.nextLine().trim());
        System.out.print("Bairro.........................: ");
        endereco.setBairro(scanner.nextLine().trim());
        System.out.print("Cidade.........................: ");
        endereco.setCidade(scanner.nextLine().trim());
        System.out.print("Estado.........................: ");
        endereco.setEstado(scanner.nextLine().trim());
    }
}
