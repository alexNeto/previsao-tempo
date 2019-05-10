package com.ts.previsao.tempo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    protected static final String URL = "jdbc:sqlite:bdprevisao.db";

    protected Connection conecta() throws Exception {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new Exception();
        }
        return conn;
    }
}
