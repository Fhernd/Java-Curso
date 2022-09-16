package modelos;

public class Atencion {
    private int id;
    private int cantidad;
    private double precio;
    private int tipoAtencionId;
    private int servicioId;

    public Atencion() {
    }

    public Atencion(int cantidad, double precio, int tipoAtencionId, int servicioId) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.tipoAtencionId = tipoAtencionId;
        this.servicioId = servicioId;
    }

    public Atencion(int id, int cantidad, double precio, int tipoAtencionId, int servicioId) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio = precio;
        this.tipoAtencionId = tipoAtencionId;
        this.servicioId = servicioId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getTipoAtencionId() {
        return tipoAtencionId;
    }

    public void setTipoAtencionId(int tipoAtencionId) {
        this.tipoAtencionId = tipoAtencionId;
    }

    public int getServicioId() {
        return servicioId;
    }

    public void setServicioId(int servicioId) {
        this.servicioId = servicioId;
    }
}
