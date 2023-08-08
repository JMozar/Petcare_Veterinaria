
package Modelo;

import Interfaces.InterfazArreglo;


public class InyeccionVacunaArreglo implements InterfazArreglo{
    private InyeccionVacuna[] inyecciones;
    private int indice;
    private final String[] cabecera =  {"CODIGO",
                            "FECHA","N. VACUN.","MASCOTA", "EMPLEADO"};
    
    public InyeccionVacunaArreglo(int tamano) {
        this.inyecciones = new InyeccionVacuna[tamano];
        this.indice = 0;
    }
    
    public void agregar(InyeccionVacuna inyecciones){
        this.inyecciones[this.indice] = inyecciones;
        this.indice++;
    }
    
    public void eliminar(int x) {
        int numero=0;
        if (x >= 0) {
            for (int i = x; i < inyecciones.length - 1; i++) {
                inyecciones[i] = inyecciones[i + 1];
                inyecciones[i + 1] = null;
                numero=i;
            }
        }else{
            //System.out.println("Posicion invalida");
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
        Object resultado[][] = new Object[this.indice][5];
        if(!isVacio()){
            for(int indice = 0; indice< this.indice;indice++){
                resultado[indice][0] = this.inyecciones[indice].getCodigo();
                resultado[indice][1] = this.inyecciones[indice].getFecha();
                resultado[indice][2] = this.inyecciones[indice].getNroVacuna();
                resultado[indice][3] = this.inyecciones[indice].getMascota().getNombre();
                resultado[indice][4] = this.inyecciones[indice].getEmpleado().getNombre();
                
            }
        }
        return resultado;
    }
    
    @Override
    public String toString() {
        String resultado = "";
        for(int i=0 ; i < this.indice; i++){
            resultado = resultado + this.inyecciones[i]+"\n";
        }
        return resultado;
    }
    
    
}