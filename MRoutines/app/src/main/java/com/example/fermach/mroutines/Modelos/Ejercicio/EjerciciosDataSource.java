package com.example.fermach.mroutines.Modelos.Ejercicio;

/**
 *
 * Interfaz con los metodos de nuestro servicio REST
 * de ejercicios
 *
 * @author Fermach
 * @version 1.0.
 *
 */
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;

import java.util.List;

public interface EjerciciosDataSource {

        void getEjercicios(CargaEjerciciosCallback callback);
        void getEjerciciosPorRutina(String rutina, CargaEjerciciosCallback callback);
        void createEjercicio(Ejercicio ejercicio, CreateEjercicioCallback callback);
        void deleteEjercicio(String id_ejercicio, DeleteEjercicioCallback callback);
        void deleteEjercicios(String nombre_rutina, DeleteEjerciciosCallback callback);
        void updateEjercicio(String id_ejercicio, Ejercicio ejercicio, UpdateEjercicioCallback callback);
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
            void onEjercicioCreado();
            void onEjercicioCreadoError();
        }
        interface DeleteEjercicioCallback {
            void onEjercicioEliminado();
            void onEjercicioEliminadoError();
         }
        interface DeleteEjerciciosCallback {
            void onEjerciciosEliminados();
            void onEjerciciosEliminadosError();
         }
        interface UpdateEjercicioCallback {
            void onEjercicioActualizado(String id_ejercicio);
            void onEjercicioActualizadoError();
        }
}
