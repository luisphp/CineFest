package hurtado.luis.ejemplo.cinefest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.support.v4.content.IntentCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cz.msebera.android.httpclient.Header;

public class Index extends AppCompatActivity {


    TextView salto1, login, saludo, cartelera_cine, misEntradas, miInformacion;
    RelativeLayout relativo;

    public static String nombre, apellido, correo_auth, contrasena, pregunta_secreta, respuesta_secreta;

    public static int id, edad, cedula;

    public static boolean sesion;

    private ViewPager viewPager;

    private String urlimages = "http://androidwstest.esy.es/peliculas_cienfest_index_images.php";

    private Gson gson;

    private AsyncHttpClient client;

    ViewPagerResponse viewPagerResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        salto1 = (TextView) findViewById(R.id.Salto1);
        cartelera_cine = (TextView) findViewById(R.id.cartelera);
        login = (TextView) findViewById(R.id.Login);
        saludo = (TextView) findViewById(R.id.Usuario);
        relativo = (RelativeLayout) findViewById(R.id.RL);

        misEntradas = (TextView) findViewById(R.id.MisEntradas);
        miInformacion = (TextView)findViewById(R.id.MiInformación);
        viewPager = (ViewPager) findViewById(R.id.ViewPagerIndex);


        if (sesion == true) {
            Intent i = this.getIntent();

            if(!i.hasExtra("id_l")){

            }else {


                id = Integer.parseInt(i.getExtras().getString("id_l"));
                nombre = i.getExtras().getString("nombre_l");
                apellido = i.getExtras().getString("apellido_l");
                contrasena = i.getExtras().getString("contrasena_l");
                correo_auth = i.getExtras().getString("correo_auth_l");
                pregunta_secreta = i.getExtras().getString("pregunta_secreta_l");
                respuesta_secreta = i.getExtras().getString("respuesta_secreta_l");
                cedula = Integer.parseInt(i.getExtras().getString("cedula_l"));
                edad = Integer.parseInt(i.getExtras().getString("edad_l"));

                saludo.setText("Hola " + nombre);

                //Textview
            }
        } else {

            saludo.setText("");

        }


        salto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento_0 = new Intent(Index.this, Proximamente.class);
                startActivity(intento_0);
            }
        });

        cartelera_cine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento_0 = new Intent(Index.this, Cartelera.class);
                startActivity(intento_0);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (saludo.getText() != "") {

                    createSimpleDialog().show();


                } else {
                    Intent intento_1 = new Intent(Index.this, Login_usuario.class);
                    startActivity(intento_1);
                }

            }
        });

        misEntradas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (sesion == false) {

                    Intent intento_1 = new Intent(Index.this, Login_usuario.class);
                    startActivity(intento_1);

                } else {

                    Intent intento_1 = new Intent(Index.this, MisEntradas.class);
                    startActivity(intento_1);

                }


            }
        });

        miInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (sesion == false) {

                    Intent intento_1 = new Intent(Index.this, Login_usuario.class);
                    startActivity(intento_1);

                } else {

                    Intent intento_1 = new Intent(Index.this, MiInformacion.class);
                    startActivity(intento_1);

                }

            }
        });


        client = new AsyncHttpClient();
        client.get(Index.this, urlimages, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseSTR = new String(responseBody);


                if (responseBody.length >= 3) {

                    gson = new Gson();
                    viewPagerResponse = gson.fromJson(responseSTR, ViewPagerResponse.class);
                    ViewPagerAdapter viewAdapter = new ViewPagerAdapter(Index.this, viewPagerResponse.getImagenes());
                    viewPager.setAdapter(viewAdapter);


                } else if (responseBody.length <= 2) {

                    Toast.makeText(getApplicationContext(), "Tamaño: " + responseSTR.length() + " Sin resultados", Toast.LENGTH_SHORT).show();
                    viewPager.setBackgroundResource(R.drawable.cinefest_logo);

                } else if (responseBody.length <= 0) {

                    Toast.makeText(getApplicationContext(), "Error de Servidor", Toast.LENGTH_SHORT).show();
                    viewPager.setBackgroundResource(R.drawable.cinefest_logo);

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

               // Toast.makeText(getApplicationContext(), "Verifica tu conexión!", Toast.LENGTH_SHORT).show();




            }
        });

        //Toast.makeText(getApplicationContext(), viewPagerResponse.getImagenes().size(), Toast.LENGTH_SHORT).show();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTS(),2000,4000);

    }

    public AlertDialog createSimpleDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Index.this);
        builder.setMessage("Ya iniciaste sesión, ¿desea ingresar con otra cuenta?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        nombre = "";
                        sesion = false;
                        contrasena = "";
                        apellido = "";
                        correo_auth = "";
                        pregunta_secreta = "";
                        respuesta_secreta = "";

                        id = 0;
                        edad = 0;
                        cedula = 0;

                        saludo.setText("");


                        Intent intento_1 = new Intent(Index.this, Login_usuario.class);
                        startActivity(intento_1);
                    }

                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        Toast.makeText(Index.this, "Entendido, conservamos tus datos!", Toast.LENGTH_SHORT).show();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }



    @Override
    public void onBackPressed() {

        /*
        new android.support.v7.app.AlertDialog.Builder(this)
                .setMessage("¿Desea salir?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(a);
                    }
                }).create().show();

                */


        MensajeSalida();

    }

    private AlertDialog MensajeSalida() {

        AlertDialog.Builder mBuilder  = new AlertDialog.Builder(Index.this);

        View Myview = getLayoutInflater().inflate(R.layout.dialog_salida, null);



        Button salir = (Button) Myview.findViewById(R.id.Salir);
        Button quedarme = (Button) Myview.findViewById(R.id.Quedarme);


        mBuilder.setView(Myview);

        final AlertDialog dialog = mBuilder.create();
        dialog.show();


        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();

                sesion = false;

                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);



            }
        });

        quedarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();

            }
        });

        return mBuilder.create();
    }

    public class  TimerTS extends TimerTask{

        @Override
        public void run() {
            Index.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    }else if (viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(2);
                    }else if (viewPager.getCurrentItem() == 2){
                        viewPager.setCurrentItem(3);
                    }else if (viewPager.getCurrentItem() == 3){
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }


}
