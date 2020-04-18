package modelos;

import java.util.ArrayList;
import java.util.List;

public class GestionInventario {
	
	private List<Cliente> clientes;
	private List<Proveedor> proveedores;
	private List<Producto> productos;
	private List<Factura> facturas;
	
	public GestionInventario() {
		clientes = new ArrayList<>();
		proveedores = new ArrayList<>();
		productos = new ArrayList<>();
		facturas = new ArrayList<>();
	}

	public Cliente buscarClientePorCedula(String cedula) {
		return clientes.stream().filter(c -> c.getCedula().equals(cedula)).findFirst().orElse(null);
	}

}
