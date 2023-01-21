package vistas.models;

import modelos.Empleado;

import javax.swing.*;

public class EmpleadosComboBoxModel  extends AbstractListModel implements ComboBoxModel {
    private Empleado[] empleados;
    private Empleado selection = null;

    public EmpleadosComboBoxModel(Empleado[] empleados) {
        this.empleados = empleados;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (Empleado) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }

    @Override
    public int getSize() {
        return empleados.length;
    }

    @Override
    public Object getElementAt(int index) {
        return empleados[index];
    }
}
