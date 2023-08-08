
package Modelo;


public class HistorialMedico {

    private Mascota mascota;
    
    private CitasArreglo citas;
    private InyeccionVacunaArreglo inyecciones;
    

    public HistorialMedico (Mascota mascota) {
        this.mascota = mascota;
        this.citas = new CitasArreglo(5);
        this.inyecciones = new InyeccionVacunaArreglo(5);
    }
    
    public void añadirCitas(Citas cita){
        this.citas.agregar(cita);
    }
    public void añadirInyeccines(InyeccionVacuna inyeccion){
        this.inyecciones.agregar(inyeccion);
    }

    public String mostrarHistorial(){
        String texto="";
        
        return  texto+
                "--------------------------------------------------------------------------"+"\n"+
                mostrarCitasRegistradas()+
                "--------------------------------------------------------------------------"+"\n"+
                mostrarInyeccionesRegistradas()+
                "--------------------------------------------------------------------------"+"\n";
    }
    
    public String mostrarCitasRegistradas(){
        return "Registro de citas :"+"\n"+
        citas.toString();
    }
    
    public String mostrarInyeccionesRegistradas(){
        return "Registro de inyecciones :"+"\n"+
        inyecciones.toString();
    }  
}