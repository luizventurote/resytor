package control;

import control.ResytorException.TFijException;
import dao.Dao;
import java.sql.SQLException;
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
    
    static double log(int x, int base) {
        return (double) (Math.log(x) / Math.log(base));
    }
    
    public ArrayList<Documento> pesquisarPorTermos(String termos) throws SQLException {
        
        // Remove as strop words dos termos
        termos = Resytor.removeStopWords(termos);
        termos = termos.toLowerCase();
        
        // Cria um array de string a partir de uma string;
        String termosArray[] = termos.split(" ");
               
        // Inicia a lista de termos
        this.listaTermos = new ArrayList();
        
        // Objeto para armazenar os valores antes de adiciona-los na lista
        Termo termo;
        
        // Array que armazena o resultado da pesquisa
        ArrayList<Documento> arrayResultado = new ArrayList();
        
        // Adiciona os termos no objeto
        for(int i=0; i < termosArray.length; i++){
            termo = new Termo(termosArray[i]); 
            listaTermos.add(termo);
        }
        
        for(int i=0; i < listaTermos.size(); i++){
            System.out.println(listaTermos.get(i).getTermo()); 
        }
        
        // Preparando os documentos
        this.listaDocumentos = Resytor.getTodosDocumentos();
        
        // O ultimo documento corresponde ao proprio argumento de pesquisa - para efeito de comparacao com os demais documentos
        Documento doc;
        doc = new Documento(termos); 
        listaDocumentos.add(doc);
        
        // Termo
        Termo termo_i;
        
        // Quantidade de termos da pesquisa
        int listaTermosSize = listaTermos.size();
        
        // Documento
        Documento documento_j;
                 
        // Tamanho da lista de documentos
        int listDocSize = listaDocumentos.size();
        
        // Calcular a frequencia e frequencia total
        for(int i=0; i < listaTermosSize; i++){
            
            // Termo
            termo_i = listaTermos.get(i);
            
            // Frequencia total do termo na lista de documentos
            int freqTotal = 0;
            
            // Inicializa o array para armazenar a frequencia do termo em cada documento
            int[] frequencia = new int[listDocSize];
            
            // Variável que armazena a quantidade de documentos que contém o termo
            int cont_doc = 0;
            
            for(int j=0; j < listDocSize; j++){
                
                // Recupera o conteúdo do Documento
                String conteudo = listaDocumentos.get(j).getConteudo();
                
                // Remove as stopwords
                conteudo = Resytor.removeStopWords(conteudo);
                
                // Formata o conteúdo
                conteudo = conteudo.toLowerCase();
                
                // Realiza o cálculo da frequencia de cada Termo em um Documento
                frequencia[j] = Resytor.fij(conteudo, termo_i.getTermo());
                
                // Soma a frequencia
                freqTotal += frequencia[j];
                
                // Realiza a contagem de documentos que contém o termo
                if(frequencia[j] > 0) {
                    cont_doc++;
                }
            }
            
            termo_i.setFrequencia(frequencia);
            termo_i.setFrequenciaTotal(freqTotal);
            termo_i.setQtdDoc(cont_doc);
        }
        
        // Cálculo do TF, IDF E TFIDF
        for(int i=0; i < listaTermosSize; i++) {
            
            // Termo
            termo_i = listaTermos.get(i);
            
            // Inicializa o array para armazenar a frequencia do termo em cada documento
            int[] frequencia = termo_i.getFrequencia();
            
            // Array de TF
            double[] termTF = new double[listDocSize];
            
            // Array de IDF
            double[] termIDF = new double[listDocSize];
            
            // Array TFIDF
            double[] termTFIDF = new double[listDocSize];
            
            for(int j=0; j < listDocSize; j++) {
                
                if(frequencia[j] > 0) {
                    
                    termTF[j]       =   Resytor.log(frequencia[j], 2) + 1;
                    termIDF[j]      =   ( Resytor.log(listDocSize,2) / termo_i.getQtdDoc() );
                    termTFIDF[j]    =   termTF[j] * termIDF[j];
                    
                } else {
                    
                    termTF[j]       =   0;
                    termIDF[j]      =   0;
                    termTFIDF[j]    =   0;
                    
                }
                
            }
            
            // Seta novos valores
            termo_i.setTF(termTF);
            termo_i.setIDF(termIDF);
            termo_i.setTFIDF(termTFIDF);
            
        }
        
        // Exibe os dados
        for(int i=0; i < listaTermosSize; i++){
            
            // Termo
            termo_i = listaTermos.get(i);
            
            System.out.println("\nTermo: "+termo_i.getTermo()+"{"+termo_i.getFrequenciaTotal()+"} {"+termo_i.getQtdDoc()+"}");
            
            // Array de TF
            double[] termTF = termo_i.getTF();
            
            // Array de IDF
            double[] termIDF = termo_i.getIDF();
            
            // Array TFIDF
            double[] termTFIDF = termo_i.getTFIDF();
            
            // Frequência
            int[] frequenciaTermo = termo_i.getFrequencia();
            
            for(int j=0; j < listDocSize; j++){
                
                System.out.println("Doc_"+j+": "+frequenciaTermo[j]+" {TF:"+termTF[j]+"} "+"{IDF:"+termIDF[j]+"} "+"{TFIDF:"+termTFIDF[j]+"}");
 
            }
            
            System.out.println("");
        }
        
        System.out.println("#################################");
        
        // Representacao VETORIAL dos Documentos
        for(int j=0; j < listDocSize; j++){
            
            int p = 0;
            
            // Documento
            documento_j = listaDocumentos.get(j);
            
            System.out.println("Doc_"+j);
            
            // Array de representação
            double[] representacao = new double[listaTermosSize];
            
            for(int i=0; i < listaTermosSize; i++) {
                
                // Termo
                termo_i = listaTermos.get(i);
                
                System.out.println(" - "+termo_i.getTermo());
                
                // Seta os valores da representação de cada termo em um documento
                representacao[p] = termo_i.getTFIDF()[j];
                
                System.out.println("  - "+representacao[p]);
                
                p++;
                
            }
            
            // Seta os valores da representação
            documento_j.setRepresentacao(representacao);
            
        }
        
        // SIMILARIDADES
        for(int j=0; j < listDocSize; j++) {
            
            // Documento
            documento_j = listaDocumentos.get(j);
            Documento ultimo_documento = listaDocumentos.get(listDocSize-1);
            
            // Representação do último Documento
            double[] ultimo_documento_rep = ultimo_documento.getRepresentacao();
            
            System.out.println(ultimo_documento.getConteudo());
           
            //Percorrendo todos os documentos da coleção
            double A=0, B=0, C=0, D=0, E=0;
            
            for(int i=0; i<listaTermosSize; i++){
                
                // Representações
                double[] documento_j_rep = documento_j.getRepresentacao();
                
                // Percorrendo a representacao vetorial de cada documento
                A = A + ( documento_j_rep[i] * ultimo_documento_rep[i] );
                B = B + ( documento_j_rep[i] * documento_j_rep[i] );
                B = Math.sqrt(B);
                C = C + ( ultimo_documento_rep[i] * ultimo_documento_rep[i] );
                C = Math.sqrt(C);
                E = B * C;
               
            }
            
            // Setando a similaridade do ducumento em relação a pesquisa
            documento_j.setSimilaridade(A/E);
            
            if(documento_j.getSimilaridade() > 0) {
                
                // Verifica se é o último documento para não adiciona-lo na lista
                if(j < (listDocSize-1) ) {
                    //System.out.println("Documento "+j+" com similaridade de: "+documento_j.getSimilaridade());
                    arrayResultado.add(documento_j);
                }
            }
        }
        
        // Retornando resultados
        return arrayResultado;
        
    }
    
    /**
     * Método responsável para pegar todas as msg do banco 
     * e armazenar seu conteúdo em objetos do tipo Documento
     * 
     * getTodosDocumentos(); assinatuna da chamada do método
     * @return arrayList com todos os documentos do banco
     */
    public static ArrayList<Documento> getTodosDocumentos() throws SQLException{
        Dao msgBanco = new Dao();
        ArrayList<String> allMessages = new ArrayList();
        ArrayList<Documento> documentos = new ArrayList();
        allMessages = msgBanco.getAllMessages();
        
        for(String msg:allMessages){
            documentos.add(new Documento(msg));
        }
        return documentos;
    }
}