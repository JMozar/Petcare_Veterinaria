
package Modelo;

import java.io.Serializable;

public class UsuarioArreglo implements Serializable{
    private Usuario[] usuarios;
    private int indice, tamano;
    private final String[] cabecera =  {"USUARIO","CONTRASEÑA","ESTADO" };

    public UsuarioArreglo(int tamano) {
        this.usuarios = new Usuario[tamano];
        this.tamano=tamano;
        this.indice = 0;
    }
    
    public String[] getCabecera() {
        return cabecera;
    }
        
    public Object[][] getDatos(){
        Object resultado[][] = new Object[this.indice][3];
        if(!vacio()){
            for(int indice = 0; indice< this.indice;indice++){
                resultado[indice][0] = this.usuarios[indice].getUsuario();
                resultado[indice][1] = this.usuarios[indice].getClave();
                resultado[indice][2] = "ACTIVO";
            }
        }
        return resultado;
    }
    
    public boolean agregar(Usuario usuario){
        boolean result = false;
        if(!lleno()){
            this.usuarios[indice] = usuario;
            indice++;
            result = true;
        }else{
            crecer();
        }
        
        return result;
    }
    
    public void crecer(){
        int tamanho = this.tamano*3;
        Usuario[] nuevoArreglo = new Usuario[tamanho];
        for(int i=0; i < this.indice; i++){
            nuevoArreglo[i] = this.usuarios[i];
        }
        this.usuarios = nuevoArreglo;
    }

    private boolean lleno() {
        if(this.indice == this.usuarios.length) 
            return true;
        else 
            return false;
    }
    
    private boolean vacio() {
        if(this.indice == 0) 
            return true;
        else 
            return false;
    }
    
    
    public Usuario ingresar(String usuario, String clave){
        Usuario result = null;
        for( Usuario u : this.usuarios ){
            if(u != null && u.ingresar(usuario, clave)  ){
                result = u;
                break;
            }
        }
        return result;
    }
    
    public Usuario[] getDatosCombo(){
        Usuario resultado[]= null;
        if(!vacio()){
            resultado = new Usuario[this.indice];
            for(int i = 0; i<this.indice; i++){
                resultado[i]= this.usuarios[i];
            }
        }
        return resultado;
    }
    
    
}
