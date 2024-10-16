package br.com.fiap.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Enderecos {

    private int idEndereco;
    private int numero;
    private String cep;
    private String logadouro;
    private String bairro;
    @SerializedName("localidade")  // Mapeia o campo "localidade" da resposta JSON para "cidade"
    private String cidade;
    @SerializedName("uf")  // Mapeia o campo "uf" da resposta JSON para "estado"
    private String estado;
    private String complemento;

    public Enderecos() {
    }

    public Enderecos(int idEndereco, int numero, String cep, String logadouro, String bairro, String cidade, String estado, String complemento) {
        this.idEndereco = idEndereco;
        this.numero = numero;
        this.cep = cep;
        this.logadouro = logadouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.complemento = complemento;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLogadouro() {
        return logadouro;
    }

    public void setLogadouro(String logadouro) {
        this.logadouro = logadouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enderecos enderecos)) return false;
        return idEndereco == enderecos.idEndereco && numero == enderecos.numero && Objects.equals(cep, enderecos.cep) && Objects.equals(logadouro, enderecos.logadouro) && Objects.equals(bairro, enderecos.bairro) && Objects.equals(cidade, enderecos.cidade) && Objects.equals(estado, enderecos.estado) && Objects.equals(complemento, enderecos.complemento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEndereco, numero, cep, logadouro, bairro, cidade, estado, complemento);
    }

    @Override
    public String toString() {
        return "Enderecos{" +
                "idEndereco=" + idEndereco +
                ", numero=" + numero +
                ", cep='" + cep + '\'' +
                ", logadouro='" + logadouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", complemento='" + complemento + '\'' +
                '}';
    }
}
