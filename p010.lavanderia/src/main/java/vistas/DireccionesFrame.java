package vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.GridLayout;

public class DireccionesFrame extends JInternalFrame {
	private JTextField txtDescripcion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DireccionesFrame frame = new DireccionesFrame();
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
	public DireccionesFrame() {
		setTitle("Direcciones");
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		JPanel pnlDatos = new JPanel();
		pnlDatos.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlDatos, BorderLayout.NORTH);
		pnlDatos.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblDescripcion = new JLabel("Dirección:");
		pnlDatos.add(lblDescripcion, "2, 2");
		
		txtDescripcion = new JTextField();
		pnlDatos.add(txtDescripcion, "10, 2, fill, default");
		txtDescripcion.setColumns(10);
		
		JLabel lblClienteId = new JLabel("Cliente:");
		pnlDatos.add(lblClienteId, "2, 4");
		
		JComboBox cbxClienteId = new JComboBox();
		pnlDatos.add(cbxClienteId, "10, 4, fill, default");
		
		JButton btnBuscarClientePordocumento = new JButton("Buscar cliente por documento...");
		pnlDatos.add(btnBuscarClientePordocumento, "10, 6, default, bottom");
		
		JPanel pnlBotones = new JPanel();
		pnlBotones.setBorder(new TitledBorder(null, "Acciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlBotones, BorderLayout.CENTER);
		pnlBotones.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnNuevo = new JButton("Nuevo");
		pnlBotones.add(btnNuevo);
		
		JButton btnNewButton = new JButton("Guardar");
		pnlBotones.add(btnNewButton);
		
		JButton btnBuscar = new JButton("Buscar...");
		pnlBotones.add(btnBuscar);
		
		JButton btnEditar = new JButton(" Editar");
		pnlBotones.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		pnlBotones.add(btnEliminar);

	}

}
