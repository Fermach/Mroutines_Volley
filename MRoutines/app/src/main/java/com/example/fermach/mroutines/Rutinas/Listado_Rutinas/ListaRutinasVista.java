package com.example.fermach.mroutines.Rutinas.Listado_Rutinas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fermach.mroutines.Ejercicios.Listado_Ejercicios.ListaEjerciciosVista;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;
import com.example.fermach.mroutines.R;
import com.example.fermach.mroutines.Rutinas.Crear_Rutinas.CrearRutinaVista;
import java.util.List;


/**
 * Created by Fermach on 18/01/2018.
 */

public class ListaRutinasVista extends Fragment implements ListaRutinasContract.View{

    private ListaRutinasContract.Presenter presenter;
    private ListaRutinasAdapter rutinasAdapter;
    private ListView list_rutinas;
    private TextView num_rutinas;
    private View myView;
    private FloatingActionButton fab_rutinas;
    private Fragment fragment;
    private Rutina rutina;
    private boolean rutinaActualizada;
    private boolean eliminar_rutins;
    String nombre_rutina_eliminar;
    private final String RUTINA ="RUTINA";

    public ListaRutinasVista() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.lista_rutinas, container, false);

        fragment = new ListaRutinasVista();
        rutinaActualizada=false;
        eliminar_rutins=false;


        inicializarVistas();
        activarControladores();

        presenter=new ListaRutinasPresenter(this);
        presenter.cargaRutinas();


        Bundle args = getArguments();

        if(args!=null) {

            eliminar_rutins = (Boolean) args.getSerializable("ELIMINAR_RUTINA");
            nombre_rutina_eliminar=(String) args.getSerializable("RUTINA_A_ELIMINAR_NOMBRE");
            Log.i("Argumentos", "RECOGIDOS =" + eliminar_rutins);

            presenter.borrarRutina(nombre_rutina_eliminar);
        }else{
            Log.i("Argumentos", "NULOS" );

        }


        return myView;
    }


    public void inicializarVistas(){
        list_rutinas=myView.findViewById(R.id.list_rutinas);
        fab_rutinas=myView.findViewById(R.id.fab_rutinas);
        num_rutinas=myView.findViewById(R.id.num_Rutinas_lista);
    }

    public void activarControladores() {

        fab_rutinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new CrearRutinaVista();
                getFragmentManager().beginTransaction().replace(R.id.content_main, fragment ).commit();

            }
        });


    }



    @Override
        public void poblarListaRutinas(List<Rutina> rutinas){
         rutinasAdapter= new ListaRutinasAdapter(myView.getContext(), rutinas);
         list_rutinas.setAdapter(rutinasAdapter);
    }

    @Override
    public void activarListaClickable(final List<Rutina> rutinas) {
        list_rutinas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                rutina= rutinas.get(position);
                Bundle args = new Bundle();
                args.putSerializable(RUTINA, rutina.getNombre());
                Fragment toFragment = new ListaEjerciciosVista();
                toFragment.setArguments(args);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_main, toFragment, RUTINA)
                        .addToBackStack(RUTINA).commit();
            }
        });

        list_rutinas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                rutina= rutinas.get(position);
                FragmentManager fragmentManager=getFragmentManager();

                DialogFragment dialogFragment= ListaRutinasMenuLClick.newInstance(rutina);
                dialogFragment.show(fragmentManager, "menuRutinas");

                return true;
            }
        });
    }

    @Override
    public void mostrarRutinas( List<Rutina> rutinas) {
        num_rutinas.setText("Numero de rutinas: "+ rutinas.size());

    }


    @Override
    public void onRutinaEliminada() {
        presenter.cargaRutinas();


    }


}
