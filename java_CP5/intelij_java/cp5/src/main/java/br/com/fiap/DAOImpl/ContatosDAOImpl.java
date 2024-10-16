package br.com.fiap.DAOImpl;

import br.com.fiap.dao.ContatosDAO;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Contatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatosDAOImpl implements ContatosDAO {

    @Override
    public int create(Contatos contato, Connection connection) throws SQLException {
        String seqSql = "SELECT seq_contatos.NEXTVAL FROM dual";
        PreparedStatement seqStmt = connection.prepareStatement(seqSql);
        ResultSet rsSeq = seqStmt.executeQuery();
        int idContato = 0;
        if (rsSeq.next()) {
            idContato = rsSeq.getInt(1);
        } else {
            throw new SQLException("Falha ao obter próximo valor da sequência seq_contatos.");
        }

        String sql = "INSERT INTO contatos (id_contato, telefone, email, contato) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setInt(1, idContato);
        stmt.setString(2, contato.getTelefone());
        stmt.setString(3, contato.getEmail());
        stmt.setString(4, contato.getContato());

        stmt.executeUpdate();

        return idContato;
    }

    @Override
    public Contatos readById(int id) {

        String sql = "SELECT * FROM contatos WHERE id_contato = ?";
        Contatos contatos  = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                contatos = new Contatos(
                        rs.getInt("id_contato"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("contato")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contatos;
    }

    @Override
    public List<Contatos> readAll() {
        String sql = "SELECT * FROM contatos";
        List<Contatos> contatosList = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
             ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Contatos contato = new Contatos(
                        rs.getInt("id_contato"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("contato")
                );
                contatosList.add(contato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contatosList;
    }

    @Override
    public void update(Contatos contatos) {
        String sql = "UPDATE contatos SET telefone = ?, email = ?, contato = ? WHERE id_contato = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, contatos.getTelefone());
            stmt.setString(2, contatos.getEmail());
            stmt.setString(3, contatos.getContato());
            stmt.setInt(4, contatos.getIdContato());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM contatos WHERE id_contato = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
