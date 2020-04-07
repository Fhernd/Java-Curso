package modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Factura {
	private int id;
	private Date fecha;
	private String cedulaCliente;
	private double impuesto;
	private double total;
	private List<Integer> idsProductos;
	
	public Factura() {
		idsProductos = new ArrayList<>();
	}

	public Factura(String cedulaCliente, double impuesto) {
		this();
		fecha = new Date();
		this.cedulaCliente = cedulaCliente;
		this.impuesto = impuesto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	public double getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(double impuesto) {
		this.impuesto = impuesto;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	public void agregarIdProducto(int idProducto) {
		idsProductos.add(idProducto);
	}

	public Integer[] getIdsProductos() {
		Integer[] idsProductosCopia = new Integer[idsProductos.size()];
		idsProductos.toArray(idsProductosCopia);
		
		return idsProductosCopia;
	}
}
