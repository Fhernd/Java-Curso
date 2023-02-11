package vistas;

import javax.swing.*;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.toedter.calendar.JDateChooser;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utilidades.Utilidad;

import java.io.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class GeneracionReportesFrame extends JInternalFrame {

    private GestorLavanderiaGUI gestorLavanderiaGUI;

    /**
     * Serial version ID.
     */
    private static final long serialVersionUID = 8174451787899692365L;
    private JSpinner spnCantidadClientes;
    private JDateChooser datReporte1FechaInicio;
    private JDateChooser datReporte1FechaFinal;
    private JDateChooser datReporte3FechaInicio;
    private JDateChooser datReporte3FechaFinal;

    /**
     * Create the frame.
     */
    public GeneracionReportesFrame(GestorLavanderiaGUI gestorLavanderiaGUI) {
        this.gestorLavanderiaGUI = gestorLavanderiaGUI;

        setTitle("Generación de Reportes");
        setBounds(100, 100, 500, 540);
        getContentPane().setLayout(new GridLayout(4, 1, 0, 0));

        JPanel pnlReporte1 = new JPanel();
        pnlReporte1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Reporte 1: Clientes con m\u00E1s servicios solicitados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        getContentPane().add(pnlReporte1);
        pnlReporte1.setLayout(new FormLayout(new ColumnSpec[]{
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"),},
                new RowSpec[]{
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        RowSpec.decode("default:grow"),
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        RowSpec.decode("default:grow"),
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        RowSpec.decode("default:grow"),}));

        JLabel lblReporte1CantidadClientes = new JLabel("Cantidad clientes:");
        pnlReporte1.add(lblReporte1CantidadClientes, "2, 2");

        spnCantidadClientes = new JSpinner();
        pnlReporte1.add(spnCantidadClientes, "12, 2");

        JLabel lblReporte1FechaInicio = new JLabel("Fecha inicio:");
        pnlReporte1.add(lblReporte1FechaInicio, "2, 4");

        datReporte1FechaInicio = new JDateChooser();
        pnlReporte1.add(datReporte1FechaInicio, "12, 4, fill, fill");

        JLabel lblReporte1FechaFinal = new JLabel("Fecha final:");
        pnlReporte1.add(lblReporte1FechaFinal, "2, 6");

        datReporte1FechaFinal = new JDateChooser();
        pnlReporte1.add(datReporte1FechaFinal, "12, 6, fill, fill");

        JPanel pnlReporte1Botones = new JPanel();
        pnlReporte1.add(pnlReporte1Botones, "12, 8, fill, fill");
        pnlReporte1Botones.setLayout(new GridLayout(0, 2, 10, 0));

        JButton btnReporte1Visualizar = new JButton("Visualizar");
        btnReporte1Visualizar.addActionListener(e -> {
            if (validarDatosReporte1()) {
                final int cantidadClientes = (int) spnCantidadClientes.getValue();
                final String fechaInicio = Utilidad.fechaToString(datReporte1FechaInicio.getDate()) + " 00:00:00";
                final String fechaFinal = Utilidad.fechaToString(datReporte1FechaFinal.getDate()) + " 23:59:59";

                Map parametrosReporte = new HashMap();
                parametrosReporte.put("cantidadClientes", cantidadClientes);
                parametrosReporte.put("fechaInicio", fechaInicio);
                parametrosReporte.put("fechaFinal", fechaFinal);

                visualizarReporte(parametrosReporte, "/reportes/Reporte1ClienteServicios.jasper");
            }
        });
        pnlReporte1Botones.add(btnReporte1Visualizar);

        JButton btnReporte1Guardar = new JButton("Guardar...");
        btnReporte1Guardar.addActionListener(e -> {
            if (validarDatosReporte1()) {
                final int cantidadClientes = (int) spnCantidadClientes.getValue();
                final String fechaInicio = Utilidad.fechaToString(datReporte1FechaInicio.getDate()) + " 00:00:00";
                final String fechaFinal = Utilidad.fechaToString(datReporte1FechaFinal.getDate()) + " 23:59:59";

                Map parametrosReporte = new HashMap();
                parametrosReporte.put("cantidadClientes", cantidadClientes);
                parametrosReporte.put("fechaInicio", fechaInicio);
                parametrosReporte.put("fechaFinal", fechaFinal);

                guardarReporte(parametrosReporte, "/reportes/Reporte1ClienteServicios.jasper");
            }
        });
        pnlReporte1Botones.add(btnReporte1Guardar);

        JPanel pnlReporte2 = new JPanel();
        pnlReporte2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Reporte 2: Tipos de atenci\u00F3n m\u00E1s solicitados (10)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        getContentPane().add(pnlReporte2);
        pnlReporte2.setLayout(new GridLayout(2, 1, 0, 15));

        JButton btnReporte2Visualizar = new JButton("Visualizar");
        btnReporte2Visualizar.addActionListener(e -> {
            Map parametrosReporte = new HashMap();
            visualizarReporte(parametrosReporte, "/reportes/Reporte2TipoAtencionMasSolicitados.jasper");
        });
        pnlReporte2.add(btnReporte2Visualizar);

        JButton btnReporte2Guardar = new JButton("Guardar...");
        btnReporte2Guardar.addActionListener(e -> {
            Map parametrosReporte = new HashMap();
            guardarReporte(parametrosReporte, "/reportes/Reporte2TipoAtencionMasSolicitados.jasper");
        });
        pnlReporte2.add(btnReporte2Guardar);

        JPanel pnlReporte3 = new JPanel();
        pnlReporte3.setBorder(new TitledBorder(null, "Reporte 3: Empleados con Mayor N\u00FAmero de Atenci\u00F3n de Servicios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(pnlReporte3);
        pnlReporte3.setLayout(new FormLayout(new ColumnSpec[]{
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"),},
                new RowSpec[]{
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        RowSpec.decode("default:grow"),
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        RowSpec.decode("default:grow"),
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        RowSpec.decode("default:grow"),}));

        JLabel lblReporte3FechaInicio = new JLabel("Fecha inicio.");
        pnlReporte3.add(lblReporte3FechaInicio, "2, 2");

        datReporte3FechaInicio = new JDateChooser();
        pnlReporte3.add(datReporte3FechaInicio, "14, 2, fill, fill");

        JLabel lblReporte3FechaFinal = new JLabel("Fecha final:");
        pnlReporte3.add(lblReporte3FechaFinal, "2, 4");

        datReporte3FechaFinal = new JDateChooser();
        pnlReporte3.add(datReporte3FechaFinal, "14, 4, fill, fill");

        JPanel pnlReporte3Botones = new JPanel();
        pnlReporte3.add(pnlReporte3Botones, "14, 6, fill, fill");
        pnlReporte3Botones.setLayout(new GridLayout(0, 2, 10, 0));

        JButton btnReporte3Visualizar = new JButton("Visualizar");
        btnReporte3Visualizar.addActionListener(e -> {
            if (validarDatosReporte3y4(datReporte3FechaInicio, datReporte3FechaFinal)) {
                final String fechaInicio = Utilidad.fechaToString(datReporte3FechaInicio.getDate()) + " 00:00:00";
                final String fechaFinal = Utilidad.fechaToString(datReporte3FechaFinal.getDate()) + " 23:59:59";

                Map parametrosReporte = new HashMap();
                parametrosReporte.put("fechaInicio", fechaInicio);
                parametrosReporte.put("fechaFinal", fechaFinal);

                visualizarReporte(parametrosReporte, "/reportes/Reporte3EmpleadosMasActivos.jasper");
            }
        });
        pnlReporte3Botones.add(btnReporte3Visualizar);

        JButton btnReporte3Guardar = new JButton("Guardar...");
        btnReporte3Guardar.addActionListener(e -> {
            if (validarDatosReporte3y4(datReporte3FechaInicio, datReporte3FechaFinal)) {
                final String fechaInicio = Utilidad.fechaToString(datReporte3FechaInicio.getDate()) + " 00:00:00";
                final String fechaFinal = Utilidad.fechaToString(datReporte3FechaFinal.getDate()) + " 23:59:59";

                Map parametrosReporte = new HashMap();
                parametrosReporte.put("fechaInicio", fechaInicio);
                parametrosReporte.put("fechaFinal", fechaFinal);

                guardarReporte(parametrosReporte, "/reportes/Reporte3EmpleadosMasActivos.jasper");
            }
        });
        pnlReporte3Botones.add(btnReporte3Guardar);
        
        JPanel pnlReporte4 = new JPanel();
        pnlReporte4.setBorder(new TitledBorder(null, "Reporte 4: Reporte de Ganancias para un Intervalo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(pnlReporte4);
        pnlReporte4.setLayout(new FormLayout(new ColumnSpec[] {
        		FormSpecs.RELATED_GAP_COLSPEC,
        		FormSpecs.DEFAULT_COLSPEC,
        		FormSpecs.RELATED_GAP_COLSPEC,
        		FormSpecs.DEFAULT_COLSPEC,
        		FormSpecs.RELATED_GAP_COLSPEC,
        		FormSpecs.DEFAULT_COLSPEC,
        		FormSpecs.RELATED_GAP_COLSPEC,
        		FormSpecs.DEFAULT_COLSPEC,
        		FormSpecs.RELATED_GAP_COLSPEC,
        		FormSpecs.DEFAULT_COLSPEC,
        		FormSpecs.RELATED_GAP_COLSPEC,
        		FormSpecs.DEFAULT_COLSPEC,
        		FormSpecs.RELATED_GAP_COLSPEC,
        		ColumnSpec.decode("default:grow"),},
        	new RowSpec[] {
        		FormSpecs.RELATED_GAP_ROWSPEC,
        		RowSpec.decode("default:grow"),
        		FormSpecs.RELATED_GAP_ROWSPEC,
        		RowSpec.decode("default:grow"),
        		FormSpecs.RELATED_GAP_ROWSPEC,
        		RowSpec.decode("default:grow"),}));
        
        JLabel lblReporte4FechaInicio = new JLabel("Fecha inicio:");
        pnlReporte4.add(lblReporte4FechaInicio, "2, 2");
        
        JDateChooser datReporte4FechaInicio = new JDateChooser();
        pnlReporte4.add(datReporte4FechaInicio, "14, 2, fill, fill");
        
        JLabel lblReporte4FechaFinal = new JLabel("Fecha final:");
        pnlReporte4.add(lblReporte4FechaFinal, "2, 4");
        
        JDateChooser datReporte4FechaFinal = new JDateChooser();
        pnlReporte4.add(datReporte4FechaFinal, "14, 4, fill, fill");
        
        JPanel pnlReporte4Botones = new JPanel();
        pnlReporte4.add(pnlReporte4Botones, "14, 6, fill, fill");
        pnlReporte4Botones.setLayout(new GridLayout(0, 2, 10, 0));
        
        JButton btnReporte4Visualizar = new JButton("Visualizar");
        btnReporte4Visualizar.addActionListener(e -> {
            if (validarDatosReporte3y4(datReporte4FechaInicio, datReporte4FechaFinal)) {
                final String fechaInicio = Utilidad.fechaToString(datReporte4FechaInicio.getDate()) + " 00:00:00";
                final String fechaFinal = Utilidad.fechaToString(datReporte4FechaFinal.getDate()) + " 23:59:59";

                Map parametrosReporte = new HashMap();
                parametrosReporte.put("fechaInicio", fechaInicio);
                parametrosReporte.put("fechaFinal", fechaFinal);

                visualizarReporte(parametrosReporte, "/reportes/Reporte4GananciasIntervaloFechas.jasper");
            }
        });
        pnlReporte4Botones.add(btnReporte4Visualizar);
        
        JButton btnReporte4Guardar = new JButton("Guardar...");
        btnReporte4Guardar.addActionListener(e -> {
            if (validarDatosReporte3y4(datReporte4FechaInicio, datReporte4FechaFinal)) {
                final String fechaInicio = Utilidad.fechaToString(datReporte4FechaInicio.getDate()) + " 00:00:00";
                final String fechaFinal = Utilidad.fechaToString(datReporte4FechaFinal.getDate()) + " 23:59:59";

                Map parametrosReporte = new HashMap();
                parametrosReporte.put("fechaInicio", fechaInicio);
                parametrosReporte.put("fechaFinal", fechaFinal);

                guardarReporte(parametrosReporte, "/reportes/Reporte4GananciasIntervaloFechas.jasper");
            }
        });
        pnlReporte4Botones.add(btnReporte4Guardar);
    }

    /**
     * Valida los datos del reporte 3.
     *
     * @return true si los datos son válidos, false en caso contrario.
     */
    private boolean validarDatosReporte3y4(JDateChooser fechaInicioControl, JDateChooser fechaFinalControl) {
        final Date fechaInicio = fechaInicioControl.getDate();
        final Date fechaFinal = fechaFinalControl.getDate();

        if (fechaInicio == null) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una fecha de inicio.", "Mensaje", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (fechaFinal == null) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una fecha final.", "Mensaje", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (fechaInicio.after(fechaFinal)) {
            JOptionPane.showMessageDialog(this, "La fecha de inicio no puede ser mayor a la fecha final.", "Mensaje", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    /**
     * Guarda el reporte en un archivo PDF.
     *
     * @param parametrosReporte Parámetros del reporte.
     * @param nombreReporte     Nombre del reporte.
     */
    private void guardarReporte(Map parametrosReporte, String nombreReporte) {
        File reporte = new File(getClass().getResource(nombreReporte).getFile());

        if (!reporte.exists()) {
            JOptionPane.showMessageDialog(this, "No se encontró el reporte.", "Mensaje", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF", "pdf"));

        int seleccion = fileChooser.showSaveDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();

            try {
                InputStream is = new BufferedInputStream(new FileInputStream(reporte.getAbsolutePath()));
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametrosReporte, gestorLavanderiaGUI.getConexion());

                JasperExportManager.exportReportToPdfFile(jasperPrint, archivoSeleccionado.getAbsolutePath() + ".pdf");

                JOptionPane.showMessageDialog(this, "El reporte se guardó correctamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } catch (JRException | FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Visualización de un reporte con ciertos parámetros.
     *
     * @param parametrosReporte Parámetros del reporte.
     * @param nombreReporte     Nombre del reporte.
     */
    private void visualizarReporte(Map parametrosReporte, String nombreReporte) {
        File reporte = new File(getClass().getResource(nombreReporte).getFile());

        if (!reporte.exists()) {
            JOptionPane.showMessageDialog(this, "No se encontró el reporte.", "Mensaje", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            InputStream is = new BufferedInputStream(new FileInputStream(reporte.getAbsolutePath()));
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametrosReporte, gestorLavanderiaGUI.getConexion());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException | FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Valida los datos ingresados para el reporte 1.
     */
    private boolean validarDatosReporte1() {
        final int cantidadClientes = (int) spnCantidadClientes.getValue();

        if (cantidadClientes <= 0) {
            JOptionPane.showMessageDialog(this, "La cantidad de clientes debe ser mayor a 0.", "Mensaje", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        final Date fechaInicio = datReporte1FechaInicio.getDate();
        final Date fechaFinal = datReporte1FechaFinal.getDate();

        if (fechaInicio == null) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una fecha de inicio.", "Mensaje", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (fechaFinal == null) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una fecha final.", "Mensaje", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (fechaInicio != null && fechaFinal != null && fechaInicio.after(fechaFinal)) {
            JOptionPane.showMessageDialog(this, "La fecha de inicio debe ser menor a la fecha final.", "Mensaje", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

}
