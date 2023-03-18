package alumnos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AlumnosService {
    Connection conn;
    public AlumnosService(Connection conn){
        this.conn = conn;
    }

    public ArrayList<Alumno> requestAll() throws SQLException{
        Statement statement = null;
        ArrayList<Alumno> result = new ArrayList<Alumno>();
        statement = this.conn.createStatement();   
        String sql = "SELECT id, nombre, apellido1, apellido2, dni, grupo_id FROM alumnos";
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        while(querySet.next()) {
            Long id = querySet.getLong("id");
            String nombre = querySet.getString("nombre");
            String apellido1 = querySet.getString("apellido1");
            String apellido2 = querySet.getString("apellido2");
            String dni = querySet.getString("dni");
            Long grupo_id = querySet.getLong("grupo_id");
            result.add(new Alumno(id, nombre, apellido1,apellido2,dni,grupo_id));
        } 
        statement.close();    
        return result;
    }

    public Alumno requestById(long id) throws SQLException{
        Statement statement = null;
        Alumno result = null;
        statement = this.conn.createStatement();    
        String sql = String.format("SELECT * FROM alumnos WHERE id=%d", id);
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        if(querySet.next()) {
            String nombre = querySet.getString("nombre");
            String apellido1 = querySet.getString("apellido1");
            String apellido2 = querySet.getString("apellido2");
            String dni = querySet.getString("dni");
            Long grupo_id = querySet.getLong("grupo_id");
            result = new Alumno(id, nombre, apellido1,apellido2,dni,grupo_id);
        }
        statement.close();    
        return result;
    }

    public void create(String nombre, String apellido1, String apellido2, String dni, Long grupo_id) throws SQLException{
        Statement statement = null;
        statement = this.conn.createStatement();    
        String sql = String.format("INSERT INTO alumnos (nombre, apellido1, apellido2, dni, grupo_id) VALUES ('%s', '%s', '%s', '%s', '%d')", nombre, apellido1,apellido2,dni, grupo_id);
        statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
        statement.close();
    }

    public void create(String nombre, String apellido1, String apellido2, String dni) throws SQLException{
        Statement statement = null;
        statement = this.conn.createStatement();    
        String sql = String.format("INSERT INTO alumnos (nombre, apellido1, apellido2, dni, grupo_id) VALUES ('%s', '%s', '%s', '%s', NULL)", nombre, apellido1,apellido2,dni);
        statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
        statement.close();
    }

    public void update(long id, String nombre, String apellido1, String apellido2, String dni, Long grupo_id) throws SQLException{
        Statement statement = null;
        statement = this.conn.createStatement();    
        String sql = String.format("UPDATE alumnos SET nombre = '%s', apellido1 = '%s', apellido2 = '%s', dni ='%s', grupo_id = '%d' WHERE id=%d", nombre, apellido1, apellido2, dni, grupo_id, id);
        // Ejecución de la consulta
        int affectedRows = statement.executeUpdate(sql);
        statement.close();
    }

    public void update(long id, String nombre, String apellido1, String apellido2, String dni) throws SQLException{
        Statement statement = null;
        statement = this.conn.createStatement();    
        String sql = String.format("UPDATE alumnos SET nombre = '%s', apellido1 = '%s', apellido2 = '%s', dni ='%s', grupo_id = NULL WHERE id=%d", nombre, apellido1, apellido2, dni, id);
        statement.executeUpdate(sql);
        statement.close();
    }

    public void delete(long id) throws SQLException{
        Statement statement = null;
        statement = this.conn.createStatement();    
        String sql = String.format("DELETE FROM alumnos WHERE id=%d", id);
        statement.executeUpdate(sql);
        statement.close();
    }

    public ArrayList<Alumno> requestGrupo(long id_grupo) throws SQLException{
        Statement statement = null;
        ArrayList<Alumno> result = new ArrayList<Alumno>();
        statement = this.conn.createStatement();   
        String sql = "SELECT id, nombre, apellido1, apellido2, dni FROM alumnos WHERE grupo_id="+id_grupo;
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        while(querySet.next()) {
            Long id = querySet.getLong("id");
            String nombre = querySet.getString("nombre");
            String apellido1 = querySet.getString("apellido1");
            String apellido2 = querySet.getString("apellido2");
            String dni = querySet.getString("dni");
            result.add(new Alumno(id, nombre, apellido1,apellido2,dni,id_grupo));
        } 
        statement.close();    
        return result;
    }
}