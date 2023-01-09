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
}
