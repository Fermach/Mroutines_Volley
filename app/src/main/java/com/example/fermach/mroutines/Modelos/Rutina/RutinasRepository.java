package com.example.fermach.mroutines.Modelos.Rutina;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fermach on 18/01/2018.
 */

public class RutinasRepository implements RutinasDataSource{

    private static RutinasRepository INSTANCIA = null;
    private List<Rutina> listaRutinas = new ArrayList<>();

    public static RutinasRepository getInstance() {
        if (INSTANCIA == null) {
            INSTANCIA = new RutinasRepository();
        }
        return INSTANCIA;
    }



    @Override
    public void getRutinas(CargaRutinasCallback callback) {
        callback.onRutinasCargadas(listaRutinas);


    }

    @Override
    public void createRutina(Rutina rutina, CreateRutinaCallback callback) {
      if(listaRutinas.add(rutina)){
          callback.onRutinaCreada(listaRutinas);
          Log.i("Lista de rutinas:", listaRutinas.toString());
      }else{
          callback.onRutinaCreadaError();
      }
    }

    @Override
    public void deleteRutina(DeleteRutinaCallback callback) {

    }

    @Override
    public void updateRutina(UpdateRutinaCallback callback) {

    }

    @Override
    public void getRutina(int posicion, CargaRutinaCallback callback) {

    }
}
