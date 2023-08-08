
package Modelo;

import static Modelo.ConexionBaseDatos.conectar;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


public class ConsultasPersonas extends ConexionBaseDatos{
    
    //Metodo que retorna una tabla con los datos de la Base de datos
    public static DefaultTableModel listar(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("DNI");
        modelo.addColumn("Celular");
        
        Connection con=conectar();
        PreparedStatement ps=null;
        String sql="SELECT * FROM personas";
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
    public boolean registrarPersonas(ClientePersona persona){
        PreparedStatement ps=null;
        Connection con=conectar();
        String sql = ("INSERT INTO personas(nombre,apellido,DNI,celular) VALUES (?,?,?,?)");//sentencia_guardar
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,persona.getNombre());
            ps.setString(2,persona.getApellido());
            ps.setString(3,persona.getDNI());
            ps.setString(4,persona.getTelefono()); 

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
    public boolean eliminarPersona(int codPersona){
        PreparedStatement ps=null;
        Connection con=conectar();
        String sql = ("DELETE FROM personas WHERE idPersona=? ");//sentencia_eliminar
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,codPersona); //
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
    //Retorna el id de la familia a la que pertenece
    public int verificaFamilia(int idPer){//string usuario
        int busqueda_usuario=-100;
        PreparedStatement ps=null;
        ResultSet rs;
        Connection conexion = null;
        try {
            conexion = ConexionBaseDatos.conectar();
            String sentencia_buscar = ("SELECT idFamilia FROM personas WHERE idPersona ='"+idPer+"'");
            ps = conexion.prepareStatement(sentencia_buscar);
            rs = ps.executeQuery();
            //validadmos y traemos el resultado
            if(rs.next()){
                busqueda_usuario = rs.getInt("idFamilia");

            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return busqueda_usuario;
    }
    //Metodo para asignar un integrante a una familia
    public void asignaFamilia(int idPer, int codFamilia){//persona/ id de la familia a colocar
        Connection conexion=conectar();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql = "UPDATE personas set idFamilia=? WHERE idPersona=?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, codFamilia);
            
            ps.setInt(2, idPer);
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static DefaultTableModel listarIntegrantes(int codFamilia){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("DNI");
        modelo.addColumn("Celular");
        
        Connection con=conectar();
        PreparedStatement ps=null;
        String sql="SELECT * FROM personas WHERE idFamilia = '" + codFamilia + "'";
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
}
