
package Modelo;

import Datos.Repositorio;
import static Modelo.ConexionBaseDatos.conectar;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


public class ConsultasMascotas extends ConexionBaseDatos{
    
    //Metodo que retorna una tabla con los datos de la Base de datos
    public static DefaultTableModel listar(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Especie");
        modelo.addColumn("Raza");
        modelo.addColumn("Color");
        modelo.addColumn("Fecha_Nac");
        modelo.addColumn("Talla");
        modelo.addColumn("Peso");
        Connection con=conectar();
        PreparedStatement ps=null;
        String sql="SELECT * FROM mascotas";
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
                modelo.addRow(filas);
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
    public boolean registrarMascotas(Mascota mascota){
        PreparedStatement ps=null;
        Connection con=conectar();
        String sql = ("INSERT INTO mascotas(nombre,especie,raza,color,fecha_Nacimiento,talla,peso) VALUES (?,?,?,?,?,?,?)");//sentencia_guardar
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,mascota.getNombre());
            ps.setString(2,mascota.getEspecie());
            ps.setString(3,mascota.getRaza());
            ps.setString(4,mascota.getColorPelo());
            ps.setString(5,mascota.getFechaNacimiento());
            ps.setFloat(6,mascota.getTalla());
            ps.setFloat(7,mascota.getPeso());

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
    public boolean eliminarMascota(int codMascota){
        PreparedStatement ps=null;
        Connection con=conectar();
        String sql = ("DELETE FROM mascotas WHERE idMascota=? ");//sentencia_eliminar
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,codMascota); //
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
    public int verificaFamilia(int idMas){//string usuario
        int busqueda_usuario=-100;
        PreparedStatement ps=null;
        ResultSet rs;
        Connection conexion = null;
        try {
            conexion = ConexionBaseDatos.conectar();
            String sentencia_buscar = ("SELECT idFamilia FROM mascotas WHERE idMascota ='"+idMas+"'");
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
    
    //Metodo para asignar una mascota a una familia
    public void asignaFamilia(int idMas, int codFamilia){//persona/ id de la familia a colocar
        Connection conexion=conectar();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql = "UPDATE mascotas set idFamilia=? WHERE idMascota=?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, codFamilia);
            
            ps.setInt(2, idMas);
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
   
    //Metodo que retorna solo las mascotas de la familia
    public static DefaultTableModel listarMascotas(int codFamilia){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Especie");
        modelo.addColumn("Raza");
        modelo.addColumn("Color");
        modelo.addColumn("Fecha_Nac");
        modelo.addColumn("Talla");
        modelo.addColumn("Peso");
        
        Connection con=conectar();
        PreparedStatement ps=null;
        String sql="SELECT * FROM mascotas WHERE idFamilia = '" + codFamilia + "'";
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
    
    //Metodo que crea una copia local de las mascotas de la base de datos
    public static void llenar(){
        Connection con=conectar();
        PreparedStatement ps=null;
        String sql="SELECT * FROM mascotas";
        ResultSet rs;
        
        try {
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            while (rs.next()) {
                Mascota m = new Mascota("Perro");
                m.setCodigo(rs.getInt("idMascota"));
                m.setNombre(rs.getString("nombre"));
                m.setEspecie(rs.getString("especie"));
                m.setRaza(rs.getString("raza"));
                m.setColorPelo(rs.getString("color"));
                m.setFechaNacimiento(rs.getString("fecha_Nacimiento"));
                m.setTalla(rs.getFloat("talla"));
                m.setPeso(rs.getFloat("peso"));
                Repositorio.mascotas.setNum(rs.getInt("idMascota"));
                Repositorio.mascotas.agregar(m);

            }

        } catch (SQLException e) {
            System.out.println("Error de carga: "+e.getMessage());
        }finally{
            ps=null;
            rs=null;
            
        }
        
    }
    
    
}
