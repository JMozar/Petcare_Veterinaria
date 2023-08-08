
package Modelo;

public class InyeccionVacuna {
    private static int contador;
    private int codigo;
    private int nroVacuna;
    private String fecha;    
    private Mascota mascota;
    private Empleado empleado;



    public InyeccionVacuna(int nroVacuna, String fecha, Mascota mascota, Empleado empleado) {
        this.codigo = contador;
        contador++;
        this.nroVacuna = nroVacuna;
        this.fecha = fecha;
        this.mascota = mascota;
        this.empleado = empleado;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNroVacuna() {
        return nroVacuna;
    }

    public void setNroVacuna(int nroVacuna) {
        this.nroVacuna = nroVacuna;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    

    @Override
    public String toString() {
        return "Cod=" + codigo + ", nroVacuna=" + nroVacuna  + ", fecha=" + fecha + ", Empleado=" + empleado.getNombre()+" "+empleado.getApellido();
    }
}
