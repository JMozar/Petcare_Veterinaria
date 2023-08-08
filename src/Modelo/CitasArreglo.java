
package Modelo;

import Interfaces.InterfazArreglo;


public class CitasArreglo implements InterfazArreglo{
    private Citas[] citas;
    private int indice;
    private final String[] cabecera =  {"CODIGO",
                            "FECHA","DIAG.","TRAT","MASCOTA", "EMPLEADO","TALLA","PESO"};
    
    public CitasArreglo (int tamano) {
        this.citas = new Citas[tamano];
        this.indice = 0;
    }
    
    public void agregar(Citas citas){
        this.citas[this.indice] = citas;
        this.indice++; 
    }
    
    public void eliminar(int x) {
        int numero=0;
        if (x >= 0) {
            for (int i = x; i < citas.length - 1; i++) {
                citas[i] = citas[i + 1];
                citas[i + 1] = null;
                numero=i;
            }
            
        }else{
            System.out.println("Posicion invalida");
        }
        indice--;

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
                resultado[indice][0] = this.citas[indice].getCodigo();
                resultado[indice][1] = this.citas[indice].getFecha();
                resultado[indice][2] = this.citas[indice].getDiagnostico();
                resultado[indice][3] = this.citas[indice].getTratamiento();
                resultado[indice][4] = this.citas[indice].getMascota().getNombre();
                resultado[indice][5] = this.citas[indice].getEmpleado().getNombre();
                resultado[indice][6] = this.citas[indice].getRegistroTalla();
                resultado[indice][7] = this.citas[indice].getRegistroPeso();
                
            }
        }
        return resultado;
    }
    
    @Override
    public String toString() {
        String resultado = "";
        for(int i=0 ; i < this.indice; i++){
            resultado = resultado + this.citas[i]+"\n\n";
        }
        return resultado;
    }
    
    
}