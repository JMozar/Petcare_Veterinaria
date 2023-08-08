package Controlador;

import Modelo.*;
import Vista.*;
import Datos.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorCita {

    private frmCita vista;
    private CitasArreglo modelo;

    public ControladorCita(frmCita vista, CitasArreglo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.botonRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (vista.txtFechacita.getText().isEmpty() || vista.txtDiagnostico.getText().isEmpty()
                        || vista.txtTratamiento.getText().isEmpty() || vista.txtTratamiento.getText().isEmpty()
                        || vista.txtTalla.getText().isEmpty() || vista.txtPeso.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Complete todos los campos");
                } else {
                    try {

                        Float Talla = Float.parseFloat(vista.txtTalla.getText());
                        Float Peso = Float.parseFloat(vista.txtPeso.getText());

                        if (Talla > 0 && Peso > 0) {
                            Citas c = new Citas(vista.txtFechacita.getText(), vista.txtDiagnostico.getText(), vista.txtDiagnostico.getText(),
                                    (Empleado) vista.comboEmpleado.getSelectedItem(), (Mascota) vista.comboMascotas.getSelectedItem(),
                                    Talla, Peso);
                            Repositorio.citas.agregar(c);
                            //Registramos la cita en el historial de la mascota
                            Mascota m=(Mascota) vista.comboMascotas.getSelectedItem();
                            m.registrarCitaHistorial(c);
                            
                            JOptionPane.showMessageDialog(null, "Cita AGREGADA");
                            actualizarTabla();
                            limpiarCampos();
                        } else {
                            JOptionPane.showMessageDialog(null, "Digite numeros validos");
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Solo se pueden digitar numeros en talla y peso");

                    }

                }
            }
        }
        );
        this.vista.botonRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControladorPrincipal controlador = new ControladorPrincipal(Repositorio.usuario_validado, new frmPrincipal());               
                controlador.iniciar();
                vista.dispose();
            }
        }
        );
        this.vista.botonEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = vista.tblCitasRepo.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar alguna Cita");
                } else {
                    int valor = Integer.parseInt(vista.tblCitasRepo.getValueAt(fila, 0).toString());
                    Repositorio.citas.eliminar(valor);
                    actualizarTabla();
                    JOptionPane.showMessageDialog(null, "Cita eliminada");
                }
            }
        }
        );
    }

    public void actualizarTabla() {
        DefaultTableModel modelotabla = new DefaultTableModel(this.modelo.getDatos(), this.modelo.getCabecera());
        this.vista.tblCitasRepo.setModel(modelotabla);
    }
    public void limpiarCampos(){
        this.vista.txtFechacita.setText("");
        this.vista.txtTalla.setText("");
        this.vista.txtPeso.setText("");
        this.vista.txtDiagnostico.setText("");       
        this.vista.txtTratamiento.setText("");
        
    }

    public void iniciar() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);

        DefaultComboBoxModel modeloCboMascotas = new DefaultComboBoxModel();
        //traer todos los usuarios del repo
        for (Mascota m : Repositorio.mascotas.getDatosCombo()) {
            modeloCboMascotas.addElement(m);
        }
        //le doy al combo del form el modelo
        this.vista.comboMascotas.setModel(modeloCboMascotas);

        //lo del combobox empleado
        DefaultComboBoxModel modeloCboEmpleados = new DefaultComboBoxModel();
        //traer todos los usuarios del repo
        for (Empleado e : Repositorio.empleados.getDatosCombo()) {
            modeloCboEmpleados.addElement(e);
        }
        //le doy al combo del form el modelo
        this.vista.comboEmpleado.setModel(modeloCboEmpleados);

        //lo del jtable
        actualizarTabla();
    }
}
