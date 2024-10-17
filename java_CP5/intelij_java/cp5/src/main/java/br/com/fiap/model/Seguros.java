package br.com.fiap.model;

import java.util.Objects;

public class Seguros {

    private int idSeguro;
    private String tipoSeguro;
    private String perfil;
    private double valor;
    private Clientes clientes;
    private Pagamentos pagamentos;


    public Seguros() {
        super();
    }

    public Seguros(int idSeguro, String tipoSeguro, String perfil, double valor) {
        super();
        this.idSeguro = idSeguro;
        this.tipoSeguro = tipoSeguro;
        this.perfil = perfil;
        this.valor = valor;
    }

    public int getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(int idSeguro) {
        this.idSeguro = idSeguro;
    }

    public String getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(String tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seguros seguros)) return false;
        return idSeguro == seguros.idSeguro && Double.compare(valor, seguros.valor) == 0 && Objects.equals(tipoSeguro, seguros.tipoSeguro) && Objects.equals(perfil, seguros.perfil) && Objects.equals(clientes, seguros.clientes) && Objects.equals(pagamentos, seguros.pagamentos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSeguro, tipoSeguro, perfil, valor, clientes, pagamentos);
    }

    @Override
    public String toString() {
        return "Seguros{" +
                "idSeguro=" + idSeguro +
                ", tipoSeguro='" + tipoSeguro + '\'' +
                ", perfil='" + perfil + '\'' +
                ", valor=" + valor +
                '}';
    }

    
}
