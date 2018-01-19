package com.example.fermach.mroutines.Modelos.Rutina;


/**
 * Created by Fermach on 18/01/2018.
 */

import java.util.List;

public interface RutinasDataSource {

        void getRutinas(CargaRutinasCallback callback);
        void createRutina(Rutina rutina, CreateRutinaCallback callback);
        void deleteRutina(DeleteRutinaCallback callback);
        void updateRutina(UpdateRutinaCallback callback);
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
            void onRutinaEliminada(List<Rutina> rutinas);
            void onRutinaEliminadaError();
         }
        interface UpdateRutinaCallback {
            void onRutinaActualizada(Rutina rutina);
            void onRutinaActualizadaError();
        }
}
