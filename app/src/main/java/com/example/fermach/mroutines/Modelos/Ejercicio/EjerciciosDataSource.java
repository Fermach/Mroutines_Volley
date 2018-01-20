package com.example.fermach.mroutines.Modelos.Ejercicio;


/**
 * Created by Fermach on 18/01/2018.
 */

import com.example.fermach.mroutines.Modelos.Rutina.Rutina;

import java.util.List;

public interface EjerciciosDataSource {

        void getEjercicios(CargaEjerciciosCallback callback);
        void createEjercicio(Ejercicio ejercicio, CreateEjercicioCallback callback);
        void deleteEjercicio(DeleteEjercicioCallback callback);
        void updateEjercicio(UpdateEjercicioCallback callback);
        void getEjercicio(int posicion, CargaEjercicioCallback callback);

        interface CargaEjerciciosCallback {
            void onEjerciciosCargados(List<Ejercicio> ejercicios);
            void onEjerciciosCargadosError();
        }
        interface CargaEjercicioCallback {
            void onEjercicioCargada(Ejercicio ejercicio);
            void onEjercicioCargadaError();
        }
        interface CreateEjercicioCallback {
            void onEjercicioCreado(List<Ejercicio> ejercicios);
            void onEjercicioCreadoError();
        }
        interface DeleteEjercicioCallback {
            void onEjercicioEliminado(List<Ejercicio> ejercicios);
            void onEjercicioEliminadoError();
         }
        interface UpdateEjercicioCallback {
            void onEjercicioActualizado(Ejercicio ejercicio);
            void onEjercicioActualizadoError();
        }
}
