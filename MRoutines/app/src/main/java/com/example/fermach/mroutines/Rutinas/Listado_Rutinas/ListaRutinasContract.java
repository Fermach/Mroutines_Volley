package com.example.fermach.mroutines.Rutinas.Listado_Rutinas;

import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;

import java.util.List;

/**
 *
 * Interfaz con los metodos de nuestro view y presenter de Lista de rutinas
 *
 * @author Fermach
 * @version 1.0.
 *
 */

public interface ListaRutinasContract {

    interface View {
        void onRutinaEliminada();
        void poblarListaRutinas(List<Rutina> rutinas);
        void activarListaClickable(List<Rutina> rutinas);
        void mostrarRutinas(List<Rutina> rutinas);
    }
    interface Presenter {
        void cargaRutinas();
        void borrarRutina(String nombre_rutina);
    }
}
