package com.example.fermach.mroutines.Rutinas.Crear_Rutinas;

import android.util.Log;

import com.example.fermach.mroutines.Modelos.Ejercicio.EjerciciosRepository;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasDataSource;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasRepository;

import java.util.List;

/**
 * Created by Fermach on 18/01/2018.
 */

public class CrearRutinaPresenter implements CrearRutinaContract.Presenter{
    private RutinasRepository rutinasRepository;
    private CrearRutinaContract.View rutinasView;



    public CrearRutinaPresenter(CrearRutinaContract.View rutinasView) {
        this.rutinasView = rutinasView;
        this.rutinasRepository = RutinasRepository.getInstance();
    }

    @Override
    public void cargaRutinas() {
         rutinasRepository.getRutinas(new RutinasDataSource.CargaRutinasCallback() {
             @Override
             public void onRutinasCargadas(List<Rutina> rutinas) {
                 rutinasView.mostrarRutinas(rutinas);
             }

             @Override
             public void onRutinasCargadasError() {

             }
         });

    }



    @Override
    public void crearRutina(Rutina rutina) {
        Rutina miRutina= rutina;
        Log.i("Mi rutina creada:", rutina.toString());
        rutinasRepository.createRutina(miRutina, new RutinasDataSource.CreateRutinaCallback() {
            @Override
            public void onRutinaCreada(List<Rutina> rutinas) {
                rutinasView.mostrarRutinas(rutinas);
            }

            @Override
            public void onRutinaCreadaError() {

            }
        });
    }

}
