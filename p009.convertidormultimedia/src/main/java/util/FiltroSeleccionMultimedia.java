package util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FiltroSeleccionMultimedia extends FileFilter {
	
	private final static String MPEG4 = "mp4";
	private final static String MP3 = "mp3";
	private final static String WAV = "wav";

	@Override
	public boolean accept(File f) {
		if (f.isDirectory() ) {
			return true;
		}
		
		String extension = obtenerExtensionArchivo(f);
		
		if (!extension.isEmpty()) {
			return extension.equals(MPEG4) || extension.equals(MP3) || extension.equals(WAV);
		}
		
		return false;
	}

	private String obtenerExtensionArchivo(File archivo) {
		String extension = "";
		
		String nombreArchivo = archivo.getName();
		int indiceCaracterPunto = nombreArchivo.lastIndexOf('.');
		
		if (indiceCaracterPunto > 0 && indiceCaracterPunto < nombreArchivo.length() - 1) {
			extension = nombreArchivo.substring(indiceCaracterPunto + 1);
		}
		
		return extension;
	}

	@Override
	public String getDescription() {
		return "Sólo archivos multimedia (MP3, MP4, WAV, ...)";
	}

}
