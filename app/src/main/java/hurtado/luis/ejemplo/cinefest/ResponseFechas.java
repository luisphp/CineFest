package hurtado.luis.ejemplo.cinefest;

import java.util.List;

/**
 * Created by luis.hurtado on 16/03/2018.
 */
public class ResponseFechas {


    private List<FechasBean> Fechas;

    public List<FechasBean> getFechas() {
        return Fechas;
    }

    public void setFechas(List<FechasBean> Fechas) {
        this.Fechas = Fechas;
    }

    public static class FechasBean {
        /**
         * fecha : Lunes 26-02-2018
         */

        private String fecha;

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }
    }
}
