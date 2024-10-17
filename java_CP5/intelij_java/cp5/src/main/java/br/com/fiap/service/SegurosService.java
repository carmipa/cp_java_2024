package br.com.fiap.service;

import br.com.fiap.dao.SegurosDAO;
import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.Seguros;

import java.sql.SQLException;
import java.util.List;

public class SegurosService {

    private SegurosDAO segurosDAO;

    public SegurosService() {
        this.segurosDAO = DAOFactory.getSeguroDAO(); // Injeção do DAO via Factory
    }

    public void cadastrarSeguros(Seguros seguros) throws SQLException {
        segurosDAO.create(seguros);
    }

    public Seguros buscarSegurosPorId(int id) throws SQLException {
        return segurosDAO.readById(id);
    }

    public List<Seguros> listarTodosSeguros() throws SQLException {
        return segurosDAO.readAll();
    }

    public void atualizarSeguros(Seguros seguros) throws SQLException {
        segurosDAO.update(seguros);
    }

    public void deletarSeguros(int id) throws SQLException {
        segurosDAO.delete(id);
    }
}
