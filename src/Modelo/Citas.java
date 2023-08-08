
package Modelo;

public class Citas {

    private static int contador=0;
    private int codigo;
    private String Fecha;
    private String Diagnostico;
    private String Tratamiento;
    

    private Empleado empleado;
    private Mascota mascota;
    
    private float registroTalla;
    private float registroPeso;

    public Citas (String Fecha, String Diagnostico, String Tratamiento, Empleado empleado, Mascota mascota, float talla, float peso) {
        this.codigo=contador;
        contador++;
        
        this.Fecha = Fecha;
        this.Diagnostico = Diagnostico;
        this.Tratamiento = Tratamiento;
        this.empleado = empleado;
        this.mascota = mascota;
        
        modificarPesoMascota(peso);
        modificarTallaMascota(talla);
        this.registroTalla=talla;
        this.registroPeso=peso;
        
        
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }



    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }



    public void identificar(String codigoP) {

    }

    public void modificarTallaMascota(float talla) {
        if(talla>0){
            this.mascota.setTalla(talla);
        }else{
            //System.out.println("Talla invalida");
        }
        
    }
    
    public void modificarPesoMascota(float peso){
        if(peso>0){
            this.mascota.setPeso(peso);
        }else{
            //System.out.println("Peso invalido");
        }
        
    }

    public String getDiagnostico() {
        return Diagnostico;
    }

    public void setDiagnostico(String Diagnostico) {
        this.Diagnostico = Diagnostico;
    }

    public String getTratamiento() {
        return Tratamiento;
    }

    public void setTratamiento(String Tratamiento) {
        this.Tratamiento = Tratamiento;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public float getRegistroTalla() {
        return registroTalla;
    }

    public float getRegistroPeso() {
        return registroPeso;
    }

    @Override
    public String toString() {
        
        return  "Cod=" + codigo + ", Fecha=" + Fecha +", Peso=" + registroPeso + ", Talla=" + registroTalla + 
                ", Empleado=" + empleado.getNombre()+" "+empleado.getApellido()+
                ", \nDiagnostico=" + 
                Diagnostico + ", \nTratamiento=" + Tratamiento   
                ;
    }
    
}
