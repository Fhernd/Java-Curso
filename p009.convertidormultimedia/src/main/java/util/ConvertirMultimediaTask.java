package util;

import java.io.File;

import javax.swing.SwingWorker;

import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

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
		try {
			AudioAttributes audioAttributes = new AudioAttributes();
			audioAttributes.setCodec("libmp3lame");
			audioAttributes.setBitRate(128000);
			audioAttributes.setChannels(2);
			audioAttributes.setSamplingRate(44100);
			
			EncodingAttributes encodingAttributes = new EncodingAttributes();
			encodingAttributes.setAudioAttributes(audioAttributes);
			
			Encoder encoder = new Encoder();
			encoder.encode(new MultimediaObject(archivoEntrada), archivoSalida, encodingAttributes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
