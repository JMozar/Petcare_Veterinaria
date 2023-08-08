
package Modelo;

import java.io.Serializable;


public class EspecieArreglo implements Serializable{
    private Especie[] especies;
    private int indice;

    public EspecieArreglo(int tamano) {
        this.especies = new Especie[tamano];
        this.indice = 0;
    }

    public void agregar(Especie especie) {
        this.especies[this.indice] = especie;
        this.indice++;
    }
    public Especie[] devolverArreglo(){
        return this.especies;
    }
    
    public Especie[] getDatosCombo() {
        Especie resultado[] = null;
        if (!vacio()) {
            resultado = new Especie[this.indice];
            for (int i = 0; i < this.indice; i++) {
                resultado[i] = this.especies[i];
            }
        }
        return resultado;
    }
    
    public boolean vacio(){
        if(this.indice==0){
            return true;
        }else{
            return false;
        }
    }
}
