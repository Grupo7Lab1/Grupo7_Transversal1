package dominio;

public class Materia {

    private int idMateria;
    private String codigo;
    private String nombre;
    private boolean activo;

    // Constructor vacío, utilizado al recuperar datos de la BD.
    public Materia() {
        this.activo = true;
    }

    // Constructor para crear una materia sin ID.
    public Materia(String codigo, String nombre, boolean activo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.activo = activo;
    }

    // Constructor para una materia que ya tiene ID.
    public Materia(int idMateria, String codigo,
            String nombre, boolean activo) {
        this(codigo, nombre, activo);
        this.idMateria = idMateria;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }

}
