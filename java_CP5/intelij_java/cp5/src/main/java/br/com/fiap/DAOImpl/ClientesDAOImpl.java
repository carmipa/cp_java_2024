package br.com.fiap.DAOImpl;

import br.com.fiap.dao.ClientesDAO;
import br.com.fiap.dao.ContatosDAO;
import br.com.fiap.dao.EnderecosDAO;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Clientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientesDAOImpl implements ClientesDAO {

    @Override
    public void create(Clientes cliente, Connection connection) throws SQLException {
        try {
            connection.setAutoCommit(false);

            EnderecosDAO enderecosDAO = new EnderecosDAOImpl();
            ContatosDAO contatosDAO = new ContatosDAOImpl();

            int idEndereco = enderecosDAO.create(cliente.getEnderecos(), connection);
            int idContato = contatosDAO.create(cliente.getContatos(), connection);

            String seqSql = "SELECT seq_clientes.NEXTVAL FROM dual";
            PreparedStatement seqStmt = connection.prepareStatement(seqSql);
            ResultSet rsSeq = seqStmt.executeQuery();
            int idCliente = 0;
            if (rsSeq.next()) {
                idCliente = rsSeq.getInt(1);
            } else {
                throw new SQLException("Falha ao obter próximo valor da sequência seq_clientes.");
            }

            String sqlCliente = "INSERT INTO clientes (id_cliente, tipo_cliente, nome, tipo_documento, numero_documento, data_nascimento, enderecos_id_endereco, contatos_id_contato) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmtCliente = connection.prepareStatement(sqlCliente);

            stmtCliente.setInt(1, idCliente);
            stmtCliente.setString(2, cliente.getTipoCliente());
            stmtCliente.setString(3, cliente.getNome());
            stmtCliente.setString(4, cliente.getTipoDocumento());
            stmtCliente.setString(5, cliente.getNumeroDocumento());
            stmtCliente.setString(6, cliente.getDataNacimento());
            stmtCliente.setInt(7, idEndereco);
            stmtCliente.setInt(8, idContato);

            stmtCliente.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            throw e;
        }
    }


    @Override
    public Clientes readById(int id) {
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
        Clientes clientes = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                clientes = new Clientes(
                        rs.getInt("id_cliente"),
                        rs.getString("tipo_cliente"),
                        rs.getString("nome"),
                        rs.getString("tipo_documento"),
                        rs.getString("numero_documento"),
                        rs.getString("data_nascimento")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public List<Clientes> readAll() {
        String sql = "SELECT * FROM clientes";
        List<Clientes> clientesList = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Clientes cliente = new Clientes(
                        rs.getInt("id_cliente"),
                        rs.getString("tipo_cliente"),
                        rs.getString("nome"),
                        rs.getString("tipo_documento"),
                        rs.getString("numero_documento"),
                        rs.getString("data_nascimento")
                );
                clientesList.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientesList;
    }

    @Override
    public void update(Clientes cliente) {
        String sql = "UPDATE clientes SET tipo_cliente = ?, nome = ?, tipo_documento = ?, numero_documento = ?, data_nascimento = ? WHERE id_cliente = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, cliente.getTipoCliente());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getTipoDocumento());
            stmt.setString(4, cliente.getNumeroDocumento());
            stmt.setString(5, cliente.getDataNacimento());
            stmt.setInt(6, cliente.getIdCliente());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}