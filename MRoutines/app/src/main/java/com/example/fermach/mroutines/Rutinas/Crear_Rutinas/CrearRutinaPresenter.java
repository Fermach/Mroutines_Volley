package com.example.fermach.mroutines.Rutinas.Crear_Rutinas;

import android.util.Log;

import com.example.fermach.mroutines.Modelos.Ejercicio.EjerciciosRepository;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasDataSource;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasRepository;

import java.util.List;

/**
 * Este es el presentador de datos que conecta el fragmento para crear rutinas
 * con el repositorio de datos
 * @author Fermach
 * @version 1.0.
 *
 */
public class CrearRutinaPresenter implements CrearRutinaContract.Presenter{
    private RutinasRepository rutinasRepository;
    private CrearRutinaContract.View rutinasView;



    public CrearRutinaPresenter(CrearRutinaContract.View rutinasView) {
        this.rutinasView = rutinasView;
        this.rutinasRepository = RutinasRepository.getInstance();
    }



    @Override
    public void crearRutina(Rutina rutina) {
        Rutina miRutina= rutina;
        Log.i("Mi rutina creada:", rutina.toString());
        rutinasRepository.createRutina(miRutina, new RutinasDataSource.CreateRutinaCallback() {
            @Override
            public void onRutinaCreada() {
                Log.i("PresenterCrear_rutina", "Rutina creada correctamentee");
                rutinasView.onRutinaCreada();
            }

            @Override
            public void onRutinaCreadaError() {
                Log.i("PresenterCrear_rutina", "No se pudo crear la rutina");
                rutinasView.onRutinaCreadaError();
            }
        });
    }

}
