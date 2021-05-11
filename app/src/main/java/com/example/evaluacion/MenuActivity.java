package com.example.evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void clickmenu(View view){
        switch (view.getId()){
            case R.id.mtemp:
                Intent temperatura = new Intent(getApplicationContext(), TemperaturaActivity.class);
                startActivity(temperatura);
                break;
            case R.id.mhumedad:
                Intent humedad = new Intent(getApplicationContext(), HumedadActivity.class);
                startActivity(humedad);
                break;
            case R.id.mcontroles:
                Intent controles = new Intent(getApplicationContext(), ControlesActivity.class);
                startActivity(controles);
                break;
            case R.id.mmovimiento:
                Intent movimiento = new Intent(getApplicationContext(), MovimientoActivity.class);
                startActivity(movimiento);
                break;
        }

    }
}