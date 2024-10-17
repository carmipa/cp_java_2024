package br.com.fiap.DAOImpl;

import br.com.fiap.dao.EnderecosDAO;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Enderecos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EnderecosDAOImpl implements EnderecosDAO {

    @Override
    public int create(Enderecos endereco, Connection connection) throws SQLException {
        String seqSql = "SELECT seq_enderecos.NEXTVAL FROM dual";
        PreparedStatement seqStmt = connection.prepareStatement(seqSql);
        ResultSet rsSeq = seqStmt.executeQuery();
        int idEndereco = 0;
        if (rsSeq.next()) {
            idEndereco = rsSeq.getInt(1);
        } else {
            throw new SQLException("Falha ao obter próximo valor da sequência seq_enderecos.");
        }

        String sql = "INSERT INTO enderecos (id_endereco, logradouro, numero, complemento, bairro, cidade, estado, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setInt(1, idEndereco);
        stmt.setString(2, endereco.getLogradouro());
        stmt.setInt(3, endereco.getNumero());
        stmt.setString(4, endereco.getComplemento());
        stmt.setString(5, endereco.getBairro());
        stmt.setString(6, endereco.getCidade());
        stmt.setString(7, endereco.getEstado());
        stmt.setString(8, endereco.getCep());

        stmt.executeUpdate();

        return idEndereco;
    }

    @Override
    public Enderecos readById(int id) {

        String sql = "SELECT * FROM enderecos WHERE id_endereco = ?";
        Enderecos enderecos = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                enderecos = new Enderecos(
                        rs.getInt("id_endereco"),
                        rs.getInt("numero"),
                        rs.getString("cep"),
                        rs.getString("logradouro"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("complemento")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enderecos;
    }

    @Override
    public List<Enderecos> readAll() {

        String sql = "SELECT * FROM enderecos";
        List<Enderecos> enderecosList = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Enderecos enderecos = new Enderecos(
                        rs.getInt("id_endereco"),
                        rs.getInt("numero"),
                        rs.getString("cep"),
                        rs.getString("logradouro"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("complemento")
                );
                enderecosList.add(enderecos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enderecosList;
    }

    @Override
    public void update(Enderecos enderecos) {

        String sql = "UPDATE enderecos SET numero = ?, cep = ?, logradouro = ?, bairro = ?, cidade = ?, estado = ?, complemento = ? WHERE id_endereco = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, enderecos.getNumero());
            stmt.setString(2, enderecos.getCep());
            stmt.setString(3, enderecos.getLogradouro());
            stmt.setString(4, enderecos.getBairro());
            stmt.setString(5, enderecos.getCidade());
            stmt.setString(6, enderecos.getEstado());
            stmt.setString(7, enderecos.getComplemento());
            stmt.setInt(8, enderecos.getIdEndereco());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {

            String sql = "DELETE FROM enderecos WHERE id_endereco = ?";
            try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setInt(1, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }
}
