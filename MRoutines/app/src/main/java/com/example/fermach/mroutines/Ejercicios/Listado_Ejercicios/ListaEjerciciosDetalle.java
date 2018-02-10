package com.example.fermach.mroutines.Ejercicios.Listado_Ejercicios;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
import com.example.fermach.mroutines.R;

/**
 *
 * Fragmento con el detalle del ejercicio clickeado de nuestra lista
 *
 * @author Fermach
 * @version 1.0.
 *
 */

public class ListaEjerciciosDetalle extends DialogFragment {

    private Ejercicio ejercicio;

    //al instanciar el fragmento le pasamos el ejercicio seleccionado
    public static ListaEjerciciosDetalle newInstance(Ejercicio ejercicio) {
        ListaEjerciciosDetalle fragment = new ListaEjerciciosDetalle();
        Bundle args = new Bundle();
        args.putSerializable("EJERCICIO", ejercicio);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //al crear el fragmneto recibimos el ejercicio de nuestra instancia
        ejercicio =(Ejercicio) getArguments()
                .getSerializable("EJERCICIO");

        Log.i("Ejercicio en Detalle: ", ejercicio.toString());

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View v = inflater.inflate(R.layout.ejercicio_detalle, container, false);


        TextView nombre_detalle= v.findViewById(R.id.ejer_detalle_nombre);
        TextView tipo_detalle= v.findViewById(R.id.ejer_detalle_tipo);
        TextView series_detalle= v.findViewById(R.id.ejer_detalle_series);
        TextView repeticiones_detalle= v.findViewById(R.id.ejer_detalle_respeticiones);
        TextView tiempo_detalle= v.findViewById(R.id.ejer_detalle_tiempo);

        nombre_detalle.setText("Nombre: "+ejercicio.getNombre());
        tipo_detalle.setText("Tipo: "+ejercicio.getTipo());
        series_detalle.setText("Series: "+ejercicio.getSeries());
        repeticiones_detalle.setText("Repeticiones: "+ejercicio.getRepeticiones());
        tiempo_detalle.setText("Duración: "+ejercicio.getTiempo());

      return v;
    }
}
