
package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Ruan
 */
public class TCPCliente {
    String mensagem;

    public TCPCliente(String mensagem) {
        this.mensagem = mensagem;
        String nomeHost = "localhost"; //localhost deve ser substituido quando sistema deixar de ser local
        Socket s = null;
        try {
            int serverPort = 7896;
            s = new Socket(nomeHost, serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeUTF(mensagem); 
            String data = in.readUTF();
            System.out.println("Recebido: " + data);
        } catch (UnknownHostException e) {
            System.out.println("Socket: "
                    + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {/*close falhou*/

                }
            }
        }//finally
    }
    
}
