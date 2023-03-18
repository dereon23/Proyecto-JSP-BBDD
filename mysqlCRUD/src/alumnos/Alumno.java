package alumnos;
public class Alumno {
    long id;
    String nombre;
    String apellido1;
    String apellido2;
    String dni;
    long grupo_id;
 
    public Alumno(){
        this(0,"","","","",0);
    }

    public Alumno(long id, String nombre, String apellido1, String apellido2, String dni, long grupo_id){
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2=apellido2;
        this.dni=dni;
        this.grupo_id=grupo_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public long getGrupo() {
        return grupo_id;
    }

    public void setGrupo(long grupo_id) {
        this.grupo_id = grupo_id;
    }

    @Override
    public String toString() {
        if(grupo_id==0)
        return String.format("ID: %d, Nombre: %s, Apellido1: %s, Apellido2: %s, DNI: %s, Grupo: Ninguno", 
        this.id, this.nombre, this.apellido1, this.apellido2, this.dni);
        else
        return String.format("ID: %d, Nombre: %s, Apellido1: %s, Apellido2: %s, DNI: %s, Grupo: %s", 
        this.id, this.nombre, this.apellido1, this.apellido2, this.dni, this.grupo_id);
    }
}
