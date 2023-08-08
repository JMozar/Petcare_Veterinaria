
package Modelo;

import java.io.Serializable;

public class Usuario implements Serializable{
    
    //Cambien las variables     
    private String Usuario;
    private String Clave;
    private boolean Estado;
    private String fecha_Registro;

    public Usuario() {
    }
    
    
    public Usuario(String Usuario, String Clave) {
        this.Usuario = Usuario;
        this.Clave = Clave;
        this.Estado = false;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }
    

    public boolean isActivo() {
        return Estado;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String Clave) {
        this.Clave = Clave;
    }

    public String getFecha_Registro() {
        return fecha_Registro;
    }

    public void setFecha_Registro(String fecha_Registro) {
        this.fecha_Registro = fecha_Registro;
    }
    
    
    public boolean ingresar(String user, String password){
        boolean result = false;
        if( this.Usuario.equalsIgnoreCase(user) && 
                this.Clave.equals(password)&&
                !isActivo() ){
            this.Estado = true;
            result = true;
        }            
        return result; 
    }
    
    public boolean salir(){
        boolean result = false;
        if(this.Estado){
            this.Estado = false;
            result = true;
        }
        return result;
    }
    
    
}
