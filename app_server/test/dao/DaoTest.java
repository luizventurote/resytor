package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LuizVenturote https://github.com/luizventurote
 */
public class DaoTest {
    
    private Dao instance = new Dao();
    
    public DaoTest() throws SQLException {
        Dao.Conectar();
    }

    /**
     * Testa a inserção no banco de dados
     */
    @Test
    public void testInsert() throws Exception {
        
        System.out.println("Método: testInsert()");
        
        // Grava a mensagem no banco de dados
        boolean result = this.instance.insert("RESYTOR JUnit Test!");
        
        assertEquals(true, result);
        
        System.out.println("---------------------------------------");
        
    }
    
    /**
     * Verifica se todas as mensagens existentes no banco estão sendo retornadas
     */
    @Test
    public void test_getAllMessages() {
        
        System.out.println("Método: test_getAllMessages()");
        
        ArrayList<String> arrayMensagens;
        
        int qtd=0;
        
        try {
            
            arrayMensagens = this.instance.getAllMessages();
            
            for (int i=0; i<arrayMensagens.size(); i++) {
                qtd++;
            }
            
            System.out.println("Quantidade de mensagens: "+qtd);
            
            assertEquals(true, true);
            
            System.out.println("---------------------------------------");
            
        } catch (SQLException ex) {
            
            Logger.getLogger(DaoTest.class.getName()).log(Level.SEVERE, null, ex);
            
            assertEquals(true, false);
            
        }
        
    }
    
}
