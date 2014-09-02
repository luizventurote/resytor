package control;

import control.ResytorException.TFijException;
import dao.Dao;
import java.util.LinkedList;

/**
 *
 * @author LuizVenturote https://github.com/luizventurote
 */
public class Resytor {
    
    Dao dao;

    public Resytor() {
        this.dao = new Dao();
    }
    
    /**
     * Método para calcular freqüência de termo TFij
     * 
     * @param fij
     * @return retorna um valor int
     */
    public int calculaTFij(int fij)  throws TFijException{
        int tFij = 0;

        if(fij > 0){
            // O metodo .log() da classe MATH calcula log na base 10, se dividimos o resultado por /Math.log(2)
            // teremos o resultado correto na base 2.
            tFij = (int) (1 + (Math.log(fij)/Math.log(2)));
        }else{  
            if(fij == 0){
                tFij = 0;
            }else{
                if(fij < 0){
                    // fij não pode ser manor que 0;
                    throw new TFijException();
                }
            }
        }
        return tFij;
    }
    
    /**
     * Método para calcular o tamanho do documento
     * 
     * @param doc (documento)
     * @return retorna um valor int
     */
    public static int tmDoc(String doc) {

        int tam = doc.length();
        int cont = 1;

        for (int i = 0; i < tam; i++) {
            if (doc.charAt(i) == ' ') {
                cont++;
            }
        }

        return cont;
    }

    /**
     * Método para calcular a frequencia do termo no documento
     * 
     * @param doc
     * @param termo
     * @return retorna um valor int
     */
    public static int fij(String doc, String termo) {

        int pos = -1;
        int cont = 0;

        while (true) {
            pos = doc.indexOf(termo, pos + 1);
            if (pos < 0) {
                break;
            }
            cont++;
        }
        return cont;
    }

    /**
     * Método para calcular a frenquencia do termo na coleção
     * 
     * @param colecao
     * @param termo
     * @return retorna um valor int 
     */
    public static int Fi(LinkedList colecao, String termo) {

        int total = 0;

        for (Object doc : colecao) {

            int pos = -1;
            int cont = 0;

            while (true) {
                pos = doc.toString().indexOf(termo, pos + 1);
                if (pos < 0) {
                    break;
                }
                cont++;
            }

            total += cont;

        }

        return total;
    }
    
    /**
     * Método para calcular a frequencia do documento no termo
     * 
     * @param colecao
     * @param termo
     * @return retorna um valor int
     */   
    public static int ni(LinkedList colecao, String termo){
        
       int cont = 0;
        
        for (Object doc : colecao) {
            if(doc.toString().lastIndexOf(termo) != -1){
                cont++;           
           }
        }
        
        return cont;
    }
    
    
    public static int CalcularTest(int num) {
        return num +10;
    }


}
