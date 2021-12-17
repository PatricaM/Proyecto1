package com.map.miaprendizaje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Niveles extends AppCompatActivity {

    Button animales, niveles, matematicas, colores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveles);

        animales=findViewById(R.id.btn_animales);
        animales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Niveles.this, Animales.class);
                startActivity(intent);
            }
        });
        niveles=findViewById(R.id.btn_abecedario);
        niveles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Niveles.this, Abecedario.class);
                startActivity(intent);
            }
        });
        matematicas=findViewById(R.id.btn_Matematicas);
        matematicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Niveles.this, Matematicas.class);
                startActivity(intent);
            }
        });
        colores=findViewById(R.id.btn_colores);
        colores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Niveles.this, Colores.class);
                startActivity(intent);
            }
        });
    }
}