package Controlador;

import Modelo.*;
import Vista.*;
import Datos.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class ControladorMascotasFamilia {
    private frmMascotasFamilia vista;
    private int codFamilia;
    private ConsultasMascotas modeloC = new ConsultasMascotas();
    
    
    public ControladorMascotasFamilia (frmMascotasFamilia vista, int codFamilia){
        this.vista = vista;
        this.codFamilia = codFamilia;
        
        
        this.vista.btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (vista.txtCodMascota.getText().isEmpty() ) {
                    JOptionPane.showMessageDialog(null, "Digite un numero");

                } else {
                    try {
                        
                       int codigo=(Integer.parseInt(vista.txtCodMascota.getText()));
                        if(modeloC.verificaFamilia(codigo)==-1){
                            
                        
                        modeloC.asignaFamilia(codigo, codFamilia);
                        JOptionPane.showMessageDialog(null, "Mascota Agregada a la familia");
                        actualizarTabla();
                        vista.txtCodMascota.setText("");
                        }else{
                            JOptionPane.showMessageDialog(null, "Esta mascota ya tiene dueño");
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
                int fila = vista.tblMascotasFamilia.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una mascota");
                } else {
                    int valor = Integer.parseInt(vista.tblMascotasFamilia.getValueAt(fila, 0).toString());
                    modeloC.asignaFamilia(valor, -1);
                    actualizarTabla();//actualizamos
                    JOptionPane.showMessageDialog(null, "Mascota Eliminada de la familia");
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
        
        this.vista.btnMascotasRegistradas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControladorMascotasRegistradas controlador = new ControladorMascotasRegistradas( new frmMascotasRegistradas(),Repositorio.mascotas);
                controlador.iniciar();
            }
        }
        );
    }
    
    public void actualizarTabla() {
          this.vista.tblMascotasFamilia.setModel(ConsultasMascotas.listarMascotas(codFamilia));
    }
    
    public void iniciar() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
        
        actualizarTabla();
    }
    
}
