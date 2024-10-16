package br.com.fiap.model;

import br.com.fiap.InterfaceModel.IPagamentos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Pagamentos implements IPagamentos {

    private int idPagemnto;
    private String dataPagamento;
    private String tipoPagamento;
    private String formaPagamento;
    private int parcelas;
    private double valorParcela;
    private double desconto;
    private double valorTotal;
    private Clientes clientes;
    private Seguros seguros;

    public Pagamentos() {
        super();
    }

    public Pagamentos(int idPagemnto, String dataPagamento, String tipoPagamento, String formaPagamento, int parcelas, double valorParcela, double desconto, double valorTotal) {
        this.idPagemnto = idPagemnto;
        this.dataPagamento = dataPagamento;
        this.tipoPagamento = tipoPagamento;
        this.formaPagamento = formaPagamento;
        this.parcelas = parcelas;
        this.valorParcela = valorParcela;
        this.desconto = desconto;
        this.valorTotal = valorTotal;
    }

    public int getIdPagemnto() {
        return idPagemnto;
    }

    public void setIdPagemnto(int idPagemnto) {
        this.idPagemnto = idPagemnto;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public Seguros getSeguros() {
        return seguros;
    }

    public void setSeguros(Seguros seguros) {
        this.seguros = seguros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pagamentos that)) return false;
        return idPagemnto == that.idPagemnto && parcelas == that.parcelas && Double.compare(valorParcela, that.valorParcela) == 0 && Double.compare(desconto, that.desconto) == 0 && Double.compare(valorTotal, that.valorTotal) == 0 && Objects.equals(dataPagamento, that.dataPagamento) && Objects.equals(tipoPagamento, that.tipoPagamento) && Objects.equals(formaPagamento, that.formaPagamento) && Objects.equals(clientes, that.clientes) && Objects.equals(seguros, that.seguros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPagemnto, dataPagamento, tipoPagamento, formaPagamento, parcelas, valorParcela, desconto, valorTotal, clientes, seguros);
    }

    @Override
    public String toString() {
        return "Pagamentos{" +
                "idPagemnto=" + idPagemnto +
                ", dataPagamento='" + dataPagamento + '\'' +
                ", tipoPagamento='" + tipoPagamento + '\'' +
                ", formaPagamento='" + formaPagamento + '\'' +
                ", parcelas=" + parcelas +
                ", valorParcela=" + valorParcela +
                ", desconto=" + desconto +
                ", valorTotal=" + valorTotal +
                '}';
    }

    @Override
    public String definirDataPagamentoAtual() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataAtual = LocalDate.now();
        this.dataPagamento = dataAtual.format(formatter);

        return this.dataPagamento;
    }
}