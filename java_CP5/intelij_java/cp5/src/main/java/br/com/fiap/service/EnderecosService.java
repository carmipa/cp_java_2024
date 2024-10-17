package br.com.fiap.service;

import br.com.fiap.dao.EnderecosDAO;
import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.Enderecos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EnderecosService {

    private EnderecosDAO enderecosDAO;

    public EnderecosService() {
        this.enderecosDAO = DAOFactory.getEnderecoDAO();
    }

    public int cadastrarEnderecos(Enderecos enderecos, Connection connection) throws SQLException {
        return enderecosDAO.create(enderecos, connection);

    }

    public Enderecos buscarEnderecosPorId(int id) {
        return enderecosDAO.readById(id);
    }

    public List<Enderecos> listarTodosEnderecos() {
        return enderecosDAO.readAll();
    }

    public void atualizarEnderecos(Enderecos enderecos) {
        enderecosDAO.update(enderecos);
    }

    public void deletarEnderecos(int id) {
        enderecosDAO.delete(id);
    }
}
