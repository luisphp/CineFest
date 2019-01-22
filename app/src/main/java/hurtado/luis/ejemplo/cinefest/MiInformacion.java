package hurtado.luis.ejemplo.cinefest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MiInformacion extends AppCompatActivity {

    private EditText nombre, apellido, correo, contrasena, confirmcontrasena, respuesta_secreta, cedula;
    private RadioGroup genero;
    private RadioButton mas, fem;
    private Spinner Pregunta_secreta;
    private TextView generoMI, pregunta, pre_pregunta;
    private Button EnviarDatos;
    private String resultado = "";

    private int id_mi = Index.id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_informacion);


        nombre = (EditText) findViewById(R.id.InNombreMI);
        apellido = (EditText) findViewById(R.id.InApellidoMI);
        correo = (EditText) findViewById(R.id.InCorreoMI);
        contrasena = (EditText) findViewById(R.id.InContraseñaMI);
        confirmcontrasena = (EditText) findViewById(R.id.InConfirmContraseña2MI);
        respuesta_secreta = (EditText) findViewById(R.id.InSecretaMI);
        cedula = (EditText) findViewById(R.id.InCedulaMI);
        pre_pregunta = (TextView)findViewById(R.id.secret_question);

        genero = (RadioGroup) findViewById(R.id.OpcionesMI);
        mas = (RadioButton) findViewById(R.id.radio_masculinoMI);
        fem = (RadioButton) findViewById(R.id.radio_femeninoMI);


        Pregunta_secreta = (Spinner) findViewById(R.id.PreguntaMI);

        EnviarDatos = (Button) findViewById(R.id.EnviarDatosMI);

        nombre.setText(Index.nombre);
        apellido.setText(Index.apellido);
        correo.setText(Index.correo_auth);
        contrasena.setText(Index.contrasena);
        confirmcontrasena.setText(Index.contrasena);
        respuesta_secreta.setText(Index.respuesta_secreta);
        pre_pregunta.setText(Index.pregunta_secreta);
        cedula.setText(String.valueOf(Index.cedula));


        List list = new ArrayList();
        list.add("Nombre de Primera Mascota");
        list.add("Hora de mi nacimiento");
        list.add("Color favorito");

        EnviarDatos.setEnabled(false);

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Pregunta_secreta.setAdapter(arrayAdapter);

        nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                refresh();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                refresh();

            }

            @Override
            public void afterTextChanged(Editable editable) {

                refresh();

            }
        });

        apellido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                refresh();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                refresh();
            }

            @Override
            public void afterTextChanged(Editable editable) {

                refresh();

            }
        });

        correo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                refresh();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                refresh();
            }

            @Override
            public void afterTextChanged(Editable editable) {

                refresh();

            }
        });

        contrasena.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                refresh();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                refresh();
            }

            @Override
            public void afterTextChanged(Editable editable) {

                refresh();

            }
        });

        confirmcontrasena.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                refresh();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                refresh();
            }

            @Override
            public void afterTextChanged(Editable editable) {

                refresh();

            }
        });

        respuesta_secreta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                refresh();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                refresh();
            }

            @Override
            public void afterTextChanged(Editable editable) {

                refresh();

            }
        });

        cedula.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                refresh();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                refresh();
            }

            @Override
            public void afterTextChanged(Editable editable) {

                refresh();

            }
        });

        EnviarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (contrasena.getText().toString().equals(confirmcontrasena.getText().toString())){

                    ActualizarDatos();

                }else{

                    Toast.makeText(MiInformacion.this,"Verifique su contraseña!",Toast.LENGTH_SHORT).show();

                }
                
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

    private void ActualizarDatos() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Verificando Datos");
        pd.show();
        pd.setCancelable(false);

        //Se usa set Cancelable para prevenir que se altere el proceso.
        pd.setCancelable(false);

        // En este String se toma el valor seleccionado en el genero.
        String radiovalue = ((RadioButton) findViewById(genero.getCheckedRadioButtonId())).getText().toString();

        String url = "http://androidwstest.esy.es/actualizar_datos.php";

        String url2 = url + "?nombre=" + Uri.encode(nombre.getText().toString()) +
                "&apellido=" + Uri.encode(apellido.getText().toString()) +
                "&correo=" + Uri.encode(correo.getText().toString()) +
                "&contrasena=" + Uri.encode(contrasena.getText().toString()) +
                "&pregunta_secreta=" + Uri.encode(Pregunta_secreta.getSelectedItem().toString()) +
                "&respuesta_secreta=" + Uri.encode(respuesta_secreta.getText().toString()) +
                "&cedula=" + Uri.encode(cedula.getText().toString()) +
                "&genero=" + Uri.encode(radiovalue)+
                "&id_u=" + Uri.encode(String.valueOf(Index.id));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(MiInformacion.this, url2, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String responseSTR = new String(responseBody);

                try {
                    JSONArray jsonArray = new JSONArray(responseSTR);

                    for (int i = 0; i < jsonArray.length(); i++) {

                       JSONObject jsonobject = (JSONObject) jsonArray.get(i);

                       resultado = jsonArray.getJSONObject(i).getString("resultado");

                    }

                } catch (JSONException e) {

                    e.printStackTrace();

                }

                pd.dismiss();


                if (resultado.equals("Error")) {

                    Toast.makeText(MiInformacion.this, "Error de servidor, intenta nuevamente", Toast.LENGTH_SHORT).show();



                } else if (resultado.equals("actualizacion_exitosa")) {

                    //Se llevo a cabo la actualizacion de la informacion

                    createSimpleDialogE();

                }

                resultado = "";

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                pd.dismiss();

                Toast.makeText(MiInformacion.this, " Verifica tu conexion ", Toast.LENGTH_SHORT).show();

            }
        });}

    private AlertDialog createSimpleDialogE() {

        final AlertDialog.Builder mBuilder  = new AlertDialog.Builder(MiInformacion.this);

        View Myview = getLayoutInflater().inflate(R.layout.mi_info, null);


        Button ok = (Button) Myview.findViewById(R.id.OkMI);



            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {




                    iniciar_sesion_r(String.valueOf(id_mi), nombre.getText().toString(), apellido.getText().toString(),
                            contrasena.getText().toString(), correo.getText().toString(), Pregunta_secreta.getSelectedItem().toString(),
                            respuesta_secreta.getText().toString(), String.valueOf(cedula.getText()),"0");




                }
            });






        mBuilder.setView(Myview);

        final AlertDialog dialog = mBuilder.create();
        dialog.show();


        return mBuilder.create();
    }


    private void refresh() {

        if (nombre.getText().length() > 0
                && apellido.getText().length() > 0
                && correo.getText().length() > 0
                && contrasena.getText().length() > 0 && confirmcontrasena.getText().length() > 0
                && respuesta_secreta.getText().length() > 0 && cedula.getText().length() > 0
                ) {

            EnviarDatos.setEnabled(true);

        } else {

            EnviarDatos.setEnabled(false);

        }
    }


}
