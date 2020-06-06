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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
	private ImageIcon imagenOriginal;
	private JButton btnAplicar;
	private JButton btnRestaurarImagen;
	private JButton btnGuardarImagen;

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
					imagenOriginal = new ImageIcon(imagenSeleccionada.getAbsolutePath());

					imagen = new ImageIcon(escalarImagen(convertirABufferedImage(imagen.getImage())));

					lblImagen.setIcon(imagen);
					
					btnAplicar.setEnabled(true);
					btnRestaurarImagen.setEnabled(true);
					btnGuardarImagen.setEnabled(true);
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
		pnlOperaciones
				.setBorder(new TitledBorder(null, "Operaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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

		btnAplicar = new JButton("Aplicar");
		btnAplicar.setEnabled(false);
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tamagnioPorcentaje = txtTamagnioPorcentaje.getText().trim();
				String gradosRotacion = txtRotacionGrados.getText().trim();

				if (!tamagnioPorcentaje.isEmpty()) {
					try {
						int valor = Integer.parseInt(tamagnioPorcentaje);

						if (valor <= 0 || valor > 100) {
							JOptionPane.showMessageDialog(VentanaPrincipal.this,
									"El campo Tamaño (porcentaje) debe ser un número entero positivo.");
							return;
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(VentanaPrincipal.this,
								"El campo Tamaño (porcentaje) debe ser un número entero.");
						return;
					}
				}
				
				if (!gradosRotacion.isEmpty()) {
					try {
						int valor = Integer.parseInt(gradosRotacion);

						if (valor <= 0 || valor > 360) {
							JOptionPane.showMessageDialog(VentanaPrincipal.this,
									"El campo Rotación (grados) debe ser un número entero positivo.");
							return;
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(VentanaPrincipal.this,
								"El campo Rotación (grados) debe ser un número entero.");
						return;
					}
				}
				
				BufferedImage bi = convertirABufferedImage(((ImageIcon)lblImagen.getIcon()).getImage());
				
				int grado = gradosRotacion.isEmpty() ? 0 : Integer.parseInt(gradosRotacion);
				
				bi = rotarImagen(bi, grado);
				
				int porcentaje = tamagnioPorcentaje.isEmpty() ? 100 : Integer.parseInt(tamagnioPorcentaje);
				
				if (tamagnioPorcentaje.isEmpty() && grado % 90 != 0) {
					porcentaje = 50;
				}
				
				bi = redimensionarImagen(bi, porcentaje);
				
				lblImagen.setIcon(new ImageIcon(bi));
			}

			
		});
		pnlBotones.add(btnAplicar);

		btnRestaurarImagen = new JButton("Restaurar");
		btnRestaurarImagen.setEnabled(false);
		btnRestaurarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restaurarImagen();
			}
		});
		pnlBotones.add(btnRestaurarImagen);

		btnGuardarImagen = new JButton("Guardar");
		btnGuardarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser seleccionArchivo = new JFileChooser();
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes PNG", "png");
				seleccionArchivo.setFileFilter(filtro);
				
				int resultado = seleccionArchivo.showSaveDialog(VentanaPrincipal.this);
				
				if (resultado == JFileChooser.APPROVE_OPTION) {
					File archivoImagen = seleccionArchivo.getSelectedFile();
					
					try {
						ImageIcon imagen = (ImageIcon) lblImagen.getIcon();
						
						
					} catch (Exception ex) {
						
					}
				}
			}
		});
		btnGuardarImagen.setEnabled(false);
		pnlBotones.add(btnGuardarImagen);

		setLocationRelativeTo(null);
	}
	
	protected void restaurarImagen() {
		
		int resultado = JOptionPane.showConfirmDialog(this, "¿Está seguro de querer restaurar la imagen?", "Confirmación", JOptionPane.YES_NO_OPTION);
		
		if (resultado == JOptionPane.YES_OPTION) {
			lblImagen.setIcon(new ImageIcon(escalarImagen(convertirABufferedImage(imagenOriginal.getImage()))));
		}
	}

	protected BufferedImage rotarImagen(BufferedImage bi, int grados) {
		double radianes = Math.toRadians(grados);
		
		AffineTransform rotacion = AffineTransform.getRotateInstance(radianes);
		
		int ancho = bi.getWidth();
		int alto = bi.getHeight();
		
		Rectangle rectangulo = rotacion.createTransformedShape(new Rectangle(ancho, alto)).getBounds();
		
		rotacion = new AffineTransform();
		rotacion.translate(rectangulo.width * 0.5, rectangulo.height * 0.5);
		rotacion.rotate(radianes);
		rotacion.translate(-0.5 * ancho, -0.5 * alto);
		
		AffineTransformOp op = new AffineTransformOp(rotacion, AffineTransformOp.TYPE_BILINEAR);
		
		return op.filter(bi, null);
	}

	private BufferedImage redimensionarImagen(BufferedImage bi, int valor) {
		double escala = valor / 100.0;
		
		AffineTransform redimensionamiento = AffineTransform.getScaleInstance(escala, escala);
		AffineTransformOp op = new AffineTransformOp(redimensionamiento, AffineTransformOp.TYPE_BICUBIC);
		
		return op.filter(bi, null);
	}

	private BufferedImage convertirABufferedImage(Image imagen) {
		if (imagen instanceof BufferedImage) {
			return (BufferedImage) imagen;
		}

		BufferedImage bi = new BufferedImage(imagen.getWidth(null), imagen.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);

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
