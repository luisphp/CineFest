package hurtado.luis.ejemplo.cinefest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Xml;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class Checkout extends AppCompatActivity {

    private TextView Nombre_Pelicula, Nombre_cine, Fecha, Horario, Nombre_sala, Sillas_seleccionadas,
                     Monto_a_Pagar, preview, txt10;

    private ImageView img1, img2, imgBanner;

    private Button proceder;

    private Spinner nacionalidad, mes, año;

    private EditText nom_en_tarjeta, num_tarjeta, num_cedula, num_cvv;

    private String[] Nac = {"V", "E"};

    private List<Integer> Mes = new ArrayList<>();

    private List<Integer> Año = new ArrayList<>();

    private Gson gson;

    private AsyncHttpClient client;

    private ProgressDialog pd;
    private String url_checkout = "http://androidwstest.esy.es/checkout_cine.php";


    private String resultado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        //Recibo los datos de la actividad anterior

        Intent i = this.getIntent();

        final String horas = i.getExtras().getString("ihoras");
        final String asientos_ocupados = i.getExtras().getString("iasientos_ocupados");
        final String cine = i.getExtras().getString("icine");
        final String id_funcion = i.getExtras().getString("iid_funcion");
        final String fecha = i.getExtras().getString("ifecha");
        final String sala = i.getExtras().getString("isala");
        final String id_hora_sillas = i.getExtras().getString("iid_hora_sillas");
        final int cant_sillas = i.getExtras().getInt("cant_sillas");
        final float Total_a_pagar = i.getExtras().getFloat("Total_a_pagar");
        final String seleccionados = i.getExtras().getString("seleccionados");


        //Instancio

        Nombre_Pelicula = (TextView) findViewById(R.id.Nombre_de_pelicula);
        Nombre_cine = (TextView) findViewById(R.id.Nombre_de_cine);
        Fecha = (TextView) findViewById(R.id.Fecha_funcion);
        Horario = (TextView) findViewById(R.id.Hora_funcion);
        Nombre_sala = (TextView) findViewById(R.id.Sala_funcion);
        Sillas_seleccionadas = (TextView) findViewById(R.id.SillasSeleccionadas);
        Monto_a_Pagar = (TextView) findViewById(R.id.MontoTotal);

        preview = (TextView) findViewById(R.id.Preview);

        img1 = (ImageView) findViewById(R.id.btnleft);
        img2 = (ImageView) findViewById(R.id.btnright);
        txt10 = (TextView) findViewById(R.id.contador);
        imgBanner = (ImageView) findViewById(R.id.BannerPelicula);
        proceder = (Button) findViewById(R.id.Proceder);

        proceder.setEnabled(false);

        Picasso.with(getApplicationContext()).load(Funciones.img_url.toString()).resize(200, 280).into(imgBanner);


        nacionalidad = (Spinner) findViewById(R.id.SpinnerNacionalidad);
        mes = (Spinner) findViewById(R.id.MesExp);
        año = (Spinner) findViewById(R.id.AñoExp);

        Nombre_Pelicula.setText(Funciones.titulo.getText());
        Nombre_cine.setText("Cine " + cine);
        Fecha.setText(fecha);
        Horario.setText(horas);
        Nombre_sala.setText("Sala " + sala);
        Sillas_seleccionadas.setText(seleccionados);
        Monto_a_Pagar.setText(String.valueOf(Total_a_pagar));

        nom_en_tarjeta = (EditText) findViewById(R.id.TarjetaName);
        num_tarjeta = (EditText) findViewById(R.id.NumeroDeTarjeta);
        num_cedula = (EditText) findViewById(R.id.Cedula);
        num_cvv = (EditText) findViewById(R.id.CVV);


        nom_en_tarjeta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {refreshC();}

            @Override
            public void afterTextChanged(Editable editable) {
                refreshC();
            }});

        num_tarjeta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {refreshC();}

            @Override
            public void afterTextChanged(Editable editable) {
                refreshC();
            }});

        num_cedula.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {refreshC();}

            @Override
            public void afterTextChanged(Editable editable) {
                refreshC();
            }
        });

        num_cvv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {refreshC();}

            @Override
            public void afterTextChanged(Editable editable) {
                refreshC();
            }});


        for (int a = 1; a <= 12; a++) {Mes.add(a);}

        for (int b = 2018; b <= 2030; b++) {Año.add(b);}


        ArrayAdapter<String> NacAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, Nac);

        nacionalidad.setAdapter(NacAdapter);

        ArrayAdapter<Integer> MesAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, Mes);

        mes.setAdapter(MesAdapter);

        ArrayAdapter<Integer> AñoAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, Año);

        año.setAdapter(AñoAdapter);

        refreshC();


        proceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String update_asientos =  "";

                if (asientos_ocupados == ""){

                    update_asientos = seleccionados;

                }else{

                update_asientos =  asientos_ocupados + "," + seleccionados;

                }

                /*

                Probamos que todos las variables esten completas, la unica que podria estar vacia
                seria los asientos que esten opcupados, ya que, si la funcion no tiene reservaciones
                se procede a llenarla partiendo de 0

                preview.setText("Horario: " + horas + " Sillas ocupadas: " + asientos_ocupados + " Cine: " + cine +
                        " id_funcion: " + id_funcion + " Fecha: " + fecha + " Sala: " +
                        sala + " id_hora_sillas: " + id_hora_sillas + cant_sillas + " Monto: " + Total_a_pagar
                        + " Seleccionados: " + seleccionados + "Nombre: " +
                        nom_en_tarjeta.getText() + " Cedula: " + num_cedula.getText() + "TDC:" +
                        num_tarjeta.getText() + " CVV: " + num_cvv.getText() + " Nac:" + nacionalidad.getSelectedItem()
                        + " Mes V:" + mes.getSelectedItem() + " Año V:" + año.getSelectedItem() + " Usuario ID:"
                        + Index.id + "Usuario_Nombre" + Index.nombre);

                */


         EnviarDatos(String.valueOf(Index.id), update_asientos, Integer.parseInt(id_funcion),
                        Integer.parseInt(id_hora_sillas), Total_a_pagar, nom_en_tarjeta.getText().toString(),
                        num_cedula.getText().toString().replace(" ",""), num_cvv.getText().toString().replace(" ",""),
                        num_tarjeta.getText().toString(), nacionalidad.getSelectedItem().toString(),
                        Integer.parseInt(mes.getSelectedItem().toString()), Integer.parseInt(año.getSelectedItem().toString()),
                        Funciones.id_de_pelicula, seleccionados);


            }
        });

    }

    private void EnviarDatos(String id_usuario, String update_asientos, int id_funcion, int id_hora_sillas,
                             float Total_a_pagar, String nom_en_tarjeta, String num_cedula,
                             String num_cvv, String num_tarjeta, String nacionalidad, int mes, int año, String id_movie,
                             String seleccionados) {

        String parametros = "?id_u="+ id_usuario +"&id_fun="+ id_funcion +"&update_a="
        + update_asientos+"&id_h_s="+id_hora_sillas+"&total_a="+ Total_a_pagar+"&tdc_name="+nom_en_tarjeta+
                "&tdc_ced="+num_cedula+"&tdc_cvv="+num_cvv+"&tdc_num="+num_tarjeta+"&nac="+nacionalidad+
                "&mes="+mes+"&year="+año +"&id_movie="+id_movie+"&selected="+ seleccionados;


        pd = new ProgressDialog(this);
        pd.setMessage("Cargando Datos");
        pd.setCancelable(false);
        pd.show();

        client = new AsyncHttpClient();
        client.get(Checkout.this, url_checkout+ parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseSTR = new String(responseBody);

                if (responseBody.length >= 3) {

                    gson = new Gson();

                    try {

                        JSONArray jsonArray = new JSONArray(responseSTR);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonobject = (JSONObject) jsonArray.get(i);

                            resultado = jsonArray.getJSONObject(i).getString("resultado");

                            pd.dismiss();
                                //Toast.makeText(Checkout.this,"Todo bien "+resultado,Toast.LENGTH_SHORT).show();

                            if (resultado.equals(null) | resultado.equals("Error")){

                                MensajeError();

                            }else {

                                MensajeExitoso();

                            }


                        }

                    } catch (JSONException e) {

                        e.printStackTrace();

                    }

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

    private void refreshC() {

        if (nom_en_tarjeta.getText().length() > 0
                && num_tarjeta.getText().length() > 0
                && num_cedula.getText().length() > 0
                && num_cvv.getText().length() > 0) {

            proceder.setEnabled(true);

        } else {

            proceder.setEnabled(false);

        }
    }


    private void MensajeExitoso() {

    AlertDialog.Builder mBuilder  = new AlertDialog.Builder(Checkout.this);

    View view = getLayoutInflater().inflate(R.layout.checkout_dialogo, null);

    Button button = (Button) view.findViewById(R.id.CheckoutButton);

    TextView codigo = (TextView) view.findViewById(R.id.MiCodeS);

    codigo.setText(resultado);

    mBuilder.setView(view);

    final AlertDialog dialog = mBuilder.create();
    dialog.show();

    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            dialog.dismiss();

            Sala.sillas_nombre.clear();
            Sala.sillas_ocupadas.clear();
            Sala.sillas_status.clear();
            Sala.espacio_escalera.clear();
            Sala.MiSeleccion.clear();



            Intent intento_202 = new Intent(Checkout.this, Index.class);
            startActivity(intento_202);


        }
    });

}

    private void MensajeError(){

        AlertDialog.Builder mBuilder  = new AlertDialog.Builder(Checkout.this);

        View view = getLayoutInflater().inflate(R.layout.checkout_exitoso, null);

        Button button = (Button) view.findViewById(R.id.CheckoutButton);
        TextView codigo = (TextView) view.findViewById(R.id.RespustaCheckout);
        TextView texto1 = (TextView) view.findViewById(R.id.Titulo);
        TextView texto2 = (TextView) view.findViewById(R.id.RespustaCheckout2);

        texto1.setText("");
        texto2.setText("Error al registrar tu pago. Intentalo nuevamente");

        codigo.setText("Ups!");


        mBuilder.setView(view);

        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();

            }
        });

    }

    private void iniciar_sesion_r(String... details) {
        Intent i = new Intent(this, Index.class);
        i.putExtra("id_l", details[0]);
        i.putExtra("nombre_l", details[1]);
        i.putExtra("apellido_l", details[2]);
        i.putExtra("contrasena_l", details[3]);
        i.putExtra("correo_auth_l", details[4]);
        i.putExtra("pregunta_secreta_l", details[5]);
        i.putExtra("respuesta_secreta_l", details[6]);
        i.putExtra("cedula_l", details[7]);
        i.putExtra("edad_l", details[8]);
        this.startActivity(i);
    }


}
