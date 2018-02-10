package com.example.fermach.mroutines.Ejercicios.Editar_Ejercicios;

import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
/**
 *
 * Interfaz con los metodos de nuestro view y presenter del fragmento
 * para editar ejercicios
 *
 * @author Fermach
 * @version 1.0.
 *
 */

public interface EditarEjercicioContract {

    interface View {
        void onEjercicioActualizadoError();
        void onEjercicioActualizado();

    }
    interface Presenter {
        void editarEjercicio(String id_ejercicio, Ejercicio ejercicio);

    }
}
