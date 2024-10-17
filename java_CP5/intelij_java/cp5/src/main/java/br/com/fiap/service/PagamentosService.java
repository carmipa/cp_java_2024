package br.com.fiap.service;

import br.com.fiap.dao.PagamentosDAO;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.Pagamentos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PagamentosService {

    private PagamentosDAO pagamentosDAO;

    public PagamentosService() {
        this.pagamentosDAO = DAOFactory.getPagamentoDAO();
    }

    public void cadastrarPagamentos(Pagamentos pagamentos) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            pagamentosDAO.create(pagamentos, connection);
        }
    }

    public Pagamentos buscarPagamentosPorId(int id) {
        return pagamentosDAO.readById(id);
    }

    public List<Pagamentos> listarTodosPagamentos() {
        return pagamentosDAO.readAll();
    }

    public void atualizarPagamentos(Pagamentos pagamentos) {
        pagamentosDAO.update(pagamentos);
    }

    public void deletarPagamentos(int id) {
        pagamentosDAO.delete(id);
    }
}
