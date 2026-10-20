/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Persistencia.alumnoData;
import Persistencia.inscripcionData;
import dominio.Inscripcion;
import dominio.alumno;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mrjoshep
 */
public class VistaCargarNotas extends javax.swing.JInternalFrame {

    private inscripcionData id = new inscripcionData();
    private alumnoData ad = new alumnoData();
    private DefaultTableModel modeloTabla;

    public VistaCargarNotas() {
        initComponents();
        modeloTabla = new DefaultTableModel(
                new String[]{"ID Inscripcion", "Materia", "Nota"}, 0);
        jTable1.setModel(modeloTabla);

        cargarAlumnos();

        jCBSelecciondeAlumno.addActionListener(e -> cargarInscripciones());
    }

    private void cargarAlumnos() {
        jCBSelecciondeAlumno.removeAllItems();
        for (alumno a : ad.listarAlumnos()) {
            jCBSelecciondeAlumno.addItem(a);
        }
    }

    private void cargarInscripciones() {
        jCBInscripciones.removeAllItems();
        alumno a = (alumno) jCBSelecciondeAlumno.getSelectedItem();
        if (a == null) {
            return;
        }
        for (Inscripcion insc : id.obtenerInscripcionesPorAlumno(a.getIdAlumno())) {
            jCBInscripciones.addItem(insc);
        }
        listar();
    }

    private void listar() {
        modeloTabla.setRowCount(0);
        alumno a = (alumno) jCBSelecciondeAlumno.getSelectedItem();
        if (a == null) {
            return;
        }
        for (Inscripcion insc : id.obtenerInscripcionesPorAlumno(a.getIdAlumno())) {
            modeloTabla.addRow(new Object[]{
                insc.getIdInscripcion(),
                insc.getMateria().getNombre(),
                insc.getNota()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLSeleccionarAlumno = new javax.swing.JLabel();
        jCBSelecciondeAlumno = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jCBInscripciones = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLNotasdelaInscripcion = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();
        jBModificar = new javax.swing.JButton();
        jBLimpiar = new javax.swing.JButton();
        jBSalir = new javax.swing.JButton();

        setTitle("Cargar Nota");

        jLSeleccionarAlumno.setText("Seleccionar Alumno");

        jCBSelecciondeAlumno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Seleccionar materia / inscripción");

        jCBInscripciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Parcial 1", "Parcial 2", "Trabajo Practico", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jLNotasdelaInscripcion.setText("Notas de la inscripción");

        jButton1.setText("Cargar");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jBGuardar.setText("Guardar");
        jBGuardar.addActionListener(this::jBGuardarActionPerformed);

        jBModificar.setText("Modificar");
        jBModificar.addActionListener(this::jBModificarActionPerformed);

        jBLimpiar.setText("Limpiar");
        jBLimpiar.addActionListener(this::jBLimpiarActionPerformed);

        jBSalir.setText("Salir");
        jBSalir.addActionListener(this::jBSalirActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jLNotasdelaInscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLSeleccionarAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCBSelecciondeAlumno, 0, 243, Short.MAX_VALUE)
                            .addComponent(jCBInscripciones, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBSalir)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLSeleccionarAlumno)
                    .addComponent(jCBSelecciondeAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jCBInscripciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jLNotasdelaInscripcion)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jBGuardar)
                    .addComponent(jBModificar)
                    .addComponent(jBLimpiar)
                    .addComponent(jBSalir))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cargarInscripciones();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
        try {
            alumno a = (alumno) jCBSelecciondeAlumno.getSelectedItem();
            Inscripcion insc = (Inscripcion) jCBInscripciones.getSelectedItem();
            if (a == null || insc == null) {
                JOptionPane.showMessageDialog(this, "Seleccione alumno e inscripción.");
                return;
            }
            Inscripcion nueva = new Inscripcion();
            nueva.setAlumno(a);
            nueva.setMateria(insc.getMateria());
            nueva.setNota(0);
            id.guardarInscripcion(nueva);
            listar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jBModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBModificarActionPerformed
        try {
            alumno a = (alumno) jCBSelecciondeAlumno.getSelectedItem();
            Inscripcion insc = (Inscripcion) jCBInscripciones.getSelectedItem();
            if (a == null || insc == null) {
                JOptionPane.showMessageDialog(this, "Seleccione alumno e inscripción.");
                return;
            }
            String notaStr = JOptionPane.showInputDialog(this, "Ingrese la nota:");
            if (notaStr == null) {
                return;
            }
            double nota = Double.parseDouble(notaStr);
            id.actualizarNotas(
                    a.getIdAlumno(),
                    insc.getMateria().getIdMateria(),
                    nota
            );
            listar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_jBModificarActionPerformed

    private void jBLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLimpiarActionPerformed
        if (jCBSelecciondeAlumno.getItemCount() > 0) {
            jCBSelecciondeAlumno.setSelectedIndex(0);
        }
        jCBInscripciones.removeAllItems();
        modeloTabla.setRowCount(0);
    }//GEN-LAST:event_jBLimpiarActionPerformed

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        dispose();
    }//GEN-LAST:event_jBSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBLimpiar;
    private javax.swing.JButton jBModificar;
    private javax.swing.JButton jBSalir;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jCBInscripciones;
    private javax.swing.JComboBox<String> jCBSelecciondeAlumno;
    private javax.swing.JLabel jLNotasdelaInscripcion;
    private javax.swing.JLabel jLSeleccionarAlumno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
