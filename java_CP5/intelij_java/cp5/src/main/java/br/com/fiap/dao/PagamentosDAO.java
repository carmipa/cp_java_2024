package br.com.fiap.dao;

import br.com.fiap.model.Pagamentos;

import java.util.List;

public interface PagamentosDAO {

    void create(Pagamentos pagamentos);

    Pagamentos readById(int id);

    List<Pagamentos> readAll();

    void update(Pagamentos pagamentos);

    void delete(int id);
}
