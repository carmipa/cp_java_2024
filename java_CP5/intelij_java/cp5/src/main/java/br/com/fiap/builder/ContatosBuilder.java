package br.com.fiap.builder;

import br.com.fiap.model.Contatos;

public final class ContatosBuilder {
    private int idContato;
    private String telefone;
    private String email;
    private String contato;

    public ContatosBuilder() {
    }

    public ContatosBuilder(br.com.fiap.model.Contatos other) {
        this.idContato = other.getIdContato();
        this.telefone = other.getTelefone();
        this.email = other.getEmail();
        this.contato = other.getContato();
    }

    public static ContatosBuilder aContatos() {
        return new ContatosBuilder();
    }

    public ContatosBuilder withIdContato(int idContato) {
        this.idContato = idContato;
        return this;
    }

    public ContatosBuilder withTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public ContatosBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContatosBuilder withContato(String contato) {
        this.contato = contato;
        return this;
    }

    public Contatos build() {
        Contatos contatos = new Contatos();
        contatos.setIdContato(idContato);
        contatos.setTelefone(telefone);
        contatos.setEmail(email);
        contatos.setContato(contato);
        return contatos;
    }
}
