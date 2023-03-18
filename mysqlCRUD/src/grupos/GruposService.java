package grupos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GruposService {
    Connection conn;
    public GruposService(Connection conn){
        this.conn = conn;
    }

    public ArrayList<Grupo> requestAll() throws SQLException{
        Statement statement = null;
        ArrayList<Grupo> result = new ArrayList<Grupo>();
        statement = this.conn.createStatement();   
        String sql = "SELECT id, nombre, curso, tutor FROM Grupos";
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        while(querySet.next()) {
            int id = querySet.getInt("id");
            String nombre = querySet.getString("nombre");
            int curso = Integer.parseInt(querySet.getString("curso"));
            String tutor = querySet.getString("tutor");
            result.add(new Grupo(id, nombre, curso,tutor));
        } 
        statement.close();    
        return result;
    }

    public Grupo requestById(int id) throws SQLException{
        Statement statement = null;
        Grupo result = null;
        statement = this.conn.createStatement();    
        String sql = String.format("SELECT id, nombre, curso,tutor FROM Grupos WHERE id=%d", id);
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        if(querySet.next()) {
            String nombre = querySet.getString("nombre");
            int curso=  Integer.parseInt(querySet.getString("curso"));
            String tutor = querySet.getString("tutor");
            result = new Grupo(id,nombre,curso,tutor);
        }
        statement.close();    
        return result;
    }

    public void create(String nombre, int curso, String tutor) throws SQLException{
        Statement statement = null;
        statement = this.conn.createStatement();    
        String sql = String.format("INSERT INTO Grupos (nombre, curso,tutor) VALUES ('%s', '%d','%s')", nombre,curso,tutor);
        statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
        statement.close();
    }

    public void update(long id, String nombre,int curso,String tutor) throws SQLException{
        Statement statement = null;
        statement = this.conn.createStatement();    
        String sql = String.format("UPDATE Grupos SET nombre = '%s', curso='%d', tutor = '%s' WHERE id=%d", nombre, curso, tutor, id);
        statement.executeUpdate(sql);
        statement.close();
    }

    public void delete(long id) throws SQLException{
        Statement statement = null;
        statement = this.conn.createStatement();    
        String sql = String.format("DELETE FROM Grupos WHERE id=%d", id);
        statement.executeUpdate(sql);
        statement.close();
    }
}
