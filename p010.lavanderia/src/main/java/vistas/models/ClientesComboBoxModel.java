package vistas.models;

import modelos.Cliente;

import javax.swing.*;

public class ClientesComboBoxModel extends AbstractListModel implements ComboBoxModel {

    private Cliente[] clientes;
    private Cliente selection = null;

    public ClientesComboBoxModel(Cliente[] clientes) {
        this.clientes = clientes;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (Cliente) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }

    @Override
    public int getSize() {
        return clientes.length;
    }

    @Override
    public Object getElementAt(int index) {
        return clientes[index];
    }
}
