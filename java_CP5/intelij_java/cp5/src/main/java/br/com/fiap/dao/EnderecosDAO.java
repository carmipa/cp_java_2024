package br.com.fiap.dao;

import br.com.fiap.model.Enderecos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EnderecosDAO {

    int create(Enderecos enderecos,  Connection connection) throws SQLException;

    Enderecos readById(int id);

    List<Enderecos> readAll();

    void update(Enderecos enderecos);

    void delete(int id);


}
