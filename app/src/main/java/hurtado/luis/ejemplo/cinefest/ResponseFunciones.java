package hurtado.luis.ejemplo.cinefest;

import java.util.List;

/**
 * Created by luis.hurtado on 28/02/2018.
 */
public class ResponseFunciones {


    private List<PlanificacionBean> Planificacion;

    public List<PlanificacionBean> getPlanificacion() {
        return Planificacion;
    }

    public void setPlanificacion(List<PlanificacionBean> Planificacion) {
        this.Planificacion = Planificacion;
    }

    public static class PlanificacionBean {
        /**
         * fechas : Lunes 26-02-2018
         * funciones : [{"id":"1","pelicula":"Avengers Guerra del Infinito I","fecha":"Lunes 26-02-2018","sala":"M1","cine":"Metropolis","horario_sillas":[{"id":"1","sala":"M1","hora":"1:30 pm","asientos_oc":"A2,B2,C2","fecha":"Lunes 26-02-2018","id_funcion":"1"},{"id":"2","sala":"M1","hora":"4:30 pm","asientos_oc":"A1,B1,C1","fecha":"Lunes 26-02-2018","id_funcion":"1"}]}]
         */

        private String fechas;
        private List<FuncionesBean> funciones;

        public String getFechas() {
            return fechas;
        }

        public void setFechas(String fechas) {
            this.fechas = fechas;
        }

        public List<FuncionesBean> getFunciones() {
            return funciones;
        }

        public void setFunciones(List<FuncionesBean> funciones) {
            this.funciones = funciones;
        }

        public static class FuncionesBean {
            /**
             * id : 1
             * pelicula : Avengers Guerra del Infinito I
             * fecha : Lunes 26-02-2018
             * sala : M1
             * cine : Metropolis
             * horario_sillas : [{"id":"1","sala":"M1","hora":"1:30 pm","asientos_oc":"A2,B2,C2","fecha":"Lunes 26-02-2018","id_funcion":"1"},{"id":"2","sala":"M1","hora":"4:30 pm","asientos_oc":"A1,B1,C1","fecha":"Lunes 26-02-2018","id_funcion":"1"}]
             */

            private String id;
            private String pelicula;
            private String fecha;
            private String sala;
            private String cine;
            private List<HorarioSillasBean> horario_sillas;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPelicula() {
                return pelicula;
            }

            public void setPelicula(String pelicula) {
                this.pelicula = pelicula;
            }

            public String getFecha() {
                return fecha;
            }

            public void setFecha(String fecha) {
                this.fecha = fecha;
            }

            public String getSala() {
                return sala;
            }

            public void setSala(String sala) {
                this.sala = sala;
            }

            public String getCine() {
                return cine;
            }

            public void setCine(String cine) {
                this.cine = cine;
            }

            public List<HorarioSillasBean> getHorario_sillas() {
                return horario_sillas;
            }

            public void setHorario_sillas(List<HorarioSillasBean> horario_sillas) {
                this.horario_sillas = horario_sillas;
            }

            public static class HorarioSillasBean {
                /**
                 * id : 1
                 * sala : M1
                 * hora : 1:30 pm
                 * asientos_oc : A2,B2,C2
                 * fecha : Lunes 26-02-2018
                 * id_funcion : 1
                 * cine: Metropolis
                 */

                private String id;
                private String sala;
                private String hora;
                private String asientos_oc;
                private String fecha;
                private String id_funcion;
                private String cine;



                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getSala() {
                    return sala;
                }

                public void setSala(String sala) {
                    this.sala = sala;
                }

                public String getHora() {
                    return hora;
                }

                public void setHora(String hora) {
                    this.hora = hora;
                }

                public String getAsientos_oc() {
                    return asientos_oc;
                }

                public void setAsientos_oc(String asientos_oc) {
                    this.asientos_oc = asientos_oc;
                }

                public String getFecha() {
                    return fecha;
                }

                public void setFecha(String fecha) {
                    this.fecha = fecha;
                }

                public String getId_funcion() {
                    return id_funcion;
                }

                public void setId_funcion(String id_funcion) {
                    this.id_funcion = id_funcion;
                }

                public String getCine(){return cine;}

                public void setCine(String cine) {this.cine = cine;}
            }
        }
    }
}
