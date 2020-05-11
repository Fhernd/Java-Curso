package p003.buscadortexto.modelos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.SwingWorker;

public class BusquedaArchivosTask extends SwingWorker<Void, File> {
	
	private List<File> archivosSeleccionados;
	private String texto;
	private boolean buscarPorExpresionRegular;

	public BusquedaArchivosTask(List<File> archivosSeleccionados, String texto,
			boolean buscarPorExpresionRegular) {
		this.archivosSeleccionados = archivosSeleccionados;
		this.texto = texto.toLowerCase();
		this.buscarPorExpresionRegular = buscarPorExpresionRegular;
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
			boolean continuar = true;
			
			while(continuar && lector.hasNextLine()) {
				linea = lector.nextLine();
				
				if (buscarPorExpresionRegular) {
					if (Pattern.compile(texto).matches(texto, linea)) {
						continuar = false;
					}
				} else {
					if (linea.toLowerCase().contains(texto)) {
						continuar = false;
					}
				}
			}
			
			if (continuar) {
				publish(archivo);
			}
		} catch(FileNotFoundException e) {
			
		}
	}

}
