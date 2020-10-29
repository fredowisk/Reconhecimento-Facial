package Utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fred
 */
public class PersonController {
    
    ConnectDatabase con = new ConnectDatabase();
    
    public void insert(PersonModel mod) {
        try {
            con.connect();
            PreparedStatement pst = con.conn.prepareStatement("INSERT INTO person (first_name, last_name, cargo, data) values (?,?,?,?)");
            pst.setString(1, mod.getFirst_name());
            pst.setString(2, mod.getLast_name());
            pst.setString(3, mod.getCargo());
            pst.setString(4, mod.getData());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
            
            con.disconnect();
        } catch (SQLException e) {
            throw new Error(e);
        }
    }
}
