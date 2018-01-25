package com.example.fermach.mroutines.Rutinas.Crear_Editar_Rutinas;

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

    }
    interface Presenter {
        void cargaRutinas();
        void crearRutina(Rutina rutina);

    }
}
