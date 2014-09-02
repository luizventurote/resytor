package dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LuizVenturote https://github.com/luizventurote
 */
public class DaoTest {
    
    /**
     * Test of saveData method, of class Dao.
     */
    @Test
    public void testSaveData() throws Exception {
        
        String chave = "ip_servidor";
        String valor = "localhost";
        Dao instance = new Dao();
        instance.saveData(chave, valor);
        
        String result = instance.getData(chave); 
        
        // Exibe os dados do teste
        System.out.println("Método: testSaveData");
        System.out.println("Resultado esperado: "+valor);
        System.out.println("Resultado: "+result);
        System.out.println("---------------------------------------");
        
        assertEquals(valor, result);
        
        
        
    }

  
}
