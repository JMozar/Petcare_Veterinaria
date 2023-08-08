
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.*;
import Vista.*;
import Datos.*;


public class ControladorPrincipal {
    private frmPrincipal vista;
    private Usuario modelo;
    
    
    public ControladorPrincipal(Usuario modelo, frmPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;
        
        
        
        this.vista.btnMascotas.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    
                    ControladorMascota controlador = new ControladorMascota( new frmMascota(),Repositorio.mascotas);
                    controlador.iniciar();
                    vista.dispose();
                }
            }
        );
        
        this.vista.btnFamilias.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    
                    ControladorFamilias controlador = new ControladorFamilias( new frmFamilia(),Repositorio.familias);
                    controlador.iniciar();
                    vista.dispose();
                }
            }
        );
        
        this.vista.btnPersonas.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                ControladorRegistrarPersona controlador = new ControladorRegistrarPersona (new frmClientePersona(), Repositorio.personas);
                controlador.iniciar();
                vista.dispose();
                }
            }
        );
        this.vista.btnEmpleados.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                ControladorRegistrarEmpleados controlador = new ControladorRegistrarEmpleados (new frmEmpleado(), Repositorio.empleados);
                controlador.iniciar();
                vista.dispose();
                }
            }
        );
        
        this.vista.btnInyeccion.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                ControladorInyeccionVacuna controlador = new ControladorInyeccionVacuna (new frmInyeccionVacuna(), Repositorio.inyecciones);
                controlador.iniciar();
                vista.dispose();
                }
            }
        );
        
        this.vista.btnRegistrarCita.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                ControladorCita controlador = new ControladorCita (new frmCita(), Repositorio.citas);
                controlador.iniciar();
                vista.dispose();
                }
            }
        );
        
        this.vista.btnSalir.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    modelo.salir();
                    ControladorSistema controlador = new ControladorSistema( Repositorio.usuarios, new frmSistema() );
                    controlador.iniciar();
                    vista.dispose();
                }
            }
        );

    } 
    
    public void iniciar(){
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
        //traer usuario validado y ponerlo en el label
        this.vista.lblUsuario.setText(modelo.getUsuario());
    }

    
}
