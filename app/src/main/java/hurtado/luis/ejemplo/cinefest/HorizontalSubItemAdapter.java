package hurtado.luis.ejemplo.cinefest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis.hurtado on 02/03/2018.
 */
public class HorizontalSubItemAdapter extends RecyclerView.Adapter<HorizontalSubItemAdapter.HorizontalViewHolder>{

    private Context context;
    private ArrayList<String> horas;
    private ArrayList<String> asientos_ocupados;
    private ArrayList<String> cine;
    private ArrayList<String> id_funcion;
    private ArrayList<String> fecha;
    private ArrayList<String> sala;
    private ArrayList<String> id_hora_sillas;

    public HorizontalSubItemAdapter(Context context ,ArrayList<String> horas,ArrayList<String> asientos_ocupados,
                                    ArrayList<String> cine, ArrayList<String> id_funcion, ArrayList<String> fecha,
                                    ArrayList<String> sala, ArrayList<String> id_hora_sillas) {

        this.context = context;
        this.horas = horas;
        this.asientos_ocupados = asientos_ocupados;
        this.cine = cine;
        this.id_funcion = id_funcion;
        this.fecha = fecha;
        this.sala = sala;
        this.id_hora_sillas = id_hora_sillas;

    }

    @Override
    public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.sub_item_funcion,parent,false);

        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HorizontalViewHolder holder, final int position) {
            holder.btn_hora.setText(horas.get(position));

        holder.btn_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (Index.sesion == false){

                    Intent intento_1 = new Intent(context,Login_usuario.class);
                    context.startActivity(intento_1);

                }else{

                    //Toast.makeText(context,"Hora: "+horas.get(position),Toast.LENGTH_SHORT).show();

                    //TODO: Mandar los datos de la funcion seleccionada a la proxima actividad para ser procesados
                    //TODO: Datos a enviar: cine, id_funcion, fecha, asientos_oc, hora, sala, id (hora_funsion)
                    //TODO: Adicional id_pelicula, url_img, Nombre_Pelicula (10 en total).

                    openDeailActivity(horas.get(position), asientos_ocupados.get(position),
                            cine.get(position),id_funcion.get(position),fecha.get(position),
                            sala.get(position),id_hora_sillas.get(position));

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return horas.size();
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder {

            Button btn_hora;

    public HorizontalViewHolder(View itemView) {
        super(itemView);
        btn_hora = (Button) itemView.findViewById(R.id.sub_button);

    }}



    private void openDeailActivity(String ihoras, String iasientos_ocupados, String icine,
                                   String iid_funcion, String ifecha, String isala, String iid_hora_sillas) {

        Intent i = new Intent(context, PreSala.class);
        i.putExtra("ihoras", ihoras);
        i.putExtra("iasientos_ocupados", iasientos_ocupados);
        i.putExtra("icine", icine);
        i.putExtra("iid_funcion", iid_funcion);
        i.putExtra("ifecha", ifecha);
        i.putExtra("isala", isala);
        i.putExtra("iid_hora_sillas", iid_hora_sillas);
        context.startActivity(i);
    }

}