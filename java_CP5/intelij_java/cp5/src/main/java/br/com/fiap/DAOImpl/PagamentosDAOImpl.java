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

    // Códigos ANSI para formatação de texto e cores
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";

    @Override
    public Pagamentos readById(int id) {
        String sql = "SELECT * FROM pagamentos WHERE id_pagamento = ?";
        Pagamentos pagamento = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pagamento = new Pagamentos(
                        rs.getInt("id_pagamento"),
                        rs.getString("data_pagamento"),
                        rs.getString("tipo_pagamento"),
                        rs.getString("forma_pagamento"),
                        0.0, // valorServico não está no banco de dados, então passamos um valor padrão.
                        rs.getDouble("desconto"),
                        rs.getDouble("valor_total"),
                        rs.getInt("parcelas"),
                        rs.getDouble("valor_parcela")
                );

                // Exibe os dados alinhados
                System.out.println(BLUE + BOLD + "╔═══════════════════════ DADOS DO PAGAMENTO ════════════════════════╗" + RESET);
                System.out.printf("%-30s: %d\n", "ID", pagamento.getIdPagemnto());
                System.out.printf("%-30s: %s\n", "Data de Pagamento", pagamento.getDataPagamento());
                System.out.printf("%-30s: %s\n", "Tipo de Pagamento", pagamento.getTipoPagamento());
                System.out.printf("%-30s: %s\n", "Forma de Pagamento", pagamento.getFormaPagamento());
                System.out.printf("%-30s: %d\n", "Parcelas", pagamento.getParcelas());
                System.out.printf("%-30s: R$ %.2f\n", "Valor da Parcela", pagamento.getValorParcela());
                System.out.printf("%-30s: %.2f%%\n", "Desconto", pagamento.getDesconto());
                System.out.printf("%-30s: R$ %.2f\n", "Valor Total", pagamento.getValorTotal());
                System.out.println(BLUE + BOLD + "╚══════════════════════════════════════════════════════════════════╝" + RESET);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pagamento;
    }

    @Override
    public List<Pagamentos> readAll() {
        String sql = "SELECT * FROM pagamentos";
        List<Pagamentos> pagamentosList = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pagamentos pagamento = new Pagamentos(
                        rs.getInt("id_pagamento"),
                        rs.getString("data_pagamento"),
                        rs.getString("tipo_pagamento"),
                        rs.getString("forma_pagamento"),
                        0.0, // valorServico não está no banco de dados, então passamos um valor padrão.
                        rs.getDouble("desconto"),
                        rs.getDouble("valor_total"),
                        rs.getInt("parcelas"),
                        rs.getDouble("valor_parcela")
                );
                pagamentosList.add(pagamento);
            }

            // Exibe todos os pagamentos formatados
            for (Pagamentos pagamento : pagamentosList) {
                System.out.println(BLUE + BOLD + "╔═══════════════════════ DADOS DO PAGAMENTO ════════════════════════╗" + RESET);
                System.out.printf("%-30s: %d\n", "ID", pagamento.getIdPagemnto());
                System.out.printf("%-30s: %s\n", "Data de Pagamento", pagamento.getDataPagamento());
                System.out.printf("%-30s: %s\n", "Tipo de Pagamento", pagamento.getTipoPagamento());
                System.out.printf("%-30s: %s\n", "Forma de Pagamento", pagamento.getFormaPagamento());
                System.out.printf("%-30s: %d\n", "Parcelas", pagamento.getParcelas());
                System.out.printf("%-30s: R$ %.2f\n", "Valor da Parcela", pagamento.getValorParcela());
                System.out.printf("%-30s: %.2f%%\n", "Desconto", pagamento.getDesconto());
                System.out.printf("%-30s: R$ %.2f\n", "Valor Total", pagamento.getValorTotal());
                System.out.println(BLUE + BOLD + "╚══════════════════════════════════════════════════════════════════╝" + RESET);
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
