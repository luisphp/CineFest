package hurtado.luis.ejemplo.cinefest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis.hurtado on 07/03/2018.
 */
public class ResponseFuncionesTest {

    @SerializedName("Planificacion")
    @Expose
    private List<Planificacion> planificacion = new ArrayList<Planificacion>();

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseFuncionesTest() {
    }

    /**
     *
     * @param planificacion
     */
    public ResponseFuncionesTest(List<Planificacion> planificacion) {
        super();
        this.planificacion = planificacion;
    }

    public List<Planificacion> getPlanificacion() {
        return planificacion;
    }

    public void setPlanificacion(List<Planificacion> planificacion) {
        this.planificacion = planificacion;
    }

    public ResponseFuncionesTest withPlanificacion(List<Planificacion> planificacion) {
        this.planificacion = planificacion;
        return this;
    }
    public class Planificacion {

        @SerializedName("fechas")
        @Expose
        private String fechas;
        @SerializedName("funciones")
        @Expose
        private List<Funcione> funciones = new ArrayList<Funcione>();

        /**
         * No args constructor for use in serialization
         *
         */
        public Planificacion() {
        }

        /**
         *
         * @param fechas
         * @param funciones
         */
        public Planificacion(String fechas, List<Funcione> funciones) {
            super();
            this.fechas = fechas;
            this.funciones = funciones;
        }

        public String getFechas() {
            return fechas;
        }

        public void setFechas(String fechas) {
            this.fechas = fechas;
        }

        public Planificacion withFechas(String fechas) {
            this.fechas = fechas;
            return this;
        }

        public List<Funcione> getFunciones() {
            return funciones;
        }

        public void setFunciones(List<Funcione> funciones) {
            this.funciones = funciones;
        }

        public Planificacion withFunciones(List<Funcione> funciones) {
            this.funciones = funciones;
            return this;
        }

    }

    public class HorarioSilla {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("sala")
        @Expose
        private String sala;
        @SerializedName("hora")
        @Expose
        private String hora;
        @SerializedName("asientos_oc")
        @Expose
        private String asientosOc;
        @SerializedName("fecha")
        @Expose
        private String fecha;
        @SerializedName("id_funcion")
        @Expose
        private String idFuncion;

        /**
         * No args constructor for use in serialization
         */
        public HorarioSilla() {
        }

        /**
         * @param id
         * @param hora
         * @param fecha
         * @param asientosOc
         * @param idFuncion
         * @param sala
         */
        public HorarioSilla(String id, String sala, String hora, String asientosOc, String fecha, String idFuncion) {
            super();
            this.id = id;
            this.sala = sala;
            this.hora = hora;
            this.asientosOc = asientosOc;
            this.fecha = fecha;
            this.idFuncion = idFuncion;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public HorarioSilla withId(String id) {
            this.id = id;
            return this;
        }

        public String getSala() {
            return sala;
        }

        public void setSala(String sala) {
            this.sala = sala;
        }

        public HorarioSilla withSala(String sala) {
            this.sala = sala;
            return this;
        }

        public String getHora() {
            return hora;
        }

        public void setHora(String hora) {
            this.hora = hora;
        }

        public HorarioSilla withHora(String hora) {
            this.hora = hora;
            return this;
        }

        public String getAsientosOc() {
            return asientosOc;
        }

        public void setAsientosOc(String asientosOc) {
            this.asientosOc = asientosOc;
        }

        public HorarioSilla withAsientosOc(String asientosOc) {
            this.asientosOc = asientosOc;
            return this;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public HorarioSilla withFecha(String fecha) {
            this.fecha = fecha;
            return this;
        }

        public String getIdFuncion() {
            return idFuncion;
        }

        public void setIdFuncion(String idFuncion) {
            this.idFuncion = idFuncion;
        }

        public HorarioSilla withIdFuncion(String idFuncion) {
            this.idFuncion = idFuncion;
            return this;
        }
    }

    public class Funcione {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("pelicula")
        @Expose
        private String pelicula;
        @SerializedName("fecha")
        @Expose
        private String fecha;
        @SerializedName("sala")
        @Expose
        private String sala;
        @SerializedName("cine")
        @Expose
        private String cine;
        @SerializedName("horario_sillas")
        @Expose
        private List<HorarioSilla> horarioSillas = new ArrayList<HorarioSilla>();

        /**
         * No args constructor for use in serialization
         *
         */
        public Funcione() {
        }

        /**
         *
         * @param id
         * @param fecha
         * @param cine
         * @param sala
         * @param horarioSillas
         * @param pelicula
         */
        public Funcione(String id, String pelicula, String fecha, String sala, String cine, List<HorarioSilla> horarioSillas) {
            super();
            this.id = id;
            this.pelicula = pelicula;
            this.fecha = fecha;
            this.sala = sala;
            this.cine = cine;
            this.horarioSillas = horarioSillas;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Funcione withId(String id) {
            this.id = id;
            return this;
        }

        public String getPelicula() {
            return pelicula;
        }

        public void setPelicula(String pelicula) {
            this.pelicula = pelicula;
        }

        public Funcione withPelicula(String pelicula) {
            this.pelicula = pelicula;
            return this;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public Funcione withFecha(String fecha) {
            this.fecha = fecha;
            return this;
        }

        public String getSala() {
            return sala;
        }

        public void setSala(String sala) {
            this.sala = sala;
        }

        public Funcione withSala(String sala) {
            this.sala = sala;
            return this;
        }

        public String getCine() {
            return cine;
        }

        public void setCine(String cine) {
            this.cine = cine;
        }

        public Funcione withCine(String cine) {
            this.cine = cine;
            return this;
        }

        public List<HorarioSilla> getHorarioSillas() {
            return horarioSillas;
        }

        public void setHorarioSillas(List<HorarioSilla> horarioSillas) {
            this.horarioSillas = horarioSillas;
        }

        public Funcione withHorarioSillas(List<HorarioSilla> horarioSillas) {
            this.horarioSillas = horarioSillas;
            return this;
        }

    }
}
