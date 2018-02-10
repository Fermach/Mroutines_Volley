package com.example.fermach.mroutines.Ejercicios.Crear_Ejercicios;

import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;

import java.util.List;

/**
 *
 * Interfaz con los metodos de nuestro view y presenter del fragmento
 * para crear ejercicios
 *
 * @author Fermach
 * @version 1.0.
 *
 */


public interface CrearEjercicioContract {

    interface View {
        void onEjercicioCreado();
        void onEjercicioCreadoError();
    }
    interface Presenter {

        void crearEjercicio(Ejercicio ejercicio);
    }
}
