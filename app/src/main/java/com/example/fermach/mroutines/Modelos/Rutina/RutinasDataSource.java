package com.example.fermach.mroutines.Modelos.Rutina;


/**
 * Created by Fermach on 18/01/2018.
 */

import java.util.List;

public interface RutinasDataSource {

        void getRutinas(CargaRutinasCallback callback);
        void createRutina(Rutina rutina, CreateRutinaCallback callback);
        void deleteRutina(String rutina_nombre, DeleteRutinaCallback callback);
        void updateRutina(String rutina_nombre, Rutina rutina, UpdateRutinaCallback callback);
        void getRutina(int posicion, CargaRutinaCallback callback);

        interface CargaRutinasCallback {
            void onRutinasCargadas(List<Rutina> rutinas);
            void onRutinasCargadasError();
        }
        interface CargaRutinaCallback {
            void onRutinaCargada(Rutina rutina);
            void onRutinaCargadaError();
        }
        interface CreateRutinaCallback {
            void onRutinaCreada( List<Rutina> rutinas);
            void onRutinaCreadaError();
        }
        interface DeleteRutinaCallback {
            void onRutinaEliminada();
            void onRutinaEliminadaError();
         }
        interface UpdateRutinaCallback {
            void onRutinaActualizada(String rutina_nombre);
            void onRutinaActualizadaError();
        }
}
