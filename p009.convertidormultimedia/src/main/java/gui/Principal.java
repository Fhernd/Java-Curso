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

public class Principal extends JFrame {

	/**
	 * ID de versión de la clase.
	 */
	private static final long serialVersionUID = -7157629293555435045L;
	private JPanel pnlPrincipal;
	private JTextField textField;

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
		setBounds(100, 100, 450, 200);
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
		
		JLabel lblNewLabel = new JLabel("New label");
		pnlEntradaComponentes.add(lblNewLabel);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		pnlEntradaComponentes.add(horizontalStrut);
		
		textField = new JTextField();
		pnlEntradaComponentes.add(textField);
		textField.setColumns(10);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		pnlEntradaComponentes.add(horizontalStrut_1);
		
		JButton btnNewButton = new JButton("New button");
		pnlEntradaComponentes.add(btnNewButton);
	}

}
