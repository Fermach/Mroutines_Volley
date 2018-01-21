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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fermach.mroutines.Ejercicios.Crear_Editar_Ejercicios.CrearEjercicioVista;
import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasRepository;
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
    CrearRutina_EjerciciosAdapter ejerciciosAdapter;
    private Button añadir_ejerc_rutina;
    private Button añadir_rutina;
    private Button cancelar_rutina;
    private ListView list_crear_rutina;
    View myView;
    private Fragment fragment;
    private TextView num_rutinas;
    private String nombre;
    private String nivel;
    private String tipo;
    private Rutina rutina;
    private final String RUTINA ="RUTINA"
;

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
        presenter.cargaEjercicios();

        return myView;
    }


    public void inicializarVistas(){

        et_nombre_rutina= (EditText)myView.findViewById(R.id.editText_nombreRutina);
        sp_tipo_rutina= (Spinner)myView.findViewById(R.id.spinner_tipoRutina);
        sp_nivel_rutina= (Spinner)myView.findViewById(R.id.spinner_nivelRutina);
        añadir_ejerc_rutina= (Button)myView.findViewById(R.id.btn_crearEjercicio_enForm);
        añadir_rutina= (Button)myView.findViewById(R.id.btn_crear_formRutina);
        cancelar_rutina= (Button)myView.findViewById(R.id.btn_cancelar_formRutina);
        list_crear_rutina= (ListView)myView.findViewById(R.id.list_crearRutina);
        num_rutinas=myView.findViewById(R.id.num_Rutinas);
    }

    public void activarControladores(){

        añadir_rutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre= et_nombre_rutina.getText().toString();
                tipo= sp_tipo_rutina.getSelectedItem().toString().toLowerCase();
                nivel= sp_nivel_rutina.getSelectedItem().toString().toLowerCase();
                rutina=new Rutina(nombre,tipo,nivel);

                Log.i("Rutina creada:", rutina.toString());
                presenter.crearRutina(rutina);
            }
        });

        añadir_ejerc_rutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombre= et_nombre_rutina.getText().toString();
                tipo= sp_tipo_rutina.getSelectedItem().toString().toLowerCase();
                nivel= sp_nivel_rutina.getSelectedItem().toString().toLowerCase();
                rutina=new Rutina(nombre,tipo,nivel);

                Log.i("Valor de Rutina2:", nombre);
                //no funciona (SOLUCIONAR)
                Log.i("Valor de nombre:", nombre);
                if(nombre=="" || nombre==null){
                    Snackbar.make(myView,"Debe introducir el nombre de la rutina", Snackbar.LENGTH_SHORT).show();
                }else{
                    presenter.crearRutina(rutina);
                    Bundle args = new Bundle();
                    args.putSerializable(RUTINA, nombre);
                    fragment = new CrearEjercicioVista();
                    getFragmentManager().beginTransaction().replace(R.id.content_main, fragment, RUTINA)
                            .addToBackStack(RUTINA).commit();
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
        String[] valores_tipo= {"Musculacion","Cardiovascular","Otro"};
        String[] valores_nivel= {"Bajo","Medio","Alto"};

        sp_nivel_rutina.setAdapter(new ArrayAdapter<String>
                (getContext(),R.layout.support_simple_spinner_dropdown_item,valores_nivel));
        sp_tipo_rutina.setAdapter(new ArrayAdapter<String>
                (getContext(),R.layout.support_simple_spinner_dropdown_item,valores_tipo));

    }

    @Override
    public void poblarListaEjercicios(List<Ejercicio> ejercicios){
      ejerciciosAdapter= new CrearRutina_EjerciciosAdapter(myView.getContext(), ejercicios);
      list_crear_rutina.setAdapter(ejerciciosAdapter);
    }

    @Override
    public void mostrarRutinas(List<Rutina> rutinas) {
        num_rutinas.setText("Numero de rutinas: "+ rutinas.size());
    }

    @Override
    public void mostrarError() {


    }
}
