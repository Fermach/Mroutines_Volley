package com.example.fermach.mroutines.Rutinas.Editar_Rutinas;

import com.example.fermach.mroutines.Modelos.Rutina.Rutina;

import java.util.List;

/**
 * Created by Fermach on 18/01/2018.
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
