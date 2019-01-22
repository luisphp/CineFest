package hurtado.luis.ejemplo.cinefest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MisEntradas extends AppCompatActivity {


    ListView listView;
    String urlme = "http://androidwstest.esy.es/mis_entradas.php";
    String parametros = "?id_usu="+Index.id;
    Gson gson;
    AsyncHttpClient client;
    ProgressDialog pd;
    ResponseMisEntradas responseMisEntradas;
    CustomAdapterME customAdapterME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_entradas);

        listView = (ListView) findViewById(R.id.LVMisEntradas);



        pd = new ProgressDialog(this);
        pd.setMessage("Cargando Datos");
        pd.setCancelable(false);
        pd.show();

        client = new AsyncHttpClient();
        client.get(MisEntradas.this, urlme + parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseSTR = new String(responseBody);


                if (responseBody.length >= 15) {

                    gson = new Gson();
                    responseMisEntradas = gson.fromJson(responseSTR, ResponseMisEntradas.class);
                    customAdapterME = new CustomAdapterME(MisEntradas.this, responseMisEntradas.getEntradas());
                    listView.setAdapter(customAdapterME);

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



    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();
                    }
                }).create().show();
    }
}
