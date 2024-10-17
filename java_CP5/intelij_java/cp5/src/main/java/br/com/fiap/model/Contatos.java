package br.com.fiap.model;

import java.util.Objects;

public class Contatos {

    private int idContato;
    private String telefone;
    private String email;
    private String contato;

    public Contatos() {
    }

    public Contatos(int idContato, String telefone, String email, String contato) {
        this.idContato = idContato;
        this.telefone = telefone;
        this.email = email;
        this.contato = contato;
    }

    public int getIdContato() {
        return idContato;
    }

    public void setIdContato(int idContato) {
        this.idContato = idContato;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contatos contatos)) return false;
        return idContato == contatos.idContato && Objects.equals(telefone, contatos.telefone) && Objects.equals(email, contatos.email) && Objects.equals(contato, contatos.contato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContato, telefone, email, contato);
    }

    @Override
    public String toString() {
        return "Contatos{" +
                "idContato=" + idContato +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", contato='" + contato + '\'' +
                '}';
    }


}
