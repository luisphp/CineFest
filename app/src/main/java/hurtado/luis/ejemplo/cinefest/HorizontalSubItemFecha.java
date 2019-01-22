package hurtado.luis.ejemplo.cinefest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis.hurtado on 15/03/2018.
 */
public class HorizontalSubItemFecha extends RecyclerView.Adapter<HorizontalSubItemFecha.HorizontalViewHolder> {

    private ArrayList<String> fechas;
    private Context context;

    public HorizontalSubItemFecha( Context context, ArrayList<String> fechas) {
        this.fechas = fechas;
        this.context = context;
    }




    @Override
    public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.sub_item_funcion,parent,false);

        return new HorizontalViewHolder(view);
    }



    @Override
    public void onBindViewHolder(HorizontalViewHolder holder, final int position) {

        holder.btn_hora.setText( fechas.get(position));





        holder.btn_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Funciones.date = fechas.get(position).toString();



            ///TODO: ejecutar DescargarFechas(), que no se como madres, lo voy a hacer por que esta en otra actividad

                Funciones.cambio.setText(fechas.get(position).toString());



            }
        });



    }

    @Override
    public int getItemCount() {
        return fechas.size();
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder {

        Button btn_hora;

        public HorizontalViewHolder(View itemView) {
            super(itemView);

            btn_hora = (Button) itemView.findViewById(R.id.sub_button);
        }}


}
