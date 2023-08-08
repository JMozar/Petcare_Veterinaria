
package Librerias;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Serializar{
    

    public static void serializar ( String sNombreArchivo, 
                                Object  arreglo) {
        try {
            ObjectOutputStream escritor = 
              new  ObjectOutputStream (
                      new FileOutputStream (sNombreArchivo));
            escritor.writeObject(arreglo);
            escritor.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Object deserializar (String sNombreArchivo) {
        Object  arreglo;
         try{
            ObjectInputStream lector = 
                new ObjectInputStream(
                        new FileInputStream(sNombreArchivo)); 
            arreglo = (Object)lector.readObject();
        
        } catch ( FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return arreglo;    
    
    }
}