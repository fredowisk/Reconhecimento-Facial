package Utils;

import Principal.Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 *
 * @author Fred
 */
public class ConnectDatabase {
    
    public Statement stm;
    public ResultSet rs;
    public Connection conn;

    private final String driver = "org.postgresql.Driver";
    private final String root = "jdbc:postgresql://192.168.99.100:5436/facial_recognition";
    private String user = "postgres";
    private String pass = "root";
    
    public void connect() {
        try {
        System.setProperty("jdbc.Driver", driver);
        conn = DriverManager.getConnection(root,user,pass);
        } catch(SQLException e) {
            throw new Error(e);
        }
    }
    
    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new Error(e);
        }
    }
    
    public void executeSQL(String SQL) {
        try {
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(SQL);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }
    
    public void validate(String username, String password) {
        try {
           stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
           String SQL = "SELECT * FROM user WHERE user_username=" + username + " AND user_password=" + password;
           ResultSet rs = stm.executeQuery(SQL);
           
           while(rs.next()) {
               String user = rs.getString("username");
               String pass = rs.getString("password");
               
               new Login().Validate(user, pass);
           }
           
        } catch (SQLException e) {
            throw new Error(e);
        }
    }
}
