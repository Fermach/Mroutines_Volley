package com.example.fermach.mroutines.Ejercicios.Editar_Ejercicios;

import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;

/**
 * Created by Fermach on 18/01/2018.
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
