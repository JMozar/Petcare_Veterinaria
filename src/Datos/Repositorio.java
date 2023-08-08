
package Datos;

import Modelo.ClienteFamiliaArreglo;
import Modelo.ClientePersonaArreglo;
import Modelo.EmpleadoArreglo;
import Modelo.EspecieArreglo;
import Modelo.InyeccionVacunaArreglo;
import Modelo.MascotaArreglo;
import Modelo.CitasArreglo;
import Modelo.Usuario;
import Modelo.UsuarioArreglo;
import java.io.Serializable;

public class Repositorio implements Serializable{
    
    //Copias locales de la base de datos
    public static UsuarioArreglo usuarios = new UsuarioArreglo(100);
    public static Usuario usuario_validado;
    
    public static ClienteFamiliaArreglo familias = new ClienteFamiliaArreglo(100);
    public static ClientePersonaArreglo personas = new ClientePersonaArreglo(100);
    
    public static InyeccionVacunaArreglo inyecciones = new InyeccionVacunaArreglo(100);
    public static EspecieArreglo especies = new EspecieArreglo(2);
    public static CitasArreglo citas= new CitasArreglo(100);
    
    public static MascotaArreglo mascotas = new MascotaArreglo(100);
    public static EmpleadoArreglo empleados = new EmpleadoArreglo(100);
}
