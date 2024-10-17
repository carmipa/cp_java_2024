package br.com.fiap.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // Informações de conexão fixas para um único usuário
    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static final String USER = "paulo"; // Substitua pelo usuário correto
    private static final String PASSWORD = "12345"; // Substitua pela senha correta

    // Construtor privado para evitar instâncias
    private ConnectionFactory() {}

    // Método para obter uma nova conexão
    public static Connection getConnection() throws SQLException {
        try {
            logAccess("Tentando estabelecer conexão...");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            logAccess("Conexão estabelecida com sucesso.");
            return conn;
        } catch (SQLException e) {
            logError("Erro ao conectar ao banco de dados.", e);
            throw e; // Propaga a exceção para ser tratada onde o método é chamado
        }
    }

    // Método para registrar logs de acesso
    private static void logAccess(String message) {
        System.out.println("[INFO] " + message);
    }

    // Método para registrar logs de erros
    private static void logError(String message, Exception e) {
        System.err.println("[ERRO] " + message);
        e.printStackTrace(System.err);
    }
}
