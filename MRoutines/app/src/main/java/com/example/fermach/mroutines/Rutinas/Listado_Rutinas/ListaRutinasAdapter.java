package com.example.fermach.mroutines.Rutinas.Listado_Rutinas;

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
 * Adaptador para el listado de rutinas
 *
 * @author Fermach
 * @version 1.0.
 *
 */

public class ListaRutinasAdapter extends ArrayAdapter<Rutina> {

    public ListaRutinasAdapter(@NonNull Context context, List<Rutina> rutinas) {

        super(context, 0,rutinas);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Rutina rutina= getItem(position);


        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_rutinas_item, parent, false);
        }

        TextView nombre = convertView.findViewById(R.id.nombre_rutina_lista);
        TextView tipo = convertView.findViewById(R.id.tipo_rutina_lista);
        TextView nivel = convertView.findViewById(R.id.nivel_rutina_lista);

        nombre.setText(rutina.getNombre());
        tipo.setText("Tipo: "+rutina.getTipo());
        nivel.setText("Nivel: "+rutina.getNivel());


        return convertView;
    }
}
