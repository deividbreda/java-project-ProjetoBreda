/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author deivi
 */
public class Bd {
    private static Connection con = null;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost/breda";
    private static final String usuario = "root";
    private static final String senha = "vertrigo";
    public synchronized void fecha() {
        try {
        con.close();
        } catch (SQLException e) {
        System.out.println("erro na conexÃ£o\n" + e.getMessage());
        }
    }
    public static Connection getCon() {
        if (con == null) { 
            try {
                Class.forName(driver);
                con = DriverManager.getConnection(url, usuario, senha);
            } catch (Exception e) {
            System.out.println("erro na conexao\n" + e.getMessage());
            }
        }
    return con;
    }
        public static void setCon(Connection cone) { con = cone;
    }
}


