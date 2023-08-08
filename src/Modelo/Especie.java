package Modelo;

import java.io.Serializable;

public class Especie implements Serializable{
    
    private String nombreEspecie;

    public Especie (String nombreEspecie) {
        this.nombreEspecie = nombreEspecie;
    }

    public String getNombreEspecie() {
        return nombreEspecie;
    }

    public void setNombreEspecie(String nombreEspecie) {
        this.nombreEspecie = nombreEspecie;
    }

    @Override
    public String toString() {
        return nombreEspecie ;
    }

}