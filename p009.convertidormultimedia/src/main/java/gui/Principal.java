package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.border.TitledBorder;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class Principal extends JFrame {

	/**
	 * ID de versión de la clase.
	 */
	private static final long serialVersionUID = -7157629293555435045L;
	private JPanel pnlPrincipal;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 292);
		pnlPrincipal = new JPanel();
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlPrincipal);
		pnlPrincipal.setLayout(new BoxLayout(pnlPrincipal, BoxLayout.Y_AXIS));
		
		Box verticalBox = Box.createVerticalBox();
		pnlPrincipal.add(verticalBox);
		
		JPanel pnlEntrada = new JPanel();
		pnlEntrada.setBorder(new TitledBorder(null, "Entrada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlPrincipal.add(pnlEntrada);
		pnlEntrada.setLayout(new BoxLayout(pnlEntrada, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		pnlEntrada.add(verticalStrut);
		
		JPanel pnlEntradaComponentes = new JPanel();
		pnlEntrada.add(pnlEntradaComponentes);
		pnlEntradaComponentes.setLayout(new BoxLayout(pnlEntradaComponentes, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("Archivo:");
		pnlEntradaComponentes.add(lblNewLabel);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		pnlEntradaComponentes.add(horizontalStrut);
		
		textField = new JTextField();
		pnlEntradaComponentes.add(textField);
		textField.setColumns(10);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		pnlEntradaComponentes.add(horizontalStrut_1);
		
		JButton btnNewButton = new JButton("Seleccionar...");
		pnlEntradaComponentes.add(btnNewButton);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnlEntrada.add(verticalStrut_1);
		
		JPanel pnlSalida = new JPanel();
		pnlSalida.setBorder(new TitledBorder(null, "Salida", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlPrincipal.add(pnlSalida);
		pnlSalida.setLayout(new BoxLayout(pnlSalida, BoxLayout.Y_AXIS));
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		pnlSalida.add(verticalStrut_2);
		
		JPanel pnlSalidaComponentes = new JPanel();
		pnlSalida.add(pnlSalidaComponentes);
		pnlSalidaComponentes.setLayout(new BoxLayout(pnlSalidaComponentes, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_1 = new JLabel("Archivo:");
		pnlSalidaComponentes.add(lblNewLabel_1);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		pnlSalidaComponentes.add(horizontalStrut_2);
		
		textField_1 = new JTextField();
		pnlSalidaComponentes.add(textField_1);
		textField_1.setColumns(10);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		pnlSalidaComponentes.add(horizontalStrut_3);
		
		JButton btnNewButton_1 = new JButton("Seleccionar...");
		pnlSalidaComponentes.add(btnNewButton_1);
		
		Component verticalStrut_3 = Box.createVerticalStrut(10);
		pnlSalida.add(verticalStrut_3);
		
		JComboBox comboBox = new JComboBox();
		pnlSalida.add(comboBox);
		
		Component verticalStrut_5 = Box.createVerticalStrut(10);
		pnlSalida.add(verticalStrut_5);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		pnlPrincipal.add(verticalStrut_4);
		
		JPanel panel = new JPanel();
		pnlPrincipal.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_2 = new JButton("Convertir");
		panel.add(btnNewButton_2);
	}

}
