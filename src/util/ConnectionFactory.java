/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Julio Cesar Nomelini
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private final String database = "jdbc:mysql://localhost/emissao";
    private final String login = "root";
    private final String password = "";

    public static Connection getConnection() {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "");
            properties.setProperty("useSSL", "false");
            properties.setProperty("useTimezone", "true");
            properties.setProperty("serverTimezone", "UTC");
            properties.setProperty("allowPublicKeyRetrieval","true");
            
            String con = "jdbc:mysql://localhost/emissao";
            
            return DriverManager.getConnection(con, properties);
        } catch (java.sql.SQLException e) {
            return null;
        }
    }
    
    public boolean connectionCheck(){
        try (Connection conexao = ConnectionFactory.getConnection()) {
            return conexao != null;         
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }    
}
