package br.gov.ac.seap.pga.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.ConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConection {

    private static Connection con = null;

    public static Connection getConnection2() {
        if (con == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/pga";
                String usuario = "sgcn";
                String senha = "sgcn@130";
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, usuario, senha);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return con;
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        if (con == null) {
            try {
                InitialContext initialContext = new InitialContext();
                DataSource dataSource = (DataSource) initialContext.lookup("jboss/datasources/pgaDS");
                con = dataSource.getConnection();
                return con;
            } catch (NamingException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return con;
    }
}
