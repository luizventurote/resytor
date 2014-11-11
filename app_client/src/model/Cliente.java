package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Cliente extends Thread {  
    
    private Socket conexao;  
    public Cliente(Socket socket) {  
        this.conexao = socket;  
    }  
    
    public static void main(String[] args) {
        
        try {    
            Socket socket = new Socket("127.0.0.1",1500);// 8010);  
            
            PrintStream saida = new PrintStream(socket.getOutputStream());  
            BufferedReader teclado = new BufferedReader(new InputStreamReader(  
                    System.in));  
            System.out.print("Digite seu nome: ");  
            String meuNome = teclado.readLine();  
  
            // envia o nome digitado para o servidor  
            saida.println(meuNome.toUpperCase());  
  
            Thread thread = new Cliente(socket);  
            thread.start();  
            
            String msg;  
            while (true) {  
                System.out.print("Mensagem > ");  
                msg = teclado.readLine();  
                saida.println(msg);  
            }  
        } catch (IOException e) {  
            System.out.println("Falha na Conexao... .. ." + " IOException: "  
                    + e);  
        }
    }//main
    
    public void run() {  
        try {  
            // recebe mensagens de outro cliente através do servidor  
            BufferedReader entrada = new BufferedReader(new InputStreamReader(  
                    this.conexao.getInputStream()));  
            String msg;  
            while (true) {  
                // pega o que o servidor enviou  
                msg = entrada.readLine();  
                if (msg == null) {  
                    System.out.println("Conexão encerrada!");  
                    System.exit(0);  
                }  
                System.out.println();  
                System.out.println(msg);  
                System.out.print("Responder > ");  
            }  
        } catch (IOException e) {  
            System.out.println("Ocorreu uma Falha... .. ." + " IOException: "  
                    + e);  
        }  
   }//run
    
}//fim
