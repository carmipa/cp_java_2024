package br.com.fiap.builder;

import br.com.fiap.model.Pagamentos;

public final class PagamentosBuilder {
    private String dataPagamento;
    private String tipoPagamento;
    private String formaPagamento;
    private int parcelas;
    private double valorParcela;
    private double desconto;
    private double valorTotal;
    private int idPagemnto;

    public PagamentosBuilder() {
    }

    public PagamentosBuilder(br.com.fiap.model.Pagamentos other) {
        this.dataPagamento = other.getDataPagamento();
        this.tipoPagamento = other.getTipoPagamento();
        this.formaPagamento = other.getFormaPagamento();
        this.parcelas = other.getParcelas();
        this.valorParcela = other.getValorParcela();
        this.desconto = other.getDesconto();
        this.valorTotal = other.getValorTotal();
        this.idPagemnto = other.getIdPagemnto();
    }

    public static PagamentosBuilder aPagamentos() {
        return new PagamentosBuilder();
    }

    public PagamentosBuilder withDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
        return this;
    }

    public PagamentosBuilder withTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
        return this;
    }

    public PagamentosBuilder withFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
        return this;
    }

    public PagamentosBuilder withParcelas(int parcelas) {
        this.parcelas = parcelas;
        return this;
    }

    public PagamentosBuilder withValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
        return this;
    }

    public PagamentosBuilder withDesconto(double desconto) {
        this.desconto = desconto;
        return this;
    }

    public PagamentosBuilder withValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
        return this;
    }

    public PagamentosBuilder withIdPagemnto(int idPagemnto) {
        this.idPagemnto = idPagemnto;
        return this;
    }

    public Pagamentos build() {
        Pagamentos pagamentos = new Pagamentos();
        pagamentos.setDataPagamento(dataPagamento);
        pagamentos.setTipoPagamento(tipoPagamento);
        pagamentos.setFormaPagamento(formaPagamento);
        pagamentos.setParcelas(parcelas);
        pagamentos.setValorParcela(valorParcela);
        pagamentos.setDesconto(desconto);
        pagamentos.setValorTotal(valorTotal);
        pagamentos.setIdPagemnto(idPagemnto);
        return pagamentos;
    }
}
