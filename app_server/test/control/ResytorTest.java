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
            
    /**
     * Testa o método realiza o cálculo de log
     */
    @Test
    public void test_log() {
        
        // Variável para contar erros
        int error = 0;
 
        // Método a ser testado
        double result = Resytor.log(10, 10);
        if(result != 1) {
            error++;
        }
        
        result = Resytor.log(10, 2);
        if(result != 3.3219280948873626) {
            error++;
        }
        
        result = Resytor.log(10, 5);
        if(result != 1.4306765580733933) {
            error++;
        }
        
        
        // Exibe os dados do teste
        System.out.println("Método: test_log");
        System.out.println("Erros: "+error);
        System.out.println("---------------------------------------");

        assertEquals(0, error);
    }
            
    /**
     * Testa o método que calcula a frequência de uma palavra na string
     */
    @Test
    public void test_removeStopWords() {
        
        System.out.println("Removendo strop words");
        
        String[] textos = new String[3];
        int[] tamanhos = new int[3];
        
        // Frases
        textos[0] = "Isso é um teste!";
        tamanhos[0] = 2;
        textos[1] = "Inflacao e um aumento generalizado de precos e resulta em perda do poder de compra.";
        tamanhos[1] = 8;
        textos[2] = "Sistemas Tradicionais de Arquivos foram substituidos pelos Sistemas de Bancos de Dados!";
        tamanhos[2] = 9;
        
        // Variável para contar erros
        int error = 0, qtd_sw=0;
        
        for(int i=0; i<tamanhos.length; i++) {
            
            // Remove as stopwords da string
            textos[i] = Resytor.removeStopWords(textos[i]);
            
            // Quantidade de stopwords
            qtd_sw = textos[i].split(" ").length;
            
            System.out.println("Qtd stopwords: "+qtd_sw+" - "+ textos[i]);
            
            if(qtd_sw != tamanhos[i]) {
                error++;
            }
        }
        
        System.out.println("Quantidade de erros: "+error);
        
        assertEquals(0, error);

        System.out.println("---------------------------------------");
    }
            
    /**
     * Testa a pesquisa por termos
     */
    @Test
    public void test_pesquisarPorTermos() {
        
        System.out.println("Teste do método de pesquisa");
       
        Resytor resytor = new Resytor();
        
        resytor.pesquisarPorTermos("sistemas distribuidos e bancos de dados");
        
        System.out.println("---------------------------------------");
        
    }
            
       
    
}
