package grupos;

public class Grupo {
    long id;
    String nombre;
    int curso;
    String tutor;

    public Grupo(long id, String nombre,int curso,String tutor){
        this.id = id;
        this.nombre = nombre;
        this.curso=curso;
        this.tutor = tutor;
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

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    @Override
    public String toString() {
        
        return String.format("ID: %d, Nombre: %s, Curso: %d, Tutor: %s", this.id, this.nombre, this.curso ,this.tutor);
    }
    
}
