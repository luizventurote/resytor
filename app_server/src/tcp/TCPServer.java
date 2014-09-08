package tcp;

import control.Core;
import java.io.*;
import java.net.*;
import java.sql.SQLException;

/**
 *
 * @author Ruan
 */
public class TCPServer extends Thread {
    
    @Override
    public void run() {
        TCPServer.startServer();
    }

    public static void startServer() {
        try {
            
            System.out.println("Server On!");
            
            int serverPort = 7896;
            DataInputStream in;
            DataOutputStream out;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            
            while (true) {
                
                Socket clientSocket = listenSocket.accept();
                in = new DataInputStream(clientSocket.getInputStream());
                out = new DataOutputStream(clientSocket.getOutputStream());
                
                // Recebe a mensagem
                String data = in.readUTF(); 
                System.out.println("Mensagem recebida: " + data);
                
                // Envia a mensagem para o Core
                Core core = Core.getInstance();
                String resposta = core.execute(data);
                
                // Envia a Resposta
                out.writeUTF(resposta);
            }
            
        } catch (IOException e){
            System.out.println("Erro: " + e.getMessage( ));
            
        } catch (SQLException e){
            System.out.println("Erro: " + e.getMessage( ));
        }
    }
}
