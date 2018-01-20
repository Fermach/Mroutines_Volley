package com.example.fermach.mroutines.Rutinas.Crear_Rutinas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
import com.example.fermach.mroutines.R;

import java.util.List;

/**
 * Created by Fermach on 18/01/2018.
 */

public class CrearRutina_EjerciciosAdapter extends ArrayAdapter<Ejercicio> {

    public CrearRutina_EjerciciosAdapter(@NonNull Context context, List<Ejercicio> ejercicios) {

        super(context, 0,ejercicios);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Ejercicio ejercicio= getItem(position);


        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_ejercicios_formulario, parent, false);
        }

        TextView nombre = convertView.findViewById(R.id.item_ejercicioNombre_form);
        TextView series = convertView.findViewById(R.id.item_ejercicioSeries_form);
        TextView repeticiones = convertView.findViewById(R.id.item_ejercicioRepeticiones_form);
        TextView tiempo = convertView.findViewById(R.id.item_ejercicioDuracion_form);
        TextView tipo = convertView.findViewById(R.id.item_ejercicioTipo_form);

        nombre.setText("Nombre: "+ejercicio.getNombre());
        series.setText("Series: "+ejercicio.getSeries());
        repeticiones.setText("Repeticiones: "+ejercicio.getRepeticiones());
        tiempo.setText("Tiempo: "+ejercicio.getTiempo());
        tipo.setText("Tipo: "+ejercicio.getTipo());


        return convertView;
    }
}
