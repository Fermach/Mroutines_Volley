package com.example.fermach.mroutines.Ejercicios.Crear_Editar_Ejercicios;

import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;

import java.util.List;

/**
 * Created by Fermach on 18/01/2018.
 */

public interface CrearEjercicioContract {

    interface View {
        void mostrarEjercicios(List<Ejercicio> ejercicios);
        void mostrarError();
    }
    interface Presenter {
        void cargaEjercicios();
        void crearEjercicio(Ejercicio ejercicio);
    }
}
