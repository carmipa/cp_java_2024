package br.com.fiap.dao;

import br.com.fiap.model.Contatos;

import java.util.List;

public interface ContatosDAO {

    void create(Contatos contatos);

    Contatos readById(int id);

    List<Contatos> readAll();

    void update(Contatos contatos);

    void delete(int id);
}
