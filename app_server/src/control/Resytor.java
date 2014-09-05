package control;

import control.ResytorException.TFijException;
import dao.Dao;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import model.Documento;
import model.Termo;

/**
 *
 * @author LuizVenturote https://github.com/luizventurote
 */
public class Resytor {
    
    private Dao dao;
    private ArrayList<Termo> listaTermos;
    private ArrayList<Documento> listaDocumentos;

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
	stopWords.add("COM"); stopWords.add("SE"); stopWords.add("ESSE"); stopWords.add("VOU"); stopWords.add("do");
        stopWords.add("é");
        
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
        
        msg = msg.replace("  ", " ");
                
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
    
    public void pesquisarPorTermos(String termos) {
        
        // Remove as strop words dos termos
        termos = Resytor.removeStopWords(termos);
        termos = termos.toLowerCase();
        
        // Cria um array de string a partir de uma string;
        String termosArray[] = termos.split(" ");
               
        // Inicia a lista de termos
        this.listaTermos = new ArrayList();
        
        // Objeto para armazenar os valores antes de adiciona-los na lista
        Termo termo;
        
        // Adiciona os termos no objeto
        for(int i=0; i < termosArray.length; i++){
            termo = new Termo(termosArray[i]); 
            listaTermos.add(termo);
        }
        
        for(int i=0; i < listaTermos.size(); i++){
            System.out.println(listaTermos.get(i).getTermo()); 
        }
        
        // Preparando os documentos
        this.listaDocumentos = new ArrayList();
        Documento doc;
        doc = new Documento("Inflacao e um aumento generalizado de precos e resulta em perda do poder de compra."); 
        listaDocumentos.add(doc);
        doc = new Documento("A inflacao cronica provoca sempre uma perda do poder de compra (ou poder aquisitivo) da populacao."); 
        listaDocumentos.add(doc);
        doc = new Documento("O Universo em expansao: a Teoria Inflacionaria da origem do Universo"); 
        listaDocumentos.add(doc);
        doc = new Documento("A Origem do Universo, conhecida como teoria do BIG BANG surgiu das observacoes de Hubble"); 
        listaDocumentos.add(doc);
        doc = new Documento("A origem da vida e a teoria da evolucao de Darwin."); 
        listaDocumentos.add(doc);
        doc = new Documento("Sistemas Tradicionais de Arquivos foram substituidos pelos Sistemas de Bancos de Dados!"); 
        listaDocumentos.add(doc);
        doc = new Documento("Sistemas Distribuidos sao uma alternativa aos Sistemas Centralizados."); 
        listaDocumentos.add(doc);
        doc = new Documento("Sistemas Operacionais, Bancos de Dados e Redes de Computadores."); 
        listaDocumentos.add(doc);
        doc = new Documento("Sistemas de Inteligencia Artificial Distribuida..."); 
        listaDocumentos.add(doc);
        doc = new Documento("Ultraman, Ultraseven e Speed Racer sao personagens da ficcao japonesa."); 
        listaDocumentos.add(doc);
        
        // O ultimo documento corresponde ao proprio argumento de pesquisa - para efeito de comparacao com os demais documentos
        doc = new Documento(termos); 
        listaDocumentos.add(doc);
        
        // Calcular a frequencia
        for(int i=0; i < listaTermos.size(); i++){
            int[] frequencia = new int[listaDocumentos.size()];
            for(int j=0; j < listaDocumentos.size(); j++){
                String conteudo = listaDocumentos.get(j).getConteudo();
                conteudo = Resytor.removeStopWords(conteudo);
                conteudo = conteudo.toLowerCase();
                frequencia[j] = Resytor.fij(conteudo, listaTermos.get(i).getTermo());
 
            }
            listaTermos.get(i).setFrequencia(frequencia);
        }
        
        for(int i=0; i < listaTermos.size(); i++){
            System.out.println("Termo: "+ listaTermos.get(i).getTermo());
            int[] frequenciaTermo = listaTermos.get(i).getFrequencia();
            for(int j=0; j < listaDocumentos.size(); j++){
                
                System.out.println("Doc_"+j+": "+frequenciaTermo[j]);
 
            }
        }
        
    }


}
