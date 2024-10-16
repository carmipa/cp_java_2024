package br.com.fiap.model;

public final class SegurosBuilder {
    private int idSeguro;
    private String tipoSeguro;
    private String perfil;
    private double valor;

    public SegurosBuilder() {
    }

    public SegurosBuilder(Seguros other) {
        this.idSeguro = other.getIdSeguro();
        this.tipoSeguro = other.getTipoSeguro();
        this.perfil = other.getPerfil();
        this.valor = other.getValor();
    }

    public static SegurosBuilder aSeguros() {
        return new SegurosBuilder();
    }

    public SegurosBuilder withIdSeguro(int idSeguro) {
        this.idSeguro = idSeguro;
        return this;
    }

    public SegurosBuilder withTipoSeguro(String tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
        return this;
    }

    public SegurosBuilder withPerfil(String perfil) {
        this.perfil = perfil;
        return this;
    }

    public SegurosBuilder withValor(double valor) {
        this.valor = valor;
        return this;
    }

    public Seguros build() {
        Seguros seguros = new Seguros();
        seguros.setIdSeguro(idSeguro);
        seguros.setTipoSeguro(tipoSeguro);
        seguros.setPerfil(perfil);
        seguros.setValor(valor);
        return seguros;
    }
}
