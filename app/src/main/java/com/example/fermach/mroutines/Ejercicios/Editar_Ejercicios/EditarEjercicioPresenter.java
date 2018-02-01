package com.example.fermach.mroutines.Ejercicios.Editar_Ejercicios;

import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
import com.example.fermach.mroutines.Modelos.Ejercicio.EjerciciosDataSource;
import com.example.fermach.mroutines.Modelos.Ejercicio.EjerciciosRepository;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasDataSource;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasRepository;

/**
 * Created by Fermach on 18/01/2018.
 */

public class EditarEjercicioPresenter implements EditarEjercicioContract.Presenter{
    private EjerciciosRepository ejerciciosRepository;
    private EditarEjercicioContract.View ejerciciosView;



    public EditarEjercicioPresenter(EditarEjercicioContract.View ejerciciosView) {
        this.ejerciciosView = ejerciciosView;
        this.ejerciciosRepository = EjerciciosRepository.getInstance();
    }


    @Override
    public void editarEjercicio(String id_ejercicio, Ejercicio ejercicio) {
        Ejercicio miEjercicio= ejercicio;
        ejerciciosRepository.updateEjercicio(id_ejercicio, ejercicio, new EjerciciosDataSource.UpdateEjercicioCallback() {
            @Override
            public void onEjercicioActualizado(String id_ejercicio) {
                ejerciciosView.onEjercicioActualizado();
            }

            @Override
            public void onEjercicioActualizadoError() {
                 ejerciciosView.onEjercicioActualizadoError();
            }
        });

    }




}
