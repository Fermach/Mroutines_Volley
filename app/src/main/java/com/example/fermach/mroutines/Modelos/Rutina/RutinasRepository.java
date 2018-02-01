package com.example.fermach.mroutines.Modelos.Rutina;

import android.util.Log;

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
    private List<Rutina> listaRutinas = new ArrayList<>();

    public static RutinasRepository getInstance() {
        if (INSTANCIA == null) {
            INSTANCIA = new RutinasRepository();
        }
        return INSTANCIA;
    }



    @Override
    public void getRutinas(CargaRutinasCallback callback) {
        callback.onRutinasCargadas(listaRutinas);


    }

    @Override
    public void createRutina(Rutina rutina, CreateRutinaCallback callback) {
      if(listaRutinas.add(rutina)){
          callback.onRutinaCreada(listaRutinas);
          Log.i("Lista de rutinas:", listaRutinas.toString());
      }else{
          callback.onRutinaCreadaError();
      }
    }

    @Override
    public void deleteRutina(String rutina_nombre, final DeleteRutinaCallback callback) {
       //borramos todos los ejercicios de esa rutina
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


    }

    @Override
    public void updateRutina(String rutina_nombre, Rutina rutina, UpdateRutinaCallback callback) {
        Log.i("Update rutina nombre:", rutina_nombre);
        Log.i("Update rutina:", rutina_nombre);
        callback.onRutinaActualizada(rutina_nombre);
    }

    @Override
    public void getRutina(int posicion, CargaRutinaCallback callback) {

    }
}
