package br.com.fiap.builder;

import br.com.fiap.model.Clientes;
import br.com.fiap.model.Pagamentos;
import br.com.fiap.model.Seguros;

public final class ClientesBuilder {
    private int idCliente;
    private String tipoCliente;
    private String nome;
    private String tipoDocumento;
    private String numeroDocumento;
    private String dataNacimento;
    private Seguros seguros;
    private Pagamentos pagamentos;

    public ClientesBuilder() {
    }

    public ClientesBuilder(br.com.fiap.model.Clientes other) {
        this.idCliente = other.getIdCliente();
        this.tipoCliente = other.getTipoCliente();
        this.nome = other.getNome();
        this.tipoDocumento = other.getTipoDocumento();
        this.numeroDocumento = other.getNumeroDocumento();
        this.dataNacimento = other.getDataNacimento();
        this.seguros = other.getSeguros();
        this.pagamentos = other.getPagamentos();
    }

    public static ClientesBuilder aClientes() {
        return new ClientesBuilder();
    }

    public ClientesBuilder withIdCliente(int idCliente) {
        this.idCliente = idCliente;
        return this;
    }

    public ClientesBuilder withTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
        return this;
    }

    public ClientesBuilder withNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ClientesBuilder withTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
        return this;
    }

    public ClientesBuilder withNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
        return this;
    }

    public ClientesBuilder withDataNacimento(String dataNacimento) {
        this.dataNacimento = dataNacimento;
        return this;
    }

    public ClientesBuilder withSeguros(Seguros seguros) {
        this.seguros = seguros;
        return this;
    }

    public ClientesBuilder withPagamentos(Pagamentos pagamentos) {
        this.pagamentos = pagamentos;
        return this;
    }

    public Clientes build() {
        Clientes clientes = new Clientes();
        clientes.setIdCliente(idCliente);
        clientes.setTipoCliente(tipoCliente);
        clientes.setNome(nome);
        clientes.setTipoDocumento(tipoDocumento);
        clientes.setNumeroDocumento(numeroDocumento);
        clientes.setDataNacimento(dataNacimento);
        clientes.setSeguros(seguros);
        clientes.setPagamentos(pagamentos);
        return clientes;
    }
}
