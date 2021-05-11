package com.example.evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ControlesActivity extends AppCompatActivity {

    private String FocoURL = "http://192.168.252.142:80/api/foco/actualizar";
    private String BombaURL = "http://192.168.252.142:80/api/bomba/actualizar";
    private String VentiladorURL = "http://192.168.252.142:80/api/ventilador/actualizar";
    private String FococheckURL = "http://192.168.252.142:80/api/foco/mostrar";
    private String BombacheckURL = "http://192.168.252.142:80/api/bomba/mostrar";
    private String VentiladorcheckURL = "http://192.168.252.142:80/api/ventilador/mostrar";
    private Switch focos,bomba,ventilador;
    private RequestQueue queue,queue1,queue2,queue3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controles);
        queue = Volley.newRequestQueue(getApplicationContext());
        queue1 = Volley.newRequestQueue(getApplicationContext());
        queue2 = Volley.newRequestQueue(getApplicationContext());
        queue3 = Volley.newRequestQueue(getApplicationContext());
        focos = findViewById(R.id.swfocos);
        ventilador = findViewById(R.id.swventilador);
        bomba = findViewById(R.id.swbomba);


    }

    public void clickcontroles(View view){
        switch (view.getId()){
            case R.id.swfocos:
                //focos = (Switch)findViewById(R.id.swfocos);
                if (focos.isChecked()){
                    //Toast.makeText(this, "Foco encendido", Toast.LENGTH_SHORT).show();
                    JSONObject Prenderfoco = new JSONObject();
                    try {
                        Prenderfoco.put("value","ON");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest enviaron = new JsonObjectRequest(Request.Method.POST, FocoURL, Prenderfoco, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(ControlesActivity.this, "Foco Encendido", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(enviaron);
                }
                else {
                    JSONObject apagarfoco = new JSONObject();
                    try {
                        apagarfoco.put("value","OFF");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest enviaroff = new JsonObjectRequest(Request.Method.POST, FocoURL, apagarfoco, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(ControlesActivity.this, "Foco Apagado", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(enviaroff);
                }
                break;
            case R.id.swbomba:
                //bomba = (Switch)findViewById(R.id.swbomba);
                if (bomba.isChecked()){
                    JSONObject Prenderbomba = new JSONObject();
                    try {
                        Prenderbomba.put("value","ON");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest enviaron = new JsonObjectRequest(Request.Method.POST, BombaURL, Prenderbomba, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(ControlesActivity.this, "Bomba Encendida", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(enviaron);
                }
                else {
                    JSONObject apagarbomba = new JSONObject();
                    try {
                        apagarbomba.put("value","OFF");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest enviaroff = new JsonObjectRequest(Request.Method.POST, BombaURL, apagarbomba, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(ControlesActivity.this, "Bomba Apagada", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(enviaroff);
                }
                break;
            case R.id.swventilador:
                //ventilador = (Switch)findViewById(R.id.swventilador);
                if (ventilador.isChecked()){
                    JSONObject Prenderventilador = new JSONObject();
                    try {
                        Prenderventilador.put("value","ON");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest enviaron = new JsonObjectRequest(Request.Method.POST, VentiladorURL, Prenderventilador, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(ControlesActivity.this, "Ventilador Encendido", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(enviaron);
                }
                else {
                    JSONObject apagarventilador = new JSONObject();
                    try {
                        apagarventilador.put("value","OFF");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest enviaroff = new JsonObjectRequest(Request.Method.POST, VentiladorURL, apagarventilador, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(ControlesActivity.this, "Ventilador Apagado", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(enviaroff);
                }
                break;
            case R.id.btnactualizar:
                JsonObjectRequest estadofoco = new JsonObjectRequest(Request.Method.GET, FococheckURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String valorfoco = response.getString("value");
                            if (valorfoco == "ON"){
                                focos.setChecked(true);
                            }else if(valorfoco == "OFF"){
                                focos.setChecked(false);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(estadofoco);

                JsonObjectRequest estadobomba = new JsonObjectRequest(Request.Method.GET, BombacheckURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String valorbomba = response.getString("value");
                            if (valorbomba == "ON"){
                                focos.setChecked(true);
                            }else if(valorbomba == "OFF"){
                                focos.setChecked(false);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(estadobomba);

                JsonObjectRequest estadovent = new JsonObjectRequest(Request.Method.GET, VentiladorcheckURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String valorvent = response.getString("value");
                            if (valorvent == "ON"){
                                focos.setChecked(true);
                            }else if(valorvent == "OFF"){
                                focos.setChecked(false);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(estadovent);

                Toast.makeText(this, "Actualizado", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}