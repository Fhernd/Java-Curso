package p003.buscadortexto.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import p003.buscadortexto.modelos.BusquedaArchivosTask;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.awt.event.ActionEvent;

public class VentanaPrincipal {

	private JFrame frmVentanaPrincipal;
	private JTable tblArchivos;
	private JTextField txtTexto;
	private JTable tblResultados;
	private JCheckBox chkUsarBusquedaAvanzada;
	private JButton btnBuscar;
	private JButton btnDetenerBusqueda;

	private List<File> archivosSeleccionados;

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
		frmVentanaPrincipal.setBounds(100, 100, 508, 650);
		frmVentanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVentanaPrincipal.getContentPane().setLayout(null);

		JPanel pnlArchivos = new JPanel();
		pnlArchivos.setBorder(new TitledBorder(null, "Archivos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlArchivos.setBounds(10, 11, 474, 186);
		frmVentanaPrincipal.getContentPane().add(pnlArchivos);
		pnlArchivos.setLayout(null);

		JButton btnSeleccionarArchivos = new JButton("Seleccionar archivos...");
		btnSeleccionarArchivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser selectorArchivos = new JFileChooser("./");
				FileNameExtensionFilter fnef = new FileNameExtensionFilter("Archivos de texto", "txt", "text");
				selectorArchivos.setFileFilter(fnef);
				selectorArchivos.setMultiSelectionEnabled(true);

				if (selectorArchivos.showOpenDialog(frmVentanaPrincipal) == JFileChooser.APPROVE_OPTION) {
					File[] archivos = selectorArchivos.getSelectedFiles();

					archivosSeleccionados.addAll(Arrays.asList(archivos));

					JOptionPane.showMessageDialog(frmVentanaPrincipal,
							String.format("Se han agregado %d archivos.", archivos.length), "Información",
							JOptionPane.INFORMATION_MESSAGE);

					DefaultTableModel dtm = (DefaultTableModel) tblArchivos.getModel();

					for (File file : archivos) {
						String tamagnio = "";
						if (file.length() < 1_000_000) {
							tamagnio = String.format("%.2fKB", file.length() / 1024.0);
						} else {
							tamagnio = String.format("%.2fMB", file.length() / 1024.0 / 1024.0);
						}

						dtm.addRow(new Object[] { file.getName(), file.getParent(), tamagnio });
					}

					if (!archivosSeleccionados.isEmpty()) {
						txtTexto.setEnabled(true);
						chkUsarBusquedaAvanzada.setEnabled(true);
						btnBuscar.setEnabled(true);
					}
				}
			}
		});
		btnSeleccionarArchivos.setBounds(188, 22, 280, 23);
		pnlArchivos.add(btnSeleccionarArchivos);

		JScrollPane spnArchivos = new JScrollPane();
		spnArchivos.setBounds(10, 55, 454, 120);
		pnlArchivos.add(spnArchivos);

		tblArchivos = new JTable();
		tblArchivos
				.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Ruta", "Tama\u00F1o" }) {
					Class[] columnTypes = new Class[] { String.class, String.class, String.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}

					boolean[] columnEditables = new boolean[] { true, false, true };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		spnArchivos.setViewportView(tblArchivos);

		JPanel pnlBusqueda = new JPanel();
		pnlBusqueda
				.setBorder(new TitledBorder(null, "B\u00FAsqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlBusqueda.setBounds(10, 208, 474, 115);
		frmVentanaPrincipal.getContentPane().add(pnlBusqueda);
		pnlBusqueda.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblTexto = new JLabel("Texto:");
		pnlBusqueda.add(lblTexto, "2, 2");

		txtTexto = new JTextField();
		txtTexto.setEnabled(false);
		pnlBusqueda.add(txtTexto, "14, 2, 5, 1, fill, default");
		txtTexto.setColumns(10);

		chkUsarBusquedaAvanzada = new JCheckBox("¿Usar búsqueda avanzada?");
		chkUsarBusquedaAvanzada.setEnabled(false);
		pnlBusqueda.add(chkUsarBusquedaAvanzada, "14, 4");

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = txtTexto.getText().trim();

				if (texto.isEmpty()) {
					JOptionPane.showMessageDialog(frmVentanaPrincipal, "El campo Texto es obligatorio.", "Atención",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (chkUsarBusquedaAvanzada.isSelected()) {
					try {
						Pattern.compile(texto);
					} catch (PatternSyntaxException ex) {
						JOptionPane.showMessageDialog(frmVentanaPrincipal,
								"El campo Texto no representa una expresión regular.", "Atención",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
				}

				((DefaultTableModel) tblResultados.getModel()).setRowCount(0);

				BusquedaArchivosTask busqueda = new BusquedaArchivosTask(archivosSeleccionados, texto,
						chkUsarBusquedaAvanzada.isSelected());

				busqueda.addPropertyChangeListener((listener) -> {
					if (listener.getPropertyName().equals("progress")) {
						File archivo = (File) listener.getNewValue();

						DefaultTableModel dtmResultados = (DefaultTableModel) tblResultados.getModel();
						dtmResultados.addRow(new Object[] { archivo.getName() });
					}
				});
				
				btnSeleccionarArchivos.setEnabled(false);
				tblArchivos.setEnabled(false);
				txtTexto.setEnabled(false);
				chkUsarBusquedaAvanzada.setEnabled(false);
				btnBuscar.setEnabled(false);
				
				btnDetenerBusqueda.setEnabled(true);
				busqueda.execute();
			}
		});
		btnBuscar.setEnabled(false);
		pnlBusqueda.add(btnBuscar, "14, 6, 5, 1");

		JPanel pnlResultados = new JPanel();
		pnlResultados
				.setBorder(new TitledBorder(null, "Resultados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlResultados.setBounds(10, 331, 474, 279);
		frmVentanaPrincipal.getContentPane().add(pnlResultados);
		pnlResultados.setLayout(null);

		JProgressBar pgrProcesandoArchivos = new JProgressBar();
		pgrProcesandoArchivos.setBounds(10, 25, 146, 23);
		pnlResultados.add(pgrProcesandoArchivos);

		btnDetenerBusqueda = new JButton("Detener búsqueda");
		btnDetenerBusqueda.setEnabled(false);
		btnDetenerBusqueda.setBounds(175, 25, 293, 23);
		pnlResultados.add(btnDetenerBusqueda);

		JScrollPane spnResultados = new JScrollPane();
		spnResultados.setBounds(10, 59, 454, 209);
		pnlResultados.add(spnResultados);

		tblResultados = new JTable();
		tblResultados.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre archivo" }) {
			Class[] columnTypes = new Class[] { String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		spnResultados.setViewportView(tblResultados);

		archivosSeleccionados = new ArrayList<>();

		tblArchivos.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {

					int respuesta = JOptionPane.showConfirmDialog(frmVentanaPrincipal,
							"¿Está seguro de querer eliminar este archivo del listado?", "Confirmación",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (respuesta == JOptionPane.YES_OPTION) {
						int fila = tblArchivos.getSelectedRow();

						((DefaultTableModel) tblArchivos.getModel()).removeRow(fila);
						archivosSeleccionados.remove(fila);
					}
				}
			}
		});
	}
}
