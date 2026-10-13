/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import modelo.Materia;
import persistencia.MateriaData;
/**
 *
 * @author Mrjoshep
 */
public class VistaMateria extends JInternalFrame {

    private JTextField txtId, txtNombre;
    private DefaultTableModel modeloTabla;
    private MateriaData md = new MateriaData();

    public VistaMateria() {
        super("Gestión de Materias", true, true, true, true);
        setSize(600, 450);
        setLayout(new BorderLayout(5, 5));

        JPanel pForm = new JPanel(new GridLayout(2, 2, 5, 5));
        pForm.setBorder(BorderFactory.createTitledBorder("Datos de la Materia"));
        pForm.add(new JLabel("ID:"));   txtId = new JTextField(); pForm.add(txtId);
        pForm.add(new JLabel("Nombre:")); txtNombre = new JTextField(); pForm.add(txtNombre);

        JPanel pBtn = new JPanel(new FlowLayout());
        JButton btnNuevo      = new JButton("Nuevo");
        JButton btnInsertar   = new JButton("Insertar");
        JButton btnBuscar     = new JButton("Buscar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar   = new JButton("Eliminar Físico");
        JButton btnBaja       = new JButton("Baja Lógica");
        JButton btnAlta       = new JButton("Alta Lógica");
        JButton btnListar     = new JButton("Listar");
        for (JButton b : new JButton[]{btnNuevo,btnInsertar,btnBuscar,btnActualizar,
                                        btnEliminar,btnBaja,btnAlta,btnListar})
            pBtn.add(b);

        modeloTabla = new DefaultTableModel(new String[]{"ID","Nombre","Estado"}, 0);
        JTable tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);

        JPanel pNorte = new JPanel(new BorderLayout());
        pNorte.add(pForm, BorderLayout.CENTER);
        pNorte.add(pBtn,  BorderLayout.SOUTH);
        add(pNorte, BorderLayout.NORTH);
        add(scroll,  BorderLayout.CENTER);

        btnNuevo.addActionListener(e -> { txtId.setText(""); txtNombre.setText(""); });

        btnInsertar.addActionListener(e -> {
            try {
                if (md.insertar(new Materia(txtNombre.getText()))) {
                    JOptionPane.showMessageDialog(this, "Materia insertada.");
                    listar();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        btnBuscar.addActionListener(e -> {
            try {
                Materia m = md.buscar(Integer.parseInt(txtId.getText()));
                if (m != null) txtNombre.setText(m.getNombre());
                else JOptionPane.showMessageDialog(this, "No encontrada.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        btnActualizar.addActionListener(e -> {
            try {
                Materia m = new Materia(Integer.parseInt(txtId.getText()), txtNombre.getText(), 1);
                if (md.actualizar(m)) {
                    JOptionPane.showMessageDialog(this, "Materia actualizada.");
                    listar();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                int ok = JOptionPane.showConfirmDialog(this, "¿Eliminar físicamente?");
                if (ok == JOptionPane.YES_OPTION && md.eliminar(Integer.parseInt(txtId.getText()))) {
                    JOptionPane.showMessageDialog(this, "Eliminada.");
                    listar();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        btnBaja.addActionListener(e -> {
            try {
                if (md.bajaLogica(Integer.parseInt(txtId.getText())))
                    JOptionPane.showMessageDialog(this, "Baja lógica aplicada.");
                listar();
            } catch (Exception ex) { }
        });

        btnAlta.addActionListener(e -> {
            try {
                if (md.altaLogica(Integer.parseInt(txtId.getText())))
                    JOptionPane.showMessageDialog(this, "Alta lógica aplicada.");
                listar();
            } catch (Exception ex) { }
        });

        btnListar.addActionListener(e -> listar());

        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int f = tabla.getSelectedRow();
                txtId.setText(modeloTabla.getValueAt(f, 0).toString());
                txtNombre.setText(modeloTabla.getValueAt(f, 1).toString());
            }
        });

        listar();
    }

    private void listar() {
        modeloTabla.setRowCount(0);
        for (Materia m : md.listarTodos()) {
            modeloTabla.addRow(new Object[]{m.getIdMateria(), m.getNombre(), m.getEstado()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
