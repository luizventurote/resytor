package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por efetuar a conexão com o banco
 *
 * @author Matheus Claudino
 */
public class MySQLNativeDriver {

    protected String nomeBanco, usuario, senha;
    private String ip, porta;

    private void setValues(String nomeBanco, String usuario,
            String senha, String ip, String porta) {
        this.nomeBanco = nomeBanco;
        this.usuario = usuario;
        this.senha = senha;
        this.ip = ip;
        this.porta = porta;
    }

    public MySQLNativeDriver(String nomeBanco, String usuario, String senha, String ip, String porta) {
        this.setValues(nomeBanco, usuario, senha, ip, porta);
    }

    public MySQLNativeDriver(String nomeBanco, String usuario, String senha) {
        this.setValues(nomeBanco, usuario, senha,
                "localhost", "3306");
    }

    public Connection obterConexao() throws SQLException {
        Connection conexao = null;

        try {
            String url = "jdbc:mysql://" + this.ip + ":" + this.porta
                    + "/" + this.nomeBanco;
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection(url,
                    this.usuario, this.senha);
        } catch (ClassNotFoundException erro) {
            System.out.println("Driver MySQL não encontrado");
        } catch (SQLException erro) {
            System.out.println("Problemas na conexão com banco de dados: " + erro.getMessage());
        } finally {
            return conexao;
        }

    }
}
