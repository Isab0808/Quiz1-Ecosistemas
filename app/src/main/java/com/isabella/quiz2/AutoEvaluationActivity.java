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

public class AutoEvaluationActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    SharedPreferences prefs;
    Button btnFinish;
    CheckBox temas,ejercicios,noComprendí;
    int puntos=0;
    boolean checked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_evaluation);

        prefs = getSharedPreferences("datos", MODE_PRIVATE);
        temas= findViewById(R.id.cbTemas);
        ejercicios= findViewById(R.id.cbEjercicios);
        noComprendí= findViewById(R.id.cbNoComprendí);
        temas.setOnCheckedChangeListener(this);
        ejercicios.setOnCheckedChangeListener(this);
        noComprendí.setOnCheckedChangeListener(this);
        btnFinish= findViewById(R.id.btnFinish);
        btnFinish.setBackgroundColor(Color.parseColor("#9b9b9b"));


        btnFinish.setOnClickListener(v->{

            if(checked){

                if(temas.isChecked()){

                    puntos+=3;

                }

                if(ejercicios.isChecked()){

                    puntos+=3;
                }

                //suma puntos de la pantalla anterior con los actuales
                puntos += prefs.getInt("puntos",0);


                prefs.edit().putString("usuario",prefs.getString("usuario","") + " :" + puntos +"\n").apply();


                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            }

            else{

                Toast.makeText(this, "complete todos los campos", Toast.LENGTH_SHORT).show();
            }


        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {



        if(temas.isChecked()||ejercicios.isChecked()||noComprendí.isChecked()){


            checked=true;

            btnFinish.setBackgroundColor(Color.parseColor("#FF3700B3"));
        }

        else{

            btnFinish.setBackgroundColor(Color.parseColor("#9b9b9b"));
        }


        if(noComprendí.isChecked()){

            temas.setChecked(false);
            ejercicios.setChecked(false);
        }

        }
    }
