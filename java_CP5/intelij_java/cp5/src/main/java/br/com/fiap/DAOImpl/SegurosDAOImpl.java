package br.com.fiap.DAOImpl;

import br.com.fiap.dao.SegurosDAO;
import br.com.fiap.model.Seguros;
import br.com.fiap.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SegurosDAOImpl implements SegurosDAO {
    @Override
    public void create(Seguros seguros) {

        String sql = "INSERT INTO seguros (tipo, perfil, valor) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, seguros.getTipoSeguro());
            stmt.setString(2, seguros.getPerfil());
            stmt.setDouble(3, seguros.getValor());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Seguros readById(int id) {

        String sql = "SELECT * FROM seguros WHERE id_seguro = ?";
        Seguros seguros = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                seguros = new Seguros(
                        rs.getInt("id_seguro"),
                        rs.getString("tipo"),
                        rs.getString("perfil"),
                        rs.getDouble("valor")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seguros;
    }

    @Override
    public List<Seguros> readAll() {

        String sql = "SELECT * FROM seguros";
        List<Seguros> segurosList = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Seguros seguros = new Seguros(
                        rs.getInt("id_seguro"),
                        rs.getString("tipo"),
                        rs.getString("perfil"),
                        rs.getDouble("valor")
                );
                segurosList.add(seguros);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return segurosList;
    }

    @Override
    public void update(Seguros seguros) {

        String sql = "UPDATE seguros SET tipo = ?, perfil = ?, valor = ? WHERE id_seguro = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, seguros.getTipoSeguro());
            stmt.setString(2, seguros.getPerfil());
            stmt.setDouble(3, seguros.getValor());
            stmt.setInt(4, seguros.getIdSeguro());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM seguros WHERE id_seguro = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
