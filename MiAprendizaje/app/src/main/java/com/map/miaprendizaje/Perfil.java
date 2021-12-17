package com.map.miaprendizaje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.FocusFinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Perfil extends AppCompatActivity {

    Button btnregresar, btnguardar;
    ImageView imagen;
    AdminSQLiteOpenHelper db;
    EditText txtnombre, edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        txtnombre=(EditText)findViewById(R.id.txt_nom);
        edad=(EditText)findViewById(R.id.txt_edad);
        btnguardar=(Button)findViewById(R.id.btn_guardar);

        db = new AdminSQLiteOpenHelper(this);



        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = txtnombre.getText().toString();
                String age = edad.getText().toString();
                Integer cont = 5;

                Usuario obj = new Usuario(cont, nom, age);
                if (nom.equals("") || age.equals("")) {
                    Toast.makeText(Perfil.this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    db.addUser(obj);
                    cont++;

                    Toast.makeText(Perfil.this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                }
                txtnombre.setText(nom);
            }
        });

        btnregresar=findViewById(R.id.btn_regresar);
        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Perfil.this, MainActivity.class);
                startActivity(intent);
            }
        });
        imagen=(ImageView) findViewById(R.id.imagemId);

    }


    public void cargarImagen(){
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la aplicación"),10);
    }
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri path=data.getData();
            imagen.setImageURI(path);
        }
    }

    public void onclick(View view) {
        cargarImagen();
    }
}