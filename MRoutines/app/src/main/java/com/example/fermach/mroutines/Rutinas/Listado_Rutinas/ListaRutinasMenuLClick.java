package com.example.fermach.mroutines.Rutinas.Listado_Rutinas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.fermach.mroutines.Ejercicios.Listado_Ejercicios.ListaEjerciciosContract;
import com.example.fermach.mroutines.Ejercicios.Listado_Ejercicios.ListaEjerciciosPresenter;
import com.example.fermach.mroutines.Ejercicios.Listado_Ejercicios.ListaEjerciciosVista;
import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;
import com.example.fermach.mroutines.R;
import com.example.fermach.mroutines.Rutinas.Editar_Rutinas.EditarRutinaVista;

/**
 * Created by Fermach on 25/01/2018.
 */

public class ListaRutinasMenuLClick extends DialogFragment {

    private Rutina rutina;
    private ListaRutinasContract.Presenter presenter;
    private final String RUTINA_NOMBRE ="RUTINA_NOMBRE";
    private final String ELIMINAR_RUTINA ="ELIMINAR_RUTINA";
    private final String RUTINA_A_ELIMINAR_NOMBRE ="RUTINA_A_ELIMINAR_NOMBRE";


    public static ListaRutinasMenuLClick newInstance(Rutina rutina) {

        Bundle args = new Bundle();

        args.putSerializable("RUTINA", rutina);
        ListaRutinasMenuLClick fragment = new ListaRutinasMenuLClick();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter= new ListaRutinasPresenter();


        rutina =(Rutina) getArguments()
                .getSerializable("RUTINA");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      final View v = inflater.inflate(R.layout.menu_edicion_borrado_rutina, container, false);


        Button borrar_rutina= v.findViewById(R.id.btn_borrar_rutina);
        Button editar_rutina= v.findViewById(R.id.btn_editar_rutina);


        borrar_rutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myBuild = new AlertDialog.Builder(view.getContext());
                myBuild.setMessage("¿Estás seguro de que deseas borrar esta rutina?");
                myBuild.setTitle("Borrar Rutina");
                myBuild.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        getDialog().dismiss();
                        Bundle args_eli = new Bundle();
                        args_eli.putSerializable(RUTINA_A_ELIMINAR_NOMBRE, rutina.getNombre());
                        args_eli.putSerializable(ELIMINAR_RUTINA, true);
                        Fragment toFragment = new ListaRutinasVista();
                        toFragment.setArguments(args_eli);
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content_main, toFragment)
                                .addToBackStack(ELIMINAR_RUTINA).addToBackStack(RUTINA_A_ELIMINAR_NOMBRE).commit();
                    }
                });
                myBuild.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                myBuild.show();

             }
        });

        editar_rutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                getDialog().dismiss();
                Bundle bundle = new Bundle();
                bundle.putSerializable(RUTINA_NOMBRE, rutina);
                Fragment toFragment = new EditarRutinaVista();
                toFragment.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_main, toFragment, RUTINA_NOMBRE)
                        .addToBackStack(RUTINA_NOMBRE).commit();
            }
        });

      return v;
    }
}
