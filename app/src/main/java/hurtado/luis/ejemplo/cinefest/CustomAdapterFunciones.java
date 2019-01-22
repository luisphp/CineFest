package hurtado.luis.ejemplo.cinefest;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis.hurtado on 05/03/2018.
 */

public class CustomAdapterFunciones extends BaseAdapter implements Filterable{

    String arreglo_completo ="";

    private List<ResponseFunciones.PlanificacionBean> fFuncionesItem;
    private Context fContext;

    public CustomAdapterFunciones(Context fContext, List<ResponseFunciones.PlanificacionBean> fFuncionesItem) {

        this.fFuncionesItem = fFuncionesItem;
        this.fContext = fContext;

    }

    private LayoutInflater inflater;

    @Override
    public int getCount() {
        return fFuncionesItem.size();
    }

    @Override
    public Object getItem(int i) {
        return fFuncionesItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        /*if (view == null) {
            view = LayoutInflater.from(fContext).inflate(R.layout.item_funcion, viewGroup, false);
        } */

        LayoutInflater inflater = (LayoutInflater) fContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rootView = inflater.inflate(R.layout.item_funcion, viewGroup, false);

        final ResponseFunciones.PlanificacionBean funcion = (ResponseFunciones.PlanificacionBean) getItem(i);

        TextView Nombre_Cine = (TextView) rootView.findViewById(R.id.Nombre_de_cine);
        TextView Nombre_Sala = (TextView) rootView.findViewById(R.id.Nombre_de_sala);
        //TextView Horario = (TextView) rootView.findViewById(R.id.Horario);

            Nombre_Cine.setText("Cine "+funcion.getFunciones().get(i).getCine());
            Nombre_Sala.setText("Sala "+funcion.getFunciones().get(i).getSala());

        int j = 0;

       // ListView listaarreglo;
       // listaarreglo = (ListView) rootView.findViewById(R.id.listass);

        final ArrayList<String> horas = new ArrayList<String>();

        //TODO: Adicionalmente enviar estas listas a Horizontal Adapter Fechas para ser procesados y enviar el dato
        //TODO: en especifico a la actividad PreSala.class

        final ArrayList<String> asientos_ocupados = new ArrayList<String>();
        final ArrayList<String> cine = new ArrayList<String>();
        final ArrayList<String> id_funcion = new ArrayList<String>();
        final ArrayList<String> fecha = new ArrayList<String>();
        final ArrayList<String> sala = new ArrayList<String>();
        final ArrayList<String> id_hora_sillas = new ArrayList<String>();

        /*
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.RecyclerV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(fContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        */

        while(j < funcion.getFunciones().get(i).getHorario_sillas().size()){

           // arreglo = arreglo + " " +funcion.getFunciones().get(i).getHorario_sillas().get(j).getHora();

            //TODO: Primero se envia la horas ya que con las horas se construira la vista

            horas.add(funcion.getFunciones().get(i).getHorario_sillas().get(j).getHora());

            //TODO: luego las demas listas.

            asientos_ocupados.add(funcion.getFunciones().get(i).getHorario_sillas().get(j).getAsientos_oc());
            cine.add(funcion.getFunciones().get(i).getHorario_sillas().get(j).getCine());
            id_funcion.add(funcion.getFunciones().get(i).getHorario_sillas().get(j).getId_funcion());
            fecha.add(funcion.getFunciones().get(i).getHorario_sillas().get(j).getFecha());
            sala.add(funcion.getFunciones().get(i).getHorario_sillas().get(j).getSala());
            id_hora_sillas.add(funcion.getFunciones().get(i).getHorario_sillas().get(j).getId());

            j = j+1;

        }

        RecyclerView Rv;
        Rv = (RecyclerView) rootView.findViewById(R.id.RecyclerV);
        Rv.setLayoutManager(new LinearLayoutManager(fContext,LinearLayoutManager.HORIZONTAL, false));
        HorizontalSubItemAdapter adaptersub;
        adaptersub = new HorizontalSubItemAdapter(fContext, horas,asientos_ocupados,cine,id_funcion,fecha,sala,id_hora_sillas);
        Rv.setAdapter(adaptersub);



  /*
  ArrayAdapter<String> adapter = new ArrayAdapter<String>(fContext, android.R.layout.simple_list_item_1, horas);
  listaarreglo.setAdapter(adapter);
  listaarreglo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
  @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int x, long l) {

    Toast.makeText(fContext,"Sillas Ocupadas "+asientos_ocupados.get(x)+" Cine "+cine.get(x), Toast.LENGTH_SHORT).show();

            }
        });
  */

rootView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        //Toast.makeText(fContext,"Cine "+funcion.getFunciones().get(i).getCine().toString(),Toast.LENGTH_SHORT).show();
        //Snackbar.make(view, "Cine "+funcion.getFunciones().get(i).getCine().toString()).show();
        Toast.makeText(fContext,"Horas: "+horas.size(),Toast.LENGTH_SHORT).show();
    }
});

        return rootView;

    }

    @Override
    public Filter getFilter() {
        return null;
    }


}
