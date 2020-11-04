package Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Fred
 */
public class PersonController {
//Criando conexão com o banco
    ConnectDatabase con = new ConnectDatabase();
//Metodo que insere uma pessoa no banco, recebendo o seu model como parâmetro
    public void insert(PersonModel mod) {
        try {
            //conectando ao banco
            con.connect();
            //criando a SQL para fazer a inserção no banco
            PreparedStatement pst = con.conn.prepareStatement("INSERT INTO person (first_name, last_name, cargo, data) values (?,?,?,?)");
            //setando os valores que vão passar na SQL
            pst.setString(1, mod.getFirst_name());
            pst.setString(2, mod.getLast_name());
            pst.setString(3, mod.getCargo());
            pst.setString(4, mod.getData());
            //executando um Update
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
//desconectando do banco
            con.disconnect();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no cadastro!");
        }
    }

    //função que vai buscar um usuário especifico
    public boolean show(PersonModel mod) {
        try {
            //conectando ao banco
            con.connect();
            //criando a SQL
            PreparedStatement pst = con.conn.prepareStatement("SELECT id, first_name, last_name, cargo, \"data\" FROM person WHERE first_name=? AND last_name=?");
            //setando os valores que vão entrar no comando SQL
            pst.setString(1, mod.getFirst_name());
            pst.setString(2, mod.getLast_name());
            //pegando o resultado da Query SQL
            ResultSet rs = pst.executeQuery();
            //equanto tiverem mais resultados...
            while (rs.next()) {
                //salve todos eles no model
                mod.setId(rs.getInt("id"));
                mod.setFirst_name(rs.getString("first_name"));
                mod.setLast_name(rs.getString("last_name"));
                mod.setCargo(rs.getString("cargo"));
                mod.setData(rs.getString("data"));
                //retorne true para atualizar os valores da jTable
                return true;
            }
//desconecte
            con.disconnect();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar usuário!");
        }
        //retorne false caso contrário
        return false;
    }

    //função que vai pegar todos os usuários, receberá um return que será uma lista de Person
    public List<PersonModel> searchAll() {
        //criando uma lista que aceita apenas valores do tipo Person
        List<PersonModel> persons = new ArrayList();
        try {
            //conectando ao banco
            con.connect();
            //criando a SQL que vai buscar todos os valores do banco
            PreparedStatement pst = con.conn.prepareStatement("SELECT id, first_name, last_name, cargo, \"data\" FROM person");
            //pegando o retorno do comando SQL
            ResultSet rs = pst.executeQuery();
            //enquanto houver um próximo valor...
            while (rs.next()) {
                //criando uma instância do model
                PersonModel mod = new PersonModel();
                //setando os valores recebidos no model
                mod.setId(rs.getInt("id"));
                mod.setFirst_name(rs.getString("first_name"));
                mod.setLast_name(rs.getString("last_name"));
                mod.setCargo(rs.getString("cargo"));
                mod.setData(rs.getString("data"));
                //coloque todos os valores setados na lista de Persons
                persons.add(mod);
            }

            //desconecte do banco
            con.disconnect();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar usuário!");
        }
        //retorne a lista de pessoas
        return persons;
    }
}
