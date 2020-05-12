package p003.buscadortexto.modelos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 * Representa una tarea asíncrona para la búsqueda de texto en archivos de texto plano.
 * 
 * @author John Ortiz Ordoñez. 
 *
 */
public class BusquedaArchivosTask extends SwingWorker<Void, File> {
	
	/**
	 * Representa la lista de los archivos que el usuario ha seleccionado.
	 */
	private List<File> archivosSeleccionados;
	/**
	 * Clave de búsqueda sobre los archivos de texto plano.
	 */
	private String texto;
	/**
	 * Determina si se busca por expresión regular.
	 */
	private boolean buscarPorExpresionRegular;
	/**
	 * Modelo de datos de la tabla de resultados.
	 */
	private DefaultTableModel dtmResultados;

	/**
	 * Crea un nuevo objeto BusquedaArchivosTask con los parámetros básicos para la búsqueda y 
	 * la visualización de resultados en una tabla.
	 * 
	 * @param archivosSeleccionados Conjunto de archivos seleccionados.
	 * @param texto Clave de búsqueda.
	 * @param buscarPorExpresionRegular ¿Se debe buscar por expresión regular? (true: sí, false: no).
	 * @param dtmResultados Modelo de datos de la tabla de resultados.
	 */
	public BusquedaArchivosTask(List<File> archivosSeleccionados, String texto,
			boolean buscarPorExpresionRegular, DefaultTableModel dtmResultados) {
		this.archivosSeleccionados = archivosSeleccionados;
		this.texto = texto.toLowerCase();
		this.buscarPorExpresionRegular = buscarPorExpresionRegular;
		this.dtmResultados = dtmResultados;
	}

	/**
	 * Realiza una tarea de forma asíncrona en un thread (hilo) independiente. 
	 * 
	 * En segundo plano (background) se itera por cada archivo de texto plano a procesar (buscar).
	 */
	@Override
	protected Void doInBackground() throws Exception {
		for (File archivo : archivosSeleccionados) {
			buscarContenido(archivo);
		}
		
		return null;
	}

	/**
	 * Busca el contenido en el archivo de texto actual (File).
	 * 
	 * @param archivo Achivo de texto actual.
	 */
	@SuppressWarnings("static-access")
	private void buscarContenido(File archivo) {
		try {
			Scanner lector = new Scanner(archivo, "UTF-8");
			String linea;
			boolean encontrado = false;
			
			while(lector.hasNextLine()) {
				linea = lector.nextLine();
				
				if (buscarPorExpresionRegular) {
					if (Pattern.compile(texto).matcher(linea).find()) {
						encontrado = true;
						break;
					}
				} else {
					if (linea.toLowerCase().contains(texto)) {
						encontrado = true;
						break;
					}
				}
			}
			
			if (encontrado) {
				dtmResultados.addRow(new Object[] { archivo.getName() });
			}
		} catch(FileNotFoundException e) {
			
		}
	}

}
