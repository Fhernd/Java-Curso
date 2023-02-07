package vistas;

import javax.swing.*;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.toedter.calendar.JDateChooser;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import utilidades.Utilidad;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GeneracionReportesFrame extends JInternalFrame {

    private GestorLavanderiaGUI gestorLavanderiaGUI;

    /**
     * Serial version ID.
     */
    private static final long serialVersionUID = 8174451787899692365L;
    private JSpinner spnCantidadClientes;
    private JDateChooser datReporte1FechaInicio;
    private JDateChooser datReporte1FechaFinal;

    /**
     * Create the frame.
     */
    public GeneracionReportesFrame(GestorLavanderiaGUI gestorLavanderiaGUI) {
        this.gestorLavanderiaGUI = gestorLavanderiaGUI;

        setTitle("Generación de Reportes");
        setBounds(100, 100, 500, 540);
        getContentPane().setLayout(new GridLayout(4, 1, 0, 0));

        JPanel pnlReporte1 = new JPanel();
        pnlReporte1.setBorder(new TitledBorder(null, "Clientes con m\u00E1s servicios solicitados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
        pnlReporte1Botones.setLayout(new GridLayout(0, 2, 0, 0));

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

//                final String rutaReporte = "reportes/Reporte1ClienteServicios.jasper";
//                File reporte = new File(rutaReporte);

                // Cargar el archivo Reporte1ClienteServicios.jasper desde la carpeta resources del proyecto:
                File reporte = new File(getClass().getResource("/reportes/Reporte1ClienteServicios.jasper").getFile());

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
        });
        pnlReporte1Botones.add(btnReporte1Visualizar);

        JButton btnReporte1Guardar = new JButton("Guardar...");
        btnReporte1Guardar.addActionListener(e -> {
            // TODO: Guardar reporte 1
        });
        pnlReporte1Botones.add(btnReporte1Guardar);

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
