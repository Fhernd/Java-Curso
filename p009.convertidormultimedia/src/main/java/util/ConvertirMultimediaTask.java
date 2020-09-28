package util;

import java.io.File;

import javax.swing.SwingWorker;

import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;
import ws.schild.jave.info.VideoSize;

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
		} else if (formatoEntrada.equals(FiltroSeleccionMultimedia.MPEG4) && formatoSalida.equals(FiltroSeleccionMultimedia.MKV)) {
			convertirMp4AMkv();
		}
		
		return null;
	}



	private void convertirMp4AMkv() {
		try {
			AudioAttributes audioAttributes = new AudioAttributes();
			audioAttributes.setCodec("libmp3lame");
			audioAttributes.setBitRate(64000);
			audioAttributes.setChannels(1);
			audioAttributes.setSamplingRate(22050);
			
			VideoAttributes videoAttributes = new VideoAttributes();
			videoAttributes.setCodec("mpeg4");
			videoAttributes.setBitRate(160000);
			videoAttributes.setFrameRate(15);
			videoAttributes.setSize(new VideoSize(400, 300));
			
			EncodingAttributes encodingAttributes = new EncodingAttributes();
			encodingAttributes.setAudioAttributes(audioAttributes);
			encodingAttributes.setVideoAttributes(videoAttributes);
			
			Encoder encoder = new Encoder();
			encoder.encode(new MultimediaObject(archivoEntrada), archivoSalida, encodingAttributes);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
