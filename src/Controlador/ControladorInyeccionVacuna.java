package Controlador;

import Modelo.*;
import Vista.*;
import Datos.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorInyeccionVacuna {

    private frmInyeccionVacuna vista;
    private InyeccionVacunaArreglo modelo;

    public ControladorInyeccionVacuna(frmInyeccionVacuna vista, InyeccionVacunaArreglo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        //botones
        this.vista.btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int numVacuna;
                
                //comienza
                //Mascota mascota1 = new Mascota("Peque",especie1,"Mostaza","12-08-2018");
                if (vista.txtNumVacuna.getText().isEmpty() || vista.txtFecha.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Complete todos los campos");
                } else {
                    try {

                        numVacuna = Integer.parseInt(vista.txtNumVacuna.getText());
                        Mascota m = (Mascota) vista.cmbMascotas.getSelectedItem();

                        if (numVacuna > 0 && numVacuna <= m.getVacunasDesignadas().getNumVacunas()) {
                            InyeccionVacuna i = new InyeccionVacuna(numVacuna, vista.txtFecha.getText(),
                                    (Mascota) vista.cmbMascotas.getSelectedItem(),
                                    (Empleado) vista.cmbEmpleados.getSelectedItem());

                            //Agregamos las mascotas al repo
                            m.vacunar(numVacuna - 1);//con esto se cambia el estado de la vacuna seleccionada
                            m.registrarInyeccionHistorial(i);
                            Repositorio.inyecciones.agregar(i);
                            JOptionPane.showMessageDialog(null, "Inyeccion AGREGADA");
                            //Actualizar tabla
                            actualizarTabla();
                            System.out.println(Repositorio.inyecciones.toString());//mascotas que estan en repo
                        } else {
                            JOptionPane.showMessageDialog(null, "Num.Vacuna invalido");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Digite un numero en Num. vacuna");
                        
                    }

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

        this.vista.btnVacunasCalendario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Mascota m = (Mascota) vista.cmbMascotas.getSelectedItem();
                ControladorVacunasCalendario controlador = new ControladorVacunasCalendario(new frmVacunarCalendario(), m);
                controlador.iniciar();
            }
        }
        );

        this.vista.btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = vista.tblInyeccionesRepo.getSelectedRow();//seleccion de fila de la tabla

                //eliminar
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una Inyeccion");
                } else {
                    int valor = Integer.parseInt(vista.tblInyeccionesRepo.getValueAt(fila, 0).toString());//codigo de mascota
                    Repositorio.inyecciones.eliminar(valor);//metodo para eliminar(de un arreglo de mascotas)
                    actualizarTabla();//actualizamos
                    JOptionPane.showMessageDialog(null, "Inyeccion Eliminada");
                }

            }
        }
        );
    }

    public void actualizarTabla() {
        //lo del jtable
        DefaultTableModel modelotabla = new DefaultTableModel(this.modelo.getDatos(), this.modelo.getCabecera());
        this.vista.tblInyeccionesRepo.setModel(modelotabla);
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
        this.vista.cmbMascotas.setModel(modeloCboMascotas);

        //lo del combobox empleado
        DefaultComboBoxModel modeloCboEmpleados = new DefaultComboBoxModel();
        //traer todos los usuarios del repo
        for (Empleado e : Repositorio.empleados.getDatosCombo()) {
            modeloCboEmpleados.addElement(e);
        }
        //le doy al combo del form el modelo
        this.vista.cmbEmpleados.setModel(modeloCboEmpleados);

        //lo del jtable
        actualizarTabla();
    }
}
