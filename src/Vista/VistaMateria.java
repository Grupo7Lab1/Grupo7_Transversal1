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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLDatosdelaMateria = new javax.swing.JLabel();
        jLCodigo = new javax.swing.JLabel();
        jTCodigo = new javax.swing.JTextField();
        jLanio = new javax.swing.JLabel();
        jTanio = new javax.swing.JTextField();
        jLCatedra = new javax.swing.JLabel();
        jTCatedra = new javax.swing.JTextField();
        jLEstado = new javax.swing.JLabel();
        jCBActiva = new javax.swing.JCheckBox();
        jBNuevo = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();
        jBActualizar = new javax.swing.JButton();
        jBBorrar = new javax.swing.JButton();
        jBBajaLogica = new javax.swing.JButton();
        jBAltaLogica = new javax.swing.JButton();
        jBListar = new javax.swing.JButton();
        jBSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setTitle("Materia");

        jLDatosdelaMateria.setText("Datos de la Materia");

        jLCodigo.setText("Codigo");

        jLanio.setText("Año");

        jTanio.addActionListener(this::jTanioActionPerformed);

        jLCatedra.setText("Catedra");

        jLEstado.setText("Estado");

        jCBActiva.setText("Activa");

        jBNuevo.setText("Nuevo");
        jBNuevo.addActionListener(this::jBNuevoActionPerformed);

        jBGuardar.setText("Guardar");
        jBGuardar.addActionListener(this::jBGuardarActionPerformed);

        jBActualizar.setText("Actualizar");
        jBActualizar.addActionListener(this::jBActualizarActionPerformed);

        jBBorrar.setText("Borrar");
        jBBorrar.addActionListener(this::jBBorrarActionPerformed);

        jBBajaLogica.setText("Baja Logica");
        jBBajaLogica.addActionListener(this::jBBajaLogicaActionPerformed);

        jBAltaLogica.setText("Alta Logica");
        jBAltaLogica.addActionListener(this::jBAltaLogicaActionPerformed);

        jBListar.setText("Listar");
        jBListar.addActionListener(this::jBListarActionPerformed);

        jBSalir.setText("Salir");
        jBSalir.addActionListener(this::jBSalirActionPerformed);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Catedra", "Año", "Activa"
            }
        ));
        jScrollPane1.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLCatedra)
                                .addGap(18, 18, 18)
                                .addComponent(jTCatedra, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLanio, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTanio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBActiva, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jLDatosdelaMateria)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBNuevo)
                                    .addComponent(jBBajaLogica))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jBAltaLogica)
                                        .addGap(18, 18, 18)
                                        .addComponent(jBListar)
                                        .addGap(27, 27, 27))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jBGuardar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jBActualizar)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(jBBorrar))
                                    .addComponent(jBSalir, javax.swing.GroupLayout.Alignment.TRAILING))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLDatosdelaMateria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCodigo)
                    .addComponent(jTCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLanio)
                    .addComponent(jTanio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCatedra)
                    .addComponent(jTCatedra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLEstado)
                    .addComponent(jCBActiva))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBNuevo)
                    .addComponent(jBGuardar)
                    .addComponent(jBActualizar)
                    .addComponent(jBBorrar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBBajaLogica)
                    .addComponent(jBAltaLogica)
                    .addComponent(jBListar)
                    .addComponent(jBSalir))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTanioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTanioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTanioActionPerformed

    private void jBNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNuevoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBNuevoActionPerformed

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jBActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBActualizarActionPerformed

    private void jBBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBorrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBBorrarActionPerformed

    private void jBBajaLogicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBajaLogicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBBajaLogicaActionPerformed

    private void jBAltaLogicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAltaLogicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBAltaLogicaActionPerformed

    private void jBListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBListarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBListarActionPerformed

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBActualizar;
    private javax.swing.JButton jBAltaLogica;
    private javax.swing.JButton jBBajaLogica;
    private javax.swing.JButton jBBorrar;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBListar;
    private javax.swing.JButton jBNuevo;
    private javax.swing.JButton jBSalir;
    private javax.swing.JCheckBox jCBActiva;
    private javax.swing.JLabel jLCatedra;
    private javax.swing.JLabel jLCodigo;
    private javax.swing.JLabel jLDatosdelaMateria;
    private javax.swing.JLabel jLEstado;
    private javax.swing.JLabel jLanio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTCatedra;
    private javax.swing.JTextField jTCodigo;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTanio;
    // End of variables declaration//GEN-END:variables
}
