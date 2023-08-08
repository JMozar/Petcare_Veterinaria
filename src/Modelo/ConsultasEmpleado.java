
package Modelo;

import Datos.Repositorio;
import static Modelo.ConexionBaseDatos.conectar;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


public class ConsultasEmpleado extends ConexionBaseDatos{
    
    //Metodo que retorna una tabla con los datos de la Base de datos
    public static DefaultTableModel listar(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Celular");
        modelo.addColumn("Especialidad");
        modelo.addColumn("Sueldo");
        
        Connection con=conectar();
        PreparedStatement ps=null;
        String sql="SELECT * FROM empleados";
        ResultSet rs;
        
        try {
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            
            while (rs.next()) {//llenar cada fila
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);//llenamos filas
            }
            
        } catch (SQLException e) {
            System.out.println("Error de listado: "+e.getMessage());
        }finally{
            ps=null;
            rs=null;
            
        }
        return modelo;
    }
    
    //Metodo que sirve para registrar en la base de datos
    public boolean registrarEmpleado(Empleado empleado){
        PreparedStatement ps=null;
        Connection con=conectar();
        String sql = ("INSERT INTO empleados(nombre,apellido,DNI,celular,especialidad,sueldo) VALUES (?,?,?,?,?,?)");//sentencia_guardar
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,empleado.getNombre());
            ps.setString(2,empleado.getApellido());
            ps.setString(3,empleado.getDNI());
            ps.setString(4,empleado.getTelefono()); 
            ps.setString(5,empleado.getEspecialidad());
            ps.setFloat(6,empleado.getSueldo()); 
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally{
            try{
                con.close();
            }catch(SQLException e){
                System.err.println(e);
            }
        }
    }
    
    //Metodo para eliminar de la base de datos(a partir del codigo)
    public boolean eliminarEmpleado(int codEmpleado){
        PreparedStatement ps=null;
        Connection con=conectar();
        String sql = ("DELETE FROM empleados WHERE idEmpleado=? ");//sentencia_eliminar
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,codEmpleado); //
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally{
            try{
                con.close();
            }catch(SQLException e){
                System.err.println(e);
            }
        }
    }
    //Metodo que sorve para hacer una copia local de los datos que estan en la base de datos
    public static void llenar(){

        Connection con=conectar();
        PreparedStatement ps=null;
        String sql="SELECT * FROM empleados";
        ResultSet rs;
        
        try {
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            
            while (rs.next()) {

                Empleado e = new Empleado();
                e.setCodigo(rs.getInt("idEmpleado"));
                e.setNombre(rs.getString("nombre"));
                e.setApellido(rs.getString("apellido"));
                e.setDNI(rs.getString("DNI"));
                e.setTelefono(rs.getString("celular"));
                e.setEspecialidad(rs.getString("especialidad"));
                e.setSueldo(rs.getFloat("sueldo"));
                Repositorio.empleados.agregar(e);
            }
            
        } catch (SQLException e) {
            System.out.println("Error de carga: "+e.getMessage());
        }finally{
            ps=null;
            rs=null;
            
        }
    }

    
}
