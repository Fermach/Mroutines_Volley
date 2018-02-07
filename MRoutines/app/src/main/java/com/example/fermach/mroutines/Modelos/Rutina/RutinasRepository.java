package com.example.fermach.mroutines.Modelos.Rutina;

import android.util.Log;

import com.example.fermach.mroutines.Modelos.Api.RutinasAPI;
import com.example.fermach.mroutines.Modelos.Ejercicio.EjerciciosDataSource;
import com.example.fermach.mroutines.Modelos.Ejercicio.EjerciciosRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fermach on 18/01/2018.
 */

public class RutinasRepository implements RutinasDataSource{

    private EjerciciosRepository ejerciciosRepository;
    private static RutinasRepository INSTANCIA = null;
    private List<Rutina> listaRutinas =null;

    public static RutinasRepository getInstance() {
        if (INSTANCIA == null) {
            INSTANCIA = new RutinasRepository();
        }
        return INSTANCIA;
    }



    @Override
    public void getRutinas(final CargaRutinasCallback callback) {
        Log.i("REpository", "Repository");

            RutinasAPI rutinasAPI= new RutinasAPI();
            rutinasAPI.getRutinas(new CargaRutinasCallback() {
                @Override
                public void onRutinasCargadas(List<Rutina> rutinas) {
                    listaRutinas=rutinas;
                    Log.i("Lista de rutinas", "Repository"+listaRutinas);
                    callback.onRutinasCargadas(rutinas);
                }

                @Override
                public void onRutinasCargadasError() {
                     callback.onRutinasCargadasError();
                    Log.i("REpository", "Error al cargar rutinas");
                }
            });

    }

    @Override
    public void createRutina(Rutina rutina, final CreateRutinaCallback callback) {
        RutinasAPI rutinasAPI= new RutinasAPI();
        rutinasAPI.createRutina(rutina, new CreateRutinaCallback() {
            @Override
            public void onRutinaCreada() {
                Log.i("REpository", "Rutina creada correctamentee");
                callback.onRutinaCreada();
            }

            @Override
            public void onRutinaCreadaError() {
                Log.i("REpository", "No se ha podido crear la rutina ");
                callback.onRutinaCreadaError();
            }
        });
    }

    @Override
    public void deleteRutina(final String rutina_nombre, final DeleteRutinaCallback callback) {
       //borramos todos los ejercicios de esa rutina
        RutinasAPI rutinasAPI= new RutinasAPI();
        rutinasAPI.deleteRutina(rutina_nombre, new DeleteRutinaCallback() {
            @Override
            public void onRutinaEliminada() {
                Log.i("REpository", "Se ha eliminado la rutina ");
                callback.onRutinaEliminada();
              /*
                ejerciciosRepository= EjerciciosRepository.getInstance();
                ejerciciosRepository.deleteEjercicios(rutina_nombre, new EjerciciosDataSource.DeleteEjerciciosCallback() {
                    @Override
                    public void onEjerciciosEliminado() {

                        //aqui borramos la rutina




                        callback.onRutinaEliminada();
                    }

                    @Override
                    public void onEjerciciosEliminadoError() {

                    }
                });
                */
            }

            @Override
            public void onRutinaEliminadaError() {
                Log.i("REpository", "No se ha podido eliminar la rutina ");
                callback.onRutinaEliminadaError();
            }
        });



    }

    @Override
    public void updateRutina(String rutina_nombre, Rutina rutina, final UpdateRutinaCallback callback) {
       RutinasAPI rutinasAPI= new RutinasAPI();
       rutinasAPI.updateRutina(rutina_nombre, rutina, new UpdateRutinaCallback() {
           @Override
           public void onRutinaActualizada(String rutina_nombre) {
               Log.i("REpository", "Se ha actualizado la rutina de nombre "+rutina_nombre);

               callback.onRutinaActualizada(rutina_nombre);
           }

           @Override
           public void onRutinaActualizadaError() {
               Log.i("REpository", "No se ha podido actualizar la rutina ");

               callback.onRutinaActualizadaError();
           }
       });
    }

    @Override
    public void getRutina(int posicion, CargaRutinaCallback callback) {

    }
}
