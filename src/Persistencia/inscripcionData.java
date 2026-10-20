/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import dominio.Inscripcion;
import dominio.alumno;
import dominio.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class inscripcionData {

    private Connection con = null;
    private materiaData matData = new materiaData();
    private alumnoData aluData = new alumnoData();

    public inscripcionData() throws SQLException {
        this.con = conexion.conectar();
    }

    // Guardar o Inscribir Alumno en Materia
    public void guardarInscripcion(Inscripcion insc) {
        String sql = "INSERT INTO inscripcion (nota, idAlumno, idMateria) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, insc.getNota());
            ps.setInt(2, insc.getAlumno().getIdAlumno());
            ps.setInt(3, insc.getMateria().getIdMateria());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                insc.setIdInscripcion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Inscripción realizada con éxito.");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripción: " + ex.getMessage());
        }
    }

    // Obtener todas las inscripciones
    public List<Inscripcion> obtenerInscripciones() {
        ArrayList<Inscripcion> cursadas = new ArrayList<>();
        String sql = "SELECT * FROM inscripcion";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripcion(rs.getInt("idInscripcion"));

                alumno a = aluData.buscarPorId(rs.getInt("idAlumno"));
                Materia m = matData.buscarMateria(rs.getInt("idMateria"));

                insc.setAlumno(a);
                insc.setMateria(m);
                insc.setNota(rs.getDouble("nota"));

                cursadas.add(insc);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener inscripciones: " + ex.getMessage());
        }

        return cursadas;
    }

    // Obtener inscripciones por Alumno
    public List<Inscripcion> obtenerInscripcionesPorAlumno(int idAlumno) {
        ArrayList<Inscripcion> cursadas = new ArrayList<>();
        String sql = "SELECT * FROM inscripcion WHERE idAlumno = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripcion(rs.getInt("idInscripcion"));

                alumno a = aluData.buscarPorId(rs.getInt("idAlumno"));
                Materia m = matData.buscarMateria(rs.getInt("idMateria"));

                insc.setAlumno(a);
                insc.setMateria(m);
                insc.setNota(rs.getDouble("nota"));

                cursadas.add(insc);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener inscripciones del alumno: " + ex.getMessage());
        }

        return cursadas;
    }

    // Obtener materias cursadas por Alumno
    public List<Materia> obtenerMateriasCursadas(int idAlumno) {
        ArrayList<Materia> materias = new ArrayList<>();
        String sql = "SELECT m.idMateria, m.nombre, m.activo "
                + "FROM inscripcion i "
                + "JOIN materia m ON i.idMateria = m.idMateria "
                + "WHERE i.idAlumno = ? AND m.activo = 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setActivo(rs.getBoolean("activo"));

                materias.add(materia);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener materias cursadas: " + ex.getMessage());
        }

        return materias;
    }

    // Obtener materias NO cursadas por Alumno
    public List<Materia> obtenerMateriasNOCursadas(int idAlumno) {
        ArrayList<Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM materia "
                + "WHERE activo = 1 AND idMateria NOT IN "
                + "(SELECT idMateria FROM inscripcion WHERE idAlumno = ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setActivo(rs.getBoolean("activo"));
                materias.add(materia);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener materias no cursadas: " + ex.getMessage());
        }

        return materias;
    }

    // Borrar o Anular Inscripción
    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria) {
        String sql = "DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Inscripción borrada exitosamente.");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar inscripción: " + ex.getMessage());
        }
    }

    // Actualizar Nota de una Inscripcion
    public void actualizarNota(int idAlumno, int idMateria, double nota) {
        String sql = "UPDATE inscripcion SET nota = ? WHERE idAlumno = ? AND idMateria = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Nota actualizada con éxito.");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la nota: " + ex.getMessage());
        }
    }

    // Obtener Alumnos inscritos en una Materia especifica
    public List<alumno> obtenerAlumnosPorMateria(int idMateria) {
        ArrayList<alumno> alumnosMateria = new ArrayList<>();
        String sql = "SELECT a.idAlumno, a.dni, a.nombre, a.apellido, "
                + "a.fechaNacimiento, a.activo "
                + "FROM inscripcion i "
                + "JOIN alumno a ON i.idAlumno = a.idAlumno "
                + "WHERE i.idMateria = ? AND a.activo = 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                alumno alu = new alumno();
                alu.setIdAlumno(rs.getInt("idAlumno"));
                alu.setDni(rs.getString("dni"));
                alu.setActivo(rs.getBoolean("activo"));
                alu.setApellido(rs.getString("apellido"));
                alu.setNombre(rs.getString("nombre"));
                alu.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());

                alumnosMateria.add(alu);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener alumnos por materia: " + ex.getMessage());
        }

        return alumnosMateria;
    }
}
