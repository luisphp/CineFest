package hurtado.luis.ejemplo.cinefest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import java.net.URLEncoder;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Funciones extends AppCompatActivity {

    public static TextView titulo;
    public static TextView cambio;
    public static String date = "";
    public static String img_url = "";



    ImageView img;
    String url = "http://androidwstest.esy.es/funciones_test.php?id_p=";
    String url2 = "http://androidwstest.esy.es/funciones_c.php?id_p=";
    Gson gson, gsono;
    AsyncHttpClient client;
    ProgressDialog pd;
    ResponseFunciones responseF;
    ListView funciones;
    CustomAdapterFunciones adapterF;
    ResponseFunciones respuestadelF;
    ResponseFuncionesTest testor;
    RecyclerView Fechas;
    public static String identificador = "";
    HorizontalSubItemFecha adapterFecha;
    ResponseFechas responseFechas;

    public static String id_de_pelicula ="";







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funciones);

        Intent i = this.getIntent();

        String id = i.getExtras().getString("id");
        String Nombre_español = i.getExtras().getString("Nombre_español");
        String Sinopsis = i.getExtras().getString("Sinopsis");
        String Duracion = i.getExtras().getString("Duracion");
        String Reparto = i.getExtras().getString("Reparto");
        String Nombre_original = i.getExtras().getString("Nombre_original");
        String Fecha_estreno = i.getExtras().getString("Fecha_estreno");
        String Poster = i.getExtras().getString("Poster");

        //list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //list.setAdapter(new HorizontalSubItemAdapter(new String[]{"Android","C4","JAVA"}));

        identificador = id.toString();
        img_url = Poster.toString();

        id_de_pelicula = id;


        titulo = (TextView) findViewById(R.id.NombrePelicula);
        funciones = (ListView) findViewById(R.id.Funciones);
        img = (ImageView) findViewById(R.id.BannerPelicula);
        titulo.setText(Nombre_español);
        Fechas = (RecyclerView) findViewById(R.id.dates);
        cambio = (TextView) findViewById(R.id.Refresh);



        Picasso.with(getApplicationContext()).load(Poster).resize(200, 280).into(img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // DescargarFunciones();
            }
        });

        titulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DescargarFechas();
            }
        });

        cambio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //DescargarFunciones();

            }

            @Override
            public void afterTextChanged(Editable editable) {
                DescargarFunciones();

            }
        });

        DescargarFechas();



    }

    public void DescargarFechas() {

        pd = new ProgressDialog(this);
        pd.setMessage("Cargando Datos");
        pd.setCancelable(false);
        pd.show();

        client = new AsyncHttpClient();
        client.get(Funciones.this, url2 + identificador, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String responseSTRfunciones = new String(responseBody);

                if (responseBody.length >= 3) {

                    gson = new Gson();
                    responseFechas = gson.fromJson(responseSTRfunciones, ResponseFechas.class);

                    Fechas.setLayoutManager(new LinearLayoutManager(Funciones.this, LinearLayoutManager.HORIZONTAL, false));
                    HorizontalSubItemFecha adaptersub;

                    int alfa = 0;
                    ArrayList<String> fechitas = new ArrayList<String>();

                    while (alfa< responseFechas.getFechas().size()){

                        fechitas.add(responseFechas.getFechas().get(alfa).getFecha());

                        alfa = alfa+1;

                    }




                    adaptersub = new HorizontalSubItemFecha(Funciones.this, fechitas);
                    Fechas.setAdapter(adaptersub);
                    pd.dismiss();


                } else if (responseBody.length <= 2) {

                    Toast.makeText(getApplicationContext(), "Error " + responseSTRfunciones.length() + "Sin resultados", Toast.LENGTH_SHORT).show();
                    pd.dismiss();

                } else if (responseBody.length <= 0) {

                    Toast.makeText(getApplicationContext(), "Error 404! - Not Found", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(Funciones.this, "Verifique su conexión", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

    }

    public void DescargarFunciones() {

        pd = new ProgressDialog(this);
        pd.setMessage("Cargando Datos");
        pd.setCancelable(false);
        pd.show();

        String fecha = cambio.getText().toString();


        client = new AsyncHttpClient();
        client.get(Funciones.this, url + identificador+"&fecha_f="+fecha.replaceAll(" ","%20") , new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String responseSTRfunciones = new String(responseBody);

                if (responseBody.length >= 3) {

                    gsono = new Gson();
                    responseF = gsono.fromJson(responseSTRfunciones, ResponseFunciones.class);
                    adapterF = new CustomAdapterFunciones(Funciones.this, responseF.getPlanificacion());
                    funciones.setAdapter(adapterF);


                     /*
                        test.setText(responseF.getPlanificacion().get(0).
                        getFunciones().get(0).
                        getHorario_sillas().get(0).
                        getAsientos_oc());
                    */

                    //Se deja en blanco la variable donde se selecciona la fecha por si la persona selecciona otras fechas

                    date = "";



                    pd.dismiss();

                } else if (responseBody.length <= 2) {

                    Toast.makeText(getApplicationContext(), "Error " + responseSTRfunciones.length() + "Sin resultados", Toast.LENGTH_SHORT).show();
                    pd.dismiss();

                } else if (responseBody.length <= 0) {

                    Toast.makeText(getApplicationContext(), "Error 404! - Not Found", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(Funciones.this, "Verifique su conexión", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

    }

}
