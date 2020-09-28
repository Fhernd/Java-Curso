package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.border.TitledBorder;

import util.ConvertirMultimediaTask;
import util.FiltroSeleccionMultimedia;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	/**
	 * ID de versión de la clase.
	 */
	private static final long serialVersionUID = -7157629293555435045L;
	private JPanel pnlPrincipal;
	private JTextField txtEntradaArchivo;
	private JTextField txtSalidaArchivo;
	private JComboBox cbxFormatos;

	Map<String, List<String>> formatosSalida;
	
	private String formatoEntrada;
	private String formatoSalida;
	
	private File archivoEntrada;
	private File archivoSalida;

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
		setResizable(false);
		setTitle("Convertidor Multimedia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 320);
		pnlPrincipal = new JPanel();
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlPrincipal);
		pnlPrincipal.setLayout(new BoxLayout(pnlPrincipal, BoxLayout.Y_AXIS));

		JPanel pnlEntrada = new JPanel();
		pnlEntrada.setBorder(new TitledBorder(null, "Entrada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlPrincipal.add(pnlEntrada);
		pnlEntrada.setLayout(new BoxLayout(pnlEntrada, BoxLayout.Y_AXIS));

		Component vst1 = Box.createVerticalStrut(20);
		pnlEntrada.add(vst1);

		JPanel pnlEntradaComponentes = new JPanel();
		pnlEntrada.add(pnlEntradaComponentes);
		pnlEntradaComponentes.setLayout(new BoxLayout(pnlEntradaComponentes, BoxLayout.X_AXIS));

		JLabel lblEntradaArchivo = new JLabel("Archivo:");
		pnlEntradaComponentes.add(lblEntradaArchivo);

		Component hst1 = Box.createHorizontalStrut(20);
		pnlEntradaComponentes.add(hst1);

		txtEntradaArchivo = new JTextField();
		txtEntradaArchivo.setEditable(false);
		pnlEntradaComponentes.add(txtEntradaArchivo);
		txtEntradaArchivo.setColumns(10);

		Component hst2 = Box.createHorizontalStrut(20);
		pnlEntradaComponentes.add(hst2);

		JButton btnEntradaSeleccionarArchivo = new JButton("Seleccionar...");
		btnEntradaSeleccionarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser dialogoSeleccionMultimedia = new JFileChooser();
				dialogoSeleccionMultimedia.addChoosableFileFilter(new FiltroSeleccionMultimedia());
				dialogoSeleccionMultimedia.setMultiSelectionEnabled(false);
				dialogoSeleccionMultimedia
						.removeChoosableFileFilter(dialogoSeleccionMultimedia.getAcceptAllFileFilter());

				if (dialogoSeleccionMultimedia.showOpenDialog(Principal.this) == JFileChooser.APPROVE_OPTION) {
					archivoEntrada = dialogoSeleccionMultimedia.getSelectedFile();

					String ruta = archivoEntrada.getAbsolutePath();

					txtEntradaArchivo.setText(ruta);

					String extension = identificarFormatoSalida(ruta).toLowerCase();

					if (!extension.isEmpty()) {
						cargarFormatosSalida(extension);
						formatoEntrada = extension;
					} else {
						JOptionPane.showMessageDialog(Principal.this, "El archivo seleccionado no tiene extensión.",
								"Mensaje", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		pnlEntradaComponentes.add(btnEntradaSeleccionarArchivo);

		Component vst2 = Box.createVerticalStrut(20);
		pnlEntrada.add(vst2);

		JPanel pnlSalida = new JPanel();
		pnlSalida.setBorder(new TitledBorder(null, "Salida", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlPrincipal.add(pnlSalida);
		pnlSalida.setLayout(new BoxLayout(pnlSalida, BoxLayout.Y_AXIS));

		Component vst3 = Box.createVerticalStrut(20);
		pnlSalida.add(vst3);

		JPanel pnlSalidaComponentes = new JPanel();
		pnlSalida.add(pnlSalidaComponentes);
		pnlSalidaComponentes.setLayout(new BoxLayout(pnlSalidaComponentes, BoxLayout.X_AXIS));

		JLabel lblSalidaArchivo = new JLabel("Archivo:");
		pnlSalidaComponentes.add(lblSalidaArchivo);

		Component hst3 = Box.createHorizontalStrut(20);
		pnlSalidaComponentes.add(hst3);

		txtSalidaArchivo = new JTextField();
		txtSalidaArchivo.setEditable(false);
		pnlSalidaComponentes.add(txtSalidaArchivo);
		txtSalidaArchivo.setColumns(10);

		Component hst4 = Box.createHorizontalStrut(20);
		pnlSalidaComponentes.add(hst4);

		JButton btnSalidaSeleccionarArchivo = new JButton("Seleccionar...");
		btnSalidaSeleccionarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indiceExtension = cbxFormatos.getSelectedIndex();

				if (indiceExtension == 0) {
					JOptionPane.showMessageDialog(Principal.this, "Primero debe seleccionar una extensión.", "Mensaje",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				JFileChooser selectorArchivoSalida = new JFileChooser();
				selectorArchivoSalida.setMultiSelectionEnabled(false);
				selectorArchivoSalida.setDialogTitle("Especique la ruta y el nombre del archivo a guardar...");

				int resultado = selectorArchivoSalida.showSaveDialog(Principal.this);

				if (resultado == JFileChooser.APPROVE_OPTION) {
					archivoSalida = selectorArchivoSalida.getSelectedFile();
					String nombreArchivo = archivoSalida.toString();

					if (!nombreArchivo.endsWith(cbxFormatos.getSelectedItem().toString())) {
						formatoSalida = cbxFormatos.getSelectedItem().toString();
						nombreArchivo = nombreArchivo.concat(".".concat(cbxFormatos.getSelectedItem().toString()));
					}

					txtSalidaArchivo.setText(nombreArchivo);
				}
			}
		});
		pnlSalidaComponentes.add(btnSalidaSeleccionarArchivo);

		Component vst4 = Box.createVerticalStrut(10);
		pnlSalida.add(vst4);

		cbxFormatos = new JComboBox();
		pnlSalida.add(cbxFormatos);

		Component vst5 = Box.createVerticalStrut(10);
		pnlSalida.add(vst5);

		Component vst6 = Box.createVerticalStrut(20);
		pnlPrincipal.add(vst6);

		JPanel pnlAccion = new JPanel();
		pnlPrincipal.add(pnlAccion);
		pnlAccion.setLayout(new BorderLayout(0, 0));

		JButton btnConvertir = new JButton("Convertir");
		btnConvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtEntradaArchivo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(Principal.this, "Primero debe seleccionar un archivo de entrada.",
							"Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (txtSalidaArchivo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(Principal.this, "Primero debe seleccionar un archivo de salida.",
							"Mensaje", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				archivoSalida = new File(txtSalidaArchivo.getText());

				ConvertirMultimediaTask tareaConversiones = new ConvertirMultimediaTask(formatoEntrada, formatoSalida,
						archivoEntrada, archivoSalida);
				
				tareaConversiones.execute();
			}
		});
		pnlAccion.add(btnConvertir);

		Component verticalStrut = Box.createVerticalStrut(20);
		pnlPrincipal.add(verticalStrut);

		formatosSalida = new HashMap<>();

		formatosSalida.put(FiltroSeleccionMultimedia.MPEG4, List.of("mp3", "wav", "mkv"));
		formatosSalida.put(FiltroSeleccionMultimedia.MP3, List.of("wav", "ogg"));
		cbxFormatos.addItem("Seleccione tipo de archivo para la salida...");
	}

	protected void cargarFormatosSalida(String extension) {
		List<String> extensionesDisponiblesSalida = formatosSalida.get(extension);

		if (extensionesDisponiblesSalida != null) {
			cbxFormatos.removeAllItems();

			cbxFormatos.addItem("Seleccione tipo de archivo para la salida...");

			extensionesDisponiblesSalida.forEach(e -> cbxFormatos.addItem(e));
		}
	}

	protected String identificarFormatoSalida(String ruta) {
		String extension = "";

		int indice = ruta.lastIndexOf('.');

		if (indice > 0 && indice < ruta.length() - 1) {
			extension = ruta.substring(indice + 1);
		}

		return extension;
	}

}
