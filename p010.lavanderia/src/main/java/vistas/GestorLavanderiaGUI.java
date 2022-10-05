package vistas;

import modelos.Empleado;
import modelos.GestorLavanderia;
import modelos.Rol;
import modelos.Usuario;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class GestorLavanderiaGUI {

    private JFrame frmGestorLavanderiaGUI;
    private JMenu mnuEmpleados;
    private GestorLavanderia gestorLavanderia;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestorLavanderiaGUI window = new GestorLavanderiaGUI();
                    window.frmGestorLavanderiaGUI.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GestorLavanderiaGUI() {
        initialize();
        gestorLavanderia = new GestorLavanderia();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmGestorLavanderiaGUI = new JFrame();
        frmGestorLavanderiaGUI.setTitle("Gestor Lavandería");
        frmGestorLavanderiaGUI.setBounds(100, 100, 800, 600);
        frmGestorLavanderiaGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmGestorLavanderiaGUI.getContentPane().setLayout(new BorderLayout(0, 0));

        JDesktopPane dtpPrincipal = new JDesktopPane();
        frmGestorLavanderiaGUI.getContentPane().add(dtpPrincipal, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        frmGestorLavanderiaGUI.setJMenuBar(menuBar);

        JMenu mnuArchivo = new JMenu("Archivo");
        menuBar.add(mnuArchivo);

        JMenuItem mniIniciarSesion = new JMenuItem("Iniciar sesión...");
        mniIniciarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginFrame login = new LoginFrame(GestorLavanderiaGUI.this);
                dtpPrincipal.add(login);
                login.setVisible(true);
            }
        });
        mnuArchivo.add(mniIniciarSesion);

        JMenuItem mniSalir = new JMenuItem("Salir");
        mniSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(frmGestorLavanderiaGUI, "¿Está seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        JMenuItem mniEmpleadoUsuarioCrear = new JMenuItem("Crear nuevo empleado");
        mniEmpleadoUsuarioCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EmpleadoCrearFrame empleadoCrear = new EmpleadoCrearFrame(GestorLavanderiaGUI.this);
                dtpPrincipal.add(empleadoCrear);
                empleadoCrear.setVisible(true);
            }
        });
        mnuArchivo.add(mniEmpleadoUsuarioCrear);
        mnuArchivo.add(mniSalir);

        mnuEmpleados = new JMenu("Empleados");
        mnuEmpleados.setEnabled(false);
        menuBar.add(mnuEmpleados);

        JMenuItem mniEmpleadosCrear = new JMenuItem("Crear");
        mnuEmpleados.add(mniEmpleadosCrear);
    }

    public Usuario iniciarSesion(String email, String password) {
        return gestorLavanderia.iniciarSesion(email, password);
    }

    public void mostrarMenus() {
        mnuEmpleados.setEnabled(true);
    }

    public List<Rol> getRoles() {
        return gestorLavanderia.getRoles();
    }

    public Usuario obtenerUsuarioPorCorreo(String correo) {
        return gestorLavanderia.obtenerUsuarioPorCorreo(correo);
    }

    /**
     * Crear un nuevo empleado.
     *
     * @param empleado Empleado a crear.
     * @return Empleado Empleado creado.
     */
    public Empleado crearEmpleado(Empleado empleado) {
        return gestorLavanderia.crearEmpleado(empleado);
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param usuario Usuario a crear.
     * @return Usuario Usuario creado.
     */
    public Usuario crearUsuario(Usuario usuario) {
        return gestorLavanderia.crearUsuario(usuario);
    }

    /**
     * Elimina un empleado a partir de su ID.
     *
     * @param id ID del empleado.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    public boolean eliminarEmpleadoPorId(int id) {
        return gestorLavanderia.eliminarEmpleadoPorId(id);
    }
}
