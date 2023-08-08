
package Controlador;

import Modelo.ClientePersonaArreglo;
import Modelo.ConsultasPersonas;
import Vista.frmPersonasRegistradas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ControladorPersonasRegistradas {
    private frmPersonasRegistradas vista;
    private ClientePersonaArreglo modelo;
    private ConsultasPersonas modeloC = new ConsultasPersonas();

    public ControladorPersonasRegistradas(frmPersonasRegistradas vista, ClientePersonaArreglo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        
        this.vista.btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                vista.dispose();
            }
        }
        );
    }
    public void actualizarTabla() {
          this.vista.tblPersonasRegistradas.setModel(ConsultasPersonas.listar());
    }

    public void iniciar() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);

        actualizarTabla();
    }
}
