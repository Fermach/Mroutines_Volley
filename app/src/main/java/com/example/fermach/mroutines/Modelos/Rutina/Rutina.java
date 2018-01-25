package com.example.fermach.mroutines.Modelos.Rutina;

import java.io.Serializable;

/**
 * Created by Fermach on 13/01/2018.
 */

public class Rutina implements Serializable{
    private String nombre;
    private String tipo;
    private  String nivel;

    public Rutina() {

    }

    public Rutina(String nombre, String tipo, String nivel) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Rutina{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", nivel='" + nivel + '\'' +
                '}';
    }
}
