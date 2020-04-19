package p002.inventario.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ClientesActualizarFormulario extends JInternalFrame {
	private JTextField txtCedula;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtCorreoElectronico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientesActualizarFormulario frame = new ClientesActualizarFormulario();
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
	public ClientesActualizarFormulario() {
		setTitle("Clientes - Actualizar");
		setBounds(100, 100, 450, 300);
		
		JPanel pnlClientesActualizar = new JPanel();
		pnlClientesActualizar.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(pnlClientesActualizar, BorderLayout.CENTER);
		pnlClientesActualizar.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblCedula = new JLabel("Cédula");
		pnlClientesActualizar.add(lblCedula, "2, 2");
		
		txtCedula = new JTextField();
		pnlClientesActualizar.add(txtCedula, "12, 2, fill, default");
		txtCedula.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		pnlClientesActualizar.add(btnBuscar, "12, 4");
		
		JLabel lblNombres = new JLabel("Nombres");
		pnlClientesActualizar.add(lblNombres, "2, 6");
		
		txtNombres = new JTextField();
		txtNombres.setEditable(false);
		pnlClientesActualizar.add(txtNombres, "12, 6, fill, default");
		txtNombres.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		pnlClientesActualizar.add(lblApellidos, "2, 8");
		
		txtApellidos = new JTextField();
		txtApellidos.setEditable(false);
		pnlClientesActualizar.add(txtApellidos, "12, 8, fill, default");
		txtApellidos.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Teléfono");
		pnlClientesActualizar.add(lblTelefono, "2, 10");
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		pnlClientesActualizar.add(txtTelefono, "12, 10, fill, default");
		txtTelefono.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Dirección");
		pnlClientesActualizar.add(lblDireccion, "2, 12");
		
		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		pnlClientesActualizar.add(txtDireccion, "12, 12, fill, default");
		txtDireccion.setColumns(10);
		
		JLabel lblCorreoElectronico = new JLabel("New label");
		pnlClientesActualizar.add(lblCorreoElectronico, "2, 14");
		
		txtCorreoElectronico = new JTextField();
		txtCorreoElectronico.setEditable(false);
		pnlClientesActualizar.add(txtCorreoElectronico, "12, 14, fill, default");
		txtCorreoElectronico.setColumns(10);
		
		JButton btnActualizar = new JButton("Actualizar");
		pnlClientesActualizar.add(btnActualizar, "12, 16");

	}

}
