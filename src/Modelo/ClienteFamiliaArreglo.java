
package Modelo;

import Interfaces.InterfazArreglo;


public class ClienteFamiliaArreglo implements InterfazArreglo{
    private ClienteFamilia[] ClienteFamilia;
    private int indice;
    private final String[] cabecera =  {"CODIGO","APELLIDO FAMILIA","NRO CUENTA BANCARIA",
                            "DIRECCION", "TELÉFONO"};
    
    public ClienteFamiliaArreglo(int tamano) {
        this.ClienteFamilia = new ClienteFamilia[tamano];
        this.indice = 0;
    }
    
    public void agregar(ClienteFamilia familias){
        this.ClienteFamilia[this.indice] = familias;
        this.indice++; 
    }
    
    public void eliminar(int x) {
        int numero=0;
        if (x >= 0) {
            for (int i = x; i < ClienteFamilia.length - 1; i++) {
                ClienteFamilia[i] = ClienteFamilia[i + 1];
                ClienteFamilia[i + 1] = null;
                numero=i;
            }
            
        }else{
            //System.out.println("Posicion invalida");
        }
        indice--;

    }
    
    public ClienteFamilia[] devolverIntegrantes(){
        return this.ClienteFamilia;
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
        Object resultado[][] = new Object[this.indice][8];
        if(!isVacio()){
            for(int indice = 0; indice< this.indice;indice++){
                resultado[indice][0] = this.ClienteFamilia[indice].getCodigo();
                resultado[indice][1] = this.ClienteFamilia[indice].getApellido_Familia();
                resultado[indice][2] = this.ClienteFamilia[indice].getNum_Ctab();
                resultado[indice][3] = this.ClienteFamilia[indice].getDireccion();
                resultado[indice][4] = this.ClienteFamilia[indice].getTelefono();
                
            }
        }
        return resultado;
    }
    //devolver familia a partir de su codigo
    public ClienteFamilia devolverFamilia(int codigo) {
        ClienteFamilia resultado = null;
         for(int i=0; i < ClienteFamilia.length; i++){
            if( codigo==this.ClienteFamilia[i].getCodigo() ) {
                resultado = this.ClienteFamilia[i];
                break;
            }
        }
        return resultado;
    }
    
    
    @Override
    public String toString() {
        String resultado = "";
        for(int i=0 ; i < this.indice; i++){
            resultado = resultado + this.ClienteFamilia[i]+"\n";
        }
        return resultado;
    }
}
