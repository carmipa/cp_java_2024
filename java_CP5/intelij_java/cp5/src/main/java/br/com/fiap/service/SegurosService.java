package br.com.fiap.service;

import br.com.fiap.dao.SegurosDAO;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.Seguros;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;



public class SegurosService {

    private SegurosDAO segurosDAO;

    public SegurosService() {
        this.segurosDAO = DAOFactory.getSeguroDAO(); // Injeção do DAO via Factory
    }

    public void cadastrarSeguros(Seguros seguros) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            segurosDAO.create(seguros, connection);
        }
    }

    public Seguros buscarSegurosPorId(int id) {
        return segurosDAO.readById(id);
    }

    public List<Seguros> listarTodosSeguros() {
        return segurosDAO.readAll();
    }

    public void atualizarSeguros(Seguros seguros) {
        segurosDAO.update(seguros);
    }

    public void deletarSeguros(int id) {
        segurosDAO.delete(id);
    }
}
