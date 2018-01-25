package com.example.fermach.mroutines.Ejercicios.Listado_Ejercicios;

import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
import com.example.fermach.mroutines.Modelos.Ejercicio.EjerciciosDataSource;
import com.example.fermach.mroutines.Modelos.Ejercicio.EjerciciosRepository;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasDataSource;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasRepository;
import com.example.fermach.mroutines.Rutinas.Listado_Rutinas.ListaRutinasContract;

import java.util.List;

/**
 * Created by Fermach on 18/01/2018.
 */

public class ListaEjerciciosPresenter implements ListaEjerciciosContract.Presenter{
    private EjerciciosRepository ejerciciosRepository;
    private ListaEjerciciosContract.View ejerciciosView;



    public ListaEjerciciosPresenter(ListaEjerciciosContract.View ejerciciosView) {
        this.ejerciciosView = ejerciciosView;
        this.ejerciciosRepository = EjerciciosRepository.getInstance();
    }

    @Override
    public void cargaEjercicios(String rutina_nombre) {
         ejerciciosRepository.getEjerciciosPorRutina(rutina_nombre, new EjerciciosDataSource.CargaEjerciciosCallback() {
             @Override
             public void onEjerciciosCargados(List<Ejercicio> ejercicios) {
             ejerciciosView.poblarListaEjercicios(ejercicios);
             ejerciciosView.activarListaClickable(ejercicios);
             }

             @Override
             public void onEjerciciosCargadosError() {

             }
         });

    }

}
