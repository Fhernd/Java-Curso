package modelos;

public class Usuario {
    private int id;
    private String correo;
    private String clave;

    private int empleadoId;

    public Usuario() {
    }

    public Usuario(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }

    public Usuario(int id, String correo, String clave) {
        this.id = id;
        this.correo = correo;
        this.clave = clave;
    }

    public Usuario(String correo, String clave, int empleadoId) {
        this.correo = correo;
        this.clave = clave;
        this.empleadoId = empleadoId;
    }

    public Usuario(int id, String correo, String clave, int empleadoId) {
        this.id = id;
        this.correo = correo;
        this.clave = clave;
        this.empleadoId = empleadoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }
}
