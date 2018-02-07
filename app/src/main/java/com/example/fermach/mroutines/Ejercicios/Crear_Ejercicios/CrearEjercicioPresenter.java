package com.example.fermach.mroutines.Ejercicios.Crear_Ejercicios;

import android.util.Log;

import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
import com.example.fermach.mroutines.Modelos.Ejercicio.EjerciciosDataSource;
import com.example.fermach.mroutines.Modelos.Ejercicio.EjerciciosRepository;

import java.util.List;

/**
 * Created by Fermach on 18/01/2018.
 */

public class CrearEjercicioPresenter implements CrearEjercicioContract.Presenter{
    private EjerciciosRepository ejerciciosRepository;
    private CrearEjercicioContract.View ejerciciosView;

    public CrearEjercicioPresenter(CrearEjercicioContract.View ejerciciosView) {
        this.ejerciciosView = ejerciciosView;
        this.ejerciciosRepository = ejerciciosRepository.getInstance();
    }

    @Override
    public void cargaEjercicios() {
         ejerciciosRepository.getEjercicios(new EjerciciosDataSource.CargaEjerciciosCallback() {
             @Override
             public void onEjerciciosCargados(List<Ejercicio> ejercicios) {
                 ejerciciosView.mostrarEjercicios(ejercicios);
             }

             @Override
             public void onEjerciciosCargadosError() {

             }
         });

    }

    @Override
    public void crearEjercicio(Ejercicio ejercicio) {
        Ejercicio miEjercicio= ejercicio;
        Log.i("Mi ejercicio creado:", ejercicio.toString());
        ejerciciosRepository.createEjercicio(miEjercicio, new EjerciciosDataSource.CreateEjercicioCallback() {
            @Override
            public void onEjercicioCreado(List<Ejercicio> ejercicios) {
                ejerciciosView.mostrarEjercicios(ejercicios);
            }

            @Override
            public void onEjercicioCreadoError() {

            }
        });
    }

}