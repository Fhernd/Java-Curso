package modelos;

public class Direccion {
    private int id;
    private String descripcion;
    private int clienteId;

    public Direccion() {
    }

    public Direccion(String descripcion, int clienteId) {
        this.descripcion = descripcion;
        this.clienteId = clienteId;
    }

    public Direccion(int id, String descripcion, int clienteId) {
        this.id = id;
        this.descripcion = descripcion;
        this.clienteId = clienteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
