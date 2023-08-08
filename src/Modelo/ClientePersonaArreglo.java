package Modelo;

import Interfaces.InterfazArreglo;

public class ClientePersonaArreglo implements InterfazArreglo{

    private ClientePersona[] ClientePersonas;
    private int indice;
    private final String[] cabecera =  {"CODIGO","NOMBRE",
                            "APELLIDO","DNI", "TELEFONO"};

    public ClientePersonaArreglo(int tamano) {
        this.ClientePersonas = new ClientePersona[tamano];
        this.indice = 0;
    }

    public void agregar(ClientePersona persona) {
        this.ClientePersonas[this.indice] = persona;
        this.indice++;
    }
    public ClientePersona[] devolverArreglo(){
        return this.ClientePersonas;
    }
    

    public void eliminar(int x) {
        int numero=0;
        if (x >= 0) {
            for (int i = x; i < ClientePersonas.length - 1; i++) {
                ClientePersonas[i] = ClientePersonas[i + 1];
                ClientePersonas[i + 1] = null;
                numero=i;
            }
        }else{
            //System.out.println("Posicion invalida");
        }
        indice--;

    }
    
    public ClientePersona devolverPersona(int codigo) {
        ClientePersona resultado = null;
         for(int i=0; i < ClientePersonas.length; i++){
            if( codigo==this.ClientePersonas[i].getCodigo() ) {
                resultado = this.ClientePersonas[i];
                break;
            }
        }
        return resultado;
    }
    
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
        Object resultado[][] = new Object[this.indice][7];
        if(!isVacio()){
            for(int indice = 0; indice< this.indice;indice++){
                resultado[indice][0] = this.ClientePersonas[indice].getCodigo();
                resultado[indice][1] = this.ClientePersonas[indice].getNombre();
                resultado[indice][2] = this.ClientePersonas[indice].getApellido();
                resultado[indice][3] = this.ClientePersonas[indice].getDNI();
                resultado[indice][4] = this.ClientePersonas[indice].getTelefono();    
            }
        }
        return resultado;
    }

    @Override
    public String toString() {
        String resultado = "";
        for (int i = 0; i < this.indice; i++) {
            resultado = resultado + this.ClientePersonas[i] + "\n";
        }

        return resultado;
    }
}