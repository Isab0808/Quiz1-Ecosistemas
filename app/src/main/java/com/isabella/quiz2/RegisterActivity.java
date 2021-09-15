package com.isabella.quiz2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText etName,etCode;
    Button btnRegister;
    boolean existe=false;


    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etCode = findViewById(R.id.etCode);
        btnRegister = findViewById(R.id.btnToPreparation);
        sharedPreferences = getSharedPreferences("datos",MODE_PRIVATE);


        btnRegister.setOnClickListener(v->{

            String registrosAnteriores = sharedPreferences.getString("codigo", "");

            String nombre = etName.getText().toString();
            String codigo = etCode.getText().toString();

            if(nombre.isEmpty()||codigo.isEmpty()){

                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }


                if (registrosAnteriores != null) {

                    String[] registros;
                    registros = registrosAnteriores.split(",");


                    for (int i = 0; i < registros.length; i++) {

                        if (registros[i].contentEquals(codigo)) {

                            existe = true;
                            Toast.makeText(this, "ya existe un usario con este codigo", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else{


                            existe=false;
                        }

                    }
            }

                // no encuentra ningun usuario registrado con el codigo ingresado
                if(!existe){

                    sharedPreferences.edit().putString("usuario", sharedPreferences.getString("usuario", "") + nombre).apply();
                    sharedPreferences.edit().putString("codigo", sharedPreferences.getString("codigo", "") + codigo + ",").apply();

                    Intent intent = new Intent(this, PreparationActivity.class);
                    startActivity(intent);
                    finish();

                }

        });
    }
}