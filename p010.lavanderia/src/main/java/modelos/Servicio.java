package modelos;

import java.time.LocalDateTime;

public class Servicio {
    private int id;
    private String descripcion;
    private LocalDateTime fechaHoraRecepcion;
    private LocalDateTime fechaHoraEntrega;
    private int empleadoId;
    private int clienteId;
    private int direccionId;
    private Empleado empleado;
    private Cliente cliente;
    private Direccion direccion;

    public Servicio() {
    }

    public Servicio(String descripcion, LocalDateTime fechaHoraRecepcion, LocalDateTime fechaHoraEntrega, int empleadoId, int clienteId, int direccionId) {
        this.descripcion = descripcion;
        this.fechaHoraRecepcion = fechaHoraRecepcion;
        this.fechaHoraEntrega = fechaHoraEntrega;
        this.empleadoId = empleadoId;
        this.clienteId = clienteId;
        this.direccionId = direccionId;
    }

    public Servicio(int id, String descripcion, LocalDateTime fechaHoraRecepcion, LocalDateTime fechaHoraEntrega, int empleadoId, int clienteId, int direccionId) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaHoraRecepcion = fechaHoraRecepcion;
        this.fechaHoraEntrega = fechaHoraEntrega;
        this.empleadoId = empleadoId;
        this.clienteId = clienteId;
        this.direccionId = direccionId;
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

    public LocalDateTime getFechaHoraRecepcion() {
        return fechaHoraRecepcion;
    }

    public void setFechaHoraRecepcion(LocalDateTime fechaHoraRecepcion) {
        this.fechaHoraRecepcion = fechaHoraRecepcion;
    }

    public LocalDateTime getFechaHoraEntrega() {
        return fechaHoraEntrega;
    }

    public void setFechaHoraEntrega(LocalDateTime fechaHoraEntrega) {
        this.fechaHoraEntrega = fechaHoraEntrega;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getDireccionId() {
        return direccionId;
    }

    public void setDireccionId(int direccionId) {
        this.direccionId = direccionId;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
