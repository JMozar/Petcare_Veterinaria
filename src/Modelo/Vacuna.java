
package Modelo;

public class Vacuna {
    private String medicamentos;
    private int numVacuna;
    private String dosis;
    private String gramos;
    private String fechaVacunacion;
    private boolean recurrente;
    private int vecesAplicadas;
    private boolean estado;

    public Vacuna (int numVacuna, String medicamentos, String dosis, String gramos, String fechaVacunacion) {
        this.numVacuna=numVacuna;
        this.medicamentos = medicamentos;
        this.dosis = dosis;
        this.gramos = gramos;
        this.fechaVacunacion = fechaVacunacion;
        this.estado=false;//siempre estara sin aplicar a menos que se vacune
    }
    
    //vacunas anuales
    public Vacuna (int numVacuna,String medicamentos, String gramos, String fechaVacunacion, int vecesAplicadas) {
        this.numVacuna=numVacuna;
        this.medicamentos = medicamentos;
        this.gramos = gramos;
        this.fechaVacunacion = fechaVacunacion;
        this.vecesAplicadas = vecesAplicadas;
        this.recurrente=true;
    }
    

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getGramos() {
        return gramos;
    }

    public void setGramos(String gramos) {
        this.gramos = gramos;
    }

    public String getFechaVacunacion() {
        return fechaVacunacion;
    }

    public void setFechaVacunacion(String fechaVacunacion) {
        this.fechaVacunacion = fechaVacunacion;
    }
    
    //fue aplicada o aun no?(para vacunas unicas)
    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    //es recurrente o no?

    public boolean isRecurrente() {
        return recurrente;
    }

    public void setRecurrente(boolean recurrente) {
        this.recurrente = recurrente;
    }
    
    
    public String estadoVacuna(){
        String estado;
        if(this.estado==false){
            estado="NO aplicado";
        }else{
            estado="Aplicado";
        }
        return estado;
    }
    public void aumentarAplicacionesAnuales(){
        vecesAplicadas++;
    }
    

    @Override
    public String toString() {
        String datosVacuna="";
        if(recurrente==false){
            datosVacuna= numVacuna + ".-Medicamentos=" + medicamentos + ", Dosis=" + dosis +
                ", Gramos=" + gramos + ", FechaVacunacion=" + fechaVacunacion +
                ", Estado=" + estadoVacuna()+"";
        }else{
            datosVacuna= numVacuna + ".-Medicamentos=" + medicamentos +
                ", Gramos=" + gramos + ", FechaVacunacion=" + fechaVacunacion +
                ", Veces aplicada="+vecesAplicadas;
        }
        return datosVacuna;
    }
    
    
}