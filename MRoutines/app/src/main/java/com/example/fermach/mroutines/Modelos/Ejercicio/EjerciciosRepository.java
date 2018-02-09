package com.example.fermach.mroutines.Modelos.Ejercicio;

import android.util.Log;

import com.example.fermach.mroutines.Modelos.Api.EjerciciosAPI;
import com.example.fermach.mroutines.Modelos.Api.RutinasAPI;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fermach on 18/01/2018.
 */

public class EjerciciosRepository implements EjerciciosDataSource{

    private static EjerciciosRepository INSTANCIA = null;
    private List<Ejercicio> listaEjerciciosPorRutina = null;

    public static EjerciciosRepository getInstance() {
        if (INSTANCIA == null) {
            INSTANCIA = new EjerciciosRepository();
        }
        return INSTANCIA;
    }



    @Override
    public void getEjercicios(CargaEjerciciosCallback callback) {


    }

    @Override
    public void getEjerciciosPorRutina(String rutina, final CargaEjerciciosCallback callback) {

        EjerciciosAPI ejerciciosAPI= new EjerciciosAPI();
        ejerciciosAPI.getEjerciciosPorRutina(rutina, new CargaEjerciciosCallback() {
            @Override
            public void onEjerciciosCargados(List<Ejercicio> ejercicios) {
               listaEjerciciosPorRutina=ejercicios;
               Log.i("Lista de ejercicios", "Repository"+listaEjerciciosPorRutina);
                callback.onEjerciciosCargados(ejercicios);
            }

            @Override
            public void onEjerciciosCargadosError() {
                callback.onEjerciciosCargadosError();
                Log.i("REpository", "Error al cargar ejercicios");
            }
        });

    }

    @Override
    public void createEjercicio(Ejercicio ejercicio, final CreateEjercicioCallback callback) {
        EjerciciosAPI ejerciciosAPI= new EjerciciosAPI();
        ejerciciosAPI.createEjercicio(ejercicio, new CreateEjercicioCallback() {
            @Override
            public void onEjercicioCreado() {
                Log.i("REpository", "Ejercicio creado correctamente");
                callback.onEjercicioCreado();
            }

            @Override
            public void onEjercicioCreadoError() {
                Log.i("REpository", "No se pudo crear el ejercicio");
                callback.onEjercicioCreadoError();
            }
        });

    }

    @Override
    public void deleteEjercicio(String id_ejercicio, final DeleteEjercicioCallback callback) {

       Log.i("REpository", "Se ha recogido el ejercicio a borrar ");
       EjerciciosAPI ejerciciosAPI= new EjerciciosAPI();
       ejerciciosAPI.deleteEjercicio(id_ejercicio, new DeleteEjercicioCallback() {
           @Override
           public void onEjercicioEliminado() {
               Log.i("REpository", "Se ha eliminado el Ejercicioa ");
               callback.onEjercicioEliminado();
           }

           @Override
           public void onEjercicioEliminadoError() {
               Log.i("REpository", "No se ha podido eliminar el Ejercicioa ");
               callback.onEjercicioEliminadoError();
           }
       });
    }

    @Override
    public void deleteEjercicios(final String nombre_rutina, final DeleteEjerciciosCallback callback) {
         EjerciciosAPI ejerciciosAPI= new EjerciciosAPI();
         ejerciciosAPI.deleteEjercicios(nombre_rutina, new DeleteEjerciciosCallback() {
             @Override
             public void onEjerciciosEliminados() {
                 Log.i("Eliminar TODOS EjercicR", "SE HAN ELIMINADO TODOS LOS EJERCICIOS DE LA RUTINA!! "+ nombre_rutina);
                  callback.onEjerciciosEliminados();
            }

             @Override
             public void onEjerciciosEliminadosError() {
                 Log.i("Eliminar TODOS EjercicR", "No se haN podido eliminar todos los ejercicios de esa rutina: "+ nombre_rutina);
                 callback.onEjerciciosEliminadosError();
             }
         });
    }

    @Override
    public void updateEjercicio(String id_ejercicio, Ejercicio ejercicio, final UpdateEjercicioCallback callback) {

        EjerciciosAPI ejerciciosAPI= new EjerciciosAPI();
        ejerciciosAPI.updateEjercicio(id_ejercicio, ejercicio, new UpdateEjercicioCallback() {
            @Override
            public void onEjercicioActualizado(String id_ejercicio) {
                Log.i("REpository", "Se ha actualizado el Ejercicio con el id "+id_ejercicio);

                callback.onEjercicioActualizado(id_ejercicio);
            }

            @Override
            public void onEjercicioActualizadoError() {
                Log.i("REpository", "No se ha podido actualizar el ejercicio ");

                callback.onEjercicioActualizadoError();
            }
        });
    }

    @Override
    public void getEjercicio(int posicion, CargaEjercicioCallback callback) {

    }
}
