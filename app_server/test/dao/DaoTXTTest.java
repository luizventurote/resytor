package dao;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
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
public class DaoTXTTest {
    
    private DaoTXT instance = new DaoTXT();

    /**
     * Testa a inserção de dados no arquivo txt
     */
    @Test
    public void test_saveData() throws Exception {
        
        System.out.println("Método: saveData()");
        
        // Grava a mensagem no banco de dados
        boolean result = this.instance.saveData("RESYTOR JUnit Test!");
        
        assertEquals(true, result);
        
        System.out.println("---------------------------------------");
        
    }
    
    /**
     * Testa a inserção de dados no arquivo txt
     */
    @Test
    public void test_updateData() throws Exception {
        
        System.out.println("Método: updateData()");
        
        // Grava a mensagem no banco de dados
        boolean result = this.instance.updateData("qtd", Integer.toString(15));
        
        assertEquals(true, result);
        
        System.out.println("---------------------------------------");
        
    }
    
}
