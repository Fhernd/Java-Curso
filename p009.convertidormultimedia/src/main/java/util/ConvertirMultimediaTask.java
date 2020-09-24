package util;

import java.io.File;

import javax.swing.SwingWorker;

public class ConvertirMultimediaTask extends SwingWorker<Void, Void> {
	
	private String formatoEntrada;
	private String formatoSalida;
	private File archivoEntrada;
	private File archivoSalida;
	
	

	public ConvertirMultimediaTask(String formatoEntrada, String formatoSalida, File archivoEntrada,
			File archivoSalida) {
		this.formatoEntrada = formatoEntrada;
		this.formatoSalida = formatoSalida;
		this.archivoEntrada = archivoEntrada;
		this.archivoSalida = archivoSalida;
	}



	@Override
	protected Void doInBackground() throws Exception {
		
		if (formatoEntrada.equals(FiltroSeleccionMultimedia.MPEG4) && formatoSalida.equals(FiltroSeleccionMultimedia.MP3)) {
			convertirMp4AMp3();
		}
		
		return null;
	}



	private void convertirMp4AMp3() {
		
	}

}
