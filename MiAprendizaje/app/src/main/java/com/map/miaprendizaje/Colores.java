package com.map.miaprendizaje;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Colores extends AppCompatActivity {

    Button confirmar;
    TextView correcto, incorrecto, vidas, contador, puntos;
    EditText respuesta;
    ImageView img3;

    String [] color={"Azul","Rojo","Rosa","Verde","Amarillo","Negro","Naranja"};
    String [] color2={"azul","rojo","rosa","verde","Amarillo","negro","naranja"};
    String [] imagen={"cl1","cl2","cl3","cl4","cl5","cl6","cl7"};
    int i_punto=0;
    int i_vidas=3;
    int numero_generado=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colores);

        correcto = (TextView) findViewById(R.id.tv_correcto);
        incorrecto = (TextView) findViewById(R.id.tv_incorrecto);

        puntos = (TextView) findViewById(R.id.tv_puntos);
        vidas = (TextView) findViewById(R.id.tv_vidas);
        contador = (TextView) findViewById(R.id.tv_cuenta);
        img3 = (ImageView) findViewById(R.id.imagen3);
        respuesta = (EditText) findViewById(R.id.txt_re_puesta);
        confirmar = (Button) findViewById(R.id.btn_con_firmar);

        Establecer_imagen(numero_generado);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textconfirmar = respuesta.getText().toString().toLowerCase();
                if (textconfirmar.equals(color2[numero_generado]) | textconfirmar.equals(color[numero_generado])) {
                    correcto.setVisibility(View.VISIBLE);
                    i_punto = i_punto + 1;
                    puntos.setText("Puntos: " + i_punto);
                    Esperar1();
                } else {
                    incorrecto.setVisibility(View.VISIBLE);
                    i_vidas = i_vidas - 1;
                    vidas.setText("Vidas: " + i_vidas);
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
                confirmar.setVisibility(View.GONE);
            }
            public void onFinish(){
                confirmar.setVisibility(View.VISIBLE);
                incorrecto.setVisibility(View.INVISIBLE);
                respuesta.setText("");
                respuesta.setHint("Ingrese una respuesta");
            }
        }.start();
    }
    void Esperar1(){
        new CountDownTimer(4000,1) {
            public void onTick(long millisUntilFinished){
                contador.setText(""+(millisUntilFinished/1000+1));
                confirmar.setVisibility(View.GONE);
            }
            public void onFinish(){
                confirmar.setVisibility(View.VISIBLE);
                numero_generado=numero_generado+1;
                contador.setText("");
                Establecer_imagen(numero_generado);
                correcto.setVisibility(View.INVISIBLE);
                respuesta.setText("");
                respuesta.setHint("Ingrese una respuesta");
            }
        }.start();
    }
    void Establecer_imagen(int numero){
        int id=getResources().getIdentifier(imagen[numero],"mipmap",getPackageName());
        img3.setImageResource(id);
    }
}
