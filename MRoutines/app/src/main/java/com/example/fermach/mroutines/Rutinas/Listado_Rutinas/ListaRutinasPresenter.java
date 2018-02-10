package com.example.fermach.mroutines.Rutinas.Listado_Rutinas;

import android.util.Log;

import com.example.fermach.mroutines.Modelos.Ejercicio.EjerciciosDataSource;
import com.example.fermach.mroutines.Modelos.Ejercicio.EjerciciosRepository;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasDataSource;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasRepository;

import java.util.List;

/**
 * Este es el presentador de datos que que conecta la lista de rutinas con el repositorio de datos
 * @author Fermach
 * @version 1.0.
 *
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
        Log.i("Presenter", "Presenter");
         rutinasRepository.getRutinas(new RutinasDataSource.CargaRutinasCallback() {
             @Override
             public void onRutinasCargadas(List<Rutina> rutinas) {
                 //cuando se cargan las rutinas se realizan las siguientes acciones de la vista
                 Log.i("RUUUUTTIINAASS", rutinas.toString());
                 rutinasView.poblarListaRutinas(rutinas);
                 rutinasView.activarListaClickable(rutinas);
                 rutinasView.mostrarRutinas(rutinas);
             }

             @Override
             public void onRutinasCargadasError() {

             }
         });

    }

    /**
     *
     * @param nombre_rutina
     */
    @Override
    public void borrarRutina(String nombre_rutina) {
        rutinasRepository.deleteRutina(nombre_rutina, new RutinasDataSource.DeleteRutinaCallback() {
            @Override
            public void onRutinaEliminada() {
                Log.i("REpositoryLista_Rutinas", "Se ha eliminado la rutina ");

                rutinasView.onRutinaEliminada();
            }

            @Override
            public void onRutinaEliminadaError() {
                Log.i("REpositoryLista_Rutinas", "No se ha podido eliminar la rutina ");

            }
        });
    }


}
