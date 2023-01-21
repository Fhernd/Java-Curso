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
}
