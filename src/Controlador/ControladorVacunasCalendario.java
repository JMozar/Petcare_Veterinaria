
package Controlador;

import Modelo.*;
import Vista.*;
import Datos.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ControladorVacunasCalendario {
    private frmVacunarCalendario vista;
    private Mascota modelo;
    
    public ControladorVacunasCalendario(frmVacunarCalendario vista, Mascota modelo) {
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
        this.vista.txtAreaVacunasCalendario.setText(modelo.mostrarVacunas());
    }

    public void iniciar() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
        
        this.vista.lblNombreMascota.setText(modelo.getNombre());
        this.vista.txtAreaVacunasCalendario.setEditable(false);
        mostrarHistorial();
    }
}

