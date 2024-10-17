package br.com.fiap.factory;

import br.com.fiap.DAOImpl.*;
import br.com.fiap.dao.*;

public class DAOFactory {



    public static ClientesDAO getClienteDAO() {
        return new ClientesDAOImpl(); // Corrigido para retornar a implementação concreta
    }


    public static ContatosDAO getContatoDAO() {
        return new ContatosDAOImpl();
    }

    public static EnderecosDAO getEnderecoDAO() {
        return new EnderecosDAOImpl();
    }

    public static PagamentosDAO getPagamentoDAO() {
        return new PagamentosDAOImpl();
    }

    public static SegurosDAO getSeguroDAO() {
        return new SegurosDAOImpl();
    }
}
