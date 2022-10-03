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
     * @return Listado de roles.
     */
    public List<Rol> getRoles() {
        return accesoDatos.obtenerRoles();
    }

    /**
     * Obtiene el listado de tipos de documento.
     * @return Listado de tipos de documento.
     */
    public List<TipoDocumento> obtenerTiposDocumentos() {
        return accesoDatos.obtenerTiposDocumentos();
    }

    /**
     * Obtiene todos los tipos de atencion.
     * @return Lista de tipos de atención.
     */
    public List<TipoAtencion> obtenerTiposAtencion() {
        return accesoDatos.obtenerTiposAtencion();
    }

    /**
     * Crea una nueva dirección.
     * @param direccion Direccion a crear.
     */
    public Direccion crearDireccion(Direccion direccion) {
        return accesoDatos.crearDireccion(direccion);
    }

    /**
     * Obtener una dirección a partir del ID del cliente.
     * @param clienteId ID del cliente.
     * @return Direccion Dirección encontrada.
     */
    public Direccion obtenerDireccionPorClientId(int clienteId) {
        return accesoDatos.obtenerDireccionPorClientId(clienteId);
    }
}
