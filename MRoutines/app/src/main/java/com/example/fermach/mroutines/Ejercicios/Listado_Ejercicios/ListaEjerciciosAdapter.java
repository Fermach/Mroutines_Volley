package com.example.fermach.mroutines.Ejercicios.Listado_Ejercicios;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fermach.mroutines.App;
import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;
import com.example.fermach.mroutines.R;

import java.util.List;

/**
 * Adaptador para el listado de ejercicios
 *
 * @author Fermach
 * @version 1.0.
 *
 */

public class ListaEjerciciosAdapter extends ArrayAdapter<Ejercicio> {

    public ListaEjerciciosAdapter(@NonNull Context context, List<Ejercicio> ejercicios) {

        super(context, 0,ejercicios);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Ejercicio ejercicio= getItem(position);


        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_ejercicios_item, parent, false);
        }

        TextView nombre = convertView.findViewById(R.id.nombre_ejercicio_item);
        TextView tipo = convertView.findViewById(R.id.tipo_ejercicio_item);

        nombre.setText(ejercicio.getNombre());
        tipo.setText("Tipo: "+ejercicio.getTipo());

        return convertView;
    }
}
