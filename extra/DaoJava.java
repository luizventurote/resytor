/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fudeo;

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
public class DaoJava {
    
    public void saveData(String chave, String valor) throws FileNotFoundException, IOException{
       
        BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt", true));
        writer.write(chave + "-" + valor);
        writer.newLine();
        writer.close();        
    }
    
    public String getData(String chave) throws FileNotFoundException, IOException{

        BufferedReader reader = new BufferedReader(new FileReader("config.txt"));

        String line, nomeChave = null;
        
        while(reader.ready()){
            
            line = reader.readLine();
            
            int result = line.split("-")[0].compareTo(chave);
                       
            if (result == 0){
                nomeChave = line.split("-")[0];
            }
            else nomeChave = "";
                        
        }
        reader.close();
       
        return nomeChave;        

    }//getData
    
}
