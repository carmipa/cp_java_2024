package br.com.fiap.dao;

import br.com.fiap.model.Clientes;

import java.util.List;

public interface ClientesDAO {

    void create(Clientes clientes);

    Clientes readById(int id);

    List<Clientes> readAll();

    void update(Clientes clientes);

    void delete(int id);
}
