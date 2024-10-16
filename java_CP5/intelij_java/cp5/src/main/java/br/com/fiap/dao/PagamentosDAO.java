package br.com.fiap.dao;

import br.com.fiap.model.Pagamentos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PagamentosDAO {

    void create(Pagamentos pagamentos, Connection connection) throws SQLException;

    Pagamentos readById(int id);

    List<Pagamentos> readAll();

    void update(Pagamentos pagamentos);

    void delete(int id);
}
