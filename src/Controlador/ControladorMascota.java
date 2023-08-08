package Controlador;

import Modelo.*;
import Vista.*;
import Datos.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;


public class ControladorMascota {

    private frmMascota vista;
    private MascotaArreglo modelo;
    private ConsultasMascotas modeloC = new ConsultasMascotas();

    //a
    public ControladorMascota(frmMascota vista, MascotaArreglo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        //botones
        this.vista.btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (vista.txtNombreMascota.getText().isEmpty() || vista.txtRazaMascota.getText().isEmpty()
                        || vista.txtRazaMascota.getText().isEmpty() || vista.txtFechaNacimientoMascota.getText().isEmpty()
                        ||vista.txtColorMascota.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Complete todos los campos");
                } else {
                    
                    
                    Mascota m = new Mascota(vista.txtNombreMascota.getText(),  vista.cmbEspecie.getSelectedItem().toString(),
                            vista.txtRazaMascota.getText(),
                            vista.txtColorMascota.getText(), vista.txtFechaNacimientoMascota.getText());
                    //Agregamos las mascotas al repo
                    Repositorio.mascotas.num++;
                    m.setCodigo(Repositorio.mascotas.getNum());
                    
                    Repositorio.mascotas.agregar(m);
                    modeloC.registrarMascotas(m);
                    JOptionPane.showMessageDialog(null, "Mascota Agregada");
                    //Actualizar tabla
                    actualizarTabla();
                    limpiarCampos();
                }
            }
        }
        );

        this.vista.btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = vista.tblMascotasRepo.getSelectedRow();
                
                //eliminar
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una mascota");
                } else {
                    int valor = Integer.parseInt(vista.tblMascotasRepo.getValueAt(fila, 0).toString());//codigo de mascota
                    Repositorio.mascotas.eliminar(valor);
                    modeloC.eliminarMascota(valor);
                    
                    actualizarTabla();//actualizamos
                    JOptionPane.showMessageDialog(null, "Mascota Eliminada");
                }

            }
        }
        );
        this.vista.btnHistorialMedico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = vista.tblMascotasRepo.getSelectedRow();//seleccion de fila de la tabla 
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una mascota");
                } else {
                    int valor = Integer.parseInt(vista.tblMascotasRepo.getValueAt(fila, 0).toString());//codigo de mascota
                    
                    Mascota m= null;
                    //Repositorio.mascotas.devolverMascota(valor).mostrarHistorialmedico();
                    m=Repositorio.mascotas.devolverMascota(valor);
                    //Mascota m2=(Mascota)Repositorio.mascota[valor];
                    ControladorHistorialMedico controladorh = new ControladorHistorialMedico(new frmHistorialMedico(), m);
                    controladorh.iniciar();
                }
            }
        }
        );
        
        this.vista.btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControladorPrincipal controlador = new ControladorPrincipal(Repositorio.usuario_validado, new frmPrincipal());
                controlador.iniciar();
                vista.dispose();
            }
        }        
                
        );
    }

    public void actualizarTabla() {
          this.vista.tblMascotasRepo.setModel(ConsultasMascotas.listar());
          //ConsultasMascotas.llenar();
    }
    public void limpiarCampos(){
        this.vista.txtNombreMascota.setText("");
        this.vista.txtRazaMascota.setText("");
        this.vista.txtFechaNacimientoMascota.setText("");
        this.vista.txtColorMascota.setText("");
        this.vista.txtColorMascota.setText("");
        
    }

    public void iniciar() {
        this.vista.setLocationRelativeTo(null);

        this.vista.setVisible(true);

        //lo del combobox
        DefaultComboBoxModel modeloCboEspecies = new DefaultComboBoxModel();
        //traer todos los usuarios del repo
        for (Especie u : Repositorio.especies.getDatosCombo()) {
            modeloCboEspecies.addElement(u);
        }
        //le doy al combo del form el modelo
        this.vista.cmbEspecie.setModel(modeloCboEspecies);

        //lo del jtable
        actualizarTabla();
    }
}
