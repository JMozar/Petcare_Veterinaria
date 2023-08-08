
package Modelo;

import static Modelo.ConexionBaseDatos.conectar;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


public class ConsultasFamilias extends ConexionBaseDatos{
    
    //Metodo que retorna una tabla con los datos de la Base de datos
    public static DefaultTableModel listar(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("id");
        modelo.addColumn("Apellido");
        modelo.addColumn("Num_Cuenta");
        modelo.addColumn("Direccion");
        modelo.addColumn("Celular");
        
        Connection con=conectar();
        PreparedStatement ps=null;
        String sql="SELECT * FROM familias";
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
    public boolean registrarFamilia(ClienteFamilia familia){
        PreparedStatement ps=null;
        Connection con=conectar();
        String sql = ("INSERT INTO familias(apellido,num_Cuenta,direccion,celular) VALUES (?,?,?,?)");//sentencia_guardar
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,familia.getApellido_Familia());
            ps.setString(2,familia.getNum_Ctab());
            ps.setString(3,familia.getDireccion());
            ps.setString(4,familia.getTelefono()); 

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
    public boolean eliminarFamilia(int codFamilia){
        PreparedStatement ps=null;
        Connection con=conectar();
        String sql = ("DELETE FROM familias WHERE idFamilia=? ");//sentencia_eliminar
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,codFamilia); //
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
    
    

    
}
