package com.example.fermach.mroutines.Rutinas.Listado_Rutinas;

import com.example.fermach.mroutines.Modelos.Ejercicio.EjerciciosDataSource;
import com.example.fermach.mroutines.Modelos.Ejercicio.EjerciciosRepository;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasDataSource;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasRepository;

import java.util.List;

/**
 * Created by Fermach on 18/01/2018.
 */

public class ListaRutinasPresenter implements ListaRutinasContract.Presenter{
    private RutinasRepository rutinasRepository;
    private ListaRutinasContract.View rutinasView;


    public ListaRutinasPresenter() {
        this.rutinasRepository = RutinasRepository.getInstance();
    }

    public ListaRutinasPresenter(ListaRutinasContract.View rutinasView) {
        this.rutinasView = rutinasView;
        this.rutinasRepository = RutinasRepository.getInstance();
    }

    @Override
    public void cargaRutinas() {
         rutinasRepository.getRutinas(new RutinasDataSource.CargaRutinasCallback() {
             @Override
             public void onRutinasCargadas(List<Rutina> rutinas) {
                 rutinasView.poblarListaRutinas(rutinas);
                 rutinasView.activarListaClickable(rutinas);
                 rutinasView.mostrarRutinas(rutinas);
             }

             @Override
             public void onRutinasCargadasError() {

             }
         });

    }

    @Override
    public void borrarRutina(String nombre_rutina) {
        rutinasRepository.deleteRutina(nombre_rutina, new RutinasDataSource.DeleteRutinaCallback() {
            @Override
            public void onRutinaEliminada() {

            }

            @Override
            public void onRutinaEliminadaError() {

            }
        });
    }


}
