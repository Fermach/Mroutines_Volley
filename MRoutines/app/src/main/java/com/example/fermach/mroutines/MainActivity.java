package com.example.fermach.mroutines;


import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.fermach.mroutines.Rutinas.Crear_Rutinas.CrearRutinaVista;
import com.example.fermach.mroutines.Rutinas.Listado_Rutinas.ListaRutinasVista;

/**
  * Clase principal donde se gestionan los demás fragmentos
  *
  * @author Fermach
  * @version 1.0.
  *
  */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    public final static String MAIN_FRAGMENT="MAIN_FRAGMENT";
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragment= new Pantalla_temporal();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                fragment = new ListaRutinasVista();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();
            };
        }, 4000);
    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //detecta el item de nuestro NavigationBar clickeado y nos permite navegar por los fragmentos
        int id = item.getItemId();
        boolean itemSeleccionado= false;

        if (id == R.id.nav_nueva_rutina) {
            fragment= new CrearRutinaVista();
            itemSeleccionado=true;
        }
         else if (id == R.id.nav_lista_rutinas) {
            fragment= new ListaRutinasVista();
            itemSeleccionado=true;
        }else if (id == R.id.nav_info) {
            fragment= new Pantalla_info();
            itemSeleccionado=true;
        }

        if(itemSeleccionado==true){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
