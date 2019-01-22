package hurtado.luis.ejemplo.cinefest;

import java.util.List;

/**
 * Created by luis.hurtado on 09/05/2018.
 */
public class ResponseMisEntradas {


    private List<EntradasBean> Entradas;

    public List<EntradasBean> getEntradas() {
        return Entradas;
    }


    public static class EntradasBean {
        /**
         * id : 9
         * total_amount : 100
         * asientos_comprados : D1
         * codigo_especial : 2JYE9U-R12
         * nombre_esp : Avengers Guerra del Infinito I
         * sala : M1
         * hora : 1:30 pm
         * fecha_funcion : Lunes 26-02-2018
         * poster1 : http://androidwstest.esy.es/imagenes/avengers.jpg
         */

        private String id;
        private String total_amount;
        private String asientos_comprados;
        private String codigo_especial;
        private String nombre_esp;
        private String sala;
        private String hora;
        private String fecha_funcion;
        private String poster1;

        public String getId() {
            return id;
        }


        public String getTotal_amount() {
            return total_amount;
        }


        public String getAsientos_comprados() {
            return asientos_comprados;
        }


        public String getCodigo_especial() {
            return codigo_especial;
        }


        public String getNombre_esp() {
            return nombre_esp;
        }


        public String getSala() {
            return sala;
        }


        public String getHora() {
            return hora;
        }


        public String getFecha_funcion() {
            return fecha_funcion;
        }


        public String getPoster1() {
            return poster1;
        }


    }
}
