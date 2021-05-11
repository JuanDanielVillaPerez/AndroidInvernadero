package com.example.evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class registro extends AppCompatActivity {

    private RequestQueue cola;
    private String RegistroURL = "http://192.168.252.142:80/api/registro";
    //private String PruebaURL = "http://192.168.0.14:80/api/prueba";
    private EditText etname,etedad,etmail,etpass;
    private String name, edad, correo,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        cola = Volley.newRequestQueue(getApplicationContext());
    }

    public void clickregistro(View view){
        switch (view.getId()){
            case R.id.registrar:

                Registro();
                Toast.makeText(this, "Registro hecho, verifique su correo", Toast.LENGTH_SHORT).show();
                Intent login = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(login);
                break;
        }
    }

    public void Registro(){

        etname = (EditText)findViewById(R.id.name);
        etedad = (EditText)findViewById(R.id.edad);
        etmail = (EditText)findViewById(R.id.correo);
        etpass = (EditText)findViewById(R.id.contra);
        name = etname.getText().toString();
        edad = etedad.getText().toString();
        correo = etmail.getText().toString();
        pass = etpass.getText().toString();

        if(name.length() == 0 || edad.length()==0 || correo.length()==0 || pass.length()==0){
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show();
        }
        else {

            JSONObject registrodatos = new JSONObject();
            try {
                registrodatos.put("name",name);
                registrodatos.put("age",edad);
                registrodatos.put("email",correo);
                registrodatos.put("password",pass);
            }catch (JSONException e){
                e.printStackTrace();
            }

            JsonObjectRequest enviar_registro = new JsonObjectRequest(Request.Method.POST, RegistroURL, registrodatos, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(registro.this, "Datos enviados", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            cola.add(enviar_registro);
        }
    }
}