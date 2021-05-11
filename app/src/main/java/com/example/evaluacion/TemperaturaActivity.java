package com.example.evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class TemperaturaActivity extends AppCompatActivity {

    RequestQueue queue;
    private String TemperaturaURL = "http://192.168.252.142:80/api/temperatura/mostrar";
    private TextView temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);

        queue = Volley.newRequestQueue(getApplicationContext());
    }

    public void Temperaturaclick(View view){
        switch (view.getId()){
            case R.id.btntemperatura:
                JsonObjectRequest pedirtemp = new JsonObjectRequest(Request.Method.GET, TemperaturaURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String temperatura = response.getString("value");

                            temp = findViewById(R.id.texttemp);
                            temp.setText(temperatura);
                            temp.append("°");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(pedirtemp);

                break;
        }
    }
}