package com.example.fermach.mroutines.Rutinas.Crear_Rutinas;

import com.example.fermach.mroutines.Modelos.Rutina.Rutina;

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

public interface CrearRutinaContract {

    interface View {
        void onRutinaCreadaError();
        void onRutinaCreada();
    }
    interface Presenter {

        void crearRutina(Rutina rutina);

    }
}
