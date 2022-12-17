package vistas.models;

import modelos.TipoDocumento;

import javax.swing.*;

public class CustomComboBoxModel extends AbstractListModel implements ComboBoxModel {
    TipoDocumento[] tipoDocumentos;
    TipoDocumento selection = null;

    public CustomComboBoxModel(TipoDocumento[] tipoDocumentos) {
        this.tipoDocumentos = tipoDocumentos;
    }

    @Override
    public Object getElementAt(int index) {
        return tipoDocumentos[index];
    }

    @Override
    public int getSize() {
        return tipoDocumentos.length;
    }

    public void setSelectedItem(Object anItem) {
        selection = (TipoDocumento) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }
}
