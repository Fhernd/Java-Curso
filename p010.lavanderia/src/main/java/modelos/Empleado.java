package modelos;

public class Empleado {
    private int id;
    private String nombres;
    private String apellidos;
    private double sueldo;
    private int rolId;
    private int usuarioId;

    public Empleado() {
    }

    public Empleado(String nombres, String apellidos, double sueldo, int rolId, int usuarioId) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sueldo = sueldo;
        this.rolId = rolId;
        this.usuarioId = usuarioId;
    }

    public Empleado(int id, String nombres, String apellidos, double sueldo, int rolId, int usuarioId) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sueldo = sueldo;
        this.rolId = rolId;
        this.usuarioId = usuarioId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
