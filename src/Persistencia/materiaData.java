
package Persistencia;
import dominio.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class materiaData {
   
    public boolean guardarMateria(Materia materia) {

        String sql = "INSERT INTO materia (nombre, activo) "
                   + "VALUES (?, 1)";

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, materia.getNombre());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar materia: "
                    + e.getMessage());
            return false;
        }
    }

 
    public boolean modificarMateria(Materia materia) {

        String sql = "UPDATE materia SET nombre = ? "
                   + "WHERE idMateria = ?";

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getIdMateria());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al modificar materia: "
                    + e.getMessage());
            return false;
        }
    }

 
    public boolean eliminarMateria(int idMateria) {

        String sql = "DELETE FROM materia WHERE idMateria = ?";

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idMateria);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar materia: "
                    + e.getMessage());
            return false;
        }
    }

   
    public boolean bajaLogica(int idMateria) {

        String sql = "UPDATE materia SET activo = 0 "
                   + "WHERE idMateria = ?";

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idMateria);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error en la baja logica: "
                    + e.getMessage());
            return false;
        }
    }

   
    public Materia buscarMateria(int idMateria) {

        String sql = "SELECT * FROM materia "
                   + "WHERE idMateria = ?";

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idMateria);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Materia materia = new Materia();

                    materia.setIdMateria(
                            rs.getInt("idMateria"));

                    materia.setNombre(
                            rs.getString("nombre"));

                    materia.setActivo(
                            rs.getBoolean("activo"));

                    return materia;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar materia: "
                    + e.getMessage());
        }

        return null;
    }

   
    public ArrayList<Materia> listarMaterias() {

        ArrayList<Materia> materias = new ArrayList<>();

        String sql = "SELECT * FROM materia "
                   + "WHERE activo = 1 ORDER BY nombre";

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Materia materia = new Materia();

                materia.setIdMateria(
                        rs.getInt("idMateria"));

                materia.setNombre(
                        rs.getString("nombre"));

                materia.setActivo(
                        rs.getBoolean("activo"));

                materias.add(materia);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar materias: "
                    + e.getMessage());
        }

        return materias;
    }

   
    public ArrayList<Materia> buscarPorNombre(String nombre) {

        ArrayList<Materia> materias = new ArrayList<>();

        String sql = "SELECT * FROM materia "
                   + "WHERE nombre LIKE ? "
                   + "AND activo = 1 ORDER BY nombre";

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Materia materia = new Materia();

                    materia.setIdMateria(
                            rs.getInt("idMateria"));

                    materia.setNombre(
                            rs.getString("nombre"));

                    materia.setActivo(
                            rs.getBoolean("activo"));

                    materias.add(materia);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar por nombre: "
                    + e.getMessage());
        }

        return materias;
    }
}
