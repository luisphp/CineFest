package hurtado.luis.ejemplo.cinefest;

import java.util.List;

/**
 * Created by luis.hurtado on 22/02/2018.
 */
public class ResponseCartelera {


    private List<CarteleraBean> cartelera;

    public List<CarteleraBean> getCartelera() {
        return cartelera;
    }

    public void setCartelera(List<CarteleraBean> cartelera) {
        this.cartelera = cartelera;
    }

    public static class CarteleraBean {
        /**
         * id : 2
         * nombre_esp : La mujer maravilla: la ocupación de un lugar
         * sinopsis : Des será esta vez una mujer la encargada de salvar el mundo. Basada en el personaje de DC Comics, Mujer maravilla (2017), dirigida por Patty Jenkins, escrita a dúo por Allan Heinberg y Geoff Johns, tendrá como protagonista a una superheroína. Circunstancia de sentido no menor, si se tiene en cuenta el universo narrativo en el cual la película se inscribe, ocupado hasta el momento por los hombres y sus grandes poderes.
         * genero : Fantasia
         * f_estreno : 11-11-2017
         * duracion : 120 Minutos
         * poster1 : http://androidwstest.esy.es/imagenes/wonder_woman_1.jpg
         * nombre_original : Wonder Woman
         * Reparto : Gal Gadot, Chris Pine, Robin Wright , Elena Anaya, David Thewlis, Connie Nielsen, Ewen Bremner, Danny Huston, Said Taghmaoui
         * poster2 : http://androidwstest.esy.es/imagenes/wonder_woman_1.jpg
         * proximamente : 0
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
        private String proximamente;

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

        public String getProximamente() {
            return proximamente;
        }

        public void setProximamente(String proximamente) {
            this.proximamente = proximamente;
        }
    }
}
