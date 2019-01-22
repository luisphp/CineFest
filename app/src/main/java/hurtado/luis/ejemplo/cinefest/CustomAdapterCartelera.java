package hurtado.luis.ejemplo.cinefest;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
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
 * Created by luis.hurtado on 22/02/2018.
 */
public class CustomAdapterCartelera extends BaseAdapter {

    private List<ResponseCartelera.CarteleraBean> mCarteleraItem;
    private Context cContext;


    public CustomAdapterCartelera(Context cContext,List<ResponseCartelera.CarteleraBean> mCarteleraItem ) {
        this.mCarteleraItem = mCarteleraItem;
        this.cContext = cContext;
    }

    private LayoutInflater inflater;


    @Override
    public int getCount() {
        return mCarteleraItem.size();
    }

    @Override
    public Object getItem(int i) {
        return mCarteleraItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return  i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        LayoutInflater inflater = (LayoutInflater) cContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rootView = inflater.inflate(R.layout.item_cartelera, viewGroup, false);


        final ResponseCartelera.CarteleraBean item = (ResponseCartelera.CarteleraBean) getItem(i);

        ImageView foto = (ImageView) rootView.findViewById(R.id.imgCartelera);
        TextView nombre = (TextView) rootView.findViewById(R.id.txtCartelera1);
        TextView genero = (TextView) rootView.findViewById(R.id.txtCartelera2);

        nombre.setText(item.getNombre_esp());
        genero.setText(item.getGenero());

        Picasso.with(cContext).load(item.getPoster1()).resize(170,250).into(foto);

        final String id = item.getId();
        final String Nombre_español = item.getNombre_esp();
        final String Sinopsis = item.getSinopsis();
        final String Genero = item.getGenero();
        final String Duracion = item.getDuracion();
        final String Reparto = item.getReparto();
        final String Nombre_original = item.getNombre_original();
        final String Fecha_estreno = item.getF_estreno();
        final String Poster = item.getPoster1();










        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Enviar_datos1(id, Nombre_español, Sinopsis, Genero, Duracion, Reparto, Nombre_original, Fecha_estreno, Poster);

            }
        });


        return rootView;
    }

    private void Enviar_datos1(String... details) {
        Intent i = new Intent(cContext, Funciones.class);
        i.putExtra("id", details[0]);
        i.putExtra("Nombre_español", details[1]);
        i.putExtra("Sinopsis", details[2]);
        i.putExtra("Genero", details[3]);
        i.putExtra("Duracion", details[4]);
        i.putExtra("Reparto", details[5]);
        i.putExtra("Nombre_original", details[6]);
        i.putExtra("Fecha_estreno", details[7]);
        i.putExtra("Poster", details[8]);
        cContext.startActivity(i);
    }
}
