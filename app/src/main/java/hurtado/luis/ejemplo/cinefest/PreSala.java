package hurtado.luis.ejemplo.cinefest;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreSala extends AppCompatActivity {

    //Declaro variables

    TextView txt0, txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, txt10;
    ImageView img1, img2, imgBanner;
    FloatingActionButton f1;
    int entradas = 0;
    float precio = 100;
    float Total_pago = 0;

    int recuenta = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_sala);

        //Instancio

        txt0 = (TextView) findViewById(R.id.movie_name);
        //txt1 = (TextView)findViewById(R.id.T1);
        //txt2 = (TextView)findViewById(R.id.T2);
        txt3 = (TextView) findViewById(R.id.T3);
        txt4 = (TextView) findViewById(R.id.T4);
        txt5 = (TextView) findViewById(R.id.T5);
        txt6 = (TextView) findViewById(R.id.T6);
        txt7 = (TextView) findViewById(R.id.T7);
        txt8 = (TextView) findViewById(R.id.T8);
        txt9 = (TextView) findViewById(R.id.T9);
        img1 = (ImageView) findViewById(R.id.btnleft);
        img2 = (ImageView) findViewById(R.id.btnright);
        txt10 = (TextView) findViewById(R.id.contador);
        imgBanner = (ImageView) findViewById(R.id.BannerPelicula);
        f1 = (FloatingActionButton)findViewById(R.id.Flotante1);

        //Recibo datos de la actividad anterior Funciones (Adaptador - HorizontalSubItemFecha.class

        Intent i = this.getIntent();

        final String horas = i.getExtras().getString("ihoras");
        final String asientos_ocupados = i.getExtras().getString("iasientos_ocupados");
        final String cine = i.getExtras().getString("icine");
        final String id_funcion = i.getExtras().getString("iid_funcion");
        final String fecha = i.getExtras().getString("ifecha");
        final String sala = i.getExtras().getString("isala");
        final String id_hora_sillas = i.getExtras().getString("iid_hora_sillas");

        //Creo la lista de las sillas que estan ocupadas.


        final List<String> Entradas = new ArrayList<String>(Arrays.asList(asientos_ocupados.split(",")));

        /*
       ** Estaba probando que estubiera funcionando el i.get **

        txt0.setText(Funciones.identificador.toString());
        txt1.setText(Funciones.titulo.getText().toString());
        txt2.setText(Funciones.img_url);
        txt3.setText(cine);
        txt4.setText(fecha);
        txt5.setText(horas);
        txt6.setText(asientos_ocupados);
        txt7.setText(id_hora_sillas);
        txt8.setText(id_funcion);
        txt9.setText(sala);
        */

        // Cargo la imagen de la pelicula

        Picasso.with(getApplicationContext()).load(Funciones.img_url.toString()).resize(200, 280).into(imgBanner);

        //Cargo el nombre de la pelicula

        txt0.setText(Funciones.titulo.getText());

        // Declaro la cantidad de sillas por sala;

        int sala_M1 = 31;
        int sala_M2 = 58;
        int sala_M3 = 67;
        int sala_CV1 = 36;
        int sala_CV2 = 58;
        int sala_CV3 = 67;


        // Cargo los nombre del cine + fecha + hora + sala donde fue seleccionada la funcion.

        txt3.setText("Cine "+cine);
        txt4.setText(fecha);
        txt5.setText(horas);
        txt9.setText("Sala "+sala);


        // Declaro la variable que me calcula la cantidad de sillas disponibles, es solo una resta :D



        if (sala.equals("M1")){recuenta = sala_M1 - Entradas.size();}
        else if (sala.equals("M2")){recuenta = sala_M2 - Entradas.size();}
        else if (sala.equals("M3")){recuenta = sala_M3 - Entradas.size();}
        else if (sala.equals("CV1")){recuenta = sala_CV1 - Entradas.size();}
        else if (sala.equals("CV2")){recuenta = sala_CV2 - Entradas.size();}
        else if (sala.equals("CV3")){recuenta = sala_CV3 - Entradas.size();}

        //Le muestro al usuario las sillas que quedan disponibles


        txt6.setText("Sillas Disponibles: "+recuenta);


        //Restar Contador, Control izquierdo para restar las entradas que desea el usuario

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (entradas > 0) {

                    entradas = entradas - 1;
                    Total_pago = precio*entradas;
                    txt10.setText(String.valueOf(entradas));

                    if((Total_pago) == 0){
                        txt8.setText("Total a Pagar: -");
                    }
                    txt8.setText("Total a Pagar: " + String.valueOf(Total_pago) + " BsS.");


                }else{
                    Toast.makeText(PreSala.this,"0",Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Sumar Contador, Control Derecho para sumar las entradas que desea el usuario

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (entradas < 8 && entradas < recuenta) {
                    
                    entradas = entradas + 1;
                    Total_pago = precio*entradas;
                    txt10.setText(String.valueOf(entradas));

                }

                if((Total_pago) == 0){
                    txt8.setText("Total a Pagar: -");
                }
                txt8.setText("Total a Pagar: " + String.valueOf(Total_pago) + " BsS.");

            }
        });


        // Boton flotante para mandar los datos a la otra actividad, primero verifico que se tenga
        // seleccionada al menos 1 entrada

        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(entradas != 0){
                    Toast.makeText(PreSala.this,"Se va a la otra actividad",Toast.LENGTH_SHORT).show();

                    //TODO: Vamos a la proxima actividad

                    openDeailActivity(horas,asientos_ocupados,cine, id_funcion, fecha, sala, id_hora_sillas, entradas, Total_pago);

                }
            }
        });


    }

    private void openDeailActivity(String ihoras, String iasientos_ocupados, String icine,
                                   String iid_funcion, String ifecha, String isala,
                                   String iid_hora_sillas, int cant_sillas, float Total_a_pagar) {

        Intent i = new Intent(PreSala.this, Sala.class);
        i.putExtra("ihoras", ihoras);
        i.putExtra("iasientos_ocupados", iasientos_ocupados);
        i.putExtra("icine", icine);
        i.putExtra("iid_funcion", iid_funcion);
        i.putExtra("ifecha", ifecha);
        i.putExtra("isala", isala);
        i.putExtra("iid_hora_sillas", iid_hora_sillas);
        i.putExtra("cant_sillas", cant_sillas);
        i.putExtra("Total_a_pagar",Total_a_pagar);
        PreSala.this.startActivity(i);
    }
}
