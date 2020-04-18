package p002.inventario.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import modelos.Cliente;
import modelos.GestionInventario;

import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Aplicacion {

	private static final int CODIGO_SALIDA_SATISFACTORIA = 0;
	private JFrame frmSistemaDeGestion;
	private JDesktopPane dpnEscritorio;
	private GestionInventario gestionInventario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplicacion window = new Aplicacion();
					window.frmSistemaDeGestion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Aplicacion() {
		initialize();
		
		gestionInventario = new GestionInventario();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistemaDeGestion = new JFrame();
		frmSistemaDeGestion.setTitle("Sistema de Gestión de Inventario - Ferretería Don Tuerca");
		frmSistemaDeGestion.setBounds(100, 100, 800, 600);
		frmSistemaDeGestion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frmSistemaDeGestion.setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		frmSistemaDeGestion.setJMenuBar(menuBar);

		JMenu mnuArchivo = new JMenu("Archivo");
		menuBar.add(mnuArchivo);

		JMenuItem mniSalir = new JMenuItem("Salir");
		mniSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resultado = JOptionPane.showConfirmDialog(frmSistemaDeGestion, "¿Desea cerrar la aplicación?", "Confirmación",
						JOptionPane.YES_NO_OPTION);
				
				if (resultado == JOptionPane.YES_OPTION) {
					System.exit(CODIGO_SALIDA_SATISFACTORIA);
				}
			}
		});
		mnuArchivo.add(mniSalir);

		JMenu mnuClientes = new JMenu("Clientes");
		menuBar.add(mnuClientes);

		JMenuItem mniClientesCrear = new JMenuItem("Crear");
		mniClientesCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientesCrearFormulario clientesCrearFormulario = new ClientesCrearFormulario(Aplicacion.this);
				dpnEscritorio.add(clientesCrearFormulario);
				clientesCrearFormulario.show();
			}
		});
		mnuClientes.add(mniClientesCrear);

		JMenuItem mniClientesBuscar = new JMenuItem("Buscar");
		mnuClientes.add(mniClientesBuscar);

		JMenuItem mniClientesActualizar = new JMenuItem("Actualizar");
		mnuClientes.add(mniClientesActualizar);

		JMenuItem mniClientesEliminar = new JMenuItem("Eliminar");
		mnuClientes.add(mniClientesEliminar);

		JMenu mnuProveedores = new JMenu("Proveedores");
		menuBar.add(mnuProveedores);

		JMenuItem mniProveedoresCrear = new JMenuItem("Crear");
		mnuProveedores.add(mniProveedoresCrear);

		JMenuItem mniProveedoresBuscar = new JMenuItem("Buscar");
		mnuProveedores.add(mniProveedoresBuscar);

		JMenuItem mniProveedoresActualizar = new JMenuItem("Actualizar");
		mnuProveedores.add(mniProveedoresActualizar);

		JMenuItem mniProveedoresEliminar = new JMenuItem("Eliminar");
		mnuProveedores.add(mniProveedoresEliminar);

		JMenu mnuProductos = new JMenu("Productos");
		menuBar.add(mnuProductos);

		JMenuItem mniProductosCrear = new JMenuItem("Crear");
		mnuProductos.add(mniProductosCrear);

		JMenuItem mniProductosBuscar = new JMenuItem("Buscar");
		mnuProductos.add(mniProductosBuscar);

		JMenuItem mniProductosActualizar = new JMenuItem("Actualizar");
		mnuProductos.add(mniProductosActualizar);

		JMenuItem mniProductosEliminar = new JMenuItem("Eliminar");
		mnuProductos.add(mniProductosEliminar);

		JMenu mnuFacturacion = new JMenu("Facturación");
		menuBar.add(mnuFacturacion);

		JMenuItem mniFacturacionCrear = new JMenuItem("Crear");
		mnuFacturacion.add(mniFacturacionCrear);

		JMenuItem mniFacturacionBuscar = new JMenuItem("Buscar");
		mnuFacturacion.add(mniFacturacionBuscar);

		JMenu mnuAyuda = new JMenu("Ayuda");
		menuBar.add(mnuAyuda);

		JMenuItem mniAcercaDe = new JMenuItem("Acerca de");
		mnuAyuda.add(mniAcercaDe);
		frmSistemaDeGestion.getContentPane().setLayout(new BorderLayout(0, 0));

		dpnEscritorio = new JDesktopPane();
		frmSistemaDeGestion.getContentPane().add(dpnEscritorio, BorderLayout.CENTER);
	}

	public Cliente buscarClientePorCedula(String cedula) {
		return gestionInventario.buscarClientePorCedula(cedula);
	}

	public void crearCliente(Cliente cliente) {
		gestionInventario.crearCliente(cliente);
	}
}
