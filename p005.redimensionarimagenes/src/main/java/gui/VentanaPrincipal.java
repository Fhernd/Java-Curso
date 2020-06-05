package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JSeparator;

public class VentanaPrincipal extends JFrame {

	/**
	 * ID de serialización. 
	 */
	private static final long serialVersionUID = 2969386564630328442L;
	
	private JPanel pnlPrincipal;
	private JTextField txtTamagnioPorcentaje;
	private JTextField txtRotacionGrados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setTitle("Redimensionar Imágenes");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 560);
		pnlPrincipal = new JPanel();
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlPrincipal);
		pnlPrincipal.setLayout(new BoxLayout(pnlPrincipal, BoxLayout.Y_AXIS));
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setToolTipText("Haga click aquí para cargar una imagen");
		lblImagen.setSize(350, 500);
		lblImagen.setMaximumSize(new Dimension(400, 450));
		lblImagen.setPreferredSize(new Dimension(400, 450));
		lblImagen.setMaximumSize(new Dimension(400, 450));
		lblImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlPrincipal.add(lblImagen);
		
		JPanel pnlOperaciones = new JPanel();
		pnlOperaciones.setMaximumSize(new Dimension(400, 80));
		pnlOperaciones.setPreferredSize(new Dimension(400, 80));
		pnlOperaciones.setMaximumSize(new Dimension(400, 80));
		pnlOperaciones.setBorder(new TitledBorder(null, "Operaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlPrincipal.add(pnlOperaciones);
		pnlOperaciones.setLayout(new BoxLayout(pnlOperaciones, BoxLayout.Y_AXIS));
		
		JPanel pnlDatos = new JPanel();
		pnlDatos.setLayout(new BoxLayout(pnlDatos, BoxLayout.X_AXIS));
		
		JLabel lblTamagnioPorcentaje = new JLabel("Tamaño (%):  ");
		pnlDatos.add(lblTamagnioPorcentaje);
		
		txtTamagnioPorcentaje = new JTextField();
		pnlDatos.add(txtTamagnioPorcentaje);
		txtTamagnioPorcentaje.setColumns(10);
		
		JLabel lblRotacionGrados = new JLabel("  Rotación (grados):  ");
		pnlDatos.add(lblRotacionGrados);
		
		txtRotacionGrados = new JTextField();
		pnlDatos.add(txtRotacionGrados);
		txtRotacionGrados.setColumns(10);
		
		pnlOperaciones.add(pnlDatos);
		
		JSeparator separator = new JSeparator();
		pnlOperaciones.add(separator);
		
		JPanel pnlBotones = new JPanel();
		pnlBotones.setMaximumSize(new Dimension(400, 30));
		pnlBotones.setPreferredSize(new Dimension(400, 30));
		pnlBotones.setMaximumSize(new Dimension(400, 30));
		pnlOperaciones.add(pnlBotones);
		pnlBotones.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnAplicar = new JButton("Aplicar");
		pnlBotones.add(btnAplicar);
		
		JButton btnRestaurarImagen = new JButton("Restaurar imagen");
		pnlBotones.add(btnRestaurarImagen);
		
		JButton btnGuardarImagen = new JButton("Guardar imagen");
		pnlBotones.add(btnGuardarImagen);
		
		
		
	}

}
