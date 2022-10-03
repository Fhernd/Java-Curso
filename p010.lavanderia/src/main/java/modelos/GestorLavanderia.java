package modelos;

import datos.AccesoDatos;

import java.util.List;

public class GestorLavanderia {
    private AccesoDatos accesoDatos;

    public GestorLavanderia() {
        accesoDatos = new AccesoDatos();
    }

    /**
     * Obtiene el listado de roles.
     *
     * @return Listado de roles.
     */
    public List<Rol> getRoles() {
        return accesoDatos.obtenerRoles();
    }

    /**
     * Obtiene el listado de tipos de documento.
     *
     * @return Listado de tipos de documento.
     */
    public List<TipoDocumento> obtenerTiposDocumentos() {
        return accesoDatos.obtenerTiposDocumentos();
    }

    /**
     * Obtiene todos los tipos de atencion.
     *
     * @return Lista de tipos de atención.
     */
    public List<TipoAtencion> obtenerTiposAtencion() {
        return accesoDatos.obtenerTiposAtencion();
    }

    /**
     * Crea una nueva dirección.
     *
     * @param direccion Direccion a crear.
     */
    public Direccion crearDireccion(Direccion direccion) {
        return accesoDatos.crearDireccion(direccion);
    }

    /**
     * Obtener una dirección a partir del ID del cliente.
     *
     * @param clienteId ID del cliente.
     * @return Direccion Dirección encontrada.
     */
    public Direccion obtenerDireccionPorClientId(int clienteId) {
        return accesoDatos.obtenerDireccionPorClientId(clienteId);
    }

    /**
     * Obtener todas las direcciones de un cliente a partir de su ID.
     *
     * @param clienteId ID del cliente.
     * @return Lista de direcciones.
     */
    public List<Direccion> obtenerDireccionesPorClientId(int clienteId) {
        return accesoDatos.obtenerDireccionesPorClientId(clienteId);
    }

    /**
     * Crea un nuevo cliente.
     *
     * @param cliente Cliente a crear.
     * @return Cliente Cliente creado.
     */
    public Cliente crearCliente(Cliente cliente) {
        return accesoDatos.crearCliente(cliente);
    }

    /**
     * Obtiene un cliente a partir de su ID.
     *
     * @param clienteId ID del cliente.
     * @return Cliente Cliente encontrado.
     */
    public Cliente obtenerClientePorId(int clienteId) {
        return accesoDatos.obtenerClientePorId(clienteId);
    }

    /**
     * Obtiene un cliente a partir de su documento.
     *
     * @param documento Documento del cliente.
     * @return Cliente Cliente encontrado.
     */
    public Cliente obtenerClientePorDocumento(String documento) {
        return accesoDatos.obtenerClientePorDocumento(documento);
    }

    /**
     * Actualiza los datos de un cliente.
     *
     * @param cliente Cliente a actualizar.
     * @return true si se actualizó correctamente, false en caso contrario.
     */
    public boolean actualizarCliente(Cliente cliente) {
        return accesoDatos.actualizarCliente(cliente);
    }

    /**
     * Elimina un cliente a partir de su documento.
     *
     * @param documento Documento del cliente.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    public boolean eliminarClientePorDocumento(String documento) {
        return accesoDatos.eliminarClientePorDocumento(documento);
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param usuario Usuario a crear.
     * @return Usuario Usuario creado.
     */
    public Usuario crearUsuario(Usuario usuario) {
        return accesoDatos.crearUsuario(usuario);
    }
}
