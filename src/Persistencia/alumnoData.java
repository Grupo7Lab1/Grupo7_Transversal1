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
// GUARDAR ALUMNO

    public void guardarAlumno(alumno a) {

        String sql = "INSERT INTO alumno "
                + "(dni, nombre, apellido, fechaNacimiento, activo) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, a.getDni());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApellido());
            ps.setDate(4, Date.valueOf(a.getFechaNacimiento()));
            ps.setBoolean(5, a.isActivo());

            ps.executeUpdate();

            System.out.println("Alumno guardado correctamente.");

        } catch (SQLException e) {

            System.out.println(
                    "Error al guardar alumno: "
                    + e.getMessage()
            );
        }
    }

    // LISTAR ALUMNOS
    public List<alumno> listarAlumnos() {

        List<alumno> alumnos = new ArrayList<>();

        String sql = "SELECT idAlumno, dni, nombre, apellido, "
                + "fechaNacimiento, activo "
                + "FROM alumno "
                + "ORDER BY idAlumno";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

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

            System.out.println(
                    "Error al listar alumnos: "
                    + e.getMessage()
            );
        }

        return alumnos;
    }
    // BUSCAR ALUMNO POR DNI
    public alumno buscarPorDni(String dni) {

        String sql = "SELECT idAlumno, dni, nombre, apellido, "
                + "fechaNacimiento, activo "
                + "FROM alumno "
                + "WHERE dni = ?";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new alumno(
                            rs.getInt("idAlumno"),
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getDate("fechaNacimiento").toLocalDate(),
                            rs.getBoolean("activo")
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al buscar alumno por DNI: "
                    + e.getMessage()
            );
        }

        return null;
    }
    // BUSCAR ALUMNO POR ID
    public alumno buscarPorId(int id) {

        String sql = "SELECT idAlumno, dni, nombre, apellido, "
                + "fechaNacimiento, activo "
                + "FROM alumno "
                + "WHERE idAlumno = ?";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new alumno(
                            rs.getInt("idAlumno"),
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getDate("fechaNacimiento").toLocalDate(),
                            rs.getBoolean("activo")
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al buscar alumno por ID: "
                    + e.getMessage()
            );
        }

        return null;
    }

    // MODIFICAR ALUMNO
    public void modificar(alumno a) {

        String sql = "UPDATE alumno SET "
                + "dni = ?, "
                + "nombre = ?, "
                + "apellido = ?, "
                + "fechaNacimiento = ?, "
                + "activo = ? "
                + "WHERE idAlumno = ?";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, a.getDni());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApellido());
            ps.setDate(4, Date.valueOf(a.getFechaNacimiento()));
            ps.setBoolean(5, a.isActivo());
            ps.setInt(6, a.getIdAlumno());

            ps.executeUpdate();

            System.out.println(
                    "Alumno modificado correctamente."
            );

        } catch (SQLException e) {

            System.out.println(
                    "Error al modificar alumno: "
                    + e.getMessage()
            );
        }
    }
    // DAR DE BAJA
    public void darDeBaja(int idAlumno) {

        String sql = "UPDATE alumno "
                + "SET activo = false "
                + "WHERE idAlumno = ?";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAlumno);

            ps.executeUpdate();

            System.out.println(
                    "Alumno dado de baja correctamente."
            );

        } catch (SQLException e) {

            System.out.println(
                    "Error al dar de baja alumno: "
                    + e.getMessage()
            );
        }
    }
}
