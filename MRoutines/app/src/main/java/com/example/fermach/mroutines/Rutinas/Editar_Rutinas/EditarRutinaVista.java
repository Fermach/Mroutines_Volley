package com.example.fermach.mroutines.Rutinas.Editar_Rutinas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.fermach.mroutines.Modelos.Rutina.Rutina;
import com.example.fermach.mroutines.R;
import com.example.fermach.mroutines.Rutinas.Listado_Rutinas.ListaRutinasVista;

import java.util.List;


/**
 * Esta es la vista del fragmento para editar rutinas
 * @author Fermach
 * @version 1.0.
 *
 */

public class EditarRutinaVista extends Fragment implements EditarRutinaContract.View{

    private EditarRutinaContract.Presenter presenter;
    private EditText et_nombre_rutina;
    private Spinner sp_tipo_rutina;
    private Spinner sp_nivel_rutina;
    private Button añadir_rutina;
    private Button cancelar_rutina;
    private View myView;
    private Fragment fragment;
    private Rutina rutina_nombre;
    private String RUTINA_ACTUALIZADA="RUTINA_ACTUALIZADA";
    private String nivel;
    private String tipo;
    private Rutina rutina;
    private String nombre;


    public EditarRutinaVista() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.formulario_editar_rutina, container, false);

        fragment = new EditarRutinaVista();

        Bundle args = getArguments();
            rutina_nombre =(Rutina) args
                .getSerializable("RUTINA_NOMBRE");

        inicializarVistas();
        activarControladores();
        poblarSpinner();

        presenter=new EditarRutinaPresenter(this);

        return myView;
    }


    public void inicializarVistas(){

        et_nombre_rutina= myView.findViewById(R.id.editText_nombreRutina_editar);
        et_nombre_rutina.setText(rutina_nombre.getNombre());

        sp_tipo_rutina= myView.findViewById(R.id.spinner_tipoRutina_editar);
        sp_nivel_rutina= myView.findViewById(R.id.spinner_nivelRutina_editar);
        añadir_rutina= myView.findViewById(R.id.btn_crear_formRutina_editar);
        cancelar_rutina= myView.findViewById(R.id.btn_cancelar_formRutina_editar);

    }

    public void activarControladores(){


        //si pulsamos sobre añadir rutina nos crea unna rutina con
        //los datos intoducidos en los campos y llama al presentador para que la actualice
        añadir_rutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombre= et_nombre_rutina.getText().toString();
                tipo= sp_tipo_rutina.getSelectedItem().toString().toLowerCase();
                nivel= sp_nivel_rutina.getSelectedItem().toString().toLowerCase();

                rutina=new Rutina(nombre,tipo,nivel);

                if (nombre.length() < 1) {
                    Snackbar.make(myView,"Debe introducir un nombre valido", Snackbar.LENGTH_SHORT).show();
                } else {
                    Log.i("Rutina creada:", rutina.toString());
                    presenter.editarRutina(nombre,rutina);



                }
            }
        });

        //si pulsamos cancelar volvemos a la lista
        cancelar_rutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new ListaRutinasVista();
                getFragmentManager().beginTransaction().replace(R.id.content_main, fragment ).commit();
            }
        });
    }

    public void poblarSpinner(){
        String[] valores_tipo= {"Musculación","Cardiovascular","Otro"};
        String[] valores_nivel= {"Bajo","Medio","Alto"};

        sp_nivel_rutina.setAdapter(new ArrayAdapter<String>
                (getContext(),R.layout.support_simple_spinner_dropdown_item,valores_nivel));
        sp_tipo_rutina.setAdapter(new ArrayAdapter<String>
                (getContext(),R.layout.support_simple_spinner_dropdown_item,valores_tipo));

    }


    @Override
    public void onRutinaActualizadaError() {
        Snackbar.make(myView,"No se ha podido actualizar la rutina",Snackbar.LENGTH_SHORT);

    }

    @Override
    public void onRutinaActualizada() {

        Snackbar.make(myView, "Se ha actualizado la rutina correctamente", Snackbar.LENGTH_LONG).show();

        // cuando se actualiza la rutina lanzamos un snackbar junto a un hilo que nos lleve a la lista en 1,2 sec
        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 1.2 segundos, pasamos a la actividad principal de la aplicación
                fragment = new ListaRutinasVista();
                getFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();

            };
        }, 1200);
    }
}
