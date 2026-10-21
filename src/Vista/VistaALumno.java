/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import dominio.alumno;
import Persistencia.alumnoData;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nestor Marchizone
 */
public class VistaALumno extends javax.swing.JInternalFrame {

    private final alumnoData datos = new alumnoData();
    private int idSeleccionado = 0;

    public VistaALumno() {
        initComponents();

        configurarTabla();
        cargarTabla();

        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                seleccionarAlumno();
            }
        });
    }

    private void seleccionarAlumno() {
        int fila = jTable1.getSelectedRow();

        if (fila == -1) {
            return;
        }

        idSeleccionado = Integer.parseInt(
                jTable1.getValueAt(fila, 0).toString()
        );

        alumno a = datos.buscarPorId(idSeleccionado);

        if (a != null) {
            jTNrodeDocumento.setText(a.getDni());

            jTApellidoyNombre.setText(
                    a.getApellido() + " " + a.getNombre()
            );

            jCBActivo.setSelected(a.isActivo());

            if (a.getFechaNacimiento() != null) {
                java.sql.Date fecha = java.sql.Date.valueOf(
                        a.getFechaNacimiento()
                );

                jDCFechadeNaciemieno.setDate(
                        new java.util.Date(fecha.getTime())
                );
            } else {
                jDCFechadeNaciemieno.setDate(null);
            }
        }
    }

    private void limpiarCampos() {
        idSeleccionado = 0;
        jTNrodeDocumento.setText("");
        jTApellidoyNombre.setText("");
        jDCFechadeNaciemieno.setDate(null);
        jCBActivo.setSelected(true);
    }

    private void configurarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"ID", "DNI", "Apellido y nombre",
                    "Fecha de nacimiento", "Activo"}, 0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        javax.swing.JTable tabla = (javax.swing.JTable) jSPTablaAlumno.getViewport().getView();
        tabla.setModel(modelo);
    }

    private void cargarTabla() {
        javax.swing.JTable tabla = (javax.swing.JTable) jSPTablaAlumno.getViewport().getView();
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        List<alumno> lista = datos.listarAlumnos();

        for (alumno a : lista) {
            modelo.addRow(new Object[]{
                a.getIdAlumno(),
                a.getDni(),
                a.getApellido() + ", " + a.getNombre(),
                a.getFechaNacimiento(),
                a.isActivo()
            });
        }
    }

    private alumno leerFormulario() {
        String dni = jTNrodeDocumento.getText().trim();
        String nombreCompleto = jTApellidoyNombre.getText().trim();

        if (dni.isEmpty() || nombreCompleto.isEmpty()
                || jDCFechadeNaciemieno.getDate() == null) {
            JOptionPane.showMessageDialog(
                    this, "Completá el nombre, DNI y fecha de nacimiento."
            );
            return null;
        }

        String[] partes = nombreCompleto.split("\\s+", 2);

        if (partes.length < 2) {
            JOptionPane.showMessageDialog(
                    this, "Ingresá primero el apellido y después el nombre."
            );
            return null;
        }

        java.util.Date fecha = jDCFechadeNaciemieno.getDate();

        LocalDate nacimiento = new java.sql.Date(
                fecha.getTime()
        ).toLocalDate();

        return new alumno(
                dni,
                partes[1],
                partes[0],
                nacimiento,
                jCBActivo.isSelected()
        );
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLFechadeNacimiento = new javax.swing.JLabel();
        jLEstado = new javax.swing.JLabel();
        jTApellidoyNombre = new javax.swing.JTextField();
        jTNrodeDocumento = new javax.swing.JTextField();
        jDCFechadeNaciemieno = new com.toedter.calendar.JDateChooser();
        jCBActivo = new javax.swing.JCheckBox();
        jBInsertar = new javax.swing.JButton();
        jBActualizar = new javax.swing.JButton();
        jBBorrar = new javax.swing.JButton();
        jBBajaLogica = new javax.swing.JButton();
        jBAltaLogica = new javax.swing.JButton();
        jBLimpiar = new javax.swing.JButton();
        jSPTablaAlumno = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setTitle("Vista Alumno");

        jLabel1.setText("Apellido y Nombre");

        jLabel2.setText("Nro de Documento");

        jLFechadeNacimiento.setText("Fecha de Nacimiento");

        jLEstado.setText("Estado");

        jCBActivo.setText("Activo");

        jBInsertar.setText("Insertar");
        jBInsertar.addActionListener(this::jBInsertarActionPerformed);

        jBActualizar.setText("Actualizar");
        jBActualizar.addActionListener(this::jBActualizarActionPerformed);

        jBBorrar.setText("Borrar");
        jBBorrar.addActionListener(this::jBBorrarActionPerformed);

        jBBajaLogica.setText("Baja Logica");
        jBBajaLogica.addActionListener(this::jBBajaLogicaActionPerformed);

        jBAltaLogica.setText("Alta Logica");
        jBAltaLogica.addActionListener(this::jBAltaLogicaActionPerformed);

        jBLimpiar.setText("Limpíar");
        jBLimpiar.addActionListener(this::jBLimpiarActionPerformed);

        jSPTablaAlumno.setName("jSPTablaAlumno."); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Apellido y Nombre", "Fecha de Nacimiento", "Activo"
            }
        ));
        jSPTablaAlumno.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLFechadeNacimiento)
                            .addComponent(jLEstado)
                            .addComponent(jBInsertar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTApellidoyNombre)
                            .addComponent(jTNrodeDocumento)
                            .addComponent(jDCFechadeNaciemieno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCBActivo)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jBAltaLogica)
                                    .addComponent(jBActualizar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBBorrar, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jBLimpiar, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBBajaLogica)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSPTablaAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTApellidoyNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTNrodeDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLFechadeNacimiento)
                    .addComponent(jDCFechadeNaciemieno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLEstado)
                    .addComponent(jCBActivo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBInsertar)
                    .addComponent(jBBorrar)
                    .addComponent(jBActualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBBajaLogica)
                        .addComponent(jBAltaLogica))
                    .addComponent(jBLimpiar))
                .addGap(18, 18, 18)
                .addComponent(jSPTablaAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBInsertarActionPerformed
        alumno a = leerFormulario();

        if (a == null) {
            return;
        }

        if (datos.buscarPorDni(a.getDni()) != null) {
            JOptionPane.showMessageDialog(
                    this, "Ya existe un alumno con ese DNI."
            );
            return;
        }

        datos.guardarAlumno(a);
        cargarTabla();
        limpiarCampos();

        JOptionPane.showMessageDialog(
                this, "Alumno insertado correctamente."
        );

    }//GEN-LAST:event_jBInsertarActionPerformed

    private void jBActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBActualizarActionPerformed
        if (idSeleccionado == 0) {
            JOptionPane.showMessageDialog(
                    this, "Seleccioná un alumno de la tabla."
            );
            return;
        }

        alumno a = leerFormulario();

        if (a == null) {
            return;
        }

        alumno existente = datos.buscarPorDni(a.getDni());

        if (existente != null
                && existente.getIdAlumno() != idSeleccionado) {
            JOptionPane.showMessageDialog(
                    this, "Ese DNI pertenece a otro alumno."
            );
            return;
        }

        a.setIdAlumno(idSeleccionado);
        datos.modificar(a);
        cargarTabla();
        limpiarCampos();

        JOptionPane.showMessageDialog(
                this, "Alumno actualizado correctamente."
        );
    }//GEN-LAST:event_jBActualizarActionPerformed

    private void jBBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBorrarActionPerformed
        if (idSeleccionado == 0) {
            JOptionPane.showMessageDialog(
                    this, "Seleccioná un alumno de la tabla."
            );
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Querés eliminar definitivamente este alumno?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            datos.eliminar(idSeleccionado);
            cargarTabla();
            limpiarCampos();
        }
    }//GEN-LAST:event_jBBorrarActionPerformed

    private void jBBajaLogicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBajaLogicaActionPerformed
        if (idSeleccionado == 0) {
            JOptionPane.showMessageDialog(
                    this, "Seleccioná un alumno de la tabla."
            );
            return;
        }

        datos.darDeBaja(idSeleccionado);
        cargarTabla();
        limpiarCampos();

        JOptionPane.showMessageDialog(
                this, "Baja lógica realizada."
        );
    }//GEN-LAST:event_jBBajaLogicaActionPerformed

    private void jBAltaLogicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAltaLogicaActionPerformed
        if (idSeleccionado == 0) {
            JOptionPane.showMessageDialog(
                    this, "Seleccioná un alumno de la tabla."
            );
            return;
        }

        datos.darDeAlta(idSeleccionado);
        cargarTabla();
        limpiarCampos();

        JOptionPane.showMessageDialog(
                this, "Alta lógica realizada."
        );
    }//GEN-LAST:event_jBAltaLogicaActionPerformed

    private void jBLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_jBLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBActualizar;
    private javax.swing.JButton jBAltaLogica;
    private javax.swing.JButton jBBajaLogica;
    private javax.swing.JButton jBBorrar;
    private javax.swing.JButton jBInsertar;
    private javax.swing.JButton jBLimpiar;
    private javax.swing.JCheckBox jCBActivo;
    private com.toedter.calendar.JDateChooser jDCFechadeNaciemieno;
    private javax.swing.JLabel jLEstado;
    private javax.swing.JLabel jLFechadeNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jSPTablaAlumno;
    private javax.swing.JTextField jTApellidoyNombre;
    private javax.swing.JTextField jTNrodeDocumento;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
