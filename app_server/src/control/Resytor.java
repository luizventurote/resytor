package control;

import control.ResytorException.TFijException;
import dao.Dao;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

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
     * Método responsável pela retirada dos Stop Words do conteúdo do documento
     * 
     * removeStopWords(mensagem); assinatuna da chamada do método
     * @param mensagem conteudo do documento
     * @return conteudo do documento sem os stopWords
     */
    public static String removeStopWords(String mensagem){
        ArrayList<String> stopWords = new ArrayList();
        ArrayList<String> subString = new ArrayList();
        String msg = mensagem;
        
         Scanner scn1 = new Scanner(msg);  
         while(scn1.hasNext()){
             subString.add(scn1.next());
         }//while
        
         //Adicionando os StopWords a um array, para facilitar a comparação com o conteúdo do documento
        stopWords.add("A"); stopWords.add("AS"); stopWords.add("O"); stopWords.add("OS");
	stopWords.add("UM"); stopWords.add("UNS"); stopWords.add("UMA"); stopWords.add("UMAS");
	stopWords.add("DA"); stopWords.add("DAS"); stopWords.add("DO"); stopWords.add("DOS");
	stopWords.add("DE"); stopWords.add("PARA"); stopWords.add("PRA"); stopWords.add("COMO");
	stopWords.add("QUANDO"); stopWords.add("QUEM"); stopWords.add("QUE"); stopWords.add("E");
	stopWords.add("ESTE");	 stopWords.add("ESTES"); stopWords.add("ESTA"); stopWords.add("ESTAS");
	stopWords.add("ISTO"); stopWords.add("AQUELA"); stopWords.add("AQUELAS"); stopWords.add("AQUELE");
	stopWords.add("AQUELES"); stopWords.add("AQUILO"); stopWords.add("NAQUELA"); stopWords.add("NAQUELAS");
	stopWords.add("NAQUELE"); stopWords.add("NAQUELES"); stopWords.add("NAQUILO"); stopWords.add("EM");
	stopWords.add("NA"); stopWords.add("NO"); stopWords.add("NAS"); stopWords.add("NOS");
	stopWords.add("POR"); stopWords.add("QUE"); stopWords.add("QUANDO"); stopWords.add("COMO");
	stopWords.add("COM"); stopWords.add("SE"); stopWords.add("ESSE"); stopWords.add("VOU");
        
        //retirada dos StopPunctuations do conteudo do documento
        msg = msg.replace(".", " "); msg = msg.replace(",", " ");  
        msg = msg.replace(";", " "); msg = msg.replace(":", " ");
        msg = msg.replace("?", " "); msg = msg.replace("!", " "); 
        msg = msg.replace("...", " "); msg = msg.replace("-", " "); 
        msg = msg.replace("(", " "); msg = msg.replace(")", " "); 
        msg = msg.replace("_", " "); msg = msg.replace("*", " ");
        msg = msg.replace("[", " "); msg = msg.replace("]", " "); 
        msg = msg.replace("/", " "); msg = msg.replace("'", " ");
        msg = msg.replace("}", " "); msg = msg.replace("}", " "); 
        msg = msg.replace("+", " "); msg = msg.replace("&", " ");
        msg = msg.replace("º", " "); msg = msg.replace("ª", " "); 
        msg = msg.replace("%", " "); msg = msg.replace("§", " ");
        msg = msg.replace("$", " "); msg = msg.replace("#", " "); 
        msg = msg.replace("@", " "); msg = msg.replace("'", " ");
        
        subString.removeAll(subString);//zerando o array, para a insercao da String sem os "stopPunctuations"        
        Scanner scn2 = new Scanner(msg);  
        while(scn2.hasNext()){
             subString.add(scn2.next());
         }//while
               
        for(String str:stopWords){
            for(String sub:subString){
                if(str.equalsIgnoreCase(sub)){
                        msg = msg.replaceAll("  ", " ");//substitui os caracters da esquerda pelos da direita, de toda a String                      
                        msg = msg.replaceFirst(sub + " " , " "); 
                        msg = msg.trim();//remove espaços do inicio e fim da String
                }//if
            }//for
        }//for
        return msg;
    }//removeStopWords

    
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
