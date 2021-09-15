package com.isabella.quiz2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnRegister;
    private TextView tvEstudiantes;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("datos",MODE_PRIVATE);
        btnRegister = findViewById(R.id.registerBtn);
        tvEstudiantes = findViewById(R.id.tvEstudiantes);

        MostrarRegistros();
        btnRegister.setOnClickListener(v-> {

            Intent intent = new Intent(this,RegisterActivity.class);
            startActivity(intent);
            finish();
        });


    }

    public void MostrarRegistros(){

        String registros = sharedPreferences.getString("usuario","");

        tvEstudiantes.setText(registros);

    }

}