package hurtado.luis.ejemplo.cinefest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by luis.hurtado on 09/05/2018.
 */
public class CustomAdapterME extends BaseAdapter {

    private Context MEContext;
    private List<ResponseMisEntradas.EntradasBean> tickets;


    public CustomAdapterME(Context MEContext, List<ResponseMisEntradas.EntradasBean> tickets ) {

        this.MEContext = MEContext;
        this.tickets = tickets;

    }

    @Override
    public int getCount() {
        return tickets.size();
    }

    @Override
    public Object getItem(int i) {
        return tickets.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        LayoutInflater inflater = (LayoutInflater) MEContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rootView = inflater.inflate(R.layout.item_mis_entradas, viewGroup, false);


        ImageView fotoME = (ImageView) rootView.findViewById(R.id.ImagenME);
        TextView fecha = (TextView) rootView.findViewById(R.id.TXTME1);
        TextView hora = (TextView) rootView.findViewById(R.id.TXTME12);
        TextView sillas = (TextView) rootView.findViewById(R.id.TXTME2);
        TextView nombre_pelicula = (TextView) rootView.findViewById(R.id.TXTME3);
        TextView codigo = (TextView) rootView.findViewById(R.id.TXTME4);

        Picasso.with(MEContext).load(tickets.get(i).getPoster1()).fit().into(fotoME);

        fecha.setText(tickets.get(i).getFecha_funcion());
        sillas.setText(tickets.get(i).getAsientos_comprados());
        nombre_pelicula.setText(tickets.get(i).getNombre_esp());
        codigo.setText(tickets.get(i).getCodigo_especial());
        hora.setText(tickets.get(i).getHora());

        return rootView;
    }
}
