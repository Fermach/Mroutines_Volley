package com.example.fermach.mroutines.Modelos.Ejercicio;

import java.io.Serializable;
import java.sql.Time;

/**
 * Created by Fermach on 13/01/2018.
 */

public class Ejercicio implements Serializable {

     String nombre;
     int series;
     int repeticiones;
     String tiempo;
     String tipo;
     String rutina;

    public Ejercicio(){}

    public Ejercicio(String nombre, int series, int repeticiones, String tiempo, String tipo, String rutina) {
        this.nombre = nombre;
        this.series = series;
        this.repeticiones = repeticiones;
        this.tiempo = tiempo;
        this.tipo = tipo;
        this.rutina = rutina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRutina() {
        return rutina;
    }

    public void setRutina(String rutina) {
        this.rutina = rutina;
    }

    @Override
    public String toString() {
        return "Ejercicio{" +
                "nombre='" + nombre + '\'' +
                ", series=" + series +
                ", repeticiones=" + repeticiones +
                ", tiempo='" + tiempo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", rutina='" + rutina + '\'' +
                '}';
    }
}
