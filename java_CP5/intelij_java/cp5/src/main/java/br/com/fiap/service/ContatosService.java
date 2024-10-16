package br.com.fiap.service;

import br.com.fiap.dao.ContatosDAO;
import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.Contatos;

import java.util.List;

public class ContatosService {

    private ContatosDAO contatosDAO;

    public ContatosService() {
        this.contatosDAO = DAOFactory.getContatoDAO(); // Injeção do DAO via Factory
    }

    public void cadastrarContatos(Contatos contatos) {
        contatosDAO.create(contatos);
    }

    public Contatos buscarContatosPorId(int id) {
        return contatosDAO.readById(id);
    }

    public List<Contatos> listarTodosContatos() {
        return contatosDAO.readAll();
    }

    public void atualizarContatos(Contatos contatos) {
        contatosDAO.update(contatos);
    }

    public void deletarContatos(int id) {
        contatosDAO.delete(id);
    }
}
