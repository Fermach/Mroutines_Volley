package com.example.fermach.mroutines.Rutinas.Crear_Rutinas;

import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;

import java.util.List;

/**
 * Created by Fermach on 18/01/2018.
 */

public interface CrearRutinaContract {

    interface View {
        void mostrarRutinas(List<Rutina> rutinas);
        void mostrarError();
        void poblarListaEjercicios(List<Ejercicio> ejercicios);
    }
    interface Presenter {
        void cargaRutinas();
        void cargaEjercicios();
        void crearRutina(Rutina rutina);

    }
}
