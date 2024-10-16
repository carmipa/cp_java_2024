package br.com.fiap.model;

import java.util.Objects;

public class Clientes {

    private int idCliente;
    private String tipoCliente;
    private String nome;
    private String tipoDocumento;
    private String numeroDocumento;
    private String dataNacimento;
    private Enderecos enderecos;
    private Contatos contatos;
    private Seguros seguros;
    private Pagamentos pagamentos;

    public Clientes() {
    }

    public Clientes(int idCliente, String tipoCliente, String nome, String tipoDocumento, String numeroDocumento, String dataNacimento) {
        this.idCliente = idCliente;
        this.tipoCliente = tipoCliente;
        this.nome = nome;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.dataNacimento = dataNacimento;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getDataNacimento() {
        return dataNacimento;
    }

    public void setDataNacimento(String dataNacimento) {
        this.dataNacimento = dataNacimento;
    }

    public Enderecos getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Enderecos enderecos) {
        this.enderecos = enderecos;
    }

    public Contatos getContatos() {
        return contatos;
    }

    public void setContatos(Contatos contatos) {
        this.contatos = contatos;
    }

    public Seguros getSeguros() {
        return seguros;
    }

    public void setSeguros(Seguros seguros) {
        this.seguros = seguros;
    }

    public Pagamentos getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(Pagamentos pagamentos) {
        this.pagamentos = pagamentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clientes clientes)) return false;
        return idCliente == clientes.idCliente && Objects.equals(tipoCliente, clientes.tipoCliente) && Objects.equals(nome, clientes.nome) && Objects.equals(tipoDocumento, clientes.tipoDocumento) && Objects.equals(numeroDocumento, clientes.numeroDocumento) && Objects.equals(dataNacimento, clientes.dataNacimento) && Objects.equals(enderecos, clientes.enderecos) && Objects.equals(contatos, clientes.contatos) && Objects.equals(seguros, clientes.seguros) && Objects.equals(pagamentos, clientes.pagamentos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, tipoCliente, nome, tipoDocumento, numeroDocumento, dataNacimento, enderecos, contatos, seguros, pagamentos);
    }

    @Override
    public String toString() {
        return "Clientes{" +
                "idCliente=" + idCliente +
                ", tipoCliente='" + tipoCliente + '\'' +
                ", nome='" + nome + '\'' +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", dataNacimento='" + dataNacimento + '\'' +
                '}';
    }



}
