package p003.buscadortexto.modelos;

import java.io.File;
import java.util.List;

import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

public class BusquedaArchivosTask extends SwingWorker<Void, Void> {
	
	private List<File> archivosSeleccionados;
	private DefaultTableModel dtmResultados;

	public BusquedaArchivosTask(List<File> archivosSeleccionados, DefaultTableModel dtmResultados) {
		this.archivosSeleccionados = archivosSeleccionados;
		this.dtmResultados = dtmResultados;
	}

	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
