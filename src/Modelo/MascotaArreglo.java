
package Modelo;

import Interfaces.InterfazArreglo;
import java.io.Serializable;

public class MascotaArreglo implements InterfazArreglo{
    private Mascota[] mascotas;
    public int num;
    private int indice;
    private final String[] cabecera =  {"CODIGO","NOMBRE",
                            "ESPECIE","RAZA", "FECHA NAC.",
                            "COLOR", "TALLA", "PESO"};
    
    public MascotaArreglo(int tamano) {
        this.mascotas = new Mascota[tamano];
        this.indice = 0;
    }
    
    public void agregar(Mascota mascota){
        this.mascotas[this.indice] = mascota;
        this.indice++; 
    }
    
    public void eliminar(int x) {
        
        if (x >= 0) {
            for (int i = x; i < mascotas.length - 1; i++) {
                mascotas[i] = mascotas[i + 1];
                mascotas[i + 1] = null;
                
            }
        }else{
            //System.out.println("Posicion invalida");
        }
        indice--;

    }
    
    //Necesario para mostrar datos en tabla
    @Override
    public String[] getCabecera() {
        return cabecera;
    }
    
    @Override
    public boolean isVacio() {
        return this.indice==0;
    }
      
    @Override
    public Object[][] getDatos(){
        Object resultado[][] = new Object[this.indice][8];
        if(!isVacio()){
            for(int indice = 0; indice< this.indice;indice++){
                resultado[indice][0] = this.mascotas[indice].getCodigo();
                resultado[indice][1] = this.mascotas[indice].getNombre();
                resultado[indice][2] = this.mascotas[indice].getEspecie();
                resultado[indice][3] = this.mascotas[indice].getRaza();
                resultado[indice][4] = this.mascotas[indice].getFechaNacimiento();
                resultado[indice][5] = this.mascotas[indice].getColorPelo();
                resultado[indice][6] = this.mascotas[indice].getTalla();
                resultado[indice][7] = this.mascotas[indice].getPeso();
            }
        }
        return resultado;
    }
    //devolver mascota a partir de su codigo
    public Mascota devolverMascota(int codigo) {
        Mascota resultado = null;
         for(int i=0; i < mascotas.length; i++){
            if( codigo==this.mascotas[i].getCodigo() ) {
                resultado = this.mascotas[i];
                break;
            }
        }
        return resultado;
    }
    
    
    //para el combobox de mascotas
    public boolean vacio(){
        if(this.indice==0){
            return true;
        }else{
            return false;
        }
    }
    
    public Mascota[] getDatosCombo() {
        Mascota resultado[] = null;
        if (!vacio()) {
            resultado = new Mascota[this.indice];
            for (int i = 0; i < this.indice; i++) {
                resultado[i] = this.mascotas[i];
            }
        }
        return resultado;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
    
    @Override
    public String toString() {
        String resultado = "";
        for(int i=0 ; i < this.indice; i++){
            resultado = resultado + this.mascotas[i]+"\n";
        }
        return resultado;
    }
}
