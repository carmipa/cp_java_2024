package br.com.fiap.service;

import br.com.fiap.dao.PagamentosDAO;
import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.Pagamentos;

import java.util.List;

public class PagamentosService {

    private PagamentosDAO pagamentosDAO;

    public PagamentosService() {
        this.pagamentosDAO = DAOFactory.getPagamentoDAO();
    }

    public void cadastrarPagamentos(Pagamentos pagamentos) {
        pagamentosDAO.create(pagamentos);
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
