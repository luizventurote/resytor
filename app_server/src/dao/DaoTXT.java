package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author LuizVenturote https://github.com/luizventurote
 */
public class DaoTXT {
    
    // Nome do arquivo que armazena os dados
    private String fileName;

    public DaoTXT() {
        this.fileName = "data.txt";
    }

    public boolean saveData(String valor) throws FileNotFoundException, IOException{

        BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName, true));
        
        BufferedReader reader = new BufferedReader(new FileReader(this.fileName));
        
        String line, keyValue = null;
        
        int qtd_linhas=0;
        
        
        
        while(reader.ready()){
            
            reader.readLine();
            qtd_linhas++;
            
        }
        
        reader.close();
        
        if(qtd_linhas == 0) {
            writer.write("qtd=1");
            writer.newLine();
            qtd_linhas++;
        } else {
            int new_id = Integer.parseInt(this.getData("qtd")) + 1;
            this.updateData("qtd", Integer.toString(new_id));
        }
        
        System.out.println(qtd_linhas);
        
        writer.write(qtd_linhas+"="+valor);
        writer.newLine();
        writer.close();
            
        return true;
                
    }
    
    public String getData(String chave) throws FileNotFoundException, IOException{

        BufferedReader reader = new BufferedReader(new FileReader(this.fileName));

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
    
    public boolean updateData(String chave, String valor) throws FileNotFoundException, IOException{
               
        String data = this.getData(chave);
        
        if(data == null || "".equals(data) || !valor.equals(data)) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName, true));
            writer.write(chave + "=" + valor);
            writer.newLine();
            writer.close();
            return true;
        } else {
            return false;
        }
                
    }
    
    /**
     *
     * Método para a inserção da mensagens
     *
     */
    public boolean insert(String text) {
        
        return true;
    }
    
}
