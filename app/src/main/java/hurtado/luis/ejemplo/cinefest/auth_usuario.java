package hurtado.luis.ejemplo.cinefest;

/**
 * Created by luis.hurtado on 12/12/2017.
 */
public class auth_usuario {


    /**
     * id : 1
     * nombre : Luis
     * apellido : Hurtado
     * edad : 26
     * correo : oneil
     * contrasena : 12345
     * pregunta_secreta : Cual es tu mascota favorita
     * respuesta_secreta : camaleon
     * cedula : 19659937
     */

    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private String correo;
    private int contrasena;
    private String pregunta_secreta;
    private String respuesta_secreta;
    private int cedula;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getContrasena() {
        return contrasena;
    }

    public void setContrasena(int contrasena) {
        this.contrasena = contrasena;
    }

    public String getPregunta_secreta() {
        return pregunta_secreta;
    }

    public void setPregunta_secreta(String pregunta_secreta) {
        this.pregunta_secreta = pregunta_secreta;
    }

    public String getRespuesta_secreta() {
        return respuesta_secreta;
    }

    public void setRespuesta_secreta(String respuesta_secreta) {
        this.respuesta_secreta = respuesta_secreta;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
}
