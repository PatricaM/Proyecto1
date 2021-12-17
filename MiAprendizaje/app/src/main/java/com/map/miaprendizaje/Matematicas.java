package com.map.miaprendizaje;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Matematicas extends AppCompatActivity {

    TextView puntos, contador, vidas, txtcorrecto, txtincorrecto;
    ImageView imagen;
    EditText edit;
    Button btnconfirmar;

    String [] resp_sum={"8","2","6"};
    String [] res_sum2={"8","2","6"};
    String [] imagen_sum={"suma","resta","suma2","suma3"};
    int ipunto=0;
    int ividas=3;
    int numerogenerado=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matematicas);

        txtcorrecto=(TextView)findViewById(R.id.txtcorrecto);
        txtincorrecto=(TextView)findViewById(R.id.txtincorrecto);

        puntos=(TextView)findViewById(R.id.txtpuntos);
        vidas=(TextView)findViewById(R.id.txtvidas);
        contador=(TextView)findViewById(R.id.txtcuenta);
        imagen=(ImageView)findViewById(R.id.imagen1);
        edit=(EditText)findViewById(R.id.txtrespuesta);
        btnconfirmar=(Button)findViewById(R.id.btnconfirmar);

        Establecer_imagen(numerogenerado);

        btnconfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textconfirmar=edit.getText().toString().toLowerCase();
                if(textconfirmar.equals(resp_sum[numerogenerado])|textconfirmar.equals(res_sum2[numerogenerado])){
                    txtcorrecto.setVisibility(View.VISIBLE);
                    ipunto=ipunto+1;
                    puntos.setText("Puntos: " + ipunto);
                    Esperar1();
                }
                else {
                    txtincorrecto.setVisibility(View.VISIBLE);
                    ividas = ividas - 1;
                    vidas.setText("Vidas: " + ividas);
                    Esperar2();
                }
                if(ividas==0){
                    finish();
                }
            }
        });
    }
    void Esperar2(){
        new CountDownTimer(2000,1000) {
            public void onTick(long millisUntilFinished){
                btnconfirmar.setVisibility(View.GONE);

            }
            public void onFinish(){
                btnconfirmar.setVisibility(View.VISIBLE);
                txtincorrecto.setVisibility(View.INVISIBLE);
                edit.setText("");
                edit.setHint("Ingrese una respuesta");
            }
        }.start();
    }
    void Esperar1(){
        new CountDownTimer(4000,1) {
            public void onTick(long millisUntilFinished){
                contador.setText(""+(millisUntilFinished/1000+1));
                btnconfirmar.setVisibility(View.GONE);
            }
            public void onFinish(){
                btnconfirmar.setVisibility(View.VISIBLE);
                numerogenerado=numerogenerado+1;
                contador.setText("");
                Establecer_imagen(numerogenerado);
                txtcorrecto.setVisibility(View.INVISIBLE);
                edit.setText("");
                edit.setHint("Ingrese una respuesta");
            }
        }.start();
    }

    void Establecer_imagen(int numero){
        int id=getResources().getIdentifier(imagen_sum[numero],"mipmap",getPackageName());
        imagen.setImageResource(id);
    }
}
