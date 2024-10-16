package br.com.fiap.dao;

import br.com.fiap.model.Seguros;

import java.util.List;

public interface SegurosDAO {

    void create(Seguros seguros);

    Seguros readById(int id);

    List<Seguros> readAll();

    void update(Seguros seguros);

    void delete(int id);
}
