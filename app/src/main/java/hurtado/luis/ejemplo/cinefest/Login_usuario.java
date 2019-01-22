package hurtado.luis.ejemplo.cinefest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Login_usuario extends AppCompatActivity {


    String url = "http://androidwstest.esy.es/auth_usuario.php";
    TextView Registro;
    AsyncHttpClient client;
    EditText correo, pass;
    Button GetDatos;
    FloatingActionButton Home;
    ImageView Ver, Logo, Limpiar;
    int valor = 0;
    String nombre_l, apellido_l, correo_auth_l, contrasena_l, pregunta_secreta_l, respuesta_secreta_l;
    int id_l, edad_l, cedula_l;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_usuario);

        correo = (EditText) findViewById(R.id.Correo);
        pass = (EditText) findViewById(R.id.Contraseña);
        GetDatos = (Button) findViewById(R.id.EnviarDatos);
        Home = (FloatingActionButton) findViewById(R.id.Flotante1);
        Limpiar = (ImageView) findViewById(R.id.Limpiar);
        Ver = (ImageView) findViewById(R.id.Mostrar);
        Logo = (ImageView) findViewById(R.id.logo);
        Registro = (TextView) findViewById(R.id.Registrate);

        GetDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (correo.getText().toString() == "") {
                    Toast.makeText(Login_usuario.this, "Ingresa un correo", Toast.LENGTH_SHORT).show();

                } else {
                    Enviar();
                }

            }
        });


        Limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correo.setText("");
                pass.setText("");
            }
        });

        Ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(Login_usuario.this, "Boton en desarrollo", Toast.LENGTH_SHORT).show();

                if (valor == 1) {
                    valor = 0;
                    //pass.setInputType(NumberFormat.INTEGER_FIELD);
                    //pass.setInputType(1);
                    //Mostrar la contraseña

                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    // Toast.makeText(Login_usuario.this, "Valor: " + valor, Toast.LENGTH_SHORT).show();
                    Ver.setImageResource(R.drawable.ic_visibility_black_24dp);

                } else if (valor == 0) {
                    valor = 1;

                    //pass.setInputType(0);
                    //Ocultar Contraseña


                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    Ver.setImageResource(R.drawable.ic_visibility_off_black_24dp);
                    // Toast.makeText(Login_usuario.this, "Valor: " + valor, Toast.LENGTH_SHORT).show();


                }
            }
        });

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento_0 = new Intent(Login_usuario.this, Index.class);
                startActivity(intento_0);
            }
        });

        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login_usuario.this, Registrar_usuario.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();


            }
        });


    }


    public void Enviar() {

        pd = new ProgressDialog(this);
        pd.setMessage("Verificando datos");
        pd.show();
        pd.setCancelable(false);

        String url2 = url + "?mail=" + correo.getText().toString() + "&pass=" + pass.getText();


        client = new AsyncHttpClient();
        client.get(Login_usuario.this, url2, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String responseSTR = new String(responseBody);

                if (responseBody.length >= 3) {

                    try {
                        JSONArray jsonArray = new JSONArray(responseSTR);


                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonobject = (JSONObject) jsonArray.get(i);

                            nombre_l = jsonArray.getJSONObject(i).getString("nombre");
                            contrasena_l = jsonArray.getJSONObject(i).getString("contrasena");
                            apellido_l = jsonArray.getJSONObject(i).getString("apellido");
                            correo_auth_l = jsonArray.getJSONObject(i).getString("correo");
                            pregunta_secreta_l = jsonArray.getJSONObject(i).getString("pregunta_secreta");
                            respuesta_secreta_l = jsonArray.getJSONObject(i).getString("respuesta_secreta");

                            id_l = jsonArray.getJSONObject(i).getInt("id");
                            edad_l = jsonArray.getJSONObject(i).getInt("edad");
                            cedula_l = jsonArray.getJSONObject(i).getInt("cedula");


                            Intent intento_12 = new Intent(Login_usuario.this, Index.class);
                            intento_12.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intento_12);
                            pd.dismiss();

                            Index.sesion = true;

                            iniciar_sesion(String.valueOf(id_l), nombre_l, apellido_l, contrasena_l, correo_auth_l,
                                    pregunta_secreta_l, respuesta_secreta_l, String.valueOf(cedula_l), String.valueOf(edad_l));

                            Toast.makeText(getApplicationContext(), "Bienvenido: " + nombre_l, Toast.LENGTH_LONG).show();

                            finish();


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } else if (responseBody.length >= 2) {

                    pd.dismiss();
                    Toast.makeText(getApplicationContext(), "Usuario no registrado", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "Verifica tu conexión." + statusCode, Toast.LENGTH_LONG).show();

            }
        });


    }

    private void iniciar_sesion(String... details) {
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
