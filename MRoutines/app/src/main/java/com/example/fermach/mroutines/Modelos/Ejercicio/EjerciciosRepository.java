package com.example.fermach.mroutines.Modelos.Ejercicio;

import android.util.Log;

import com.example.fermach.mroutines.Modelos.Rutina.Rutina;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fermach on 18/01/2018.
 */

public class EjerciciosRepository implements EjerciciosDataSource{

    private static EjerciciosRepository INSTANCIA = null;
    private List<Ejercicio> listaEjercicios = new ArrayList<>();
    private List<Ejercicio> listaEjerciciosPorRutina = new ArrayList<>();

    public static EjerciciosRepository getInstance() {
        if (INSTANCIA == null) {
            INSTANCIA = new EjerciciosRepository();
        }
        return INSTANCIA;
    }



    @Override
    public void getEjercicios(CargaEjerciciosCallback callback) {
        callback.onEjerciciosCargados(listaEjercicios);

    }

    @Override
    public void getEjerciciosPorRutina(String rutina, CargaEjerciciosCallback callback) {
        for (Ejercicio e: listaEjercicios) {
            if(e.getRutina().equals(rutina)){
                listaEjerciciosPorRutina.add(e);
            }
        }

        callback.onEjerciciosCargados(listaEjerciciosPorRutina);
        listaEjerciciosPorRutina=new ArrayList<>();

    }

    @Override
    public void createEjercicio(Ejercicio rutina, CreateEjercicioCallback callback) {
      if(listaEjercicios.add(rutina)){
          callback.onEjercicioCreado(listaEjercicios);
          Log.i("Lista de ejercicios:", listaEjercicios.toString());
      }else{
          callback.onEjercicioCreadoError();
      }
    }

    @Override
    public void deleteEjercicio(String id_ejercicio, DeleteEjercicioCallback callback) {
        Log.i("Ejer a borrar REPO", id_ejercicio);
        Log.i("LEjer a borrar REPO", listaEjerciciosPorRutina.toString());

       /* for(int i=0; i< listaEjerciciosPorRutina.size();i++){

           if(id_ejercicio.equals(listaEjerciciosPorRutina.get(i).getId())){

               listaEjerciciosPorRutina.remove(i);
               callback.onEjercicioEliminado(listaEjerciciosPorRutina);
               Log.i("Ejercicio borrado: ", id_ejercicio);
           }
       }
       */
       callback.onEjercicioEliminado();
    }

    @Override
    public void deleteEjercicios(String nombre_rutina, DeleteEjerciciosCallback callback) {

    }

    @Override
    public void updateEjercicio(String id_ejercicio, Ejercicio ejercicio, UpdateEjercicioCallback callback) {

        Log.i("Update ejercicio nombre", id_ejercicio);
        Log.i("Update ejercicio:", id_ejercicio);
        callback.onEjercicioActualizado(id_ejercicio);
    }

    @Override
    public void getEjercicio(int posicion, CargaEjercicioCallback callback) {

    }
}
