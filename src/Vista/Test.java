package Vista;

import Persistencia.conexion;
import Persistencia.materiaData;
import dominio.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) {
        materiaData datos = new materiaData();

        // Código distinto en cada ejecución, de menos de 20 caracteres.
        String codigo = "T" + System.currentTimeMillis();
        Materia prueba = new Materia(codigo, "Materia de prueba", true);

        try {
            System.out.println("=== TEST DE MATERIAS ===");

            // 1. INSERTAR
            comprobar(
                    datos.guardarMateria(prueba),
                    "Insertar materia"
            );

            int id = prueba.getIdMateria();
            comprobar(id > 0, "Recuperar el ID generado");

            // 2. BUSCAR
            Materia encontrada = datos.buscarMateria(id);

            comprobar(
                    encontrada != null
                    && codigo.equals(encontrada.getCodigo())
                    && "Materia de prueba".equals(encontrada.getNombre())
                    && encontrada.isActivo(),
                    "Buscar y verificar los datos guardados"
            );
            mostrar(encontrada);

            // 3. ACTUALIZAR
            prueba.setNombre("Materia actualizada");

            comprobar(
                    datos.modificarMateria(prueba),
                    "Actualizar materia"
            );

            encontrada = datos.buscarMateria(id);

            comprobar(
                    encontrada != null
                    && "Materia actualizada".equals(encontrada.getNombre()),
                    "Verificar el nombre actualizado en la base"
            );
            mostrar(encontrada);

            // 4. BAJA LÓGICA
            comprobar(
                    datos.bajaLogica(id),
                    "Realizar baja lógica"
            );

            encontrada = datos.buscarMateria(id);

            comprobar(
                    encontrada != null && !encontrada.isActivo(),
                    "Verificar que existe y está inactiva"
            );
            mostrar(encontrada);

            // 5. ALTA LÓGICA
            comprobar(
                    datos.altaLogica(id),
                    "Realizar alta lógica"
            );

            encontrada = datos.buscarMateria(id);

            comprobar(
                    encontrada != null && encontrada.isActivo(),
                    "Verificar que volvió a estar activa"
            );
            mostrar(encontrada);

            // 6. BORRAR DEFINITIVAMENTE
            comprobar(
                    datos.eliminarMateria(id),
                    "Borrar la materia de prueba"
            );

            // Consulta directa para distinguir ausencia de un error SQL.
            comprobar(
                    !existeEnBase(id),
                    "Verificar que ya no existe en la base"
            );

            System.out.println(
                    "\nRESULTADO: todas las pruebas finalizaron correctamente."
            );

        } catch (SQLException | IllegalStateException e) {
            System.err.println("\nTEST INTERRUMPIDO: " + e.getMessage());

            if (prueba.getIdMateria() > 0) {
                System.err.println(
                        "Revisá la materia de prueba con ID "
                        + prueba.getIdMateria()
                        + " y código " + codigo
                        + "; podría haber quedado guardada."
                );
            }

            System.err.println(
                    "Revisá también los mensajes de error anteriores."
            );
        }
    }

    private static void comprobar(boolean resultado, String descripcion) {
        if (!resultado) {
            throw new IllegalStateException(descripcion);
        }

        System.out.println("[OK] " + descripcion);
    }

    private static void mostrar(Materia materia) {
        System.out.println(
                "ID: " + materia.getIdMateria()
                + " | Código: " + materia.getCodigo()
                + " | Nombre: " + materia.getNombre()
                + " | Activa: " + materia.isActivo()
        );
    }

    private static boolean existeEnBase(int idMateria)
            throws SQLException {

        String sql = "SELECT idMateria FROM materia WHERE idMateria = ?";

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idMateria);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}
