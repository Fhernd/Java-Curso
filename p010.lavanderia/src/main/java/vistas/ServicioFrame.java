package vistas;

import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.toedter.calendar.JDateChooser;
import com.raven.swing.TimePicker;
import modelos.*;
import vistas.models.ClientesComboBoxModel;
import vistas.models.DireccionComboBoxModel;
import vistas.models.EmpleadosComboBoxModel;

import java.awt.GridLayout;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ServicioFrame extends JInternalFrame {
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
    private JSpinner snnAtencionCantidad;

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
            // TODO: Guardar los datos de un nuevo servicio
        });
        pnlServiciosAcciones.add(btnServicioGuardar);

        JButton btnServicioBuscar = new JButton("Buscar...");
        btnServicioBuscar.addActionListener(e -> {
            // TODO: Buscar entre los registros de servicios
        });
        pnlServiciosAcciones.add(btnServicioBuscar);

        JButton btnServicioEditar = new JButton("Editar");
        btnServicioEditar.addActionListener(e -> {
            // TODO: Editar los datos de un servicio
        });
        pnlServiciosAcciones.add(btnServicioEditar);

        JButton btnServicioEliminar = new JButton("Eliminar");
        btnServicioEliminar.addActionListener(e -> {
            // TODO: Eliminar un servicio seleccionado
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
        });
        spnServiciosRegistros.setViewportView(tblServiciosRegistros);
        tblServiciosRegistros.setSize(200, 200);
        tblServiciosRegistros.setPreferredScrollableViewportSize(new Dimension(600, 80));


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

        JButton btnNewButton = new JButton("Agregar");
        btnNewButton.addActionListener(e -> {
            // TODO: Agregar atención
        });
        btnNewButton.setEnabled(false);
        pnlAtencionesDatos.add(btnNewButton, "18, 10");

        JPanel pnlAtencionesRegistros = new JPanel();
        pnlAtencionesRegistros.setBorder(new TitledBorder(null, "Registros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnlAtenciones.add(pnlAtencionesRegistros, BorderLayout.SOUTH);
        pnlAtencionesRegistros.setLayout(new BorderLayout(0, 0));

        JScrollPane spnAtencionesRegistros = new JScrollPane();
        pnlAtencionesRegistros.add(spnAtencionesRegistros, BorderLayout.CENTER);

        tblAtencionesRegistros = new JTable();
        tblAtencionesRegistros.setModel(new DefaultTableModel(
                new Object[][]{
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
                    false, false, false, true
            };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        spnAtencionesRegistros.setViewportView(tblAtencionesRegistros);
        tblAtencionesRegistros.setPreferredScrollableViewportSize(new Dimension(600, 80));

        establecerFechaEntregaPorDefecto(datServicioFechaEntrega);
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
        Empleado empleadoActual = gestorLavanderiaGUI.obtenerEmpleadoActual();
        cargarEmpleados(empleadoActual);

        int clientId = ((Cliente) cbxServicioCliente.getSelectedItem()).getId();

        cargarDirecciones(clientId);
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
    }

    /**
     * Carga los empleados en el combo box.
     */
    private void cargarEmpleados(Empleado empleadoActual) {
        List<Empleado> empleados = gestorLavanderiaGUI.obtenerEmpleados();

        Empleado[] empleadosArray = new Empleado[empleados.size()];
        empleados.toArray(empleadosArray);

        empleadosComboBoxModel = new EmpleadosComboBoxModel(empleadosArray);
        cbxServicioEmpleado.setModel(empleadosComboBoxModel);
        cbxServicioEmpleado.setSelectedItem(empleadoActual);
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
}
