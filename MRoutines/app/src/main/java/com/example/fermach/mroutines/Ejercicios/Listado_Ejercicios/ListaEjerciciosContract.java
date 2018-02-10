package com.example.fermach.mroutines.Ejercicios.Listado_Ejercicios;

import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;

import java.util.List;

/**
 *
 * Interfaz con los metodos de nuestro view y presenter de Lista de ejercicios
 *
 * @author Fermach
 * @version 1.0.
 *
 */

public interface ListaEjerciciosContract {

    interface View {
        void onEjercicioEliminado();
        void mostrarEjercicios(List<Ejercicio> ejercicios);
        void poblarListaEjercicios(List<Ejercicio> ejercicios);
        void activarListaClickable(List<Ejercicio> ejercicios);

    }
    interface Presenter {
        void cargaEjercicios(String rutina_nombre);
        void borrarEjercicio(String id_ejercicio);
    }
}
