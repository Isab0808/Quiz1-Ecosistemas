package com.isabella.quiz2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class PreparationActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private Button btnContinue;

    private CheckBox video, ejemplos, ninguno;
    boolean checked = false;

    int puntos=0;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparation);

        // referenciacion vistas

        video = findViewById(R.id.cbVideos);
        ejemplos = findViewById(R.id.cvEjemplos);
        ninguno = findViewById(R.id.cbNinguna);
        video.setOnCheckedChangeListener(this);
        ejemplos.setOnCheckedChangeListener(this);
        ninguno.setOnCheckedChangeListener(this);
        btnContinue = findViewById(R.id.btnToEvaluation);
        prefs  = getSharedPreferences("datos", MODE_PRIVATE);

        btnContinue.setBackgroundColor(Color.parseColor("#9b9b9b"));

        btnContinue.setOnClickListener(v -> {

            Intent intent = new Intent(this,AutoEvaluationActivity.class);
            if (checked) {

                if (video.isChecked()) {

                    puntos++;
                }

                if (ejemplos.isChecked()) {

                    puntos+=3;
                }
                prefs.edit().putInt("puntos",puntos).apply();
                startActivity(intent);
                finish();

            }

            else{
                Toast.makeText(this,"por favor seleccione una opción",Toast.LENGTH_SHORT).show();
            }


        });


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (video.isChecked() || ejemplos.isChecked() || ninguno.isChecked()) {

            btnContinue.setBackgroundColor(Color.parseColor("#FF3700B3"));
            checked = true;
        } else {

            checked = false;
            btnContinue.setBackgroundColor(Color.parseColor("#9b9b9b"));
        }

        if(buttonView.getId()==R.id.cbNinguna){

            video.setChecked(false);
            ejemplos.setChecked(false);

        }


    }
}