package p003.buscadortexto.modelos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

public class BusquedaArchivosTask extends SwingWorker<Void, File> {
	
	private List<File> archivosSeleccionados;
	private String texto;
	private boolean buscarPorExpresionRegular;
	private DefaultTableModel dtmResultados;

	public BusquedaArchivosTask(List<File> archivosSeleccionados, String texto,
			boolean buscarPorExpresionRegular, DefaultTableModel dtmResultados) {
		this.archivosSeleccionados = archivosSeleccionados;
		this.texto = texto.toLowerCase();
		this.buscarPorExpresionRegular = buscarPorExpresionRegular;
		this.dtmResultados = dtmResultados;
	}

	@Override
	protected Void doInBackground() throws Exception {
		for (File archivo : archivosSeleccionados) {
			buscarContenido(archivo);
		}
		
		return null;
	}

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
