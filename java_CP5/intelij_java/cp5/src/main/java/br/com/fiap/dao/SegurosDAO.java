package br.com.fiap.dao;

import br.com.fiap.model.Seguros;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SegurosDAO {

    void create(Seguros seguros, Connection connection) throws SQLException;

    Seguros readById(int id);

    List<Seguros> readAll();

    void update(Seguros seguros);

    void delete(int id);
}
