package br.com.fiap.service;

import br.com.fiap.dao.ClientesDAO;
import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.Clientes;
import br.com.fiap.util.DocumentoUtil;

import java.util.List;

public class ClientesService {



    private ClientesDAO clientesDAO;

    public ClientesService() {
        this.clientesDAO = DAOFactory.getClienteDAO(); // Injeção do DAO via Factory
    }

    public void cadastrarClientes(Clientes clientes) {
        clientesDAO.create(clientes);

    }

    public Clientes buscarClientesPorId(int id) {
        return clientesDAO.readById(id);
    }

    public List<Clientes> listarTodosClientes() {
        return clientesDAO.readAll();
    }

    public void atualizarClientes(Clientes clientes) {
        clientesDAO.update(clientes);
    }

    public void deletarClientes(int id) {
        clientesDAO.delete(id);
    }



}
