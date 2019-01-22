package hurtado.luis.ejemplo.cinefest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis.hurtado on 18/04/2018.
 */
public class SalaAdapterOficial extends BaseAdapter {

    private List<String> sillas = new ArrayList<>();
    private List<String> sillas_ocupadas = new ArrayList<>();
    private Context Scontext;
    private int cant_sillas;
    private List<String> espacio_escalera;


    public SalaAdapterOficial(Context Scontext, List<String> sillas, List<String> sillas_ocupadas,
                              int cant_silllas, List<String> espacio_escalera) {

        this.sillas = sillas;
        this.Scontext = Scontext;
        this.sillas_ocupadas = sillas_ocupadas;
        this.cant_sillas = cant_silllas;
        this.espacio_escalera = espacio_escalera;
    }

    @Override
    public int getCount() {
        return sillas.size();
    }

    @Override
    public Object getItem(int i) {
        return sillas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) Scontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rootView = inflater.inflate(R.layout.item_butaca_oficial, viewGroup, false);
        

        final ImageView butaca = (ImageView) rootView.findViewById(R.id.ButacaOficial);

        for (int f =0; f< sillas_ocupadas.size() ;f++){

            if (sillas.get(i).equals(sillas_ocupadas.get(f).replace(" ",""))) {

                butaca.setImageResource(R.drawable.b_ocupado);

            }
        }

        for (int r =0; r< espacio_escalera.size(); r++){

            if (sillas.get(i).equals(espacio_escalera.get(r).replace(" ",""))) {

                butaca.setVisibility(View.GONE);

                Sala.sillas_status.set(i,"Escalera");

            }

        }



        butaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Sala.sillas_status.get(i).equals("Escalera")){

                    Toast.makeText(Scontext,"Escalera",Toast.LENGTH_SHORT).show();

                }else if (Sala.sillas_status.get(i).equals("Disponible") && cant_sillas > 0){

                    Sala.sillas_status.set(i,"Seleccionado");
                    butaca.setImageResource(R.drawable.b_seleccionado);
                    cant_sillas = cant_sillas-1;
                    Sala.contador.setText(String.valueOf(cant_sillas));
                    Sala.MiSeleccion.add(sillas.get(i));


                    Sala.sillas_seleccionadas.setText("Seleccionadas: "+Sala.MiSeleccion.toString().replace("[","").replace("]",""));


                    //TODO: Enviar todos los datos a la actividad de pago o checkout


                }else if(Sala.sillas_status.get(i).equals("Ocupado")){


                    Toast.makeText(Scontext,"Butaca "+sillas.get(i)+" Ocupada ",Toast.LENGTH_SHORT).show();


                }else if(Sala.sillas_status.get(i).equals("Seleccionado")){


                    Sala.sillas_status.set(i,"Disponible");
                    butaca.setImageResource(R.drawable.b_disponible);
                    cant_sillas = cant_sillas+1;
                    Sala.contador.setText(String.valueOf(cant_sillas));
                    Sala.MiSeleccion.remove(sillas.get(i));
                    Sala.sillas_seleccionadas.setText("Seleccionadas: "+Sala.MiSeleccion.toString().replace("[","").replace("]",""));

                }

            }
        });

        return rootView;

    }
}
