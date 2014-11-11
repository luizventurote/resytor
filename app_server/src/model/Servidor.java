package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

public class Servidor extends Thread {
   
    private static Vector CLIENTES;    
    private Socket conexao;    
    private String nomeCliente;  
    private static List LISTA_DE_NOMES = new ArrayList();  
  
    public Servidor(Socket socket) {  
        this.conexao = socket;  
    }  
  
    public boolean armazena(String newName) {    
        for (int i = 0; i < LISTA_DE_NOMES.size(); i++) {  
            if (LISTA_DE_NOMES.get(i).equals(newName))  
                return true;  
        }    
        LISTA_DE_NOMES.add(newName);  
        return false;  
    }  
  
    public void remove(String oldName) {  
        for (int i = 0; i < LISTA_DE_NOMES.size(); i++) {  
            if (LISTA_DE_NOMES.get(i).equals(oldName))  
                LISTA_DE_NOMES.remove(oldName);  
        }  
    }  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        CLIENTES = new Vector();  
        try {  
            ServerSocket server = new ServerSocket(1500);  
            System.out.println("ServidorSocket rodando na porta 1500");  
            
            while (true) {  
                Socket conexao = server.accept();  
  
                Thread t = new Servidor(conexao);  
                t.start();  
            }  
        } catch (IOException e) {  
            System.out.println("IOException: " + e);  
        }  
    }
  
    @Override
    public void run() {  
        try {   
            BufferedReader entrada = new BufferedReader(new InputStreamReader(  
                    this.conexao.getInputStream()));  
            PrintStream saida = new PrintStream(this.conexao.getOutputStream());  
  
            // recebe o nome do cliente  
            this.nomeCliente = entrada.readLine();  
            
            if (armazena(this.nomeCliente)) {  
                saida.println("Este nome ja existe! Conecte novamente com outro Nome.");  
                CLIENTES.add(saida);  
                
                this.conexao.close();  
                return;  
            } else {  
  
                // mostra o nome do cliente conectado ao servidor  
                System.out.println(this.nomeCliente  
                        + " : Conectado ao Servidor!");  
            }  
            if (this.nomeCliente == null) {  
                return;  
            }  
  
           // adiciona os dados de saida do cliente no objeto CLIENTES  
            CLIENTES.add(saida);  
  
            // recebe a mensagem do cliente  
            String msg = entrada.readLine();  
            while (msg != null && !(msg.trim().equals(""))) {  
                sendToAll(saida, " escreveu: ", msg);  
   
                msg = entrada.readLine();  
            }  
 
            System.out.println(this.nomeCliente + " saiu do bate-papo!");  
            sendToAll(saida, " saiu", " do bate-papo!");  
            
            remove(this.nomeCliente);  
            CLIENTES.remove(saida);  
   
            this.conexao.close();  
        } catch (IOException e) {  
  
            System.out.println("Falha na Conexao... .. ." + " IOException: "  
                    + e);  
        }  
    }  
  
    public void sendToAll(PrintStream saida, String acao, String msg)  
            throws IOException {  
        Enumeration e = CLIENTES.elements();  
        while (e.hasMoreElements()) {  
  
            // obtém o fluxo de saída de um dos CLIENTES  
            PrintStream chat = (PrintStream) e.nextElement();  
  
            if (chat != saida) {  
                chat.println(this.nomeCliente + acao + msg);  
            }  
        }  
    }      
}