package modelos;

public class Cliente {
    private int id;
    private String documento;
    private String nombres;
    private String apellidos;
    private String correo;
    private int tipoDocumentoId;

    public Cliente() {
    }

    public Cliente(String documento, String nombres, String apellidos, String correo, int tipoDocumentoId) {
        this.documento = documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.tipoDocumentoId = tipoDocumentoId;
    }

    public Cliente(int id, String documento, String nombres, String apellidos, String correo, int tipoDocumentoId) {
        this.id = id;
        this.documento = documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.tipoDocumentoId = tipoDocumentoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTipoDocumentoId() {
        return tipoDocumentoId;
    }

    public void setTipoDocumentoId(int tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId;
    }

    @Override
    public String toString() {
        return nombres + " " + apellidos + " (" + documento + ")";
    }
}
