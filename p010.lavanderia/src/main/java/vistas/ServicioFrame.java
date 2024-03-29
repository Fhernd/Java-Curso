package vistas;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.toedter.calendar.JDateChooser;
import com.raven.swing.TimePicker;
import modelos.*;
import utilidades.Utilidad;
import vistas.models.ClientesComboBoxModel;
import vistas.models.DireccionComboBoxModel;
import vistas.models.EmpleadosComboBoxModel;
import vistas.models.TipoAtencionComboBoxModel;

import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServicioFrame extends JInternalFrame {
    public static final int BUSQUEDA_POR_CLIENTE = 0;
    public static final int BUSQUEDA_POR_EMPLEADO = 1;
    public static final int BUSQUEDA_POR_RANGO_FECHAS = 2;
    private JTextField txtServicioId;
    private JTextField txtServicioDescripcion;
    private JTextField txtServicioHoraEntrega;
    private JTable tblServiciosRegistros;
    private JTextField txtAtencionId;
    private JTextField txtAtencionPrecio;
    private JComboBox cbxAtencionTipo;
    private JTable tblAtencionesRegistros;
    private JComboBox cbxServicioDireccionEntrega;
    private JComboBox cbxServicioCliente;
    private JComboBox cbxServicioEmpleado;

    private GestorLavanderiaGUI gestorLavanderiaGUI;
    private DireccionComboBoxModel direccionComboBoxModel;
    private ClientesComboBoxModel clientesComboBoxModel;
    private EmpleadosComboBoxModel empleadosComboBoxModel;
    private TipoAtencionComboBoxModel tipoAtencionComboBoxModel;
    private JSpinner snnAtencionCantidad;
    private boolean fechaEntregaModificada = false;
    private boolean modoEdicion = false;
    private int servicioId;
    private JButton btnAtencionAgregar;

    /**
     * Create the frame.
     */
    public ServicioFrame(GestorLavanderiaGUI gestorLavanderiaGUI) {
        this.gestorLavanderiaGUI = gestorLavanderiaGUI;

        setClosable(true);
        setTitle("Servicios");
        setBounds(100, 100, 600, 770);

        JPanel pnlServicios = new JPanel();
        pnlServicios.setBorder(new TitledBorder(null, "Servicios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(pnlServicios, BorderLayout.NORTH);
        pnlServicios.setLayout(new BorderLayout(0, 0));

        JPanel pnlServiciosDatos = new JPanel();
        pnlServiciosDatos.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnlServicios.add(pnlServiciosDatos, BorderLayout.NORTH);
        pnlServiciosDatos.setLayout(new FormLayout(new ColumnSpec[]{
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
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        RowSpec.decode("default:grow"),
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        RowSpec.decode("default:grow"),
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,}));

        JLabel lblServicioId = new JLabel("ID:");
        pnlServiciosDatos.add(lblServicioId, "2, 2");

        txtServicioId = new JTextField();
        txtServicioId.setEditable(false);
        pnlServiciosDatos.add(txtServicioId, "12, 2, fill, default");
        txtServicioId.setColumns(10);

        JLabel lblServicioDescripcion = new JLabel("Descripción:");
        pnlServiciosDatos.add(lblServicioDescripcion, "2, 4");

        txtServicioDescripcion = new JTextField();
        pnlServiciosDatos.add(txtServicioDescripcion, "12, 4, fill, top");
        txtServicioDescripcion.setColumns(10);

        JLabel lblServicioFechaEntrega = new JLabel("Fecha entrega:");
        pnlServiciosDatos.add(lblServicioFechaEntrega, "2, 6");

        JDateChooser datServicioFechaEntrega = new JDateChooser();
        datServicioFechaEntrega.addPropertyChangeListener(evt -> {
            fechaEntregaModificada = true;
        });
        datServicioFechaEntrega.setSelectableDateRange(new Date(), null);
        pnlServiciosDatos.add(datServicioFechaEntrega, "12, 6, fill, fill");

        JLabel lblServicioHoraEntrega = new JLabel("Hora entrega:");
        pnlServiciosDatos.add(lblServicioHoraEntrega, "2, 8");

        txtServicioHoraEntrega = new JTextField();
        txtServicioHoraEntrega.setEditable(false);
        pnlServiciosDatos.add(txtServicioHoraEntrega, "12, 8, fill, default");
        txtServicioHoraEntrega.setColumns(10);

        JButton btnServicioSeleccionarHora = new JButton("Seleccionar hora...");
        btnServicioSeleccionarHora.addActionListener(e -> {
            TimePicker timePicker = new TimePicker();
            timePicker.showPopup(this, 100, 100);

            timePicker.addEventTimePicker(e1 -> txtServicioHoraEntrega.setText(timePicker.getSelectedTime()));
        });
        pnlServiciosDatos.add(btnServicioSeleccionarHora, "12, 10");

        JLabel lblServicioEmpleado = new JLabel("Empleado:");
        pnlServiciosDatos.add(lblServicioEmpleado, "2, 12, left, default");

        cbxServicioEmpleado = new JComboBox();
        pnlServiciosDatos.add(cbxServicioEmpleado, "12, 12, fill, default");

        JLabel lblServicioCliente = new JLabel("Cliente:");
        pnlServiciosDatos.add(lblServicioCliente, "2, 14");

        cbxServicioCliente = new JComboBox();
        cbxServicioCliente.addActionListener(e -> {
            if (cbxServicioCliente.getSelectedIndex() != -1) {
                Cliente cliente = (Cliente) cbxServicioCliente.getSelectedItem();
                cargarDirecciones(cliente.getId());
            }
        });
        pnlServiciosDatos.add(cbxServicioCliente, "12, 14, fill, default");

        JLabel lblServicioDireccion = new JLabel("Dirección entrega:");
        pnlServiciosDatos.add(lblServicioDireccion, "2, 16");

        cbxServicioDireccionEntrega = new JComboBox();
        pnlServiciosDatos.add(cbxServicioDireccionEntrega, "12, 16, fill, default");

        JPanel pnlServiciosAcciones = new JPanel();
        pnlServiciosAcciones.setBorder(new TitledBorder(null, "Acciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnlServicios.add(pnlServiciosAcciones, BorderLayout.CENTER);
        pnlServiciosAcciones.setLayout(new GridLayout(0, 5, 0, 0));

        JButton btnServicioNuevo = new JButton("Nuevo");
        btnServicioNuevo.addActionListener(e -> {
            String servicioId = txtServicioId.getText();
            String servicioDescripcion = txtServicioDescripcion.getText();
            String servicioFechaEntrega = datServicioFechaEntrega.getDate().toString();
            String servicioHoraEntrega = txtServicioHoraEntrega.getText();
            int clienteIndice = cbxServicioCliente.getSelectedIndex();

            if (!servicioId.isEmpty() || !servicioDescripcion.isEmpty() || !servicioFechaEntrega.isEmpty() || !servicioHoraEntrega.isEmpty() || clienteIndice > 0) {

                int opcion = JOptionPane.showConfirmDialog(this, "¿Desea iniciar un nuevo servicio?", "Confirmación", JOptionPane.YES_NO_OPTION);

                if (opcion == JOptionPane.YES_OPTION) {
                    limpiarCamposServicio();
                    limpiarCamposAtenciones();
                    cambiarEstadoCamposAtenciones(false);
                    establecerFechaEntregaPorDefecto(datServicioFechaEntrega);
                }
            } else {
                limpiarCamposServicio();
                limpiarCamposAtenciones();
                cambiarEstadoCamposAtenciones(false);
                establecerFechaEntregaPorDefecto(datServicioFechaEntrega);
            }
        });
        pnlServiciosAcciones.add(btnServicioNuevo);

        JButton btnServicioGuardar = new JButton("Guardar");
        btnServicioGuardar.addActionListener(e -> {
            String descripcion = txtServicioDescripcion.getText().trim();
            Date fechaEntrega = datServicioFechaEntrega.getDate();
            String horaEntrega = txtServicioHoraEntrega.getText().trim().toLowerCase();
            int empleadoId = ((Empleado) cbxServicioEmpleado.getSelectedItem()).getId();
            int clienteId = ((Cliente) cbxServicioCliente.getSelectedItem()).getId();
            int direccionId = ((Direccion) cbxServicioDireccionEntrega.getSelectedItem()).getId();

            if (descripcion.isEmpty() || fechaEntrega == null || horaEntrega.isEmpty() || empleadoId == 0 || clienteId == 0 || direccionId == 0) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos", "Mensaje", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Servicio servicio = new Servicio();
            servicio.setDescripcion(descripcion);

            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            String fechaEntregaFormateada = formatoFecha.format(fechaEntrega);

            String fechaHoraEntrega = fechaEntregaFormateada + " " + horaEntrega;

            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("hh:mm a");
            LocalTime horaEntregaLocalTime = LocalTime.parse(horaEntrega, formatoHora);

            if (horaEntregaLocalTime.isBefore(LocalTime.of(5, 0)) || horaEntregaLocalTime.isAfter(LocalTime.of(20, 0))) {
                JOptionPane.showMessageDialog(this, "La hora de entrega debe estar entre las 5AM y las 8PM.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                return;
            }

            LocalTime horaActualLocalTime = LocalTime.now();

            Date fechaActual = new Date();

            SimpleDateFormat formatoFechaActual = new SimpleDateFormat("yyyy-MM-dd");
            final String fechaActualFormateada = formatoFechaActual.format(fechaActual);

            if (fechaActualFormateada.equals(fechaEntregaFormateada) && horaEntregaLocalTime.isBefore(horaActualLocalTime)) {
                JOptionPane.showMessageDialog(this, "La hora de entrega debe ser mayor a la hora actual.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                return;
            }

            DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
            LocalDateTime fechaHoraEntregaLocalDateTime = LocalDateTime.parse(fechaHoraEntrega, formatoFechaHora);

            servicio.setFechaHoraEntrega(fechaHoraEntregaLocalDateTime);
            servicio.setEmpleadoId(empleadoId);
            servicio.setClienteId(clienteId);
            servicio.setDireccionId(direccionId);

            servicio = gestorLavanderiaGUI.crearServicio(servicio);

            if (servicio == null) {
                JOptionPane.showMessageDialog(this, "No se pudo crear el servicio", "Mensaje", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, "Servicio creado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

            limpiarCamposServicio();

            List<Servicio> servicios = gestorLavanderiaGUI.obtenerServicios();
            cargarServicios(servicios);
        });
        pnlServiciosAcciones.add(btnServicioGuardar);

        JButton btnServicioBuscar = new JButton("Buscar...");
        btnServicioBuscar.addActionListener(e -> {
            final String[] opciones = {"Cliente", "Empleado", "Rango de fechas"};

            int opcionSeleccionada = JOptionPane.showOptionDialog(this, "Seleccione el tipo de búsqueda", "Buscar servicio", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            switch (opcionSeleccionada) {
                case BUSQUEDA_POR_CLIENTE:
                    buscarPorCliente();
                    break;
                case BUSQUEDA_POR_EMPLEADO:
                    buscarPorEmpleado();
                    break;
                case BUSQUEDA_POR_RANGO_FECHAS:
                    buscarPorRangoFechas();
                    break;
                default:
                    break;
            }
        });
        pnlServiciosAcciones.add(btnServicioBuscar);

        JButton btnServicioEditar = new JButton("Editar");
        btnServicioEditar.setEnabled(false);
        btnServicioEditar.addActionListener(e -> {
            String descripcion = txtServicioDescripcion.getText().trim();
            Date fechaEntrega = datServicioFechaEntrega.getDate();
            String horaEntrega = txtServicioHoraEntrega.getText().trim().toLowerCase();
            int empleadoId = ((Empleado) cbxServicioEmpleado.getSelectedItem()).getId();
            int clienteId = ((Cliente) cbxServicioCliente.getSelectedItem()).getId();
            int direccionId = ((Direccion) cbxServicioDireccionEntrega.getSelectedItem()).getId();

            if (descripcion.isEmpty() || fechaEntrega == null || horaEntrega.isEmpty() || empleadoId == 0 || clienteId == 0 || direccionId == 0) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos", "Mensaje", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Servicio servicio = new Servicio();
            servicio.setId(servicioId);
            servicio.setDescripcion(descripcion);
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            String fechaEntregaFormateada = formatoFecha.format(fechaEntrega);

            if (!modoEdicion || fechaEntregaModificada) {

                String fechaHoraEntrega = fechaEntregaFormateada + " " + horaEntrega;

                DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("hh:mm a");
                LocalTime horaEntregaLocalTime = LocalTime.parse(horaEntrega, formatoHora);

                if (horaEntregaLocalTime.isBefore(LocalTime.of(5, 0)) || horaEntregaLocalTime.isAfter(LocalTime.of(20, 0))) {
                    JOptionPane.showMessageDialog(this, "La hora de entrega debe estar entre las 5AM y las 8PM.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                LocalTime horaActualLocalTime = LocalTime.now();

                Date fechaActual = new Date();

                SimpleDateFormat formatoFechaActual = new SimpleDateFormat("yyyy-MM-dd");
                final String fechaActualFormateada = formatoFechaActual.format(fechaActual);

                if (fechaActualFormateada.equals(fechaEntregaFormateada) && horaEntregaLocalTime.isBefore(horaActualLocalTime)) {
                    JOptionPane.showMessageDialog(this, "La hora de entrega debe ser mayor a la hora actual.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
                LocalDateTime fechaHoraEntregaLocalDateTime = LocalDateTime.parse(fechaHoraEntrega, formatoFechaHora);


                servicio.setFechaHoraEntrega(fechaHoraEntregaLocalDateTime);
            } else {
                String fechaHoraEntrega = fechaEntregaFormateada + " " + horaEntrega;

                DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
                LocalDateTime fechaHoraEntregaLocalDateTime = LocalDateTime.parse(fechaHoraEntrega, formatoFechaHora);

                servicio.setFechaHoraEntrega(fechaHoraEntregaLocalDateTime);
            }

            servicio.setEmpleadoId(empleadoId);
            servicio.setClienteId(clienteId);
            servicio.setDireccionId(direccionId);

            boolean resultado = gestorLavanderiaGUI.actualizarServicio(servicio);

            if (!resultado) {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar el servicio", "Mensaje", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, "Servicio actualizado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

            limpiarCamposServicio();

            List<Servicio> servicios = gestorLavanderiaGUI.obtenerServicios();
            cargarServicios(servicios);
        });
        pnlServiciosAcciones.add(btnServicioEditar);

        JButton btnServicioEliminar = new JButton("Eliminar");
        btnServicioEliminar.setEnabled(false);
        btnServicioEliminar.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar el servicio seleccionado?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (opcion != JOptionPane.YES_OPTION) {
                return;
            }

            final int cantidadAtenciones = gestorLavanderiaGUI.obtenerAtencionesPorServicioId(servicioId).size();

            boolean resultado = false;

            if (cantidadAtenciones != 0) {
                resultado = gestorLavanderiaGUI.eliminarAtencionesPorServicioId(servicioId);

                if (!resultado) {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar las atenciones del servicio seleccionado", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            resultado = gestorLavanderiaGUI.eliminarServicioPorId(servicioId);


            if (!resultado) {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el servicio seleccionado", "Mensaje", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, "Servicio eliminado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            btnServicioEditar.setEnabled(false);
            btnServicioEliminar.setEnabled(false);

            servicioId = 0;

            List<Servicio> servicios = gestorLavanderiaGUI.obtenerServicios();
            cargarServicios(servicios);
        });
        pnlServiciosAcciones.add(btnServicioEliminar);

        JPanel pnlServiciosRegistros = new JPanel();
        pnlServiciosRegistros.setSize(200, 200);
        pnlServiciosRegistros.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registros", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pnlServicios.add(pnlServiciosRegistros, BorderLayout.SOUTH);
        pnlServiciosRegistros.setLayout(new BorderLayout(0, 0));

        JScrollPane spnServiciosRegistros = new JScrollPane();
        spnServiciosRegistros.setSize(200, 200);
        pnlServiciosRegistros.add(spnServiciosRegistros, BorderLayout.CENTER);

        tblServiciosRegistros = new JTable();
        tblServiciosRegistros.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null},
                },
                new String[]{
                        "ID", "Descripci\u00F3n", "Fecha entrega", "Empleado", "Cliente", "Direcci\u00F3n"
                }
        ) {
            Class[] columnTypes = new Class[]{
                    String.class, String.class, String.class, String.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            boolean[] columnEditables = new boolean[]{
                    false, false, false, false, false, false
            };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        spnServiciosRegistros.setViewportView(tblServiciosRegistros);
        tblServiciosRegistros.setSize(200, 200);
        tblServiciosRegistros.setPreferredScrollableViewportSize(new Dimension(600, 80));

        tblServiciosRegistros.getSelectionModel().addListSelectionListener(e -> {
            btnServicioEditar.setEnabled(true);
            btnServicioEliminar.setEnabled(true);
            modoEdicion = true;

            if (e.getValueIsAdjusting()) {
                return;
            }

            int filaSeleccionada = tblServiciosRegistros.getSelectedRow();

            if (filaSeleccionada == -1) {
                return;
            }

            servicioId = Integer.parseInt(tblServiciosRegistros.getValueAt(filaSeleccionada, 0).toString());
            Servicio servicio = gestorLavanderiaGUI.obtenerServicioPorId(servicioId);

            txtServicioId.setText(String.valueOf(servicio.getId()));
            txtServicioDescripcion.setText(servicio.getDescripcion());

            LocalDateTime fechaHoraEntrega = servicio.getFechaHoraEntrega();

            Date fechaEntrega = Date.from(fechaHoraEntrega.atZone(ZoneId.systemDefault()).toInstant());
            datServicioFechaEntrega.setDate(fechaEntrega);

            String horaEntrega = new SimpleDateFormat("hh:mm a").format(fechaEntrega);
            txtServicioHoraEntrega.setText(horaEntrega);

            cbxServicioEmpleado.setSelectedIndex(empleadosComboBoxModel.getIndexOf(servicio.getEmpleadoId()));
            cbxServicioCliente.setSelectedIndex(clientesComboBoxModel.getIndexOf(servicio.getClienteId()));

            cargarDirecciones(servicio.getClienteId());

            cbxServicioDireccionEntrega.setSelectedIndex(direccionComboBoxModel.getIndexOf(servicio.getDireccionId()));

            cbxServicioEmpleado.repaint();
            cbxServicioCliente.repaint();
            cbxServicioDireccionEntrega.repaint();

            activarSeccionAtenciones();
            cargarTipoAtenciones();
            cargarAtenciones(servicioId);
        });

        tblServiciosRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel pnlAtenciones = new JPanel();
        pnlAtenciones.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Atenciones", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        getContentPane().add(pnlAtenciones, BorderLayout.SOUTH);
        pnlAtenciones.setLayout(new BorderLayout(0, 0));

        JPanel pnlAtencionesDatos = new JPanel();
        pnlAtencionesDatos.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnlAtenciones.add(pnlAtencionesDatos, BorderLayout.NORTH);
        pnlAtencionesDatos.setLayout(new FormLayout(new ColumnSpec[]{
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
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"),},
                new RowSpec[]{
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,}));

        JLabel lblAtencionId = new JLabel("ID:");
        pnlAtencionesDatos.add(lblAtencionId, "2, 2");

        txtAtencionId = new JTextField();
        txtAtencionId.setEditable(false);
        pnlAtencionesDatos.add(txtAtencionId, "18, 2, fill, default");
        txtAtencionId.setColumns(10);

        JLabel lblAtencionCantidad = new JLabel("Cantidad:");
        pnlAtencionesDatos.add(lblAtencionCantidad, "2, 4");

        snnAtencionCantidad = new JSpinner();
        snnAtencionCantidad.setEnabled(false);
        pnlAtencionesDatos.add(snnAtencionCantidad, "18, 4");

        JLabel lblAtencionPrecio = new JLabel("Precio:");
        pnlAtencionesDatos.add(lblAtencionPrecio, "2, 6");

        txtAtencionPrecio = new JTextField();
        txtAtencionPrecio.setEnabled(false);
        txtAtencionPrecio.setText("");
        pnlAtencionesDatos.add(txtAtencionPrecio, "18, 6, fill, top");
        txtAtencionPrecio.setColumns(10);

        JLabel lblAtencionTipo = new JLabel("Tipo:");
        pnlAtencionesDatos.add(lblAtencionTipo, "2, 8");

        cbxAtencionTipo = new JComboBox();
        cbxAtencionTipo.setEnabled(false);
        pnlAtencionesDatos.add(cbxAtencionTipo, "18, 8, fill, default");

        btnAtencionAgregar = new JButton("Agregar");
        btnAtencionAgregar.addActionListener(e -> {
            int cantidad = Integer.parseInt(snnAtencionCantidad.getValue().toString());

            if (cantidad < 1) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0", "Mensaje", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String precioTexto = txtAtencionPrecio.getText().trim();

            if (precioTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El precio no puede estar vacío", "Mensaje", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Double.parseDouble(precioTexto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El precio debe ser un número.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (Double.parseDouble(precioTexto) <= 0) {
                JOptionPane.showMessageDialog(this, "El precio debe ser mayor a 0", "Mensaje", JOptionPane.WARNING_MESSAGE);
                return;
            }

            final int tipoAtencionId = ((TipoAtencion) cbxAtencionTipo.getSelectedItem()).getId();

            Atencion atencion = new Atencion();
            atencion.setCantidad(cantidad);
            atencion.setPrecio(Double.parseDouble(precioTexto));
            atencion.setTipoAtencionId(tipoAtencionId);
            atencion.setServicioId(servicioId);

            atencion = gestorLavanderiaGUI.crearAtencion(atencion);

            if (atencion == null) {
                JOptionPane.showMessageDialog(this, "No se pudo crear la atención", "Mensaje", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, "Atención creada con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

            limpiarCamposAtencion();

            cargarAtenciones(servicioId);
        });
        btnAtencionAgregar.setEnabled(false);
        pnlAtencionesDatos.add(btnAtencionAgregar, "18, 10");

        JPanel pnlAtencionesRegistros = new JPanel();
        pnlAtencionesRegistros.setBorder(new TitledBorder(null, "Registros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnlAtenciones.add(pnlAtencionesRegistros, BorderLayout.SOUTH);
        pnlAtencionesRegistros.setLayout(new BorderLayout(0, 0));

        JScrollPane spnAtencionesRegistros = new JScrollPane();
        pnlAtencionesRegistros.add(spnAtencionesRegistros, BorderLayout.CENTER);

        tblAtencionesRegistros = new JTable();
        tblAtencionesRegistros.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                },
                new String[]{
                        "ID", "Cantidad", "Precio", "Tipo"
                }
        ) {
            Class[] columnTypes = new Class[]{
                    Integer.class, Integer.class, Double.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            boolean[] columnEditables = new boolean[]{
                    false, false, false, false
            };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        spnAtencionesRegistros.setViewportView(tblAtencionesRegistros);
        tblAtencionesRegistros.setPreferredScrollableViewportSize(new Dimension(600, 80));

        asociarMenuContextualTablaAtenciones();

        establecerFechaEntregaPorDefecto(datServicioFechaEntrega);

        cargarClientes();
        cargarEmpleados();
        cargarDirecciones(4);

        List<Servicio> servicios = gestorLavanderiaGUI.obtenerServicios();
        cargarServicios(servicios);
    }

    /**
     * Asociar un menú contextual a la tabla de atenciones.
     */
    private void asociarMenuContextualTablaAtenciones() {
        JPopupMenu pmuOpcionesAtenciones = new JPopupMenu();
        JMenuItem mniEliminarRegistro = new JMenuItem("Eliminar este registro");
        JMenuItem mniEliminarTodosRegistros = new JMenuItem("Eliminar todos los registros");

        pmuOpcionesAtenciones.add(mniEliminarRegistro);
        pmuOpcionesAtenciones.add(mniEliminarTodosRegistros);
        tblAtencionesRegistros.setComponentPopupMenu(pmuOpcionesAtenciones);

        tblAtencionesRegistros.addMouseListener(new TableMouseListener(tblAtencionesRegistros));

        mniEliminarRegistro.addActionListener(e -> {
            int filaSeleccionada = tblAtencionesRegistros.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un registro", "Mensaje", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el registro?", "Mensaje", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.NO_OPTION) {
                return;
            }

            int atencionId = (int) tblAtencionesRegistros.getValueAt(filaSeleccionada, 0);

            boolean eliminado = gestorLavanderiaGUI.eliminarAtencionPorId(atencionId);

            if (!eliminado) {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el registro.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, "Registro eliminado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

            cargarAtenciones(servicioId);
        });

        mniEliminarTodosRegistros.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar todos los registros?", "Mensaje", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.NO_OPTION) {
                return;
            }

            boolean eliminados = gestorLavanderiaGUI.eliminarAtencionesPorServicioId(servicioId);

            if (!eliminados) {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar los registros.", "Mensaje", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, "Registros eliminados con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

            cargarAtenciones(servicioId);
        });
    }

    /**
     * Limpia los campos de la sección de atenciones.
     */
    private void limpiarCamposAtencion() {
        snnAtencionCantidad.setValue(0);
        txtAtencionPrecio.setText("");
        cbxAtencionTipo.setSelectedIndex(0);
    }

    /**
     * Carga los tipos de atenciones en un JComboBox.
     */
    private void cargarTipoAtenciones() {
        TipoAtencion[] tipoAtencionesArreglo = gestorLavanderiaGUI.obtenerTiposAtencion().toArray(new TipoAtencion[0]);

        tipoAtencionComboBoxModel = new TipoAtencionComboBoxModel(tipoAtencionesArreglo);
        cbxAtencionTipo.setModel(tipoAtencionComboBoxModel);
        cbxAtencionTipo.setSelectedIndex(0);
    }

    /**
     * Activa la sección de atenciones.
     */
    private void activarSeccionAtenciones() {
        snnAtencionCantidad.setEnabled(true);
        txtAtencionPrecio.setEnabled(true);
        cbxAtencionTipo.setEnabled(true);
        btnAtencionAgregar.setEnabled(true);
    }

    /**
     * Búsqueda de los servicios por rango de fechas.
     */
    private void buscarPorRangoFechas() {
        JOptionPane pane = new JOptionPane("", JOptionPane.QUESTION_MESSAGE);
        JDialog dialog = pane.createDialog("Selección de rango de fechas");
        dialog.setSize(400, 180);
        CapturaRangoFechasJPanel capturaRangoFechasJPanel = new CapturaRangoFechasJPanel(dialog);
        dialog.setContentPane(capturaRangoFechasJPanel);
        dialog.setVisible(true);

        Date fechaInicio = capturaRangoFechasJPanel.getDatFechaInicio();
        Date fechaFin = capturaRangoFechasJPanel.getDatFechaFinal();

        if (fechaInicio != null && fechaFin != null) {
            String fechaInicioStr = Utilidad.fechaToString(fechaInicio) + " 00:00:00";
            String fechaFinStr = Utilidad.fechaToString(fechaFin) + " 23:59:59";

            List<Servicio> servicios = gestorLavanderiaGUI.obtenerServiciosPorRangoFechas(fechaInicioStr, fechaFinStr);

            if (servicios.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay servicios registrados en el rango de fechas seleccionado", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }

            cargarServicios(servicios);
        }
    }

    private void buscarPorEmpleado() {
        List<Empleado> empleados = gestorLavanderiaGUI.obtenerEmpleados();

        if (empleados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay empleados registrados", "Mensaje", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] opcionesEmpleados = new String[empleados.size()];

        for (int i = 0; i < empleados.size(); i++) {
            Empleado empleado = empleados.get(i);
            opcionesEmpleados[i] = String.format("%s %s (%d)", empleado.getNombres(), empleado.getApellidos(), empleado.getId());
        }

        String opcionSeleccionada = (String) JOptionPane.showInputDialog(null, "Seleccione un empleado:", "Mensaje", JOptionPane.QUESTION_MESSAGE, null, opcionesEmpleados, opcionesEmpleados[0]);

        final int idEmpleado = Integer.parseInt(opcionSeleccionada.substring(opcionSeleccionada.lastIndexOf("(") + 1, opcionSeleccionada.lastIndexOf(")")));

        Empleado empleado = empleados.stream().filter(e -> e.getId() == idEmpleado).findFirst().get();

        List<Servicio> servicios = gestorLavanderiaGUI.obtenerServiciosPorEmpleadoIdConClienteDireccion(empleado.getId());
        cargarServicios(servicios);
    }

    private void buscarPorCliente() {
        String documento = "";

        // Mientras que no se digite un número usando JOptionPane, se seguirá solicitando:
        while (!documento.matches("[0-9]+")) {
            documento = JOptionPane.showInputDialog("Digite el número de documento del cliente:");
        }

        Cliente cliente = gestorLavanderiaGUI.obtenerClientePorDocumento(documento);

        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con el documento " + documento, "Mensaje", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Servicio> servicios = gestorLavanderiaGUI.obtenerServiciosPorClienteDocumento(cliente.getDocumento());

        if (servicios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron servicios para el cliente " + cliente.getNombres(), "Mensaje", JOptionPane.WARNING_MESSAGE);
            limpiarTablaServicios();
            return;
        }

        cargarServicios(servicios);
    }

    /**
     * Limpia la tabla de servicios.
     */
    private void limpiarTablaServicios() {
        DefaultTableModel modelo = (DefaultTableModel) tblServiciosRegistros.getModel();
        modelo.setRowCount(0);
    }

    /**
     * Establece la fecha de entrega por defecto en la fecha actual.
     *
     * @param datServicioFechaEntrega Fecha de entrega del servicio.
     */
    private void establecerFechaEntregaPorDefecto(JDateChooser datServicioFechaEntrega) {
        Date fechaActual = new Date();
        datServicioFechaEntrega.setDate(fechaActual);
    }

    private void limpiarCamposServicio() {
        txtServicioId.setText("");
        txtServicioDescripcion.setText("");
        txtServicioHoraEntrega.setText("");
        txtServicioHoraEntrega.setText("");
        cbxServicioCliente.setSelectedIndex(0);
        cbxServicioDireccionEntrega.setSelectedIndex(0);
        cargarEmpleados();

        int clientId = ((Cliente) cbxServicioCliente.getSelectedItem()).getId();

        cargarDirecciones(clientId);

        cbxServicioEmpleado.repaint();
        cbxServicioCliente.repaint();
        cbxServicioDireccionEntrega.repaint();
    }

    /**
     * Carga las direcciones de un cliente a partir de su ID.
     *
     * @param clienteId ID del cliente.
     */
    private void cargarDirecciones(int clienteId) {
        List<Direccion> direcciones = gestorLavanderiaGUI.obtenerDireccionesPorClienteId(clienteId);
        Direccion[] direccionesArray = new Direccion[direcciones.size()];
        direcciones.toArray(direccionesArray);

        direccionComboBoxModel = new DireccionComboBoxModel(direccionesArray);
        cbxServicioDireccionEntrega.setModel(direccionComboBoxModel);
        cbxServicioDireccionEntrega.setSelectedIndex(0);
    }

    /**
     * Carga los clientes en el combo box.
     */
    private void cargarClientes() {
        List<Cliente> clientes = gestorLavanderiaGUI.obtenerClientes();

        Cliente[] clientesArray = new Cliente[clientes.size()];
        clientes.toArray(clientesArray);

        clientesComboBoxModel = new ClientesComboBoxModel(clientesArray);
        cbxServicioCliente.setModel(clientesComboBoxModel);
        cbxServicioCliente.setSelectedIndex(0);

        final int clienteId = clientes.get(0).getId();
        cargarDirecciones(clienteId);
    }

    /**
     * Carga los empleados en el combo box.
     */
    private void cargarEmpleados() {
        Empleado empleadoActual = gestorLavanderiaGUI.obtenerEmpleadoActual();
        List<Empleado> empleados = gestorLavanderiaGUI.obtenerEmpleados();

        Empleado[] empleadosArray = new Empleado[empleados.size()];
        empleados.toArray(empleadosArray);

        empleadosComboBoxModel = new EmpleadosComboBoxModel(empleadosArray);
        cbxServicioEmpleado.setModel(empleadosComboBoxModel);
        cbxServicioEmpleado.setSelectedIndex(empleadosComboBoxModel.getIndexOf(empleadoActual.getId()));
    }

    /**
     * Limpiar los campos de atenciones.
     */
    private void limpiarCamposAtenciones() {
        txtAtencionId.setText("");
        snnAtencionCantidad.setValue(0);
        txtAtencionPrecio.setText("");
        cbxAtencionTipo.setSelectedIndex(0);
    }

    /**
     * Limpiar tabla de atenciones.
     */
    private void limpiarTablaAtenciones() {
        DefaultTableModel model = (DefaultTableModel) tblAtencionesRegistros.getModel();
        model.setRowCount(0);
    }

    /**
     * Carga las atenciones para un ID de servicio dado.
     *
     * @param servicioId ID del servicio.
     */
    private void cargarAtenciones(int servicioId) {
        List<Atencion> atenciones = gestorLavanderiaGUI.obtenerAtencionesPorServicioId(servicioId);

        DefaultTableModel model = (DefaultTableModel) tblAtencionesRegistros.getModel();
        model.setRowCount(0);

        List<TipoAtencion> tiposAtencion = gestorLavanderiaGUI.obtenerTiposAtencion();

        for (Atencion atencion : atenciones) {
            model.addRow(new Object[]{
                    atencion.getId(),
                    atencion.getCantidad(),
                    atencion.getPrecio(),
                    tiposAtencion.stream().filter(tipoAtencion -> tipoAtencion.getId() == atencion.getTipoAtencionId()).findFirst().get().getNombre()
            });
        }
    }

    /**
     * Deshabilitar los campos de atenciones.
     */
    private void cambiarEstadoCamposAtenciones(boolean estado) {
        txtAtencionId.setEnabled(estado);
        snnAtencionCantidad.setEnabled(estado);
        txtAtencionPrecio.setEnabled(estado);
        cbxAtencionTipo.setEnabled(estado);
    }

    /**
     * Cargar todos los servicios existentes sobre la tabla.
     */
    private void cargarServicios(List<Servicio> servicios) {
        DefaultTableModel model = (DefaultTableModel) tblServiciosRegistros.getModel();
        model.setRowCount(0);

        for (Servicio servicio : servicios) {
            Empleado empleado = servicio.getEmpleado();
            Cliente cliente = servicio.getCliente();

            model.addRow(new Object[]{
                    servicio.getId(),
                    servicio.getDescripcion(),
                    Utilidad.fechaToString(servicio.getFechaHoraEntrega(), "yyyy-MMM-dd HH:mm"),
                    String.format("%s %s (%d)", empleado.getNombres(), empleado.getApellidos(), empleado.getId()),
                    String.format("%s %s (%d)", cliente.getNombres(), cliente.getApellidos(), cliente.getId()),
                    servicio.getDireccion().getDescripcion()
            });
        }
    }
}

class TableMouseListener extends MouseAdapter {

    private JTable table;

    public TableMouseListener(JTable table) {
        this.table = table;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        Point point = event.getPoint();
        int currentRow = table.rowAtPoint(point);
        table.setRowSelectionInterval(currentRow, currentRow);
    }
}
