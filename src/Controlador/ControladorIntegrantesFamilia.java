package Controlador;

import Modelo.*;
import Vista.*;
import Datos.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class ControladorIntegrantesFamilia {
    private frmIntegrantesFamilia vista;
    private int codFamilia;

    private ConsultasPersonas modeloC = new ConsultasPersonas();
    
    
    public ControladorIntegrantesFamilia (frmIntegrantesFamilia vista,  int codFamilia){
        this.vista = vista;
        this.codFamilia = codFamilia;
        
        this.vista.btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (vista.txtCodPersona.getText().isEmpty() ) {
                    JOptionPane.showMessageDialog(null, "Digite un numero");

                } else {
                    try {
                         int codigo=(Integer.parseInt(vista.txtCodPersona.getText()));
                        if(modeloC.verificaFamilia(codigo)==-1){
                            
                        
                        modeloC.asignaFamilia(codigo, codFamilia);
                        JOptionPane.showMessageDialog(null, "Persona Agregada a la familia");
                        actualizarTabla();
                        vista.txtCodPersona.setText("");
                        }else{
                            JOptionPane.showMessageDialog(null, "Esta Persona ya pertenece a una familia");
                        }
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Digite un numero valido");
                    }
                        

                }
            }
        }
        );
        
        this.vista.btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = vista.tblIntegrantesFamilia.getSelectedRow();
                
                //eliminar
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una persona");
                } else {
                    
                    int valor = Integer.parseInt(vista.tblIntegrantesFamilia.getValueAt(fila, 0).toString());

                    modeloC.asignaFamilia(valor, -1);
                    actualizarTabla();//actualizamos
                    
                    JOptionPane.showMessageDialog(null, "Persona eliminada de la familia");
                }

            }
        }
        );
        
        this.vista.btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
            }
        }
        );
        
        this.vista.btnPersonasRegistradas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControladorPersonasRegistradas controlador = new ControladorPersonasRegistradas( new frmPersonasRegistradas(),Repositorio.personas);
                controlador.iniciar();
            }
        }
        );
    }
    
    public void actualizarTabla() {
          this.vista.tblIntegrantesFamilia.setModel(ConsultasPersonas.listarIntegrantes(codFamilia));
    }
    
    public void iniciar() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
        
        actualizarTabla();
    }
}
