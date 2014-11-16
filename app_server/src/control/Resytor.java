package control;

import control.ResytorException.TFijException;
import dao.Dao;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import model.Cluster;
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
    private ArrayList<Cluster> listaCluster;

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
        
        String msg = mensagem.toLowerCase();
        
        //retirada dos StopPunctuations do conteudo do documento
        msg = msg.replace(".", ""); msg = msg.replace(",", "");  
        msg = msg.replace(";", ""); msg = msg.replace(":", "");
        msg = msg.replace("?", ""); msg = msg.replace("!", ""); 
        msg = msg.replace("...", ""); msg = msg.replace("-", ""); 
        msg = msg.replace("(", ""); msg = msg.replace(")", ""); 
        msg = msg.replace("_", ""); msg = msg.replace("*", "");
        msg = msg.replace("[", ""); msg = msg.replace("]", ""); 
        msg = msg.replace("/", ""); msg = msg.replace("'", "");
        msg = msg.replace("}", ""); msg = msg.replace("}", ""); 
        msg = msg.replace("+", ""); msg = msg.replace("&", "");
        msg = msg.replace("º", ""); msg = msg.replace("ª", ""); 
        msg = msg.replace("%", ""); msg = msg.replace("§", "");
        msg = msg.replace("$", ""); msg = msg.replace("#", ""); 
        msg = msg.replace("@", ""); msg = msg.replace("'", "");
        msg = msg.replace("é", "e"); msg = msg.replace("ê", "e");
        msg = msg.replace("á", "a"); msg = msg.replace("ã", "a");
        msg = msg.replace("ó", "o"); msg = msg.replace("ô", "o");
        msg = msg.replace(" a ", " "); msg = msg.replace("Ç", "C");
        msg = msg.replace("\"", ""); msg = msg.replace("”", "");
        
        msg = msg.replace(" um ", " ");
        msg = msg.replace(" em ", " ");
        msg = msg.replace(" sua ", " ");
        msg = msg.replace(" os ", " ");
        msg = msg.replace(" na ", " ");
        msg = msg.replace(" se ", " ");
        msg = msg.replace(" no ", " ");
        msg = msg.replace(" tem ", " ");
        msg = msg.replace(" nao ", " ");
        msg = msg.replace(" nos ", " ");
        msg = msg.replace(" eu ", " ");
        msg = msg.replace(" ja ", " ");
        msg = msg.replace(" e ", " ");
        msg = msg.replace(" do ", " ");
        msg = msg.replace(" so ", " ");
        msg = msg.replace(" o ", " ");
        msg = msg.replace(" meu ", " ");
        msg = msg.replace(" dos ", " ");
        msg = msg.replace(" de ", " ");
        msg = msg.replace(" nas ", " ");
        msg = msg.replace(" viu ", " ");
        msg = msg.replace(" uso ", " ");
        msg = msg.replace(" seu ", " ");
        msg = msg.replace(" of ", " ");
        msg = msg.replace(" me ", " ");
        msg = msg.replace(" bom ", " ");
        msg = msg.replace(" pra ", " ");
        msg = msg.replace(" q ", " ");
        msg = msg.replace(" que ", " ");
        msg = msg.replace(" pelos ", " ");
        msg = msg.replace(" foram ", " ");
        msg = msg.replace(" com ", " ");
        msg = msg.replace(" para ", " ");
        msg = msg.replace(" voce ", " ");
        msg = msg.replace(" como ", " ");
        msg = msg.replace(" todos ", " ");
        msg = msg.replace(" nos ", " ");
        msg = msg.replace(" dar ", " ");
        
        return msg;
        
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
    
    /**
     * Converte uma lista de Documentos em uma String
     * 
     * @author LuizVenturote https://github.com/luizventurote
     * @param ArrayList<Documento> Lista de Documentos
     * @return String String com o conteúdo de cada documento
     */
    public static String convertDocumentToString(ArrayList<Documento> documentos) {
        
        String result = "";
        
        for (int i=0; i<documentos.size(); i++) {
            result = result + "#-#" + documentos.get(i).getConteudo();
        }
        
        return result;
    }
    
    
    /**
     * Retorna todas as mensagens classificadas por assunto
     * 
     * @author LuizVenturote https://github.com/luizventurote
     * @param int k Quantidade de clusters a serem gerados
     * @return void
     */
    public ArrayList<Cluster> getMensagensClassificadas(int k) throws SQLException {
        
        // Inicia a lista de termos
        this.listaTermos = new ArrayList();
        
        // Preparando os documentos
        this.listaDocumentos = Resytor.getTodosDocumentos();
                
        // Tamanho da lista de documentos
        int listDocSize = listaDocumentos.size();
                 
        System.out.println(listDocSize);
        
        // Conteúdo de todos os documento em uma só string
        String conteudo_geral = "";
        
        // Remove as stop words das mensagens retornadas do banco
        for(int i=0; i<listDocSize; i++){
            
            // Recupera o conteúdo do Documento
            String conteudo = listaDocumentos.get(i).getConteudo();
                
            // Remove as stopwords
            conteudo = Resytor.removeStopWords(conteudo);
                
            // Formata o conteúdo
            conteudo = conteudo.toLowerCase();
            
            // Incrementa no conteúdo geral
            conteudo_geral = conteudo_geral + " " + conteudo;
          
            listaDocumentos.get(i).setConteudo(conteudo);
       
            System.out.println(listaDocumentos.get(i).getConteudo());
            
        }
        
        // Monta a lista de termos
        this.listaTermos = this.montarListaDeTermos(conteudo_geral);

        // Calcular a frequencia e frequencia total dos termos
        this.calcularFrequenciaDosTermos();
        
        // Faz a redução de dimensionalidade na lista de termos
        this.reduzirListaDeTermos(2);
        
        showListaDeTermos();
        
        // Faz o cálculo do TF, IDF e TFIDF e adiciona na lista de termos
        this.calcularTFIDF();
        
        // Representação vetorial
        this.representacaoVetorial();
        
        // Seleção de clusters
        this.selecionaClusters(k);
        
        // Gera a lista de similaridade entre os documentos
        this.calcularSimilaridadeDocumentos();
        
        // Separa os dDocumentos em clusters
        this.gerarListaDeClusters();

        return this.listaCluster;
        
    }
    
    /**
     * Gera a lista de clusters
     * 
     * @author LuizVenturote https://github.com/luizventurote
     * @return void
     */
    private void gerarListaDeClusters() {
        
        System.out.println("\n ... Lista de CLuesters ... ");
        
        this.listaCluster = new ArrayList();
 
        int listDocSize = this.listaDocumentos.size();
        
        double lista_clusters_simil[] = new double[listDocSize];
                
        for(int i=0; i<listDocSize; i++) {
            
            Documento documento_i = listaDocumentos.get(i);
            
            // Similaridade da lista de clusters
            lista_clusters_simil = documento_i.getClusterList();
            
            for(int j=0; j<listDocSize; j++) {
                
                Documento documento_j = listaDocumentos.get(j);
                
                if(lista_clusters_simil[j] > 0) {
                    
                    if(documento_j.isCluster()) {
                    
                        System.out.println("Cálculo!");
                        
                    } else {
                        
                        if(documento_i.isCluster()) {
                           
                            if(documento_j.getClassificacao() == -1) {
                                documento_j.setClassificacao(i);
                                documento_j.setSimilaridade(lista_clusters_simil[j]);
                            } else {

                                if(documento_j.getSimilaridade() < lista_clusters_simil[j]) {
                                    documento_j.setClassificacao(i);
                                    documento_j.setSimilaridade(lista_clusters_simil[j]);
                                }
                            }                            
                        }                        
                    }                    
                }
            }            
        }
        
        // Quantidade de clusters
        int qtd_clusters=0;
        
        // Cria os clusters
        for(int i=0; i<listDocSize; i++) {
            
            Documento documento_i = listaDocumentos.get(i); 
            
            if(documento_i.isCluster()) {
                Cluster cls = new Cluster();
                cls.setClasse(documento_i.getClasse());
                cls.getListaDocumentos().add(documento_i);
                cls.setClassificacao(documento_i.getClassificacao());
                this.listaCluster.add(cls);  
                
                qtd_clusters++;
            }
        }
        
        System.out.println("-----");
        
        for(int i=0; i<listDocSize; i++) {
            
            Documento documento_i = listaDocumentos.get(i); 
            
            if(!documento_i.isCluster()) {
                
                for(int j=0; j<qtd_clusters; j++) {
                    
                    Cluster cls = this.listaCluster.get(j);
                    
                    if(documento_i.getClassificacao() == cls.getClassificacao()) {
                        
                        cls.getListaDocumentos().add(documento_i);
                        
                    }
                }
            }
        }
        
        // Adiciona os documentos na lista de clusters
        int i=0;
        Iterator<Cluster> iterator = this.listaCluster.iterator();
	while (iterator.hasNext()) {
            
            int j=0;
            
            Cluster cls = this.listaCluster.get(i);
            
            System.out.println(cls.getClasse());
            
            Iterator<Documento> iterator_cls = cls.getListaDocumentos().iterator();
            while (iterator_cls.hasNext()) {
                
                Documento documento_j = cls.getListaDocumentos().get(j);
                
                System.out.println(documento_j.getConteudo());
                
                j++;
		iterator_cls.next();
            }
            
            System.out.println("");
            
            i++;
            iterator.next();
	}
            
    }
    
    /**
     * Gera a lista de similaridade entre os documentos
     * 
     * @author LuizVenturote https://github.com/luizventurote
     * @return void
     */
    private void calcularSimilaridadeDocumentos() {
        
        int listDocSize = this.listaDocumentos.size();
        double sim=0;
        
        System.out.println(" ... Lista de Similaridade dos Documentos ... ");
        
        for(int i=0; i<listDocSize; i++) {
            
            Documento documento_i = listaDocumentos.get(i);
            
            double[] sim_table = new double[listDocSize];
                
            // Verifica se o ducmento i é centróide
            if(documento_i.isCluster()) {
                System.out.print(" C |");
            } else {
                System.out.print(" x |");
            }
            
            for(int j=0; j<listDocSize; j++) {
                
                // Cálcula a similaridade entre os documentos
                if(i!=j) {
                    
                    Documento documento_j = listaDocumentos.get(j);
                    
                    sim = calcularSimilaridade(documento_i, documento_j);
                    
                    sim_table[j] = sim;
                    
                    System.out.print(" { " + sim + " } ");
                    
                } else {
                    
                    sim_table[j] = -1;
                    
                    System.out.print(" {  -  } ");
                    
                }
                
            }
            
            documento_i.setClusterList(sim_table);
            
            System.out.println("");
            
        }
    }
    
    /**
     * Adiciona a representação vetorial no documento
     * 
     * @author LuizVenturote https://github.com/luizventurote
     * @return void
     */
    private void representacaoVetorial() {
        
        int listDocSize = this.listaDocumentos.size();
        int listaTermosSize = this.listaTermos.size();
    
        // Representacao VETORIAL dos Documentos
        for(int j=0; j < listDocSize; j++){
            
            int p=0;
            
            // Documento
            Documento documento_j = listaDocumentos.get(j);
                        
            // Array de representação
            double[] representacao = new double[listaTermosSize];
            
            double TFIDF_maior=0;
            int termo_tfidf_maior=0;
            
            for(int i=0; i<listaTermosSize; i++) {
                
                // Termo
                Termo termo_i = listaTermos.get(i);
                
                if(TFIDF_maior < termo_i.getTFIDF()[j]) {
                    TFIDF_maior = termo_i.getTFIDF()[j];
                    termo_tfidf_maior = i;
                }
                
                // Seta os valores da representação de cada termo em um documento
                representacao[p] = termo_i.getTFIDF()[j];
                
                p++;
                
            }
            
            // Seta os valores da representação
            documento_j.setRepresentacao(representacao);
            
            // Seta a classificação do Documento
            documento_j.setClasse(listaTermos.get(termo_tfidf_maior).getTermo());
            
        }
    }
    
    /**
     * Cálcula a similaridade entre dois documentos
     * 
     * @author LuizVenturote https://github.com/luizventurote
     * @param Documento doc_1 Documento 1
     * @param Documento doc_2 Documento 2
     * @return similaridade Similaridade entre o conteúdo dos documentos
     */
    private double calcularSimilaridade(Documento doc_1, Documento doc_2) {
        
        double similaridade = 0;
        
        int listDocSize = this.listaDocumentos.size();
        int listaTermosSize = this.listaTermos.size();

        // Representação do último Documento
        double[] doc_2_rep = doc_2.getRepresentacao();

        //Percorrendo todos os documentos da coleção
        double A=0, B=0, C=0, D=0, E=0;

        for(int i=0; i<listaTermosSize; i++){

            // Representações
            double[] doc_1_rep = doc_1.getRepresentacao();

            // Percorrendo a representacao vetorial de cada documento
            A = A + ( doc_1_rep[i] * doc_2_rep[i] );
            B = B + ( doc_1_rep[i] * doc_1_rep[i] );
            C = C + ( doc_2_rep[i] * doc_2_rep[i] );

        }
        
        B = Math.sqrt(B);
        C = Math.sqrt(C);
        E = B * C;
        similaridade = A/E;
        
        return similaridade;
    
    }
    
    /**
     * Seleciona os documentos centróides de cada cluster
     * 
     * @author LuizVenturote https://github.com/luizventurote
     * @param int k Quantidade de clusters a serem gerados
     * @return void
     */
    private void selecionaClusters(int k) {
        
        Random gerador = new Random();
        
        int listDocSize = this.listaDocumentos.size();
        
        for(int i=0; i<k; i++) {
            
            int doc_id = gerador.nextInt(listDocSize);
            
            Documento documento_i = listaDocumentos.get(doc_id);
            
            if(documento_i.isCluster()) {
                k--;
            } else {
                documento_i.setCluster(true);
            }
            
        }
                
    }
    
    /**
     * Faz o cálculo do TF, IDF e TFIDF e adiciona na lista de termos
     * 
     * @author LuizVenturote https://github.com/luizventurote
     * @return void
     */
    private void calcularTFIDF() {
        
        int listDocSize = this.listaDocumentos.size();
        
        // Cálculo do TF, IDF E TFIDF
        for(int i=0; i<listaTermos.size(); i++) {
            
            // Termo
            Termo termo_i = listaTermos.get(i);
            
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
                    termIDF[j]      =   ( Resytor.log(listDocSize/termo_i.getQtdDoc(),2));
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
        
    }
    
    /**
     * Exibe a lista de termos
     * 
     * @author LuizVenturote https://github.com/luizventurote
     * @return void
     */
    public void showListaDeTermos() {
        
        System.out.println("\n... Lista de termos ...");
        
        for(int i=0; i<listaTermos.size(); i++) {
            
            // Termo
            Termo termo_i = this.listaTermos.get(i);
            
            System.out.println(termo_i.getTermo()+" {"+Double.toString(termo_i.getFrequenciaTotal())+"}");
        }
        
        System.out.println("Quantidade de termos: "+listaTermos.size()+"\n");
        
    }
    
    /**
     * Faz a redução de dimensionalidade na lista de termos
     * 
     * @author LuizVenturote https://github.com/luizventurote
     * @param String freqMin Frequência mínima total
     * @return void
     */
    private void reduzirListaDeTermos(int freqMin) {
        
        int listaTermosSize = this.listaTermos.size();
        
        ArrayList<Termo> listaTermosSwap = new ArrayList();
        ArrayList<Termo> listaTermosReduzida = new ArrayList();
        
        for(int i=0; i<listaTermosSize; i++){
            
            // Termo
            Termo termo_i = this.listaTermos.get(i);
            
            // Descarta alguns termos pequenos
            if(termo_i.getTermo().length() > 2) {
            
                // Frequência total do termo
                int FreqTotalTermo = (int) termo_i.getFrequenciaTotal();

                if(FreqTotalTermo >= freqMin) {
                    listaTermosSwap.add(termo_i);
                }
            }
            
            
        }
        
        int listaTermosSwapSize = listaTermosSwap.size();
        
        // Remove termos iguais
        for(int i=0; i<listaTermosSwapSize; i++){
            
            // Termo
            Termo termo_i = listaTermosSwap.get(i);
            
            // Adiciona o primeiro termo
            if(listaTermosReduzida.isEmpty()) {
                listaTermosReduzida.add(termo_i);
            }
            
            // Representa a frequência que um termo aparece na lista existente
            int frequencia=0;
            int j=0;
            
            Iterator<Termo> iterator = listaTermosReduzida.iterator();
            while (iterator.hasNext()) {
                
                // Verifica se o termo já está adicionado na lista
                if(listaTermosReduzida.get(j).getTermo().equals(termo_i.getTermo()) ) {
                    frequencia++;
                }
                
                j++;
                iterator.next();
                
            }
            
            // Verifica se a frequência do termo é igual a zero para adicionar o termo na lista
            if(frequencia == 0) {
                listaTermosReduzida.add(termo_i);
            }
        }
        
        // Adiciona a nova lista reduzina na lista principal de termos
        this.listaTermos = listaTermosReduzida;
    }
    
    
    /**
     * Faz o cálculo da frequência do termos em telação a lista de documentos
     * 
     * @author LuizVenturote https://github.com/luizventurote
     * @return void
     */
    private void calcularFrequenciaDosTermos() {
        
        int listaTermosSize = this.listaTermos.size();
        int listDocSize     = this.listaDocumentos.size();
        
        for(int i=0; i<listaTermosSize; i++){
            
            // Termo
            Termo termo_i = this.listaTermos.get(i);
            
            // Frequencia total do termo na lista de documentos
            int freqTotal = 0;
            
            // Inicializa o array para armazenar a frequencia do termo em cada documento
            int[] frequencia = new int[listDocSize];
            
            // Variável que armazena a quantidade de documentos que contém o termo
            int cont_doc = 0;
            
            for(int j=0; j < listDocSize; j++){
                
                // Recupera o conteúdo do Documento
                String conteudo = this.listaDocumentos.get(j).getConteudo();
                
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
    }

    
    /**
     * Monta uma lista de termos
     * 
     * @author LuizVenturote https://github.com/luizventurote
     * @param String termos String com os termos
     * @return ArrayList<Termo> Retorna uma lista de termos
     */
    private ArrayList<Termo> montarListaDeTermos(String termos) {
        
        // Cria um array de string a partir de uma string;
        String termosArray[] = termos.split(" ");
               
        // Inicia a lista de termos
        ArrayList<Termo> listaDeTermos = new ArrayList();
        
        // Objeto para armazenar os valores antes de adiciona-los na lista
        Termo termo;
        
        // Array que armazena o resultado da pesquisa
        ArrayList<Documento> arrayResultado = new ArrayList();
        
        // Adiciona os termos no objeto
        for(int i=0; i<termosArray.length; i++){
            if( !"".equals(termosArray[i])) {
                termo = new Termo(termosArray[i]); 
                listaDeTermos.add(termo);
            }
        }
        
        return listaDeTermos;
    }
    
}