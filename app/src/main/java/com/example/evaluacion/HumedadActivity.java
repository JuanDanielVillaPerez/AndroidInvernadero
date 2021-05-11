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

public class HumedadActivity extends AppCompatActivity {

    RequestQueue queue;
    private String HumedadURL = "http://192.168.252.142:80/api/humedad/mostrar";
    private TextView txthumedad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humedad);

        queue = Volley.newRequestQueue(getApplicationContext());
    }

    public void clickhumedad(View view){
        switch (view.getId()){
            case R.id.btnhumedad:
                JsonObjectRequest pedirhumedad = new JsonObjectRequest(Request.Method.GET, HumedadURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String humedad = response.getString("value");

                            txthumedad = findViewById(R.id.texthumedad);
                            txthumedad.setText(humedad);
                            txthumedad.append("%");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(pedirhumedad);
                break;
        }
    }
}