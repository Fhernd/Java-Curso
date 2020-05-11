package p003.buscadortexto.modelos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

public class BusquedaArchivosTask extends SwingWorker<Void, Void> {
	
	private List<File> archivosSeleccionados;
	private DefaultTableModel dtmResultados;
	private String texto;
	private boolean buscarPorExpresionRegular;

	public BusquedaArchivosTask(List<File> archivosSeleccionados, DefaultTableModel dtmResultados, String texto,
			boolean buscarPorExpresionRegular) {
		this.archivosSeleccionados = archivosSeleccionados;
		this.dtmResultados = dtmResultados;
		this.texto = texto;
		this.buscarPorExpresionRegular = buscarPorExpresionRegular;
	}

	@Override
	protected Void doInBackground() throws Exception {
		for (File archivo : archivosSeleccionados) {
			buscarContenido(archivo);
		}
		
		return null;
	}

	private void buscarContenido(File archivo) {
		try {
			Scanner lector = new Scanner(archivo, "UTF-8");
			String linea;
			
			while(lector.hasNextLine()) {
				linea = lector.nextLine();
				
				
			}
		} catch(FileNotFoundException e) {
			
		}
	}

}
