package Controlador;

import Modelo.*;
import Vista.*;
import Datos.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ControladorRegistrarEmpleados {
    private frmEmpleado vista;
    private EmpleadoArreglo modelo;
    
    private ConsultasEmpleado modeloC = new ConsultasEmpleado();
    
    DefaultTableModel modelotabla = new DefaultTableModel();
    

    public ControladorRegistrarEmpleados(frmEmpleado vista, EmpleadoArreglo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.btnRegistrar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String numTelefono;
                float sueldo;
                if (vista.txtEspecialidad.getText().isEmpty() || vista.txtSueldo.getText().isEmpty()
                        || vista.txtNombreEmpleado.getText().isEmpty() || vista.txtApellidoEmpleado.getText().isEmpty()
                        || vista.txtDNIEmpleado.getText().isEmpty() || vista.lblTelefonoEmpleado.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Complete todos los campos");
                } else {
                    try {
                        numTelefono = vista.lblTelefonoEmpleado.getText();
                        try {
                            sueldo = Float.parseFloat(vista.txtSueldo.getText());
                            Empleado em = new Empleado(vista.txtEspecialidad.getText(),
                                    sueldo, vista.txtNombreEmpleado.getText(),
                                    vista.txtApellidoEmpleado.getText(), vista.txtDNIEmpleado.getText(),
                                    numTelefono);
                            
                            modeloC.registrarEmpleado(em);
                            Repositorio.empleados.agregar(em);
                            JOptionPane.showMessageDialog(null, "Empleado Agregado");
                            actualizarTabla();
                            limpiarCampos();
                        } catch (NumberFormatException ex1) {
                            JOptionPane.showMessageDialog(null, "Sueldo invalido");
                        }
                    } catch (NumberFormatException ex2) {
                        JOptionPane.showMessageDialog(null, "Num. celular invalido");
                    }
                }

            }
        }      
        );
        this.vista.btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ControladorPrincipal controlador = new ControladorPrincipal(Repositorio.usuario_validado, new frmPrincipal());
                controlador.iniciar();
                vista.dispose();
                }
            }
        );
        this.vista.btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = vista.tblEmpleadoRepo.getSelectedRow();//seleccion de fila de la tabla
                
                //eliminar
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una empleado");
                } else {
                    int valor = Integer.parseInt(vista.tblEmpleadoRepo.getValueAt(fila, 0).toString());//codigo de empleado
                    
                    modeloC.eliminarEmpleado(valor);
                    Repositorio.empleados.eliminar(valor);//metodo para eliminar empleados
                    actualizarTabla();//actualizamos
                    JOptionPane.showMessageDialog(null, "Empleado Eliminado");
                    
                    
                }

            }
        }
        );
        
    }
    
    public void actualizarTabla() {
          this.vista.tblEmpleadoRepo.setModel(ConsultasEmpleado.listar());
    }
    
    
    
    public void limpiarCampos(){
        this.vista.txtNombreEmpleado.setText("");
        this.vista.txtApellidoEmpleado.setText("");
        this.vista.txtDNIEmpleado.setText("");
        this.vista.lblTelefonoEmpleado.setText("");
        this.vista.txtEspecialidad.setText("");
        this.vista.txtSueldo.setText("");
    }
    public void iniciar() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
        
        actualizarTabla();
    }
}
