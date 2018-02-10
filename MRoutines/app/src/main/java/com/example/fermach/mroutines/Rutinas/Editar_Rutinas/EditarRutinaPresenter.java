package com.example.fermach.mroutines.Rutinas.Editar_Rutinas;

import android.util.Log;

import com.example.fermach.mroutines.Modelos.Ejercicio.EjerciciosRepository;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasDataSource;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasRepository;
import com.example.fermach.mroutines.Rutinas.Crear_Rutinas.CrearRutinaContract;

import java.util.List;

/**
 * Este es el presentador de datos que que conecta el fragmento para editar rutinas
 * con el repositorio de datos
 * @author Fermach
 * @version 1.0.
 *
 */
public class EditarRutinaPresenter implements EditarRutinaContract.Presenter{
    private RutinasRepository rutinasRepository;
    private EditarRutinaContract.View rutinasView;



    public EditarRutinaPresenter(EditarRutinaContract.View rutinasView) {
        this.rutinasView = rutinasView;
        this.rutinasRepository = RutinasRepository.getInstance();
    }

    /**
     *
     * @param nombre_rutina
     * @param rutina
     */
    @Override
    public void editarRutina(String nombre_rutina, Rutina rutina) {
        Rutina miRutina= rutina;
        rutinasRepository.updateRutina(nombre_rutina, rutina, new RutinasDataSource.UpdateRutinaCallback() {
            @Override
            public void onRutinaActualizada(String rutina_nombre) {
                rutinasView.onRutinaActualizada();
            }

            @Override
            public void onRutinaActualizadaError() {
                 rutinasView.onRutinaActualizadaError();
            }
        });

    }




}
