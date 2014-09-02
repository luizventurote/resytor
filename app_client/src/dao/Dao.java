package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Naiara
 */
public class Dao {
    
    public void saveData(String chave, String valor) throws FileNotFoundException, IOException{
       
        Dao dao = new Dao();
        
        String data = dao.getData(chave);
        
        if(data == null || "".equals(data)) {
            BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt", true));
            writer.write(chave + "=" + valor);
            writer.newLine();
            writer.close();
        }
                
    }
    
    public String getData(String chave) throws FileNotFoundException, IOException{

        BufferedReader reader = new BufferedReader(new FileReader("config.txt"));

        String line, keyValue = null;
        
        while(reader.ready()){
            
            line = reader.readLine();
            
            int result = line.split("=")[0].compareTo(chave);
                       
            if (result == 0){
                keyValue = line.split("=")[1];
            } else keyValue = "";
                        
        }
        reader.close();
       
        return keyValue;        

    }//getData
    
}
