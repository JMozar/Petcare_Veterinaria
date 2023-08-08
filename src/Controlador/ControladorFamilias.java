package Controlador;

import Modelo.*;
import Vista.*;
import Datos.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorFamilias {

    private frmFamilia vista;
    private ClienteFamiliaArreglo modelo;
    private ConsultasFamilias modeloC = new ConsultasFamilias();

    public ControladorFamilias(frmFamilia vista, ClienteFamiliaArreglo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.btnGuardarFamilia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int numCelular;
                if ( vista.txtApellido.getText().isEmpty() || vista.txtCtaB.getText().isEmpty() || vista.txtDireccion.getText().isEmpty() || vista.txtCel.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Complete todos los campos");
                } else {
                   
                        try {
                            numCelular = Integer.parseInt(vista.txtCel.getText());
                            try {
                                ClienteFamilia f = new ClienteFamilia( vista.txtApellido.getText(),
                                        vista.txtCtaB.getText(), vista.txtDireccion.getText(), vista.txtCel.getText());
                                //Agregamos las familia al repo
                                Repositorio.familias.agregar(f);

                                JOptionPane.showMessageDialog(null, "Familia Agregada");
                                modeloC.registrarFamilia(f);//base de datos
                                
                                actualizarTabla();
                                limpiarCampos();
                                System.out.println(Repositorio.familias.toString());

                            } catch (NumberFormatException ex1) {
                                JOptionPane.showMessageDialog(null, "Digite datos validos");
                            }
                        } catch (NumberFormatException ex2) {
                            JOptionPane.showMessageDialog(null, "Número de celular invalido");
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

        this.vista.btnMascota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = vista.tblFamiliaRepo.getSelectedRow();//seleccion de fila de la tabla
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una familia");
                } else {
                    int valor = Integer.parseInt(vista.tblFamiliaRepo.getValueAt(fila, 0).toString());//codigo de mascota

                    ControladorMascotasFamilia controladorh = new ControladorMascotasFamilia(new frmMascotasFamilia(), valor);
                    controladorh.iniciar();
                }
            }
        }
        );

        this.vista.btnIntegrantes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = vista.tblFamiliaRepo.getSelectedRow();//seleccion de fila de la tabla
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una familia");
                } else {
                    int valor = Integer.parseInt(vista.tblFamiliaRepo.getValueAt(fila, 0).toString());//codigo de familia

                    ControladorIntegrantesFamilia controladorh = new ControladorIntegrantesFamilia(new frmIntegrantesFamilia(),valor);
                    controladorh.iniciar();
                }
            }
        }
        );

        this.vista.btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = vista.tblFamiliaRepo.getSelectedRow();//seleccion de fila de la tabla

                //eliminar
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una familia");
                } else {
                    int valor = Integer.parseInt(vista.tblFamiliaRepo.getValueAt(fila, 0).toString());//codigo de familia
                    Repositorio.familias.eliminar(valor);//metodo para eliminar(de un arreglo de familias)
                    modeloC.eliminarFamilia(valor);
                    actualizarTabla();//actualizamos
                    JOptionPane.showMessageDialog(null, "Familia Eliminada");
                    
                }

            }
        }
        );
    }

    public void actualizarTabla2() {
        //lo del jtable
        DefaultTableModel modelotabla = new DefaultTableModel(this.modelo.getDatos(), this.modelo.getCabecera());
        this.vista.tblFamiliaRepo.setModel(modelotabla);
    }
    public void actualizarTabla(){
        this.vista.tblFamiliaRepo.setModel(ConsultasFamilias.listar());
    }

    public void limpiarCampos() {
        vista.txtApellido.setText("");
        vista.txtCtaB.setText("");
        vista.txtDireccion.setText("");
        vista.txtCel.setText("");

    }

    public void iniciar() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
        actualizarTabla();
    }
}
