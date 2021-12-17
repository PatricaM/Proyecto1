package com.map.miaprendizaje;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Animales extends AppCompatActivity {
    TextView txt_puntos, txt_contador, txt_vidas, txt_correcto, txt_incorrecto;
    ImageView imagen2;
    EditText edit2;
    Button btn_confirmar;

    String [] nombre_animls={"Elefante","Leon","Koala","Jirafa","Tortuga","Zorro","Tucan"};
    String [] nombre_animls2={"elefante","leon","koala","jirafa","tortuga","zorro","tucan"};
    String [] imagen_animls={"img1","img2","img3","img4","img5","img6","img7"};
    int i_punto=0;
    int i_vidas=3;
    int numero_generado=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animales);

        txt_correcto = (TextView) findViewById(R.id.txt_correcto);
        txt_incorrecto = (TextView) findViewById(R.id.txt_incorrecto);

        txt_puntos = (TextView) findViewById(R.id.txt_puntos);
        txt_vidas = (TextView) findViewById(R.id.txt_vidas);
        txt_contador = (TextView) findViewById(R.id.txt_cuenta);
        imagen2 = (ImageView) findViewById(R.id.img2);
        edit2 = (EditText) findViewById(R.id.txt_respuesta);
        btn_confirmar = (Button) findViewById(R.id.btn_confirmar);

        Establecer_imagen(numero_generado);

        btn_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textconfirmar = edit2.getText().toString().toLowerCase();
                if (textconfirmar.equals(nombre_animls2[numero_generado]) | textconfirmar.equals(nombre_animls2[numero_generado])) {
                    txt_correcto.setVisibility(View.VISIBLE);
                    i_punto = i_punto + 1;
                    txt_puntos.setText("Puntos: " + i_punto);
                    Esperar1();
                } else {
                    txt_incorrecto.setVisibility(View.VISIBLE);
                    i_vidas = i_vidas - 1;
                    txt_vidas.setText("Vidas: " + i_vidas);
                    Esperar2();
                }
                if (i_vidas == 0) {
                    finish();
                }
            }
        });
    }

    void Esperar2(){
        new CountDownTimer(2000,1) {
            public void onTick(long millisUntilFinished){
                btn_confirmar.setVisibility(View.GONE);

            }
            public void onFinish(){
                btn_confirmar.setVisibility(View.VISIBLE);
                txt_incorrecto.setVisibility(View.INVISIBLE);
                edit2.setText("");
                edit2.setHint("Ingrese una respuesta");
            }
        }.start();
    }
    void Esperar1(){
        new CountDownTimer(4000,1) {
            public void onTick(long millisUntilFinished){
                txt_contador.setText(""+(millisUntilFinished/1000+1));
                btn_confirmar.setVisibility(View.GONE);
            }
            public void onFinish(){
                btn_confirmar.setVisibility(View.VISIBLE);
                numero_generado=numero_generado+1;
                txt_contador.setText("");
                Establecer_imagen(numero_generado);
                txt_correcto.setVisibility(View.INVISIBLE);
                edit2.setText("");
                edit2.setHint("Ingrese una respuesta");
            }
        }.start();
    }
    void Establecer_imagen(int numero){
        int id=getResources().getIdentifier(imagen_animls[numero],"mipmap",getPackageName());
        imagen2.setImageResource(id);
    }
}