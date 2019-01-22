package hurtado.luis.ejemplo.cinefest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sala extends AppCompatActivity {


    //Declaro variables

    private GridView gv;
    public static TextView Name_sala, sillas_seleccionadas,contador;
    private SalaAdapterOficial adapter;
    public static int recuento;
    ImageView siguiente;

    //Declaro mis listas de status de asientos, son publicas para acceder a ellas desde
    // el adapter.

    public static List<String> sillas_nombre = new ArrayList<>();
    public static List<String> sillas_ocupadas = new ArrayList<>();
    public static List<String> sillas_status = new ArrayList<>();
    public static List<String> espacio_escalera = new ArrayList<>();
    public static List<String> MiSeleccion = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala_m1);

        //Instancio

        gv = (GridView) findViewById(R.id.grider);

        Name_sala = (TextView) findViewById(R.id.NombreSala);
        contador = (TextView) findViewById(R.id.Contador);
        sillas_seleccionadas = (TextView) findViewById(R.id.Seleccionados);
        siguiente = (ImageView) findViewById(R.id.Checkout);

        //Recibo los datos de la actividad anterior PreSala.class

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


        //Muestro el nombre de la sala, cargo la lista de sientos ocupados

        Name_sala.setText("Sala " + sala);
        sillas_ocupadas.addAll(Arrays.asList(asientos_ocupados.split(",")));
        contador.setText(String.valueOf(cant_sillas));


        //Cargo la lista sala pasando como parametro el nombre de la sala

        CargarSala(sala);


        // Cargo los status de las sillas

        for (int contador = 0; contador < sillas_nombre.size(); contador++) {


            if (comprobacion(sillas_nombre.get(contador).replace(" ", "")) == true) {

                sillas_status.add("Ocupado");

                recuento = recuento + 1;

            } else {

                sillas_status.add("Disponible");

            }

        }

        //Cargar escaleras dependiendo de la sala:

        if(sala.equals("M1")){

            String[] escaleras_m1 = {"B4","C4","D4","E4","F4"};
            espacio_escalera.addAll(Arrays.asList(escaleras_m1));

        }else if(sala.equals("M2")){

            String[] escaleras_m2 = {"B3","C3","D3", "E3","F3","G3","H3","B7","C7","D7","E7","F7","G7","H7"};
            espacio_escalera.addAll(Arrays.asList(escaleras_m2));

        }else if(sala.equals("M3")) {

       String[] escaleras_m3 = {"B4","C4","D4","E4","F4", "G4","H4", "F1","G1","H1", "F10","G10","H10"};
        espacio_escalera.addAll(Arrays.asList(escaleras_m3));

        }


        //Cargo el adaptador le paso como parametro el arreglo de las sillas dependiendo de la
        //seleccioanda por el cliente.

        adapter = new SalaAdapterOficial(Sala.this, sillas_nombre, sillas_ocupadas, cant_sillas, espacio_escalera);

        gv.setAdapter(adapter);


        /*

        ¿Qué vamos a hacer en esta sección?

        1- Declarar 4 Listas de status de sillas + 1 lista del nombre de la silla:

        1.1- Disponible
        1.2- Ocupado
        1.3- Seleccionado
        1.4- EspacioVacio (El espacio vacio se llenara segun la sala y hace referencia a las escaleras)
        1.5- Llenar la lista con todos los nombres de sillas

        2- LLenar el gridview con las sillas
        3- Marcar los espacios vacios donde no se va a habilitar imagen de sillas
        4- Marcar las sillas ocupadas y dejarlas como inhabilidas

             */

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Integer.parseInt(contador.getText().toString()) == 0) {

                   // Toast.makeText(Sala.this, "Pasamos al checkout", Toast.LENGTH_SHORT).show();

                    openDeailActivity(horas, asientos_ocupados, cine, id_funcion, fecha, sala,
                            id_hora_sillas, cant_sillas,
                            MiSeleccion.toString().replace("[", "").replace("]", ""),
                            Total_a_pagar);

                } else {

                    Toast.makeText(Sala.this, "Debe seleccionar " + cant_sillas + " sillas", Toast.LENGTH_SHORT).show();


                }
            }
        });

        //Efecto para cambiar el boton de avance de color si es que se tiene seleccionadas
        //Todas las entradas compradas

       /* contador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (contador.getText().toString() == 0) {
                    siguiente.setBackgroundResource(R.color.verde_oscuro);
                } else {
                    siguiente.setBackgroundResource(R.color.Gris_Oscuro);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (Integer.parseInt(String.valueOf(Sala.contador)) == 0) {

                    siguiente.setBackgroundResource(R.color.verde_oscuro);

                } else {

                    siguiente.setBackgroundResource(R.color.Gris_Oscuro);

                }


            }
        });

*/
    }

    public void CargarSala(String nombre_sala) {

        if (nombre_sala.equals("M1")) {

            gv.setNumColumns(6);


            for (int A = 1; A <= 36; A++) {


                if (A <= 6) {
                    sillas_nombre.add("A" + A);

                } else if (A <= 12) {
                    sillas_nombre.add("B" + (A - 6));
                } else if (A <= 18) {
                    sillas_nombre.add("C" + (A - 12));
                } else if (A <= 24) {
                    sillas_nombre.add("D" + (A - 18));
                } else if (A <= 30) {
                    sillas_nombre.add("E" + (A - 24));
                } else if (A <= 36) {
                    sillas_nombre.add("F" + (A - 30));
                }

            }

        } else if (nombre_sala.equals("M2")) {

            gv.setNumColumns(9);


            for (int A = 1; A <= 72; A++) {

                if (A <= 9) {
                    sillas_nombre.add("A" + A);

                } else if (A <= 18) {
                    sillas_nombre.add("B" + (A - 9));
                } else if (A <= 27) {
                    sillas_nombre.add("C" + (A - 18));
                } else if (A <= 36) {
                    sillas_nombre.add("D" + (A - 27));
                } else if (A <= 45) {
                    sillas_nombre.add("E" + (A - 36));
                } else if (A <= 54) {
                    sillas_nombre.add("F" + (A - 45));
                } else if (A <= 63) {
                    sillas_nombre.add("G" + (A - 54));
                } else if (A <= 72) {
                    sillas_nombre.add("H" + (A - 63));
                }
            }

        } else if (nombre_sala.equals("M3")) {

            gv.setNumColumns(10);


            for (int A = 1; A <= 80; A++) {

                if (A <= 10) {
                    sillas_nombre.add("A" + A);

                } else if (A <= 20) {
                    sillas_nombre.add("B" + (A - 10));
                } else if (A <= 30) {
                    sillas_nombre.add("C" + (A - 20));
                } else if (A <= 40) {
                    sillas_nombre.add("D" + (A - 30));
                } else if (A <= 50) {
                    sillas_nombre.add("E" + (A - 40));
                } else if (A <= 60) {
                    sillas_nombre.add("F" + (A - 50));
                } else if (A <= 70) {
                    sillas_nombre.add("G" + (A - 60));
                } else if (A <= 80) {
                    sillas_nombre.add("H" + (A - 70));
                }
            }

        } else if (nombre_sala.equals("CV1")) {

            gv.setNumColumns(6);


            for (int A = 1; A <= 36; A++) {

                if (A <= 6) {
                    sillas_nombre.add("A" + A);
                } else if (A <= 12) {
                    sillas_nombre.add("B" + (A - 6));
                } else if (A <= 18) {
                    sillas_nombre.add("C" + (A - 12));
                } else if (A <= 24) {
                    sillas_nombre.add("D" + (A - 18));
                } else if (A <= 30) {
                    sillas_nombre.add("E" + (A - 24));
                } else if (A <= 36) {
                    sillas_nombre.add("F" + (A - 30));
                }

            }
        } else if (nombre_sala.equalsIgnoreCase("CV2")) {

            gv.setNumColumns(9);


            for (int A = 1; A <= 72; A++) {

                if (A <= 9) {
                    sillas_nombre.add("A" + A);

                } else if (A <= 18) {
                    sillas_nombre.add("B" + (A - 9));
                } else if (A <= 27) {
                    sillas_nombre.add("C" + (A - 18));
                } else if (A <= 36) {
                    sillas_nombre.add("D" + (A - 27));
                } else if (A <= 45) {
                    sillas_nombre.add("E" + (A - 36));
                } else if (A <= 54) {
                    sillas_nombre.add("F" + (A - 45));
                } else if (A <= 63) {
                    sillas_nombre.add("G" + (A - 54));
                } else if (A <= 72) {
                    sillas_nombre.add("H" + (A - 63));
                }
            }

        } else if (nombre_sala.equalsIgnoreCase("CV3")) {

            gv.setNumColumns(10);


            for (int A = 1; A <= 80; A++) {

                if (A <= 10) {
                    sillas_nombre.add("A" + A);

                } else if (A <= 20) {
                    sillas_nombre.add("B" + (A - 10));
                } else if (A <= 30) {
                    sillas_nombre.add("C" + (A - 20));
                } else if (A <= 40) {
                    sillas_nombre.add("D" + (A - 30));
                } else if (A <= 50) {
                    sillas_nombre.add("E" + (A - 40));
                } else if (A <= 60) {
                    sillas_nombre.add("F" + (A - 50));
                } else if (A <= 70) {
                    sillas_nombre.add("G" + (A - 60));
                } else if (A <= 80) {
                    sillas_nombre.add("H" + (A - 70));
                }
            }

        }


    }

    private boolean comprobacion(String dato) {

        boolean resultado = true;

        int intento = 0;

        for (int contador2 = 0; contador2 < sillas_ocupadas.size(); contador2++) {

            if (dato.replace(" ", "").equals(sillas_ocupadas.get(contador2).replace(" ", ""))) {

                intento = intento + 1;

            } else {

                intento = intento + 0;

            }

        }
        if (intento == 1) {

            resultado = true;

        } else if (intento == 0) {

            resultado = false;
        }

        return resultado;

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {

                        sillas_nombre.clear();
                        sillas_ocupadas.clear();
                        sillas_status.clear();
                        espacio_escalera.clear();
                        MiSeleccion.clear();

                        finish();
                    }
                }).create().show();
    }

    private void openDeailActivity(String ihoras, String iasientos_ocupados, String icine,
                                   String iid_funcion, String ifecha, String isala,
                                   String iid_hora_sillas, int cant_sillas, String seleccionados,
                                   float Total_a_pagar) {

        Intent i = new Intent(Sala.this, Checkout.class);
        i.putExtra("ihoras", ihoras);
        i.putExtra("iasientos_ocupados", iasientos_ocupados);
        i.putExtra("icine", icine);
        i.putExtra("iid_funcion", iid_funcion);
        i.putExtra("ifecha", ifecha);
        i.putExtra("isala", isala);
        i.putExtra("iid_hora_sillas", iid_hora_sillas);
        i.putExtra("cant_sillas", cant_sillas);
        i.putExtra("seleccionados", seleccionados);
        i.putExtra("Total_a_pagar", Total_a_pagar);
        Sala.this.startActivity(i);
    }

}
