package br.com.fiap.DAOImpl;

import br.com.fiap.dao.PagamentosDAO;
import br.com.fiap.model.Pagamentos;
import br.com.fiap.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagamentosDAOImpl implements PagamentosDAO {

    @Override
    public void create(Pagamentos pagamentos, Connection connection) {

        String sql = "INSERT INTO pagamentos (data_pagamento, tipo_pagamento, forma_pagamento, parcelas, valor_parcela, desconto, valor_total) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, pagamentos.getDataPagamento());
            stmt.setString(2, pagamentos.getTipoPagamento());
            stmt.setString(3, pagamentos.getFormaPagamento());
            stmt.setInt(4, pagamentos.getParcelas());
            stmt.setDouble(5, pagamentos.getValorParcela());
            stmt.setDouble(6, pagamentos.getDesconto());
            stmt.setDouble(7, pagamentos.getValorTotal());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pagamentos readById(int id) {

        String sql = "SELECT * FROM pagamentos WHERE id_pagamento = ?";
        Pagamentos pagamentos = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pagamentos = new Pagamentos(
                        rs.getInt("id_pagamento"),
                        rs.getString("data_pagamento"),
                        rs.getString("tipo_pagamento"),
                        rs.getString("forma_pagamento"),
                        rs.getInt("parcelas"),
                        rs.getDouble("valor_parcela"),
                        rs.getDouble("desconto"),
                        rs.getDouble("valor_total")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pagamentos;
    }

    @Override
    public List<Pagamentos> readAll() {

        String sql = "SELECT * FROM pagamentos";
        List<Pagamentos> pagamentosList = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pagamentos pagamentos = new Pagamentos(
                        rs.getInt("id_pagamento"),
                        rs.getString("data_pagamento"),
                        rs.getString("tipo_pagamento"),
                        rs.getString("forma_pagamento"),
                        rs.getInt("parcelas"),
                        rs.getDouble("valor_parcela"),
                        rs.getDouble("desconto"),
                        rs.getDouble("valor_total")
                );
                pagamentosList.add(pagamentos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pagamentosList;
    }

    @Override
    public void update(Pagamentos pagamentos) {

        String sql = "UPODATE pagamentos SET data_pagamento = ?, tipo_pagamento = ?, forma_pagamento = ?, parcelas = ?, valor_parcela = ?, desconto = ?, valor_total = ? WHERE id_pagamento = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, pagamentos.getDataPagamento());
            stmt.setString(2, pagamentos.getTipoPagamento());
            stmt.setString(3, pagamentos.getFormaPagamento());
            stmt.setInt(4, pagamentos.getParcelas());
            stmt.setDouble(5, pagamentos.getValorParcela());
            stmt.setDouble(6, pagamentos.getDesconto());
            stmt.setDouble(7, pagamentos.getValorTotal());
            stmt.setInt(8, pagamentos.getIdPagemnto());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM pagamentos WHERE id_pagamento = ?";

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
