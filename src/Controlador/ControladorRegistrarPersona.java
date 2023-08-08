package Controlador;

import Modelo.*;
import Vista.*;
import Datos.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControladorRegistrarPersona {

    private frmClientePersona vista;
    private ClientePersonaArreglo modelo;
    private ConsultasPersonas modeloC = new ConsultasPersonas();

    public ControladorRegistrarPersona(frmClientePersona vista, ClientePersonaArreglo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //String numTelefono;
                if (vista.txtNombre.getText().isEmpty() || vista.txtApellido.getText().isEmpty()
                        || vista.txtDNIPersona.getText().isEmpty() || vista.txtCelularPersona.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Complete todos los campos");

                } else {
                    try {
                        //numTelefono = vista.lblTelefonoPersona.getText();
                        ClientePersona cp = new ClientePersona(vista.txtNombre.getText(),
                                vista.txtApellido.getText(), vista.txtDNIPersona.getText(),
                                vista.txtCelularPersona.getText());
                        Repositorio.personas.agregar(cp);
                        modeloC.registrarPersonas(cp);
                        JOptionPane.showMessageDialog(null, "Persona Agregada");
                        actualizarTabla();
                        limpiarCampos();
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage()+"Digite un num. celular valido");
                    }

                }

            }
        }
        );
        this.vista.btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControladorPrincipal controlador = new ControladorPrincipal(Repositorio.usuario_validado, new frmPrincipal());
                controlador.iniciar();
                vista.dispose();
            }
        }
        );
        this.vista.btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = vista.tblPersonasRepo.getSelectedRow();//seleccion de fila de la tabla

                //eliminar
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una persona");
                } else {
                    int valor = Integer.parseInt(vista.tblPersonasRepo.getValueAt(fila, 0).toString());//codigo 
                    Repositorio.personas.eliminar(valor);//metodo para eliminar
                    actualizarTabla();//actualizamos
                    modeloC.eliminarPersona(valor);
                    JOptionPane.showMessageDialog(null, "Persona Eliminada");
                }

            }
        }
        );

    }
    public void actualizarTabla() {
          this.vista.tblPersonasRepo.setModel(ConsultasPersonas.listar());
    }
    public void limpiarCampos(){
        //fecha talla peso diagnostico tratamiento
        this.vista.txtNombre.setText("");
        this.vista.txtApellido.setText("");
        this.vista.txtDNIPersona.setText("");
        this.vista.txtCelularPersona.setText("");       
        
    }

    public void iniciar() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);

        actualizarTabla();
    }
}
