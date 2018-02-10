package com.example.fermach.mroutines.Modelos.Api;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.fermach.mroutines.App;
import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
import com.example.fermach.mroutines.Modelos.Ejercicio.EjerciciosDataSource;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Esta clase se utiliza volley para conectarse a nuestro servidor
 * y manda las respuesta al repositorio de datos
 *
 * @author Fermach
 * @version 1.0.
 */


public class EjerciciosAPI implements EjerciciosDataSource {

    String url_GET_porRutina="http://192.168.1.8:5000/ejercicios/rutina/";
    String url_POST="http://192.168.1.8:5000/ejercicios";
    String url_PUT="http://192.168.1.8:5000/ejercicios/id/";
    String url_DELETE="http://192.168.1.8:5000/ejercicios/id/";
    String url_DELETE_porRutina="http://192.168.1.8:5000/ejercicios/rutina/";

    public EjerciciosAPI() {
    }

    @Override
    public void getEjercicios(CargaEjerciciosCallback callback) {

    }

    @Override
    public void getEjerciciosPorRutina(String rutina, final CargaEjerciciosCallback callback) {
        Log.i("EjerciciosAPI", "EjerciciosAPI");
        RequestQueue req = VolleySingleton.getInstance(App.getAppContext()).getRequestQueue();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url_GET_porRutina+rutina,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Respuesta_GET_Ejerc", response);

                        List<Ejercicio> ejercicios = new Gson().fromJson(
                                new StringReader(response),
                                new TypeToken<List<Ejercicio>>() {
                                }.getType());



                        callback.onEjerciciosCargados(ejercicios);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        callback.onEjerciciosCargadosError();
                        Log.i("EjerciciosAPI", "ERROR Respuesta");
                    }
                });

        req.add(stringRequest);
    }

    @Override
    public void createEjercicio(final Ejercicio ejercicio, final CreateEjercicioCallback callback) {
        RequestQueue req = VolleySingleton.getInstance(App.getAppContext()).getRequestQueue();
        StringRequest sr = new StringRequest(Request.Method.POST, url_POST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Respuesta_POST_Ejercicios", response);

                        callback.onEjercicioCreado();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Respuesta_POST_Ejercicios", "Error");

                        callback.onEjercicioCreadoError();                   }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("id",ejercicio.getId());
                params.put("nombre",ejercicio.getNombre());
                params.put("series",ejercicio.getSeries());
                params.put("repeticiones", ejercicio.getRepeticiones());
                params.put("tiempo",ejercicio.getTiempo());
                params.put("tipo",ejercicio.getTipo());
                params.put("rutina",ejercicio.getRutina());


                return params;
            }

        };
        req.add(sr);
    }

    @Override
    public void deleteEjercicio(String id_ejercicio, final DeleteEjercicioCallback callback) {
        RequestQueue req = VolleySingleton.getInstance(App.getAppContext()).getRequestQueue();
        StringRequest sr = new StringRequest(Request.Method.DELETE, url_DELETE+id_ejercicio,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.i("Respuesta_DELET_Ejercs", response);
                        callback.onEjercicioEliminado();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error.
                        Log.i("Respuesta_DELET_Ejercs", "Error");
                        callback.onEjercicioEliminadoError();
                    }
                }
        );
        req.add(sr);
    }

    @Override
    public void deleteEjercicios(String nombre_rutina, final DeleteEjerciciosCallback callback) {
        RequestQueue req = VolleySingleton.getInstance(App.getAppContext()).getRequestQueue();
        StringRequest sr = new StringRequest(Request.Method.DELETE, url_DELETE_porRutina+nombre_rutina,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.i("Respuesta_DELET_EjerR", response);
                        callback.onEjerciciosEliminados();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error.
                        Log.i("Respuesta_DELET_EjerR", "Error");
                        callback.onEjerciciosEliminadosError();
                    }
                }
        );
        req.add(sr);
    }

    @Override
    public void updateEjercicio(final String id_ejercicio, final Ejercicio ejercicio, final UpdateEjercicioCallback callback) {
        RequestQueue req = VolleySingleton.getInstance(App.getAppContext()).getRequestQueue();

        StringRequest putRequest = new StringRequest(Request.Method.PUT, url_PUT+id_ejercicio,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.i("Respuesta_UPDATE_Ejerc", response);
                        callback.onEjercicioActualizado(id_ejercicio);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.i("Respuesta_UPDATE_Ejer","Error");
                        callback.onEjercicioActualizadoError();
                    }
                })
        {

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String> ();
                params.put("nombre", ejercicio.getNombre());
                params.put("series", ejercicio.getSeries());
                params.put("repeticiones", ejercicio.getRepeticiones());
                params.put("tiempo", ejercicio.getTiempo());
                params.put("tipo", ejercicio.getTipo());

                return params;
            }

        };

        req.add(putRequest);

    }

    @Override
    public void getEjercicio(int posicion, CargaEjercicioCallback callback) {

    }
}
