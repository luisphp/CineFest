package hurtado.luis.ejemplo.cinefest;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class Proximamente extends AppCompatActivity {

    ListView listview;
    ResponseProximamente responseProximamenteOBJ;
    CustomAdapterProximamente adapter;
    String url = "http://androidwstest.esy.es/peliculas_cinefest.php";
    Gson gson;
    AsyncHttpClient client;
    ProgressDialog pd;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratorio);

        listview = (ListView)findViewById(R.id.myListView);

        pd = new ProgressDialog(this);
        pd.setMessage("Cargando Datos");
        pd.setCancelable(false);
        pd.show();

        client = new AsyncHttpClient();
        client.get(Proximamente.this, url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
               String  responseSTR = new String(responseBody);



                if (responseBody.length >= 3) {

                    gson = new Gson();
                    responseProximamenteOBJ = gson.fromJson(responseSTR, ResponseProximamente.class);
                    adapter = new CustomAdapterProximamente(Proximamente.this, responseProximamenteOBJ.getEstrenos());
                    listview.setAdapter(adapter);

                    pd.dismiss();


                }
                else if(responseBody.length <= 2){

                    Toast.makeText(getApplicationContext(), "Tamaño: " + responseSTR.length() + " Sin resultados", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }

                else if (responseBody.length <= 0){
                    Toast.makeText(getApplicationContext(), "Error de Servidor", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            String responseDENIED = new String(responseBody);

                Toast.makeText(getApplicationContext(),"Sin conexión a internet"+responseDENIED.length(), Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

        /*
        listview.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.getItem(i);

                Toast.makeText(getApplicationContext(), "Nombre de pelicula: ",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        */
    }
}
