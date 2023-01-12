package vistas.models;

import modelos.Rol;

import javax.swing.*;

public class RolesComboBoxModel extends AbstractListModel implements ComboBoxModel {

    private Rol[] roles;
    private Rol selection = null;

    public RolesComboBoxModel(Rol[] roles) {
        this.roles = roles;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (Rol) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }

    @Override
    public int getSize() {
        return roles.length;
    }

    @Override
    public Object getElementAt(int index) {
        return roles[index];
    }

    public int buscarIndiceDelRol(int rolId) {
        for (int i = 0; i < roles.length; i++) {
            if (roles[i].getId() == rolId) {
                return i;
            }
        }

        return -1;
    }

    public int buscarIdRolPorNombre(String nombreRol) {
        for (int i = 0; i < roles.length; i++) {
            if (roles[i].getNombre().equals(nombreRol)) {
                return roles[i].getId();
            }
        }

        return -1;
    }
}
