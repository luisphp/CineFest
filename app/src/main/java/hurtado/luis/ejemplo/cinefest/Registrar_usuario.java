package hurtado.luis.ejemplo.cinefest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
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

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class Registrar_usuario extends AppCompatActivity {

    EditText nombre, apellido, edad, correo, contrasena, confirmcontrasena, respuesta_secreta, cedula;
    RadioGroup genero;
    RadioButton mas, fem;
    Spinner Pregunta_secreta;
    TextView preview;
    AsyncHttpClient client;
    String resultado;
    Button EnviarDatos;

    List<String> cuenta = new ArrayList<String>();

    ProgressDialog pd;


    public static String cadena;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        nombre = (EditText) findViewById(R.id.InNombre);
        apellido = (EditText) findViewById(R.id.InApellido);
        edad = (EditText) findViewById(R.id.InEdad);
        correo = (EditText) findViewById(R.id.InCorreo);
        contrasena = (EditText) findViewById(R.id.InContraseña);
        confirmcontrasena = (EditText) findViewById(R.id.InConfirmContraseña2);
        respuesta_secreta = (EditText) findViewById(R.id.InSecreta);
        cedula = (EditText) findViewById(R.id.InCedula);

        genero = (RadioGroup) findViewById(R.id.Opciones);
        mas = (RadioButton) findViewById(R.id.radio_masculino);
        fem = (RadioButton) findViewById(R.id.radio_femenino);


        Pregunta_secreta = (Spinner) findViewById(R.id.Pregunta);

        EnviarDatos = (Button) findViewById(R.id.EnviarDatos);

        preview = (TextView) findViewById(R.id.Form);

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

        edad.addTextChangedListener(new TextWatcher() {
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


        // Se envian los datos al servicio web

        EnviarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*

                TODO: Aca  solo verifico lo que se envia al navegador

                String radiovalue = ((RadioButton) findViewById(genero.getCheckedRadioButtonId())).getText().toString();

                cadena = "?nombre=" + Uri.encode(nombre.getText().toString()) +
                        "&apellido= " + Uri.encode(apellido.getText().toString()) +
                        "&edad=" + Uri.encode(edad.getText().toString()) +
                        "&correo=" + Uri.encode(correo.getText().toString()) +
                        "&contrasena=" + Uri.encode(contrasena.getText().toString()) +
                        "&pregunta=" + Uri.encode(Pregunta_secreta.getSelectedItem().toString()) +
                        "&respuesta=" + Uri.encode(respuesta_secreta.getText().toString()) +
                        "&cedula=" + Uri.encode(cedula.getText().toString()) +
                        "&genero=" + Uri.encode(radiovalue);

                preview.setText(cadena);


                */
                if (contrasena.getText().toString().equals(confirmcontrasena.getText().toString())){
                    EnviarDatos();

                }else{

                    Toast.makeText(Registrar_usuario.this,"Verifique su contraseña!",Toast.LENGTH_SHORT).show();

                }





            }
        });
    }

    private void EnviarDatos() {

        pd = new ProgressDialog(this);
        pd.setMessage("Verificando Datos");
        pd.show();
        pd.setCancelable(false);

        //Se usa set Cancelable para prevenir que se altere el proceso.
        pd.setCancelable(false);


        // En este String se toma el valor seleccionado en el genero.
        String radiovalue = ((RadioButton) findViewById(genero.getCheckedRadioButtonId())).getText().toString();

        String url = "http://androidwstest.esy.es/verify_mail.php";

        String url2 = url + "?nombre=" + Uri.encode(nombre.getText().toString()) +
                "&apellido=" + Uri.encode(apellido.getText().toString()) +
                "&edad=" + Uri.encode(edad.getText().toString()) +
                "&correo=" + Uri.encode(correo.getText().toString()) +
                "&contrasena=" + Uri.encode(contrasena.getText().toString()) +
                "&pregunta=" + Uri.encode(Pregunta_secreta.getSelectedItem().toString()) +
                "&respuesta=" + Uri.encode(respuesta_secreta.getText().toString()) +
                "&cedula=" + Uri.encode(cedula.getText().toString()) +
                "&genero=" + Uri.encode(gender());

        client = new AsyncHttpClient();
        client.get(Registrar_usuario.this, url2, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String responseSTR = new String(responseBody);

                preview.setText(cadena + " - " + responseSTR);

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

                /*
                Toast.makeText(Registrar_usuario.this, "Respuesta del Servidor: " + resultado, Toast.LENGTH_SHORT).show();
                Esto es temporal ya que el se redirigira al usuario al index.
                */

                if (resultado.equals("existente")) {

                    Toast.makeText(Registrar_usuario.this, "Este correo ya esta registrado", Toast.LENGTH_SHORT).show();

                } else if (resultado.equals("Error")) {

                    Toast.makeText(Registrar_usuario.this, "Error de servidor, intenta nuevamente", Toast.LENGTH_SHORT).show();

                } else if (resultado.equals("registro_exitoso")) {

                    //Se llevo a cabo el registro del usuario

                    createSimpleDialog().show();

                }


                resultado = "";}

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                pd.dismiss();

                Toast.makeText(Registrar_usuario.this, "Verifica tu conexion", Toast.LENGTH_SHORT).show();

            }
        });}

    private String gender() {

        String radiovalue = ((RadioButton) findViewById(genero.getCheckedRadioButtonId())).getText().toString();

        //Toast.makeText(Registrar_usuario.this, "Genero seleccionado: " + radiovalue, Toast.LENGTH_SHORT).show();

        return radiovalue;

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

    private void bajar_datos() {

        pd = new ProgressDialog(this);
        pd.setMessage("Enviando");
        pd.show();
        pd.setCancelable(false);

        String url_bajar_datos = "http://androidwstest.esy.es/auth_usuario.php";

        String url_parametros = url_bajar_datos + "?mail=" + correo.getText().toString() + "&pass=" + contrasena.getText();


        client = new AsyncHttpClient();
        client.get(Registrar_usuario.this, url_parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String responseSTR = new String(responseBody);

                try {
                    JSONArray jsonArray = new JSONArray(responseSTR);


                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonobject = (JSONObject) jsonArray.get(i);

                        String nombre_l = jsonArray.getJSONObject(i).getString("nombre");
                        String contrasena_l = jsonArray.getJSONObject(i).getString("contrasena");
                        String apellido_l = jsonArray.getJSONObject(i).getString("apellido");
                        String correo_auth_l = jsonArray.getJSONObject(i).getString("correo");
                        String pregunta_secreta_l = jsonArray.getJSONObject(i).getString("pregunta_secreta");
                        String respuesta_secreta_l = jsonArray.getJSONObject(i).getString("respuesta_secreta");

                        int id_l = jsonArray.getJSONObject(i).getInt("id");
                        int edad_l = jsonArray.getJSONObject(i).getInt("edad");
                        int cedula_l = jsonArray.getJSONObject(i).getInt("cedula");

                        Index.sesion = true;

                        pd.dismiss();

                        iniciar_sesion_r(String.valueOf(id_l), nombre_l, apellido_l, contrasena_l, correo_auth_l,
                                pregunta_secreta_l, respuesta_secreta_l, String.valueOf(cedula_l), String.valueOf(edad_l));


                        Toast.makeText(Registrar_usuario.this, " Bienvenido " + nombre_l + "!", Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "Verifica tu conexión." + statusCode, Toast.LENGTH_LONG).show();

            }
        });


    }

    public AlertDialog createSimpleDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Registrar_usuario.this);
        builder.setMessage("Registro Exitoso!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        bajar_datos();

                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void refresh() {

        if (nombre.getText().length() > 0
                && apellido.getText().length() > 0
                && edad.getText().length() > 0 && correo.getText().length() > 0
                && contrasena.getText().length() > 0 && confirmcontrasena.getText().length() > 0
                && respuesta_secreta.getText().length() > 0 && cedula.getText().length() > 0) {

            EnviarDatos.setEnabled(true);
        } else {
            EnviarDatos.setEnabled(false);
        }
    }


}
