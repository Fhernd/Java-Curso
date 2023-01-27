package vistas;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JLabel;

import com.toedter.calendar.JDateChooser;

import javax.swing.JButton;
import java.awt.*;
import java.util.Date;

public class CapturaRangoFechasJPanel extends JPanel {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 2309572527450541301L;
    private JDateChooser datFechaInicio;
    private JDateChooser datFechaFinal;

    /**
     * Create the panel.
     */
    public CapturaRangoFechasJPanel() {
        this.setSize(370, 100);
        this.setMinimumSize(new Dimension(300, 100));
        setBorder(new TitledBorder(null, "Fechas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        setLayout(new FormLayout(new ColumnSpec[] {
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
        		RowSpec.decode("default:grow"),
        		FormSpecs.RELATED_GAP_ROWSPEC,
        		RowSpec.decode("default:grow"),
        		FormSpecs.RELATED_GAP_ROWSPEC,
        		RowSpec.decode("default:grow"),
        		FormSpecs.RELATED_GAP_ROWSPEC,
        		RowSpec.decode("default:grow"),}));

        JLabel lblFechaInicio = new JLabel("Fecha inicio:");
        add(lblFechaInicio, "2, 2");

        datFechaInicio = new JDateChooser();
        add(datFechaInicio, "10, 2, fill, fill");

        JLabel lblFechaFinal = new JLabel("Fecha final:");
        add(lblFechaFinal, "2, 4");

        datFechaFinal = new JDateChooser();
        add(datFechaFinal, "10, 4, fill, fill");
        
        JPanel pnlBotones = new JPanel();
        add(pnlBotones, "10, 10, fill, fill");
        pnlBotones.setLayout(new GridLayout(1, 0, 0, 0));
        
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setMnemonic('A');
        pnlBotones.add(btnAceptar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setMnemonic('C');
        pnlBotones.add(btnCancelar);

    }

    public Date getDatFechaInicio() {
        return datFechaInicio.getDate();
    }

    public Date getDatFechaFinal() {
        return datFechaFinal.getDate();
    }
}
