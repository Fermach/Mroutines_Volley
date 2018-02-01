package com.example.fermach.mroutines.Rutinas.Listado_Rutinas;

import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;

import java.util.List;

/**
 * Created by Fermach on 18/01/2018.
 */

public interface ListaRutinasContract {

    interface View {
        void mostrarError();
        void poblarListaRutinas(List<Rutina> rutinas);
        void activarListaClickable(List<Rutina> rutinas);
        void mostrarRutinas(List<Rutina> rutinas);
    }
    interface Presenter {
        void cargaRutinas();
        void borrarRutina(String nombre_rutina);
    }
}
