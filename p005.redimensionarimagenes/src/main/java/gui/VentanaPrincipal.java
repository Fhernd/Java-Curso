package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Ventana principal de la aplicación.
 * 
 * @author John Ortiz Ordoñez
 *
 */
public class VentanaPrincipal extends JFrame {

	/**
	 * ID de serialización.
	 */
	private static final long serialVersionUID = 2969386564630328442L;

	/**
	 * Panel principal de la aplicaicón.
	 */
	private JPanel pnlPrincipal;
	/**
	 * Campo de texto para capturar el tamaño de redimensionamiento.
	 */
	private JTextField txtTamagnioPorcentaje;
	/**
	 * Campo de texto para capturar la rotación en grados.
	 */
	private JTextField txtRotacionGrados;
	/**
	 * Etiqueta de la imagen que se muestra en pantalla.
	 */
	private JLabel lblImagen;
	/**
	 * Representa la imagen que selección el usuario.
	 */
	private ImageIcon imagenOriginal;
	/**
	 * Botón para aplicar los cambios sobre la imagen.
	 */
	private JButton btnAplicar;
	/**
	 * Restaura la imagen original sobre la etiqueta.
	 */
	private JButton btnRestaurarImagen;
	/**
	 * Guarda la imagen en disco.
	 */
	private JButton btnGuardarImagen;

	/**
	 * Inicializa la ventana principal de la aplicación.
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

				BufferedImage bi = convertirABufferedImage(((ImageIcon) lblImagen.getIcon()).getImage());

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
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes JPG", "jpg");
				seleccionArchivo.setFileFilter(filtro);

				int resultado = seleccionArchivo.showSaveDialog(VentanaPrincipal.this);

				if (resultado == JFileChooser.APPROVE_OPTION) {
					File archivoImagen = seleccionArchivo.getSelectedFile();

					try {
						ImageIcon imagen = (ImageIcon) lblImagen.getIcon();

						guardarImagen(archivoImagen, imagen);

						JOptionPane.showMessageDialog(VentanaPrincipal.this, "La imagen se ha guardado en el sistema.",
								"Información", JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(VentanaPrincipal.this,
								"Se ha producido un error a la hora de guardar la imagen.", "Mensaje",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnGuardarImagen.setEnabled(false);
		pnlBotones.add(btnGuardarImagen);

		setLocationRelativeTo(null);
	}

	/**
	 * Guarda una imagen en el sistema de almacenamiento sobre ruta y archivo específicos.
	 * @param archivoImagen Archivo para la imagen que se va a guardar.
	 * @param imagen Imagen que se va a guardar.
	 * @throws IOException Captura cualquier excepción cuando se intenta guardar la imagen.
	 */
	protected void guardarImagen(File archivoImagen, ImageIcon imagen) throws IOException {
		BufferedImage bi = convertirABufferedImage(imagen.getImage());

		BufferedImage nuevaImagen = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
		ColorConvertOp nuevaImagenOp = new ColorConvertOp(null);
		nuevaImagenOp.filter(bi, nuevaImagen);

		ImageIO.write(nuevaImagen, "jpg", archivoImagen);
	}

	/**
	 * Restaura la imagen original que seleccionó el usuario. La imagen se restaura sobre 
	 * la etiqueta (o área).
	 */
	protected void restaurarImagen() {

		int resultado = JOptionPane.showConfirmDialog(this, "¿Está seguro de querer restaurar la imagen?",
				"Confirmación", JOptionPane.YES_NO_OPTION);

		if (resultado == JOptionPane.YES_OPTION) {
			lblImagen.setIcon(new ImageIcon(escalarImagen(convertirABufferedImage(imagenOriginal.getImage()))));
		}
	}

	/**
	 * Rota cierta cantidad de grados (radianes) una imagen.
	 * @param bi Imagen que se va a rotar.
	 * @param grados Cantidad de grados a rotar la imagen.
	 * @return Imagen modificada.
	 */
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

	/**
	 * Redimensiona una imagen cierta cantidad porcentual.
	 * @param bi Imagen a redimensionar.
	 * @param porcentaje Porcentaje de redimensionamiento.
	 * @return Imagen modificada.
	 */
	private BufferedImage redimensionarImagen(BufferedImage bi, int porcentaje) {
		double escala = porcentaje / 100.0;

		AffineTransform redimensionamiento = AffineTransform.getScaleInstance(escala, escala);
		AffineTransformOp op = new AffineTransformOp(redimensionamiento, AffineTransformOp.TYPE_BICUBIC);

		return op.filter(bi, null);
	}

	/**
	 * Convierte un objeto Image a un objeto BufferedImage.
	 * @param imagen Imagen sobre la que se obtendrá el objeto BufferedImage.
	 * @return Objeto BufferedImage.
	 */
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

	/**
	 * Escala una imagen para que quede visible dentro de un componente (JLabel).
	 * @param imagen Imagen a escalar.
	 * @return Imagen escalada.
	 */
	private Image escalarImagen(BufferedImage imagen) {
		return imagen.getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH);
	}
}

/**
 * Filtro para la selección de archivos de tipo imagen.
 * @author John Ortiz Ordoñez.
 *
 */
class FiltroSeleccionImagenes extends FileFilter {
	public final static String JPEG = "jpeg";
	public final static String JPG = "jpg";
	public final static String PNG = "png";

	/**
	 * Valida los archivos que se pueden escoger desde el diálogo de apertura.
	 * @param f Archivo.
	 * @return true si se puede seleccionar el archivo, false en caso contrario.
	 */
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

	/**
	 * Descripción para el filtro de selección de imagen desde el diálago de selección de archivo.
	 * 
	 * @return Indicador textual del tipo de imágenes que se pueden seleccionar.
	 */
	@Override
	public String getDescription() {
		return "Sólo imágenes (JPEG, JPG, PNG).";
	}

	/**
	 * Obtiene la extensión de un nombre de archivo.
	 * 
	 * @param archivo Archivo.
	 * @return Extensión del archivo.
	 */
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
