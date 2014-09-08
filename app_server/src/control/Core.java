package control;

/**
 *
 * @author LuizVenturote https://github.com/luizventurote
 */
public class Core {
    
    private static Core uniqueInstance = null;
    public static final int ENVIAR_MENSAGEM = 1;
    public static final int RECUPERAR_10_ULTIMAS = 2;
    public static final int RECUPERAR_POR_PALAVRACHAVE = 3;
    
    public static synchronized Core getInstance() {
        if (uniqueInstance == null) {
            return new Core();
        }
        return uniqueInstance;
    }
    
    // chamando o metodo : Interpretador.getInstance().execute(<id da acao> , <mensagem>);
    public Object execute(String mensagem) {
        
        int acao = Integer.parseInt(mensagem.split("-")[0]);
        mensagem = mensagem.split("-")[1];
        
        if (acao == ENVIAR_MENSAGEM) {
            System.out.println("Enviar mensagem!");
            return acao;
            // ENVIAR MENSAGEM
        } else if (acao == RECUPERAR_10_ULTIMAS) {
            System.out.println("Recuperar as mensagens");
            // RECUPERAR AS 10 ULTIMAS MENSAGENS
        } else if (acao == RECUPERAR_POR_PALAVRACHAVE) {
            System.out.println("Recuperar mensagens por palavra chave");
            // RECUPERAR POR PALAVRA-CHAVE
        } else {
            System.out.println("Erro");
        }
        
        return null;
    }
    
    public void verificaBD(){
        
    }
   
}
