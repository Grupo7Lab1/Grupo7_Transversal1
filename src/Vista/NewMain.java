
package Vista;

/**
 *
 * @author rotta
 */

import Persistencia.alumnoData;
import dominio.alumno;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        System.out.println(" SISTEMA SGULP - JAVA");
        alumnoData alumnoData = new alumnoData();
        // INSERT: se crea un alumno y se guarda en MariaDB.
        alumno[] alumnos = {
            new alumno("30111222", "Hernán", "López", LocalDate.of(2000, 5, 12), true),
            new alumno("32888999", "Verónica", "González", LocalDate.of(2001, 8, 20), true),
            new alumno("29419318", "Nestor", "Marchizone", LocalDate.of(2001, 8, 20), true),
            new alumno("33568985", "Leonardo", "Fabio", LocalDate.of(2001, 7, 18), true),
            new alumno("5689852", "Pepe", "Mujica", LocalDate.of(2001, 4, 28), true),
            new alumno("90456256", "Donald", "Trump", LocalDate.of(2001, 6, 20), true),
            new alumno("3256525", "Mirta", "Legrand", LocalDate.of(2001, 5, 16), true),
            new alumno("25652562", "Verónica", "Juarez", LocalDate.of(2001, 3, 1), true),
            new alumno("178956356", "Blanca", "Martinez", LocalDate.of(2001, 9, 4), true)
        };

        for (alumno a : alumnos) {
            alumnoData.guardarAlumno(a);
        }

        // SELECT: se buscan y muestran todos los alumnos.
        System.out.println("\nALUMNOS REGISTRADOS:");

        for (alumno a : alumnoData.listarAlumnos()) {
            System.out.println(a);
        }
    }
}