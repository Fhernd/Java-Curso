package vistas.models;

import modelos.TipoAtencion;

import javax.swing.*;

public class TipoAtencionComboBoxModel extends AbstractListModel implements ComboBoxModel {

        private TipoAtencion[] tipoAtencion;
        private TipoAtencion selection = null;

        public TipoAtencionComboBoxModel(TipoAtencion[] tipoAtencion) {
            this.tipoAtencion = tipoAtencion;
        }

        @Override
        public void setSelectedItem(Object anItem) {
            selection = (TipoAtencion) anItem;
        }

        @Override
        public Object getSelectedItem() {
            return selection;
        }

        @Override
        public int getSize() {
            return tipoAtencion.length;
        }

        @Override
        public Object getElementAt(int index) {
            return tipoAtencion[index];
        }
}
