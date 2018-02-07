package com.example.fermach.mroutines.Rutinas.Crear_Rutinas;

import com.example.fermach.mroutines.Modelos.Rutina.Rutina;

import java.util.List;

/**
 * Created by Fermach on 18/01/2018.
 */

public interface CrearRutinaContract {

    interface View {
        void mostrarError();

    }
    interface Presenter {
        void cargaRutinas();
        void crearRutina(Rutina rutina);

    }
}
