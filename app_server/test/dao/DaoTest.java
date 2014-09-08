package dao;

import java.sql.SQLException;
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
        
    }
    
}
