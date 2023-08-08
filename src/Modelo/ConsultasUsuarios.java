
package Modelo;

import Datos.Repositorio;
import static Modelo.ConexionBaseDatos.conectar;
import java.sql.*;

public class ConsultasUsuarios extends ConexionBaseDatos{
    
    
    //Metodo que sorve para hacer una copia local de los datos que estan en la base de datos
    public static void llenar(){
        Connection con=conectar();
        PreparedStatement ps=null;
        String sql="SELECT * FROM usuarios";
        ResultSet rs;
        
        try {
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            
            while (rs.next()) {

                Usuario u = new Usuario();
                
                u.setUsuario(rs.getString("usuario"));
                u.setClave(rs.getString("contraseña"));
                u.setFecha_Registro(rs.getString("fecha_Registro"));

                Repositorio.usuarios.agregar(u);
            }
            
        } catch (SQLException e) {
            System.out.println("Error de carga: "+e.getMessage());
        }finally{
            ps=null;
            rs=null;
            
        }
    }

    
}
