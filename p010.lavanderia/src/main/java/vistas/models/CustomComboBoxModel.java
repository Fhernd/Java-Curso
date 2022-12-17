package vistas.models;

import modelos.TipoDocumento;

import javax.swing.*;

public class CustomComboBoxModel extends AbstractListModel implements ComboBoxModel {
    TipoDocumento[] tipoDocumentos;

    public CustomComboBoxModel(TipoDocumento[] tipoDocumentos) {
        this.tipoDocumentos = tipoDocumentos;
    }

    String selection = null;

    @Override
    public Object getElementAt(int index) {
        return tipoDocumentos[index];
    }

    @Override
    public int getSize() {
        return tipoDocumentos.length;
    }

    public void setSelectedItem(Object anItem) {
        selection = (String) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }
}
