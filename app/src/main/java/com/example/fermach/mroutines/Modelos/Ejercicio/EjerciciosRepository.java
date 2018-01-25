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
    public void deleteEjercicio(DeleteEjercicioCallback callback) {

    }

    @Override
    public void updateEjercicio(UpdateEjercicioCallback callback) {

    }

    @Override
    public void getEjercicio(int posicion, CargaEjercicioCallback callback) {

    }
}
