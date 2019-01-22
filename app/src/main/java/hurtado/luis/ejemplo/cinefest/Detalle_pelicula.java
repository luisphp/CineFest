
package hurtado.luis.ejemplo.cinefest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class Detalle_pelicula extends AppCompatActivity {

    TextView Nombre_espñaol_d, Sinopsis_d, Duracion_d, Reparto_d, Nombre_original_d, Fecha_estreno_d,
            Poster_d, id_pelicula;
    ImageView imagen1, Imagen2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);

        id_pelicula = (TextView)findViewById(R.id.myid_pelicula);
        Nombre_espñaol_d = (TextView) findViewById(R.id.Nombre_esp);
        Nombre_original_d = (TextView) findViewById(R.id.myNombre_original);
        Sinopsis_d = (TextView) findViewById(R.id.MySinopsis);
        Reparto_d = (TextView) findViewById(R.id.MyReparto);
        Duracion_d = (TextView) findViewById(R.id.MyDuration);
        Fecha_estreno_d = (TextView) findViewById(R.id.myFecha_de_Estreno);
        Poster_d = (TextView) findViewById(R.id.myPoster);


        imagen1 = (ImageView)findViewById(R.id.myImageDetalle);

        Intent i = this.getIntent();

        String id = i.getExtras().getString("id");
        String Nombre_español = i.getExtras().getString("Nombre_español");
        String Sinopsis = i.getExtras().getString("Sinopsis");
        String Duracion = i.getExtras().getString("Duracion");
        String Reparto = i.getExtras().getString("Reparto");
        String Nombre_original = i.getExtras().getString("Nombre_original");
        String Fecha_estreno = i.getExtras().getString("Fecha_estreno");
        String Poster = i.getExtras().getString("Poster");

        id_pelicula.setText("id: "+id);
        Nombre_espñaol_d.setText("Nombre en Español: "+Nombre_español);
        Sinopsis_d.setText("Sinopsis: "+Sinopsis);


        Duracion_d.setText("Duracion Estimada: "+Duracion);
        Reparto_d.setText("Elenco: "+Reparto);
        Nombre_original_d.setText("Nombreen Ingles: "+Nombre_original);
        Fecha_estreno_d.setText("Fecha tentativa de estreno: "+Fecha_estreno);
        Poster_d.setText("Poster: "+Poster);

        Picasso.with(this).load(Poster).placeholder(R.drawable.place_holder).into(imagen1);


    }
}
