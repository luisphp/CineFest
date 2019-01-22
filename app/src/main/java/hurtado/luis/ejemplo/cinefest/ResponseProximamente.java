package hurtado.luis.ejemplo.cinefest;

import java.util.List;

/**
 * Created by Luis on 06/12/2017.
 */
public class ResponseProximamente {


    private List<EstrenosBean> estrenos;

    public List<EstrenosBean> getEstrenos() {
        return estrenos;
    }

    public void setEstrenos(List<EstrenosBean> estrenos) {
        this.estrenos = estrenos;
    }

    public static class EstrenosBean {
        /**
         * id : 1
         * nombre_esp : Avengers Guerra del Infinito I
         * sinopsis : Vengadores Guerra del Infinito
         * genero : Fantasia
         * f_estreno : 1-10-2017
         * duracion : 143 minutos
         * poster1 : https://goo.gl/LCH6cR
         * nombre_original : Avengers Infinity War I
         * Reparto : Robert Downey Jr., Chris Hemsworth, Mark Ruffalo, Chris Evans.
         * poster2 : https://goo.gl/LCH6cR
         * estreno : 1
         */

        private String id;
        private String nombre_esp;
        private String sinopsis;
        private String genero;
        private String f_estreno;
        private String duracion;
        private String poster1;
        private String nombre_original;
        private String Reparto;
        private String poster2;
        private String estreno;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNombre_esp() {
            return nombre_esp;
        }

        public void setNombre_esp(String nombre_esp) {
            this.nombre_esp = nombre_esp;
        }

        public String getSinopsis() {
            return sinopsis;
        }

        public void setSinopsis(String sinopsis) {
            this.sinopsis = sinopsis;
        }

        public String getGenero() {
            return genero;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }

        public String getF_estreno() {
            return f_estreno;
        }

        public void setF_estreno(String f_estreno) {
            this.f_estreno = f_estreno;
        }

        public String getDuracion() {
            return duracion;
        }

        public void setDuracion(String duracion) {
            this.duracion = duracion;
        }

        public String getPoster1() {
            return poster1;
        }

        public void setPoster1(String poster1) {
            this.poster1 = poster1;
        }

        public String getNombre_original() {
            return nombre_original;
        }

        public void setNombre_original(String nombre_original) {
            this.nombre_original = nombre_original;
        }

        public String getReparto() {
            return Reparto;
        }

        public void setReparto(String Reparto) {
            this.Reparto = Reparto;
        }

        public String getPoster2() {
            return poster2;
        }

        public void setPoster2(String poster2) {
            this.poster2 = poster2;
        }

        public String getEstreno() {
            return estreno;
        }

        public void setEstreno(String estreno) {
            this.estreno = estreno;
        }
    }
}
