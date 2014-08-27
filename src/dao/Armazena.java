/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
public class Armazena {

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
     * @throws java.sql.SQLException
     */
    public void insert(String text) throws SQLException {
        Connection conexao = Armazena.Conectar();

        String sql = "INSERT INTO mensagem(conteudo)VALUES(?);";
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, text);
            stmt.executeUpdate();
        } catch (SQLException erro) {
            System.out.println("Erro na inserção (" + erro.getLocalizedMessage() + ")");
        }
    }

    /**
     *
     * Método para a exclusão da mensagem no banco
     *
     * @param id identificador da mensagem
     * @throws java.sql.SQLException
     */
    public void delete(int id) throws SQLException {
        Connection conexao = Armazena.Conectar();

        String sql = "DELETE FROM mensagem WHERE mensagem.id = ?";
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, String.valueOf(id));
            stmt.executeUpdate();
        } catch (SQLException erro) {
            System.out.println("Erro na exclusão (" + erro.getLocalizedMessage() + ")");
        }
    }

    /**
     *
     * Método para a pesquisa das mensagem no banco por identificador
     *
     * @param id identificador da mensagem
     * @throws java.sql.SQLException
     */
    public void search(int id) throws SQLException {
        Connection conexao = Armazena.Conectar();
        ResultSet rs;
        Statement stmt = conexao.createStatement();

        String sql = "SELECT conteudo FROM mensagem WHERE mensagem.id = " + String.valueOf(id);
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                try {
                    System.out.println(rs.getString("conteudo"));
                } catch (SQLException erro) {
                    System.out.println("Erro na leitura de dados (" + erro.getLocalizedMessage() + ")");
                }
            }
        } catch (SQLException erro) {
            System.out.println("Comando SQL inválido (" + erro.getLocalizedMessage() + ")");
        }
    }

    /**
     *
     * Método para a pesquisa das ultimas dez mensagem no banco
     *
     * @throws java.sql.SQLException
     */
    public void searchLastMessage() throws SQLException {
        Connection conexao = Armazena.Conectar();
        ResultSet rs;
        Statement stmt = conexao.createStatement();

        String sql = "SELECT conteudo FROM mensagem LIMIT 10";
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                try {
                    System.out.println(rs.getString("conteudo"));
                } catch (SQLException erro) {
                    System.out.println("Erro na leitura de dados (" + erro.getLocalizedMessage() + ")");
                }
            }
        } catch (SQLException erro) {
            System.out.println("Comando SQL inválido (" + erro.getLocalizedMessage() + ")");
        }
    }

    /**
     *
     * Método para a pesquisa das mensagens que contém a palavra passada por
     * parâmetro
     *
     * @param str palavra pesquisada
     * @throws java.sql.SQLException
     */
    public void searchForName(String str) throws SQLException {
        Connection conexao = Armazena.Conectar();
        ResultSet rs;
        Statement stmt = conexao.createStatement();

        String sql = "SELECT conteudo FROM mensagem WHERE mensagem.conteudo LIKE" + " '%" + str + "%'";

        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                try {
                    System.out.println(rs.getString("conteudo"));
                } catch (SQLException erro) {
                    System.out.println("Erro na leitura de dados (" + erro.getLocalizedMessage() + ")");
                }
            }
        } catch (SQLException erro) {
            System.out.println("Comando SQL inválido (" + erro.getLocalizedMessage() + ")");
        }
    }

    /**
     *
     * Método para a atualização da mensagem no banco
     *
     * @param text mensagem recebida
     * @param id identificador da mensagem
     * @throws java.sql.SQLException
     */
    public void update(String text, int id) throws SQLException {
        Connection conexao = Armazena.Conectar();

        String sql = "UPDATE mensagem SET conteudo = ? WHERE mensagem.id = " + String.valueOf(id);
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, text);
            //stmt.setString(id, sql);
            stmt.executeUpdate();
        } catch (SQLException erro) {
            System.out.println("Erro na atualização (" + erro.getLocalizedMessage() + ")");
        }
    }
}
