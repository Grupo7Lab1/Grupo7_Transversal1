package Persistencia;

import dominio.alumno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class alumnoData {

    public void guardarAlumno(alumno a) {

        String sql = "INSERT INTO alumno "
                + "(dni, nombre, apellido, fechaNacimiento, activo) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, a.getDni());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApellido());
            ps.setDate(4, Date.valueOf(a.getFechaNacimiento()));
            ps.setBoolean(5, a.isActivo());

            ps.executeUpdate();

            System.out.println("Alumno guardado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al guardar alumno: " + e.getMessage());
        }
    }

    public List<alumno> listarAlumnos() {

        List<alumno> alumnos = new ArrayList<>();

        String sql = "SELECT idAlumno, dni, nombre, apellido, "
                + "fechaNacimiento, activo "
                + "FROM alumno ORDER BY idAlumno";

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                alumno a = new alumno(
                    rs.getInt("idAlumno"),
                    rs.getString("dni"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getDate("fechaNacimiento").toLocalDate(),
                    rs.getBoolean("activo")
                );

                alumnos.add(a);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar alumnos: " + e.getMessage());
        }

        return alumnos;
    }
}
