
package Vista;


import Modelo.*;
import Datos.*;
import Controlador.*;
import Librerias.Serializar;



public class Main {
    public static void main(String[] args) {


        Especie especie1 = new Especie("Perro");
        Especie especie2 = new Especie("Gato"); 
        Repositorio.especies.agregar(new Especie("Perro"));
        Repositorio.especies.agregar(new Especie("Gato"));

        ConsultasMascotas carga = new ConsultasMascotas();
        carga.llenar();
        ConsultasEmpleado cargaEmpleado=new ConsultasEmpleado();
        cargaEmpleado.llenar();
        ConsultasUsuarios.llenar();
        
        ControladorSistema controlador = new ControladorSistema( Repositorio.usuarios, new frmSistema() );
        controlador.iniciar();

 
    }
}
    