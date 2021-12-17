package com.map.miaprendizaje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button inicio;
    ImageButton confi;
    MediaPlayer mp;
    TextView et_nombre;
    AdminSQLiteOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_nombre=(TextView)findViewById(R.id.txt_nombre);
        inicio=findViewById(R.id.btn_iniciar);

        db = new AdminSQLiteOpenHelper(this);



        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Niveles.class);
                startActivity(intent);
            }
        });

        confi=findViewById(R.id.IBconfi);

        confi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Perfil.class);
                startActivity(intent);
            }
        });

        mp = MediaPlayer.create(this, R.raw.goats);
        mp.start();
        mp.setLooping(true);

    }
    public void iniciar(View view){
        String nombre = et_nombre.getText().toString();

        Integer cont=5;

        Usuario obj=new Usuario(cont, nombre,null);

        if(!nombre.equals("")){
            mp.stop();
            mp.release();

            Intent intent = new Intent(this, Niveles.class);

            intent.putExtra("jugador", nombre);
            startActivity(intent);
            finish();
        }else{
            et_nombre.requestFocus();
            InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(et_nombre, InputMethodManager.SHOW_IMPLICIT);
        }
    }
}