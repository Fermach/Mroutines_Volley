package com.example.fermach.mroutines.Rutinas.Crear_Rutinas;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.example.fermach.mroutines.Modelos.Rutina.Rutina;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasRepository;
import com.example.fermach.mroutines.R;

import java.util.List;


/**
 * Created by Fermach on 18/01/2018.
 */

public class CrearRutinaVista extends Fragment implements CrearRutinaContract.View{

    private CrearRutinaContract.Presenter presenter;
    private EditText et_nombre_rutina;
    private Spinner sp_tipo_rutina;
    private Spinner sp_nivel_rutina;
    private Button añadir_ejerc_rutina;
    private Button añadir_rutina;
    private Button cancelar_rutina;
    private ListView list_crear_rutina;
    View myView;
    private Fragment fragment;
    private TextView num_rutinas;
    String nombre;
    String nivel;
    String tipo;
    Rutina rutina;


    public CrearRutinaVista() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.formulario_rutina, container, false);

        fragment = new CrearRutinaVista();

        inicializarVistas();
        activarControladores();


        String[] valores_tipo= {"musculacion","cardiovascular","otro"};
        String[] valores_nivel= {"bajo","medio","alto"};

        sp_nivel_rutina.setAdapter(new ArrayAdapter<String>
                (getContext(),R.layout.support_simple_spinner_dropdown_item,valores_nivel));
        sp_tipo_rutina.setAdapter(new ArrayAdapter<String>
                (getContext(),R.layout.support_simple_spinner_dropdown_item,valores_tipo));

        presenter=new CrearRutinaPresenter(this);
        presenter.cargaRutinas();

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
                tipo= sp_tipo_rutina.getSelectedItem().toString();
                nivel= sp_nivel_rutina.getSelectedItem().toString();
                rutina=new Rutina(nombre,tipo,nivel);

                Log.i("Rutina creada:", rutina.toString());
                presenter.crearRutina(rutina);
            }
        });


    }


    @Override
    public void mostrarRutinas(List<Rutina> rutinas) {
        num_rutinas.setText("Numero de rutinas: "+ rutinas.size());
    }

    @Override
    public void mostrarError() {
    }
}
