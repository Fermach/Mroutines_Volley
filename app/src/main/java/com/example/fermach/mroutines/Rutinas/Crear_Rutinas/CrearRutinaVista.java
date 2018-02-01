package com.example.fermach.mroutines.Rutinas.Crear_Rutinas;

import android.os.Bundle;
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
 * Created by Fermach on 18/01/2018.
 */

public class CrearRutinaVista extends Fragment implements CrearRutinaContract.View{

    private CrearRutinaContract.Presenter presenter;
    private EditText et_nombre_rutina;
    private Spinner sp_tipo_rutina;
    private Spinner sp_nivel_rutina;
    private Button añadir_rutina;
    private Button cancelar_rutina;
    private View myView;
    private Fragment fragment;
    private TextView num_rutinas;
    private String rutina_nombre;
    private String nivel;
    private String tipo;
    private Rutina rutina;


    public CrearRutinaVista() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.formulario_rutina, container, false);

        fragment = new CrearRutinaVista();

        inicializarVistas();
        activarControladores();
        poblarSpinner();

        presenter=new CrearRutinaPresenter(this);
        presenter.cargaRutinas();

        return myView;
    }


    public void inicializarVistas(){

        et_nombre_rutina= myView.findViewById(R.id.editText_nombreRutina);
        sp_tipo_rutina= myView.findViewById(R.id.spinner_tipoRutina);
        sp_nivel_rutina= myView.findViewById(R.id.spinner_nivelRutina);
        añadir_rutina= myView.findViewById(R.id.btn_crear_formRutina);
        cancelar_rutina= myView.findViewById(R.id.btn_cancelar_formRutina);
        num_rutinas=myView.findViewById(R.id.num_Rutinas);
    }

    public void activarControladores(){

        añadir_rutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rutina_nombre= et_nombre_rutina.getText().toString();
                tipo= sp_tipo_rutina.getSelectedItem().toString().toLowerCase();
                nivel= sp_nivel_rutina.getSelectedItem().toString().toLowerCase();
                rutina=new Rutina(rutina_nombre,tipo,nivel);

                if (rutina_nombre.length() < 1) {
                    Snackbar.make(myView,"Debe introducir un nombre valido", Snackbar.LENGTH_SHORT).show();
                } else {
                    Log.i("Rutina creada:", rutina.toString());
                    presenter.crearRutina(rutina);

                    fragment = new ListaRutinasVista();
                    getFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();

                }
            }
        });

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
    public void mostrarRutinas(List<Rutina> rutinas) {
        num_rutinas.setText("Numero de rutinas: "+ rutinas.size());
    }

    @Override
    public void mostrarError() {


    }
}
