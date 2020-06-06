package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	/**
	 * ID de serialización. 
	 */
	private static final long serialVersionUID = 2969386564630328442L;
	
	private JPanel pnlPrincipal;
	private JTextField txtTamagnioPorcentaje;
	private JTextField txtRotacionGrados;
	private JLabel lblImagen;

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
		
		lblImagen = new JLabel("");
		lblImagen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser dialogoCargaImagen = new JFileChooser();
				dialogoCargaImagen.addChoosableFileFilter(new FiltroSeleccionImagenes());
				dialogoCargaImagen.setAcceptAllFileFilterUsed(false);
				
				int resultado = dialogoCargaImagen.showOpenDialog(VentanaPrincipal.this);
				
				if (resultado == JFileChooser.APPROVE_OPTION) {
					File imagenSeleccionada = dialogoCargaImagen.getSelectedFile();
					
					ImageIcon imagen = new ImageIcon(imagenSeleccionada.getAbsolutePath());
					
					imagen = new ImageIcon(escalarImagen(convertirABufferedImage(imagen.getImage())));
					
					lblImagen.setIcon(imagen);
				}
			}
		});
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
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tamagnioPorcentaje = txtTamagnioPorcentaje.getText().trim();
				String gradosRotacion = txtRotacionGrados.getText().trim();
				
				if (!tamagnioPorcentaje.isEmpty()) {
					
				}
			}
		});
		pnlBotones.add(btnAplicar);
		
		JButton btnRestaurarImagen = new JButton("Restaurar imagen");
		pnlBotones.add(btnRestaurarImagen);
		
		JButton btnGuardarImagen = new JButton("Guardar imagen");
		pnlBotones.add(btnGuardarImagen);
		
		
		setLocationRelativeTo(null);
	}

	private BufferedImage convertirABufferedImage(Image imagen) {
		if (imagen instanceof BufferedImage) {
			return (BufferedImage) imagen;
		}
		
		BufferedImage bi = new BufferedImage(imagen.getWidth(null), imagen.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		
		Graphics g = bi.getGraphics();
		g.drawImage(imagen, 0, 0, null);
		g.dispose();
		
		return bi;
	}
	
	private Image escalarImagen(BufferedImage imagen) {
		return imagen.getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH);
	}
}


class FiltroSeleccionImagenes extends FileFilter {
	public final static String JPEG = "jpeg";
	public final static String JPG = "jpg";
	public final static String PNG = "png";
	
	@Override
	public boolean accept(File f) {
		
		if (f.isDirectory()) {
			return true;
		}
		
		String extension = obtenerExtensionArchivo(f);
		
		if (!extension.isEmpty()) {
			return extension.equals(JPEG) || extension.equals(JPG) || extension.equals(PNG);
		}
		
		return false;
	}
	@Override
	public String getDescription() {
		return "Sólo imágenes (JPEG, JPG, PNG).";
	}
	
	String obtenerExtensionArchivo(File archivo) {
		String extension = "";
		String nombreArchivo = archivo.getName();
		int indicePunto = nombreArchivo.lastIndexOf('.');
		
		if (indicePunto > 0 && indicePunto < nombreArchivo.length() - 1) {
			extension = nombreArchivo.substring(indicePunto + 1).toLowerCase();
		}
		
		return extension;
	}
}
