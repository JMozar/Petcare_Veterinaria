
package Modelo;


public class ClientePersona extends Persona{
    private static int contador=0;
    private int codigo;
    private ClienteFamilia familia;

    public ClientePersona(String nombre, String apellido, String DNI, String telefono) {
        super(nombre, apellido, DNI, telefono);
        this.codigo=contador;
        
        contador++;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    

    public ClienteFamilia getFamilia() {
        return familia;
    }

    public void setFamilia(ClienteFamilia familia) {
        this.familia = familia;
    }

    
    
    
    @Override
    public String toString() {
        String resultado="";
        if(this!=null){
            resultado="ClientePersona{" + "nombre=" + nombre + ", DNI=" + DNI + '}';
        }
        return resultado;
    }

    

}