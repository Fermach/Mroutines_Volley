package com.example.fermach.mroutines.Ejercicios.Crear_Ejercicios;

import android.util.Log;

import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
import com.example.fermach.mroutines.Modelos.Ejercicio.EjerciciosDataSource;
import com.example.fermach.mroutines.Modelos.Ejercicio.EjerciciosRepository;

import java.util.List;

/**
 * Este es el presentador de datos que conecta el fragmento para crear ejercicios
 * con el repositorio de datos
 * @author Fermach
 * @version 1.0.
 *
 */

public class CrearEjercicioPresenter implements CrearEjercicioContract.Presenter{
    private EjerciciosRepository ejerciciosRepository;
    private CrearEjercicioContract.View ejerciciosView;

    public CrearEjercicioPresenter(CrearEjercicioContract.View ejerciciosView) {
        this.ejerciciosView = ejerciciosView;
        this.ejerciciosRepository = ejerciciosRepository.getInstance();
    }


    @Override
    public void crearEjercicio(Ejercicio ejercicio) {
        Ejercicio miEjercicio= ejercicio;
        Log.i("Mi ejercicio creado:", ejercicio.toString());
        ejerciciosRepository.createEjercicio(miEjercicio, new EjerciciosDataSource.CreateEjercicioCallback() {
            @Override
            public void onEjercicioCreado() {
                Log.i("PresenterCrearEj", "Ejercicio creado correctamente");

                ejerciciosView.onEjercicioCreado();
            }

            @Override
            public void onEjercicioCreadoError() {
                Log.i("PresenterCrearEj", "No se pudo crear el ejercicio");
                ejerciciosView.onEjercicioCreadoError();
            }
        });
    }

}
