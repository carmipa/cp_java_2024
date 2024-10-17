package br.com.fiap.dao;

import br.com.fiap.model.Seguros;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SegurosDAO {

    void create(Seguros seguros) throws SQLException;

    Seguros readById(int id) throws SQLException;

    List<Seguros> readAll() throws SQLException;

    void update(Seguros seguros) throws SQLException;

    void delete(int id) throws SQLException;
}
