package p002.inventario.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;

public class Aplicacion {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplicacion window = new Aplicacion();
					window.frame.setVisible(true);
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnuArchivo = new JMenu("Archivo");
		menuBar.add(mnuArchivo);
		
		JMenuItem mniSalir = new JMenuItem("Salir");
		mnuArchivo.add(mniSalir);
		
		JMenu mnuClientes = new JMenu("Clientes");
		menuBar.add(mnuClientes);
		
		JMenuItem mniClientesCrear = new JMenuItem("Crear");
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
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
	}
}
