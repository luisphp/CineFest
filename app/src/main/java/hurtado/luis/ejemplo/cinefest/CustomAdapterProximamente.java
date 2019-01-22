package hurtado.luis.ejemplo.cinefest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Luis on 08/12/2017.
 */
public class CustomAdapterProximamente extends BaseAdapter {


    private List<ResponseProximamente.EstrenosBean> mMovieItem;
    private Context mContext;


    public CustomAdapterProximamente(Context mContext, List<ResponseProximamente.EstrenosBean> mMovieItem) {
        this.mContext = mContext;
        this.mMovieItem = mMovieItem;
    }

    @Override
    public int getCount() {
        return mMovieItem.size();
    }

    @Override
    public Object getItem(int i) {
        return mMovieItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {



        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rootView = inflater.inflate(R.layout.each_list_item, viewGroup, false);



        ImageView foto = (ImageView) rootView.findViewById(R.id.myImageView);
        final TextView nombre = (TextView) rootView.findViewById(R.id.MyTextViewNombre);
        TextView link = (TextView) rootView.findViewById(R.id.MyLinkImage);

        nombre.setText(mMovieItem.get(i).getNombre_esp());


        link.setText("Se estrena el: "+mMovieItem.get(i).getF_estreno());
        Picasso.with(mContext).load(mMovieItem.get(i).getPoster1()).fit().placeholder(R.drawable.place_holder).into(foto);


        final String id = mMovieItem.get(i).getId();
        final String Nombre_español = mMovieItem.get(i).getNombre_esp();
        final String Sinopsis = mMovieItem.get(i).getSinopsis();
        final String Genero = mMovieItem.get(i).getGenero();
        final String Duracion = mMovieItem.get(i).getDuracion();
        final String Reparto = mMovieItem.get(i).getReparto();
        final String Nombre_original = mMovieItem.get(i).getNombre_original();
        final String Fecha_estreno = mMovieItem.get(i).getF_estreno();
        final String Poster = mMovieItem.get(i).getPoster1();


        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Pelicula: " + nombre.getText().toString(), Toast.LENGTH_SHORT).show();

                Enviar_datos1(id, Nombre_español, Sinopsis, Genero, Duracion, Reparto, Nombre_original, Fecha_estreno, Poster);


            }
        });

        return rootView;
    }

    private void Enviar_datos1(String... details) {
        Intent i = new Intent(mContext, Detalle_pelicula.class);
        i.putExtra("id", details[0]);
        i.putExtra("Nombre_español", details[1]);
        i.putExtra("Sinopsis", details[2]);
        i.putExtra("Genero", details[3]);
        i.putExtra("Duracion", details[4]);
        i.putExtra("Reparto", details[5]);
        i.putExtra("Nombre_original", details[6]);
        i.putExtra("Fecha_estreno", details[7]);
        i.putExtra("Poster", details[8]);
        mContext.startActivity(i);

    }
}