package vistas.models;

import modelos.Direccion;

import javax.swing.*;

public class DireccionComboBoxModel  extends AbstractListModel implements ComboBoxModel {
    private Direccion[] direcciones;
    private Direccion selection = null;

    public DireccionComboBoxModel(Direccion[] direcciones) {
        this.direcciones = direcciones;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (Direccion) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }

    @Override
    public int getSize() {
        return direcciones.length;
    }

    @Override
    public Object getElementAt(int index) {
        return direcciones[index];
    }

    /**
     * Devuelve el índice del elemento con el ID indicado.
     *
     * @param direccionId ID del elemento a buscar.
     * @return Índice del elemento con el ID indicado, o -1 si no se encuentra.
     */
    public int getIndexOf(int direccionId) {
        for (int i = 0; i < direcciones.length; i++) {
            if (direcciones[i].getId() == direccionId) {
                return i;
            }
        }
        return -1;
    }
}
