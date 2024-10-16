package br.com.fiap.dao;

import br.com.fiap.model.Enderecos;

import java.util.List;

public interface EnderecosDAO {

    void create(Enderecos enderecos);

    Enderecos readById(int id);

    List<Enderecos> readAll();

    void update(Enderecos enderecos);

    void delete(int id);


}
