/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Persistencia.alumnoData;
import Persistencia.inscripcionData;
import dominio.Inscripcion;
import dominio.alumno;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VistaListarInscripciones extends javax.swing.JInternalFrame {

    private DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            
            return columna == 4;
        }
    };

    private alumnoData aluData;
    private inscripcionData insData;

    public VistaListarInscripciones() {
        initComponents();

        aluData = new alumnoData();
        insData = new inscripcionData();

        armarCabeceraTabla();
        cargarComboAlumnos();
    }

    private void armarCabeceraTabla() {
        modelo.addColumn("ID Inscrip.");
        modelo.addColumn("ID Materia");
        modelo.addColumn("Materia");
        modelo.addColumn("Estado");
        modelo.addColumn("Nota");
        jTable1.setModel(modelo);
    }

    private void cargarComboAlumnos() {
        jCboAlumnos.removeAllItems();
        List<alumno> lista = aluData.listarAlumnos();
        for (alumno a : lista) {
            if (a.isActivo()) {
                jCboAlumnos.addItem(a.getNombre() + " " + a.getApellido());
            }
        }
    }

    private void borrarFilasTabla() {
           modelo.setRowCount(0);
         }

    private void cargarInscripcionesEnTabla() {
        borrarFilasTabla();
        int indiceSeleccionado = jCboAlumnos.getSelectedIndex();

        if (indiceSeleccionado != -1) {
            List<alumno> alumnos = aluData.listarAlumnos();
            alumno selec = alumnos.get(indiceSeleccionado);

            if (selec != null) {
                List<Inscripcion> lista = insData.obtenerInscripcionesPorAlumno(selec.getIdAlumno());
                for (Inscripcion i : lista) {
                    modelo.addRow(new Object[]{
                        i.getIdInscripcion(),
                        i.getMateria().getIdMateria(),
                        i.getMateria().getNombre(),
                        i.getMateria().isActivo() ? "Inscripto" : "Inactivo",
                        i.getNota()
                    });
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un alumno.");
        }
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLSeleccionarAlumno = new javax.swing.JLabel();
        jCboAlumnos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jBListar = new javax.swing.JButton();
        jBActualizar = new javax.swing.JButton();
        jBSalir = new javax.swing.JButton();

        setTitle("Listar Inscripciones");

        jLSeleccionarAlumno.setText("Seleccionar Alumno");

        jCboAlumnos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Codigo", "Materia", "Estado", "Nota"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jBListar.setText("Listar");
        jBListar.addActionListener(this::jBListarActionPerformed);

        jBActualizar.setText("Actualizar");
        jBActualizar.addActionListener(this::jBActualizarActionPerformed);

        jBSalir.setText("Salir");
        jBSalir.addActionListener(this::jBSalirActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLSeleccionarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCboAlumnos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBListar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(jBActualizar)
                        .addGap(71, 71, 71)
                        .addComponent(jBSalir)
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLSeleccionarAlumno)
                    .addComponent(jCboAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBListar)
                    .addComponent(jBActualizar)
                    .addComponent(jBSalir))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBListarActionPerformed
        cargarInscripcionesEnTabla();
    }//GEN-LAST:event_jBListarActionPerformed

    private void jBActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBActualizarActionPerformed
         int filaSeleccionada = jTable1.getSelectedRow();
        int indiceAlumno = jCboAlumnos.getSelectedIndex();

        if (filaSeleccionada != -1 && indiceAlumno != -1) {
            List<alumno> alumnos = aluData.listarAlumnos();
            alumno alu = alumnos.get(indiceAlumno);

            int idMateria = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 1).toString());
            
            try {
                double nuevaNota = Double.parseDouble(modelo.getValueAt(filaSeleccionada, 4).toString());

                if (nuevaNota >= 0 && nuevaNota <= 10) {
                    insData.actualizarNota(alu.getIdAlumno(), idMateria, nuevaNota);
                    cargarInscripcionesEnTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "La nota debe estar entre 0 y 10.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un valor numérico válido en la celda Nota.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila de la tabla para actualizar la nota.");
        }
                 
    }//GEN-LAST:event_jBActualizarActionPerformed

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        dispose();
    }//GEN-LAST:event_jBSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBActualizar;
    private javax.swing.JButton jBListar;
    private javax.swing.JButton jBSalir;
    private javax.swing.JComboBox<String> jCboAlumnos;
    private javax.swing.JLabel jLSeleccionarAlumno;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
