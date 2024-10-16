package br.com.fiap.builder;

import br.com.fiap.model.Enderecos;

public final class EnderecosBuilder {
    private int idEndereco;
    private int numero;
    private String cep;
    private String logadouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String complemento;

    public EnderecosBuilder() {
    }

    public EnderecosBuilder(br.com.fiap.model.Enderecos other) {
        this.idEndereco = other.getIdEndereco();
        this.numero = other.getNumero();
        this.cep = other.getCep();
        this.logadouro = other.getLogadouro();
        this.bairro = other.getBairro();
        this.cidade = other.getCidade();
        this.estado = other.getEstado();
        this.complemento = other.getComplemento();
    }

    public static EnderecosBuilder anEnderecos() {
        return new EnderecosBuilder();
    }

    public EnderecosBuilder withIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
        return this;
    }

    public EnderecosBuilder withNumero(int numero) {
        this.numero = numero;
        return this;
    }

    public EnderecosBuilder withCep(String cep) {
        this.cep = cep;
        return this;
    }

    public EnderecosBuilder withLogadouro(String logadouro) {
        this.logadouro = logadouro;
        return this;
    }

    public EnderecosBuilder withBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public EnderecosBuilder withCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public EnderecosBuilder withEstado(String estado) {
        this.estado = estado;
        return this;
    }

    public EnderecosBuilder withComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public Enderecos build() {
        Enderecos enderecos = new Enderecos();
        enderecos.setIdEndereco(idEndereco);
        enderecos.setNumero(numero);
        enderecos.setCep(cep);
        enderecos.setLogadouro(logadouro);
        enderecos.setBairro(bairro);
        enderecos.setCidade(cidade);
        enderecos.setEstado(estado);
        enderecos.setComplemento(complemento);
        return enderecos;
    }
}
