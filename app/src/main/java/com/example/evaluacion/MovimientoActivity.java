package com.example.evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MovimientoActivity extends AppCompatActivity {

    private String MovientoURL = "https://io.adafruit.com/api/v2/jairalejandroutt/feeds/proyecto-iotoficial.sensorpir/data/last?x-aio-key=aio_SmOv725aGOdDE9r69PN6Krktpspj";
    private TextView movimiento;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento);

        queue = Volley.newRequestQueue(getApplicationContext());
    }

    public void clickmov(View view){
        switch (view.getId()){
            case R.id.btnmov:
                JsonObjectRequest movinfo = new JsonObjectRequest(Request.Method.GET, MovientoURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String mov = response.getString("value");

                            movimiento = findViewById(R.id.textmov);
                            movimiento.setText(mov);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(movinfo);
                break;
        }
    }
}