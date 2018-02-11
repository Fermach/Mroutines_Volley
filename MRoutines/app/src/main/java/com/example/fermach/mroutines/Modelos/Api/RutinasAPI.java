package com.example.fermach.mroutines.Modelos.Api;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.fermach.mroutines.App;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;
import com.example.fermach.mroutines.Modelos.Rutina.RutinasDataSource;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
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

public class RutinasAPI implements RutinasDataSource{


    /*
    Rutas para acceder a mi servidor AWS con la base de datos MongoDB
     */
    String url_GET="http://ec2-18-219-167-230.us-east-2.compute.amazonaws.com:5000/rutinas";
    String url_POST="http://ec2-18-219-167-230.us-east-2.compute.amazonaws.com:5000/rutinas";
    String url_PUT="http://ec2-18-219-167-230.us-east-2.compute.amazonaws.com:5000/rutinas/nombre/";
    String url_DELETE="http://ec2-18-219-167-230.us-east-2.compute.amazonaws.com:5000/rutinas/nombre/";

    public RutinasAPI() {

    }


    /**
     * @param callback
     */
    @Override
    public void getRutinas(final CargaRutinasCallback callback) {
       Log.i("RutinasAPI", "RUTINASAPI");
       RequestQueue req = VolleySingleton.getInstance(App.getAppContext()).getRequestQueue();
       StringRequest stringRequest=new StringRequest(Request.Method.GET, url_GET,
               new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       Log.i("Respuesta_GET_Rutinas", response);

                       //parseamos las rutinas obtenidas json
                       List<Rutina> rutinas = new Gson().fromJson(
                               new StringReader(response),
                               new TypeToken<List<Rutina>>() {
                               }.getType());


                       //Llamo al callback para pasar la lista de usuarios
                       callback.onRutinasCargadas(rutinas);
                   }
               },
               new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                        callback.onRutinasCargadasError();
                       Log.i("RutinasAPI", "ERROR Respuesta");
                   }
               });

       req.add(stringRequest);
    }

    /**
     *
     * @param rutina
     * @param callback
     */
    @Override
    public void createRutina(final Rutina rutina, final CreateRutinaCallback callback) {
        RequestQueue req = VolleySingleton.getInstance(App.getAppContext()).getRequestQueue();
        StringRequest sr = new StringRequest(Request.Method.POST, url_POST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Respuesta_POST_Rutinas", response);

                        callback.onRutinaCreada();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Respuesta_POST_Rutinas", "Erroe");

                        callback.onRutinaCreadaError();                    }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("nombre",rutina.getNombre());
                params.put("tipo",rutina.getTipo());
                params.put("nivel", rutina.getNivel());

                return params;
            }

        };
       req.add(sr);
    }


    /**
     *
     * @param rutina_nombre
     * @param callback
     */
    @Override
    public void deleteRutina(String rutina_nombre, final DeleteRutinaCallback callback) {
        RequestQueue req = VolleySingleton.getInstance(App.getAppContext()).getRequestQueue();
        StringRequest sr = new StringRequest(Request.Method.DELETE, url_DELETE+rutina_nombre,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.i("Respuesta_DELET_Rutinas", response);
                        callback.onRutinaEliminada();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error.
                        Log.i("Respuesta_DELET_Rutinas", "Error");
                        callback.onRutinaEliminadaError();
                    }
                }
        );
        req.add(sr);
    }

    /**
     *
     * @param rutina_nombre
     * @param rutina
     * @param callback
     */
    @Override
    public void updateRutina(final String rutina_nombre, final Rutina rutina, final UpdateRutinaCallback callback) {
        RequestQueue req = VolleySingleton.getInstance(App.getAppContext()).getRequestQueue();

        StringRequest putRequest = new StringRequest(Request.Method.PUT, url_PUT+rutina_nombre,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.i("Respuesta_UPDATE_Rutinas", response);
                        callback.onRutinaActualizada(rutina_nombre);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.i("Respuesta_UPDATE_Rutinas","Error");
                        callback.onRutinaActualizadaError();
                    }
                })
        {

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String> ();
                params.put("tipo", rutina.getTipo());
                params.put("nivel", rutina.getNivel());

                return params;
            }

        };

        req.add(putRequest);
    }


    /**
     *
     * @param posicion
     * @param callback
     */
    @Override
    public void getRutina(int posicion, CargaRutinaCallback callback) {

    }
}
