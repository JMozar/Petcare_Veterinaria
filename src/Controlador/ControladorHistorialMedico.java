
package Controlador;

import Modelo.*;
import Vista.*;
import Datos.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ControladorHistorialMedico {
    private frmHistorialMedico vista;
    private Mascota modelo;
    
    public ControladorHistorialMedico(frmHistorialMedico vista, Mascota modelo) {
        this.vista = vista;
        this.modelo = modelo;

        //botones
        this.vista.btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
            }
        }
        );
        
    }
    
    public void mostrarHistorial(){
        this.vista.txtAreaHistorial.setText(modelo.mostrarHistorialmedico());
        this.vista.txtAreaVacunas.setText(modelo.mostrarVacunas());
    }
   
    

    public void iniciar() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
        this.vista.jTextField1.setEditable(false);
        this.vista.txtAreaHistorial.setEditable(false);
        this.vista.txtAreaVacunas.setEditable(false);
        this.vista.lblNombreMascota.setText(modelo.getNombre());
        mostrarHistorial();
    }
}

