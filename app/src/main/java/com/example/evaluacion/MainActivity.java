package com.example.evaluacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ASK_PERMISSION = 111;
    private RequestQueue queue1;
    private String LoginURL = "http://192.168.252.142:80/api/login";
    //private String RegistroURL = "http://127.0.0.1:8000/api/registro";
    private EditText etemail, etpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue1 = Volley.newRequestQueue(getApplicationContext());

        int permisollamar = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE);

        if (permisollamar == PackageManager.PERMISSION_DENIED){
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                requestPermissions(new String[] {Manifest.permission.CALL_PHONE, Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_ASK_PERMISSION);
                return;
            }
        }

        int permiso = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH);

        if (permiso == PackageManager.PERMISSION_DENIED){
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                requestPermissions(new String[] {Manifest.permission.BLUETOOTH, Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_ASK_PERMISSION);
                return;
            }
        }
    }

    public void mainclick(View view){
        switch (view.getId()){
            case R.id.login:
                //Intent menu = new Intent(getApplicationContext(), MenuActivity.class);
                //startActivity(menu);
                logearse();
                //verificacion();
                break;
            case R.id.registro:
                Intent registro = new Intent(getApplicationContext(), registro.class);
                startActivity(registro);
                break;
        }
    }

    private void logearse() {

        //obtengo info de los editext
        etemail = (EditText)findViewById(R.id.email);
        etpassword = (EditText)findViewById(R.id.password);
        String mail = etemail.getText().toString();
        String pass = etpassword.getText().toString();

        //Validacion del form
        if (mail.length() == 0 || pass.length() == 0){
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show();
        }
        else {
        //envia datos del logeo
            JSONObject datoslogeo = new JSONObject();
            try {
                datoslogeo.put("email", mail);
                datoslogeo.put("password", pass);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            //realiza la peticion post
            JsonObjectRequest enviarlogeo = new JsonObjectRequest(Request.Method.POST, LoginURL, datoslogeo, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    String token = null;
                    try {
                        token = response.getString("token");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();

                    if (token != ""){
                        Intent menu = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(menu);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue1.add(enviarlogeo);
        }
    }

    /*public void verificacion(){
        JsonObjectRequest requesttoken = new JsonObjectRequest(Request.Method.GET, LoginURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String token = response.getString("token");
                    Toast.makeText(MainActivity.this, token.toString(), Toast.LENGTH_SHORT).show();

                    if (token != ""){
                        Intent menu = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(menu);
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
        queue1.add(requesttoken);
    }*/

}