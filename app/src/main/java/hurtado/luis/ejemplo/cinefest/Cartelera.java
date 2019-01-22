package hurtado.luis.ejemplo.cinefest;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class Cartelera extends AppCompatActivity {

    GridView gridCartelera;
    String urlcart = "http://androidwstest.esy.es/peliculas_cinefest_cartelera.php";
    Gson gson;
    AsyncHttpClient client;
    ProgressDialog pd;
    CustomAdapterCartelera cartadap;
    ResponseCartelera responseCartelera;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartelera);

        gridCartelera = (GridView)findViewById(R.id.GridCartelera);

        pd = new ProgressDialog(this);
        pd.setMessage("Cargando Datos");
        pd.setCancelable(false);
        pd.show();

        client = new AsyncHttpClient();
        client.get(Cartelera.this, urlcart, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseSTR = new String(responseBody);


                if (responseBody.length >= 3) {

                    gson = new Gson();
                    responseCartelera = gson.fromJson(responseSTR, ResponseCartelera.class);
                    cartadap = new CustomAdapterCartelera(Cartelera.this, responseCartelera.getCartelera());
                    gridCartelera.setAdapter(cartadap);

                    pd.dismiss();


                } else if (responseBody.length <= 2) {

                    Toast.makeText(getApplicationContext(), "Tamaño: " + responseSTR.length() + " Sin resultados", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                } else if (responseBody.length <= 0) {
                    Toast.makeText(getApplicationContext(), "Error de Servidor", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Toast.makeText(getApplicationContext(), "Verifica tu conexión!", Toast.LENGTH_SHORT).show();
                pd.dismiss();

            }
        });

            //Toast.makeText(Cartelera.this,responseCartelera.getCartelera().get(0).getNombre_esp().toString(),Toast.LENGTH_SHORT).show();


}
}