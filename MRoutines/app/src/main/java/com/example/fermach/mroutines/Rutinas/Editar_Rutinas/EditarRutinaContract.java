package com.example.fermach.mroutines.Rutinas.Editar_Rutinas;

import com.example.fermach.mroutines.Modelos.Rutina.Rutina;

import java.util.List;


/**
 *
 * Interfaz con los metodos de nuestro view y presenter del fragmento
 * para editar rutinas
 *
 * @author Fermach
 * @version 1.0.
 *
 */

public interface EditarRutinaContract {

    interface View {
        void onRutinaActualizadaError();
        void onRutinaActualizada();

    }
    interface Presenter {
        void editarRutina(String nombre_rutina, Rutina rutina);

    }
}
