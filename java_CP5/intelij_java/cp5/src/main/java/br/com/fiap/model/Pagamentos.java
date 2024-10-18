package br.com.fiap.model;

import br.com.fiap.InterfaceModel.IPagamentos;

import java.time.LocalDate;
import java.util.Objects;

public class Pagamentos implements IPagamentos {

    private int idPagemnto;
    private String dataPagamento;
    private String tipoPagamento;
    private String formaPagamento;
    private double valorServico;
    private double desconto;
    private double valorTotal;
    private int parcelas;
    private double valorParcela;
    private Clientes clientes;
    private Seguros seguros;

    public Pagamentos() {
        super();
    }

    public Pagamentos(int idPagemnto, String dataPagamento, String tipoPagamento, String formaPagamento, double valorServico, double desconto, double valorTotal, int parcelas, double valorParcela) {
        this.idPagemnto = idPagemnto;
        this.dataPagamento = dataPagamento;
        this.tipoPagamento = tipoPagamento;
        this.formaPagamento = formaPagamento;
        this.valorServico = valorServico;
        this.desconto = desconto;
        this.valorTotal = valorTotal;
        this.parcelas = parcelas;
        this.valorParcela = valorParcela;
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

    public double getValorServico() {
        return valorServico;
    }

    public void setValorServico(double valorServico) {
        this.valorServico = valorServico;
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
        return idPagemnto == that.idPagemnto && Double.compare(valorServico, that.valorServico) == 0 && Double.compare(desconto, that.desconto) == 0 && Double.compare(valorTotal, that.valorTotal) == 0 && parcelas == that.parcelas && Double.compare(valorParcela, that.valorParcela) == 0 && Objects.equals(dataPagamento, that.dataPagamento) && Objects.equals(tipoPagamento, that.tipoPagamento) && Objects.equals(formaPagamento, that.formaPagamento) && Objects.equals(clientes, that.clientes) && Objects.equals(seguros, that.seguros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPagemnto, dataPagamento, tipoPagamento, formaPagamento, valorServico, desconto, valorTotal, parcelas, valorParcela, clientes, seguros);
    }

    @Override
    public String toString() {
        return "Pagamentos{" +
                "idPagemnto=" + idPagemnto +
                ", dataPagamento='" + dataPagamento + '\'' +
                ", tipoPagamento='" + tipoPagamento + '\'' +
                ", formaPagamento='" + formaPagamento + '\'' +
                ", valorServico=" + valorServico +
                ", desconto=" + desconto +
                ", valorTotal=" + valorTotal +
                ", parcelas=" + parcelas +
                ", valorParcela=" + valorParcela +
                '}';
    }

    @Override
    public String definirDataPagamentoAtual() {
        this.dataPagamento = LocalDate.now().toString();
        return this.dataPagamento;
    }


    @Override
    public void calcularValorTotalEParcelas() {
        // Calcula o valor total com desconto
        this.valorTotal = this.valorServico - this.desconto;

        // Verifica se a quantidade de parcelas é válida
        if (this.parcelas > 0) {
            // Divide o valor total pelo número de parcelas
            this.valorParcela = this.valorTotal / this.parcelas;
        } else {
            // Se não houver parcelas válidas, o valor da parcela é o valor total
            this.valorParcela = this.valorTotal;
        }
    }
}