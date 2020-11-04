package Utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fred
 */
public class UserController {
    
    //criando conexão com o banco
    ConnectDatabase con = new ConnectDatabase();
    
    //função que vai fazer a inserção, recebendo o model de usuário como parâmetro
    public void insert(UserModel mod) {
        try {
            //conectando ao banco
            con.connect();
            //criando a SQL para salvar no banco
            PreparedStatement pst = con.conn.prepareStatement("INSERT INTO public.user (username, password) values (?,?)");
            //passando os valores que serão inseridos no comando SQL
            pst.setString(1, mod.getUsername());
            pst.setString(2, mod.getPassword());
            //executando o update
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
            //desconectando do banco
            con.disconnect();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Já existe um usuário com este nome");
        }
    }
}
