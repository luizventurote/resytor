package control;

import dao.Dao;
import java.sql.SQLException;

/**
 *
 * @author LuizVenturote https://github.com/luizventurote
 */
public class Core {
    
    private Dao dao;
    private Resytor resytor;
    private static Core uniqueInstance = null;

    public Core() {
        resytor = new Resytor();
        dao = new Dao();
    }
    
    /**
     * Retorna a instância da classe
     * 
     * @return Core
     */
    public static synchronized Core getInstance() {
        if (uniqueInstance == null) {
            return new Core();
        }
        return uniqueInstance;
    }
    
    /**
     * Método responsável pela interpretação da mensagem enviada pelo cliente
     * 
     * @param String mensagem Mensagem enviada pelo usuário
     * @return Object
     */
    public String execute(String mensagem) throws SQLException {
        
        // Remove a ação do cliente da string
        int acao = Integer.parseInt(mensagem.split("-")[0]);
        mensagem = mensagem.split("-")[1];
        
        if (acao == 1) {
            this.insert(mensagem);
            return "Mensagem enviada com sucesso!";
            
        } else if (acao == 2) {
            System.out.println("Recuperar as mensagens");
            
        } else if (acao == 3) {
            System.out.println("Recuperar mensagens por palavra chave");
            
        } else {
            System.out.println("Erro");
        }
        
        return null;
    }
    
    /**
     * Cria o banco de dados
     * 
     * @return void
     */
    public void criarBD() throws SQLException, Exception{
        Dao dao = new Dao();
        dao.criarBD();
    }
    
    /**
     * Método responsável pela inserção de dados no banco
     * 
     * @author LuizVenturote https://github.com/luizventurote
     * @param String text texto a ser gravado no banco
     * @return boolean
     */
    public boolean insert(String text) throws SQLException {
        return this.dao.insert(text);
    }
}
