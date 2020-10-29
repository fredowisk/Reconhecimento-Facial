package Utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fred
 */
public class UserController {
    
    ConnectDatabase con = new ConnectDatabase();
    
    public void insert(UserModel mod) {
        try {
            con.connect();
            PreparedStatement pst = con.conn.prepareStatement("INSERT INTO public.user (user_username, user_password) values (?,?)");
            pst.setString(1, mod.getUsername());
            pst.setString(2, mod.getPassword());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
            
            con.disconnect();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }
}
