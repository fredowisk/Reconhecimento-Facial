package Utils;

import Principal.Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 *
 * @author Fred
 */
//classe que conecta ao banco
public class ConnectDatabase {
//iniciando as variaveis de conexão
    public Statement stm;
    public ResultSet rs;
    public Connection conn;
//conectando ao banco de dados Postgres
    private final String driver = "org.postgresql.Driver";
    //database do postgres
    private final String root = "jdbc:postgresql://192.168.99.100:5436/facial_recognition";
    //usuario e senha do banco de dados
    private String user = "postgres";
    private String pass = "root";

    //criando a conexão
    public void connect() {
        try {
            System.setProperty("jdbc.Driver", driver);
            conn = DriverManager.getConnection(root, user, pass);
        } catch (SQLException e) {
            throw new Error("Erro na conexão! " + e );
        }
    }

    //desconectando
    public void disconnect() {
        try {
            //fechando a conexão
            conn.close();
        } catch (SQLException e) {
            throw new Error("Erro ao desconectar! " + e);
        }
    }

    //função que executa um comando SQL
    public void executeSQL(String SQL) {
        try {
            //salvando o comando e o executando
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(SQL);
        } catch (SQLException e) {
            throw new Error("Erro ao executar SQL " + e);
        }
    }

  //função que valida os valores para login recebendo user e password  
    public void validate(String username, String password) {
        try {
            //fazendo o select no banco de dados
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM public.user WHERE username=? AND password=?");
            //setando os valores que irão ser passados no comando SQL
            stmt.setString(1, username);
            stmt.setString(2, password);
            //executando o select
            ResultSet rs = stmt.executeQuery();
            //enquanto tiver mais valores...
            while (rs.next()) {
                //salve o usuario em uma variavel
                String user = rs.getString("username");
//chame a tela de login, e envie o usuário como parãmetro, e o true, dizendo que ele pode se conectar
                new Login().Validate(user, true);
                //retorne
                return;
            }
            //caso o usuário não seja encontrado, retorne o valor do parâmetro, e o false, para que ele não se conecte.
            new Login().Validate(username, false);

        } catch (SQLException e) {
            throw new Error("Erro na validação! " + e);
        }
    }
}
