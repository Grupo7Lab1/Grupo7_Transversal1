/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Persistencia.alumnoData;
import Persistencia.inscripcionData;
import Persistencia.materiaData;
import dominio.Inscripcion;
import dominio.Materia;
import dominio.alumno;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;




public class VistaInscripcion extends javax.swing.JInternalFrame {

    private DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false; // Evita que la tabla sea editable directamente
        }
    };

    private alumnoData aluData;
    private materiaData matData;
    private inscripcionData insData;

    public VistaInscripcion() {
        initComponents();

        aluData = new alumnoData();
        matData = new materiaData();
        insData = new inscripcionData();

        armarCabeceraTabla();
        cargarComboAlumnos();

        // Estado inicial de selección
        jRBnoInscriptas.setSelected(true);
        jBInscribir.setEnabled(true);
        jBAnular.setEnabled(false);
        cargarMateriasEnTabla();
    }

    private void armarCabeceraTabla() {
        modelo.addColumn("ID");
        modelo.addColumn("Materia");
        modelo.addColumn("Año");
        jTable1.setModel(modelo);
    }

    private void cargarComboAlumnos() {
        jCboAlumnos.removeAllItems();
        List<alumno> lista = aluData.listarAlumnos(); // Método correcto de alumnoData
        for (alumno a : lista) {
            if (a.isEstado()) { // Método getter de alumno
                jCboAlumnos.addItem(a.getNombre() + " " + a.getApellido()); 
            }
        }
    }

    private void borrarFilasTabla() {
        int filas = modelo.getRowCount() - 1;
        for (int i = filas; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }

    private void cargarMateriasEnTabla() {
        borrarFilasTabla();
        int indiceSeleccionado = jCboAlumnos.getSelectedIndex();

        if (indiceSeleccionado != -1) {
            List<alumno> alumnos = aluData.listarAlumnos();
            alumno selec = alumnos.get(indiceSeleccionado);

            if (selec != null) {
                List<Materia> lista;
                if (jRBInscriptas.isSelected()) {
                    lista = insData.obtenerMateriasCursadas(selec.getIdAlumno());
                } else {
                    lista = insData.obtenerMateriasNOCursadas(selec.getIdAlumno());
                }

                for (Materia m : lista) {
                    modelo.addRow(new Object[]{m.getIdMateria(), m.getNombre(), m.getAño()});
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jlbAlumno = new javax.swing.JLabel();
        jCboAlumnos = new javax.swing.JComboBox<>();
        jLConsultarMaterias = new javax.swing.JLabel();
        jRBInscriptas = new javax.swing.JRadioButton();
        jRBnoInscriptas = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jBInscribir = new javax.swing.JButton();
        jBAnular = new javax.swing.JButton();

        setTitle("Inscripcion");

        jlbAlumno.setText("ALumno");

        jCboAlumnos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCboAlumnos.addActionListener(this::jCboAlumnosActionPerformed);

        jLConsultarMaterias.setText("Consultar Materias");

        buttonGroup1.add(jRBInscriptas);
        jRBInscriptas.setText("Inscriptas");
        jRBInscriptas.addActionListener(this::jRBInscriptasActionPerformed);

        buttonGroup2.add(jRBnoInscriptas);
        jRBnoInscriptas.setText("No Inscriptas");
        jRBnoInscriptas.addActionListener(this::jRBnoInscriptasActionPerformed);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Materia", "Estado"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jBInscribir.setText("Inscribir");
        jBInscribir.addActionListener(this::jBInscribirActionPerformed);

        jBAnular.setText("Anular");
        jBAnular.addActionListener(this::jBAnularActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCboAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLConsultarMaterias, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jRBInscriptas)))
                        .addGap(77, 77, 77)
                        .addComponent(jRBnoInscriptas))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBInscribir, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(jBAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbAlumno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCboAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLConsultarMaterias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRBnoInscriptas, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jRBInscriptas))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBAnular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBInscribir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(221, 221, 221))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCboAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCboAlumnosActionPerformed
        cargarMateriasEnTabla();
    }//GEN-LAST:event_jCboAlumnosActionPerformed

    private void jRBInscriptasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBInscriptasActionPerformed
       jBInscribir.setEnabled(false);
        jBAnular.setEnabled(true);
        cargarMateriasEnTabla();
    }//GEN-LAST:event_jRBInscriptasActionPerformed

    private void jRBnoInscriptasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBnoInscriptasActionPerformed
        jBInscribir.setEnabled(true);
        jBAnular.setEnabled(false);
        cargarMateriasEnTabla();
    }//GEN-LAST:event_jRBnoInscriptasActionPerformed

    private void jBInscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBInscribirActionPerformed
      int filaSeleccionada = jTable1.getSelectedRow();
        int indiceAlumno = jCboAlumnos.getSelectedIndex();
        
        if (filaSeleccionada != -1 && indiceAlumno != -1) {
            List<alumno> alumnos = aluData.listarAlumnos();
            alumno alu = alumnos.get(indiceAlumno);

            int idMateria = (Integer) modelo.getValueAt(filaSeleccionada, 0);
            String nombreMateria = (String) modelo.getValueAt(filaSeleccionada, 1);
            int anio = (Integer) modelo.getValueAt(filaSeleccionada, 2);

            Materia mat = new Materia(idMateria, nombreMateria, anio, true);
            Inscripcion insc = new Inscripcion(0.0, alu, mat);

            insData.guardarInscripcion(insc);
            cargarMateriasEnTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una materia de la tabla para inscribir.");
        }
    
    }//GEN-LAST:event_jBInscribirActionPerformed

    private void jBAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAnularActionPerformed
         int filaSeleccionada = jTable1.getSelectedRow();
        int indiceAlumno = jCboAlumnos.getSelectedIndex();

        if (filaSeleccionada != -1 && indiceAlumno != -1) {
            List<alumno> alumnos = aluData.listarAlumnos();
            alumno alu = alumnos.get(indiceAlumno);

            int idMateria = (Integer) modelo.getValueAt(filaSeleccionada, 0);

            insData.borrarInscripcionMateriaAlumno(alu.getIdAlumno(), idMateria);
            cargarMateriasEnTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una materia de la tabla para anular la inscripción.");
        }
    }
    }//GEN-LAST:event_jBAnularActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jBAnular;
    private javax.swing.JButton jBInscribir;
    private javax.swing.JComboBox<String> jCboAlumnos;
    private javax.swing.JLabel jLConsultarMaterias;
    private javax.swing.JRadioButton jRBInscriptas;
    private javax.swing.JRadioButton jRBnoInscriptas;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlbAlumno;
    // End of variables declaration//GEN-END:variables

