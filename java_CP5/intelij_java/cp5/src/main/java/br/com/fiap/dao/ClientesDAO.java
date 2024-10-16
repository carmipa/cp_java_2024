package br.com.fiap.dao;

import br.com.fiap.model.Clientes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ClientesDAO {

    void create(Clientes clientes,  Connection connection) throws SQLException;

    Clientes readById(int id);

    List<Clientes> readAll();

    void update(Clientes clientes);

    void delete(int id);
}
