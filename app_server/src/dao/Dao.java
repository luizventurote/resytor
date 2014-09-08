package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela persistência no banco, utilizando jdbc
 *
 * Chamada dos métodos
 *
 * dado.insert("mensagem a ser inserida"); string da nova mensagem
 * dado.update("mensagem a ser atualizada", id); passando o id da mensagem a ser
 * atualizada dado.search(id); procurando a mensagem passando o seu id
 * dado.delete(id); excluindo a mensagem passando o seu id
 * dado.searchLastMessage(); retorna 10 mensagens, ainda irei tratar o SQL para
 * retornar as 10 últimas dado.searchForName("en"); envia uma string como
 * parametro, para que retorne as mensagens que contenha essa string
 *
 * @author Matheus Claudino
 */
public class Dao {

    /**
     *
     * Método para a efetuar a conexao com o banco
     *
     * @return retorna um objeto do tipo Connection
     * @throws java.sql.SQLException
     */
    public static Connection Conectar() throws SQLException {
        MySQLNativeDriver driver = new MySQLNativeDriver("BD_resytor", "root", "");
        return driver.obterConexao();
    }

    /**
     *
     * Método para a inserção da mensagem no banco
     *
     * @param text mensagem recebida
     * @return retorna true ou false, condizente ao resultado da operação
     * @throws java.sql.SQLException
     */
    public boolean insert(String text) throws SQLException {
        Connection conexao = Dao.Conectar();

        String sql = "INSERT INTO mensagem(conteudo)VALUES(?);";
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, text);
            stmt.executeUpdate();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro na inserção (" + erro.getLocalizedMessage() + ")");
            return false;
        }
    }

    /**
     *
     * Método para a exclusão da mensagem no banco
     *
     * @param id identificador da mensagem
     * @return retorna true ou false, condizente ao resultado da operação
     * @throws java.sql.SQLException
     */
    public boolean delete(int id) throws SQLException {
        Connection conexao = Dao.Conectar();

        String sql = "DELETE FROM mensagem WHERE mensagem.id = ?";
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, String.valueOf(id));
            stmt.executeUpdate();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro na exclusão  (" + erro.getLocalizedMessage() + ")");
            return false;
        }
    }

    /**
     *
     * Método para a pesquisa das mensagem no banco por identificador
     *
     * @param id identificador da mensagem
     * @return
     * @throws java.sql.SQLException
     */
    public String search(int id) throws SQLException {
        Connection conexao = Dao.Conectar();
        ResultSet rs;
        String resultado = null;
        Statement stmt = conexao.createStatement();

        String sql = "SELECT conteudo FROM mensagem WHERE mensagem.id = " + String.valueOf(id);
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                try {
                    resultado = (rs.getString("conteudo"));
                } catch (SQLException erro) {
                    System.out.println("Erro na leitura de dados (" + erro.getLocalizedMessage() + ")");
                }
            }
        } catch (SQLException erro) {
            System.out.println("Comando SQL inválido (" + erro.getLocalizedMessage() + ")");
        }
        return resultado;

    }

    /**
     *
     * Método para a pesquisa das ultimas dez mensagem no banco
     *
     * @return retorna um arrayList de string
     * @throws java.sql.SQLException
     */
    public ArrayList searchLastMessage() throws SQLException {
        Connection conexao = Dao.Conectar();
        ResultSet rs;
        ArrayList<String> arrayMessage = new ArrayList();
        Statement stmt = conexao.createStatement();

        String sql = "SELECT mensagem.conteudo FROM mensagem ORDER BY mensagem.id DESC LIMIT 10";
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                try {
                    arrayMessage.add(rs.getString("conteudo"));
                } catch (SQLException erro) {
                    System.out.println("Erro na leitura de dados (" + erro.getLocalizedMessage() + ")");
                }
            }
        } catch (SQLException erro) {
            System.out.println("Comando SQL inválido (" + erro.getLocalizedMessage() + ")");
        }
        return arrayMessage;
    }

    /**
     *
     * Método para a pesquisa das mensagens que contém a palavra passada por
     * parâmetro
     *
     * @param str palavra pesquisada
     * @return retorna um arrayList de string
     * @throws java.sql.SQLException
     */
    public ArrayList searchForName(String str) throws SQLException {
        Connection conexao = Dao.Conectar();
        ResultSet rs;
        ArrayList<String> arrayMessage = new ArrayList();
        Statement stmt = conexao.createStatement();

        String sql = "SELECT mensagem.conteudo FROM mensagem WHERE mensagem.conteudo LIKE" + " '%" + str + "%'";

        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                try {
                    arrayMessage.add(rs.getString("conteudo"));
                } catch (SQLException erro) {
                    System.out.println("Erro na leitura de dados (" + erro.getLocalizedMessage() + ")");
                }
            }
        } catch (SQLException erro) {
            System.out.println("Comando SQL inválido (" + erro.getLocalizedMessage() + ")");
        }
        return arrayMessage;
    }

    /**
     *
     * Método para pegar todas as mensagens do banco i
     *
     * @return retorna um arrayList de string com todas as mensagens
     */
    public ArrayList getAllMessages() throws SQLException {
        Connection conexao = Dao.Conectar();
        ResultSet rs;
        ArrayList<String> arrayMessage = new ArrayList();
        Statement stmt = conexao.createStatement();

        String sql = "SELECT mensagem.conteudo FROM mensagem";
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                try {
                    arrayMessage.add(rs.getString("conteudo"));
                } catch (SQLException erro) {
                    System.out.println("Erro na leitura de dados (" + erro.getLocalizedMessage() + ")");
                }
            }
        } catch (SQLException erro) {
            System.out.println("Comando SQL inválido (" + erro.getLocalizedMessage() + ")");
        }
        return arrayMessage;

    }

    /**
     *
     * Método para a atualização da mensagem no banco
     *
     * @param text mensagem recebida
     * @param id identificador da mensagem
     * @return retorna true ou false, condizente ao resultado da operação
     * @throws java.sql.SQLException
     */
    public boolean update(String text, int id) throws SQLException {
        Connection conexao = Dao.Conectar();

        String sql = "UPDATE mensagem SET conteudo = ? WHERE mensagem.id = " + String.valueOf(id);
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, text);
            //stmt.setString(id, sql);
            stmt.executeUpdate();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro na atualização (" + erro.getLocalizedMessage() + ")");
            return false;
        }
    }

    /**
     *
     * Método para criar o banco
     *
     * @throws java.sql.SQLException, java.sql.Exception
     */
    public void criarBD() throws SQLException, Exception {
        Connection conexao = Dao.Conectar();

        String sql = "CREATE DATABASE IF NOT EXISTS BD_resytor;\n"
                + "USE BD_resytor;\n"
                + "\n"
                + "CREATE TABLE mensagem(\n"
                + "	id INT NOT NULL AUTO_INCREMENT,\n"
                + "	conteudo TEXT NOT NULL,\n"
                + "	PRIMARY KEY(id)\n"
                + ");";
        PreparedStatement stmt;

        stmt = conexao.prepareStatement(sql);
        stmt.executeUpdate();
    }
    
}
