package vistas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DemoGUI {
    private JButton btnMostrarMensaje;
    private JPanel panel1;

    public DemoGUI() {
        btnMostrarMensaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hola Mundo");
            }
        });
    }
}
