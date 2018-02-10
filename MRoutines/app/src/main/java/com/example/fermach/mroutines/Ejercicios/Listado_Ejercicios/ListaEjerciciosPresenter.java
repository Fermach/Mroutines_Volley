package com.example.fermach.mroutines.Ejercicios.Listado_Ejercicios;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import com.example.fermach.mroutines.App;
import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
import com.example.fermach.mroutines.Modelos.Ejercicio.EjerciciosDataSource;
import com.example.fermach.mroutines.Modelos.Ejercicio.EjerciciosRepository;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasDataSource;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasRepository;
import com.example.fermach.mroutines.Rutinas.Listado_Rutinas.ListaRutinasContract;

import java.util.List;

/**
 * Este es el presentador de datos que que conecta la lista de ejercicios con el repositorio de datos
 * @author Fermach
 * @version 1.0.
 *
 */
public class ListaEjerciciosPresenter implements ListaEjerciciosContract.Presenter{
    private EjerciciosRepository ejerciciosRepository;
    private ListaEjerciciosContract.View ejerciciosView;



    public ListaEjerciciosPresenter(ListaEjerciciosContract.View ejerciciosView) {
        this.ejerciciosView = ejerciciosView;
        this.ejerciciosRepository = EjerciciosRepository.getInstance();
    }

    public ListaEjerciciosPresenter() {
        this.ejerciciosRepository = EjerciciosRepository.getInstance();
    }

    @Override
    public void cargaEjercicios(String rutina_nombre) {
         ejerciciosRepository.getEjerciciosPorRutina(rutina_nombre, new EjerciciosDataSource.CargaEjerciciosCallback() {
             @Override
             public void onEjerciciosCargados(List<Ejercicio> ejercicios) {
                 //cuando se cargan los ejercicios se realizan las siguientes acciones de la vista

             ejerciciosView.poblarListaEjercicios(ejercicios);
             ejerciciosView.activarListaClickable(ejercicios);
             ejerciciosView.mostrarEjercicios(ejercicios);
             }

             @Override
             public void onEjerciciosCargadosError() {

             }
         });

    }

    @Override
    public void borrarEjercicio(String ejercicio_id) {
         Log.i("Ejercicio a borrar", ejercicio_id);
         ejerciciosRepository.deleteEjercicio(ejercicio_id, new EjerciciosDataSource.DeleteEjercicioCallback() {
             @Override
             public void onEjercicioEliminado() {
                 Log.i("REpositoryLista_Ejerc", "Se ha eliminado el Ejercicio ");

                 ejerciciosView.onEjercicioEliminado();
             }

             @Override
             public void onEjercicioEliminadoError() {
                 Log.i("REpositoryLista_Ejerc", "No se ha podido eliminar el Ejercicio ");


             }
         });
    }

}
