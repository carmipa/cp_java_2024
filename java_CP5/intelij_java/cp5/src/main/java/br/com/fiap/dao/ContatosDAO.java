package br.com.fiap.dao;

import br.com.fiap.model.Contatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ContatosDAO {

    int create(Contatos contatos,  Connection connection) throws SQLException;

    Contatos readById(int id);

    List<Contatos> readAll();

    void update(Contatos contatos);

    void delete(int id);
}
