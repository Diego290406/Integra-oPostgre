package com.seuusuario.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL      = "jdbc:postgresql://localhost:5432/teste";
    private static final String USUARIO  = "meu_usuario";
    private static final String SENHA    = "minhaSenha";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
