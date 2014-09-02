package control;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LuizVenturote https://github.com/luizventurote
 */
public class ResytorTest {

    /**
     * Testa o método que calcula a quantidade de palavras contidas em uma string
     */
    @Test
    public void test_tmDoc() throws Exception {
       
        // Resultado esperado
        int expResult = 6;
 
        // Método a ser testado
        int result = Resytor.tmDoc("To be or not to be");
        // Exibe os dados do teste
        System.out.println("Método: test_tmDoc");
        System.out.println("Resultado esperado: "+expResult);
        System.out.println("Resultado: "+result);
        System.out.println("---------------------------------------");
        
        assertEquals(expResult, result);
    }
    
    /**
     * Testa o método que calcula a frequência de uma palavra na string
     */
    @Test
    public void test_fij
            () throws Exception {
       
        // Resultado esperado
        int expResult = 2;
 
        // Método a ser testado
        int result = Resytor.fij("To be or not to be", "be");
        // Exibe os dados do teste
        System.out.println("Método: test_fij");
        System.out.println("Resultado esperado: "+expResult);
        System.out.println("Resultado: "+result);
        System.out.println("---------------------------------------");
        
        assertEquals(expResult, result);
    }
    
}
