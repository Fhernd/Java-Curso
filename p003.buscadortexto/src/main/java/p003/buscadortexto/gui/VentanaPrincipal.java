package p003.buscadortexto.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JProgressBar;

public class VentanaPrincipal {

	private JFrame frmVentanaPrincipal;
	private JTable tblArchivos;
	private JTextField txtTexto;
	private JTable tblResultados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frmVentanaPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVentanaPrincipal = new JFrame();
		frmVentanaPrincipal.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmVentanaPrincipal.setTitle("Buscador Contenido Archivos de Texto Plano");
		frmVentanaPrincipal.setResizable(false);
		frmVentanaPrincipal.setBounds(100, 100, 500, 650);
		frmVentanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVentanaPrincipal.getContentPane().setLayout(null);
		
		JPanel pnlArchivos = new JPanel();
		pnlArchivos.setBorder(new TitledBorder(null, "Archivos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlArchivos.setBounds(10, 11, 474, 186);
		frmVentanaPrincipal.getContentPane().add(pnlArchivos);
		pnlArchivos.setLayout(null);
		
		JButton btnSeleccionarArchivos = new JButton("Seleccionar archivos...");
		btnSeleccionarArchivos.setBounds(188, 22, 280, 23);
		pnlArchivos.add(btnSeleccionarArchivos);
		
		JScrollPane spnArchivos = new JScrollPane();
		spnArchivos.setBounds(10, 67, 454, 108);
		pnlArchivos.add(spnArchivos);
		
		tblArchivos = new JTable();
		tblArchivos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Tama\u00F1o"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		spnArchivos.setViewportView(tblArchivos);
		
		JPanel pnlBusqueda = new JPanel();
		pnlBusqueda.setBorder(new TitledBorder(null, "B\u00FAsqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlBusqueda.setBounds(10, 208, 474, 112);
		frmVentanaPrincipal.getContentPane().add(pnlBusqueda);
		pnlBusqueda.setLayout(new FormLayout(new ColumnSpec[] {
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
		
		JLabel lblTexto = new JLabel("Texto:");
		pnlBusqueda.add(lblTexto, "2, 2");
		
		txtTexto = new JTextField();
		txtTexto.setEnabled(false);
		pnlBusqueda.add(txtTexto, "14, 2, 5, 1, fill, default");
		txtTexto.setColumns(10);
		
		JCheckBox chkUsarBusquedaAvanzada = new JCheckBox("¿Usar búsqueda avanzada?");
		chkUsarBusquedaAvanzada.setEnabled(false);
		pnlBusqueda.add(chkUsarBusquedaAvanzada, "14, 4");
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setEnabled(false);
		pnlBusqueda.add(btnBuscar, "14, 6, 5, 1");
		
		JPanel pnlResultados = new JPanel();
		pnlResultados.setBorder(new TitledBorder(null, "Resultados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlResultados.setBounds(10, 331, 474, 279);
		frmVentanaPrincipal.getContentPane().add(pnlResultados);
		pnlResultados.setLayout(null);
		
		JProgressBar pgrProcesandoArchivos = new JProgressBar();
		pgrProcesandoArchivos.setBounds(10, 25, 146, 23);
		pnlResultados.add(pgrProcesandoArchivos);
		
		JButton btnDetenerBusqueda = new JButton("Detener búsqueda");
		btnDetenerBusqueda.setBounds(175, 25, 293, 23);
		pnlResultados.add(btnDetenerBusqueda);
		
		JScrollPane spnResultados = new JScrollPane();
		spnResultados.setBounds(10, 59, 454, 209);
		pnlResultados.add(spnResultados);
		
		tblResultados = new JTable();
		tblResultados.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre archivo"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		spnResultados.setViewportView(tblResultados);
	}
}
