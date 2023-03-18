import java.sql.*;

import alumnos.Alumno;
import alumnos.AlumnosService;
import connection.ConnectionPool;
import grupos.Grupo;
import grupos.GruposService;

import java.util.ArrayList;
import java.util.Scanner;
public class App {
    public static void listarGrupos(GruposService service){
        try {
            ArrayList<Grupo> grupos = service.requestAll();
            if(grupos.size()==0){
                System.out.println("No hay grupos de alumnos");
            }
            else{
                for(Grupo g : grupos){
                    System.out.println(g);
                }
            }
                
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public static void listarAlumnos(AlumnosService service){
        try {
            ArrayList<Alumno> alumnos = service.requestAll();
            if(alumnos.size()==0){
                System.out.println("No hay alumnos");
            }
            else{
                for(Alumno a : alumnos){
                    System.out.println(a);
                }
            }
                
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public static void listarAlumnos(AlumnosService service, long id){
        try {
            ArrayList<Alumno> alumnos = service.requestGrupo(id);
            if(alumnos.size()==0){
                System.out.println("No hay alumnos en ese grupo");
            }
            else{
                for(Alumno a : alumnos){
                    System.out.println(a);
                }
            }
                
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        // Configuración de la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/alumnos";
        String usuario = "diego";
        String clave = "12345678";

        ConnectionPool pool = new ConnectionPool(url, usuario, clave);
        GruposService gservice = new GruposService(pool.getConnection());
        AlumnosService aservice = new AlumnosService(pool.getConnection());

        String nombre, tutor; int curso;
        long id;
        String nombreAlumno, apellido1Alumno, apellido2Alumno, dniAlumno,idGrupoAlumnoStr; long idGrupoAlumno;
        boolean salir = false;

        ArrayList<Grupo> ag =gservice.requestAll();

        while(!salir){
            try {
                // Conexión a la base de datos
                System.out.println("1. Crear un grupo de alumnos");
                System.out.println("2. Editar un grupo de alumnos");
                System.out.println("3. Borrar un grupo de alumnos");
                System.out.println("4. Visualizar grupos de alumnos");
                System.out.println("5. Pasar a gestión de alumnos");
                System.out.println("6. Salir");
                int opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        System.out.println("Introduzca el nombre del grupo: ");
                        nombre = sc.nextLine();
                        System.out.println("Introduzca el curso del grupo: ");
                        curso = Integer.parseInt(sc.nextLine());
                        System.out.println("Introduzca el nombre del tutor: ");
                        tutor = sc.nextLine();
                        try {
                            gservice.create(nombre, curso, tutor);
                        } catch (SQLException e) {
                        }
                        break;
                    case 2:
                        System.out.println("Elija el grupo a editar");
                        listarGrupos(gservice);
                        id = Integer.parseInt(sc.nextLine());
                        System.out.println("Introduzca el nombre del grupo: ");
                        nombre = sc.nextLine();
                        System.out.println("Introduzca el curso del grupo: ");
                        curso = Integer.parseInt(sc.nextLine());
                        System.out.println("Introduzca el nombre del tutor: ");
                        tutor = sc.nextLine();
                        try {
                            gservice.update(id, nombre, curso, tutor);
                        } catch (SQLException e) {
                            // TODO: handle exception
                        }
                        break;
                    case 3:
                        System.out.println("Elija el grupo a borrar");
                        listarGrupos(gservice);
                        id = Integer.parseInt(sc.nextLine());
                        try {
                            gservice.delete(id);
                        } catch (SQLException e) {
                            // TODO: handle exception
                        }
                        break;
                    case 4:
                        boolean salirListados = false;
                        while(!salirListados){
                            try {
                                System.out.println("Grupos:");
                                listarGrupos(gservice);
                                System.out.println("1. Ver un grupo");
                                System.out.println("2. Volver al menú principal");
                                int opcionListados = Integer.parseInt(sc.nextLine());
                                switch (opcionListados) {
                                case 1:
                                    System.out.println("Introduzca el id del grupo: ");
                                    id=Long.parseLong(sc.nextLine());
                                    listarAlumnos(aservice,id);
                                    System.out.println("-------------------------------------\n\n");
                                    break;
                                case 2:
                                    salirListados=true;
                                    break;
                                default: break;
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                        break;
                    
                    case 5:
                    boolean salirAlumnos = false;
                    while(!salirAlumnos){
                        try {
                            // Opciones de gestión de alumnos
                            System.out.println("1. Crear un alumno");
                            System.out.println("2. Editar un alumno");
                            System.out.println("3. Borrar un alumno");
                            System.out.println("4. Visualizar alumnos");
                            System.out.println("5. Volver al menú principal");
                            int opcionAlumnos = Integer.parseInt(sc.nextLine());
                            switch (opcionAlumnos) {
                                case 1:
                                    System.out.println("Introduzca el nombre del alumno: ");
                                    nombreAlumno = sc.nextLine();
                                    System.out.println("Introduzca el primer apellido del alumno: ");
                                    apellido1Alumno = sc.nextLine();
                                    System.out.println("Introduzca el segundo apellido del alumno: ");
                                    apellido2Alumno = sc.nextLine();
                                    System.out.println("Introduzca el DNI del alumno: ");
                                    dniAlumno = sc.nextLine();
                                    System.out.println("Introduzca el id del grupo al que pertenece el alumno (o deja en blanco si no pertenece a ninguno): ");
                                    listarGrupos(gservice);
                                    ag =gservice.requestAll();
                                    idGrupoAlumnoStr = sc.nextLine();
                                    if (!idGrupoAlumnoStr.isEmpty()) {
                                        idGrupoAlumno = Integer.parseInt(idGrupoAlumnoStr);
                                    }
                                    try {
                                        if (!idGrupoAlumnoStr.isEmpty()) {
                                            idGrupoAlumno = Integer.parseInt(idGrupoAlumnoStr);
                                            aservice.create(nombreAlumno, apellido1Alumno, apellido2Alumno, dniAlumno, idGrupoAlumno);
                                        }else{
                                            aservice.create(nombreAlumno, apellido1Alumno, apellido2Alumno, dniAlumno);
                                        }
                                    } catch (SQLException e) {
  
                                    }
                                    break;
                                case 2:
                                    System.out.println("Elija el alumno a editar:");
                                    listarAlumnos(aservice);
                                    id = Integer.parseInt(sc.nextLine());
                                    System.out.println("Introduzca el nombre del alumno: ");
                                    nombreAlumno = sc.nextLine();
                                    System.out.println("Introduzca el primer apellido del alumno: ");
                                    apellido1Alumno = sc.nextLine();
                                    System.out.println("Introduzca el segundo apellido del alumno: ");
                                    apellido2Alumno = sc.nextLine();
                                    System.out.println("Introduzca el DNI del alumno: ");
                                    dniAlumno = sc.nextLine();
                                    System.out.println("Introduzca el id del grupo al que pertenece el alumno (o deja en blanco si no pertenece a ninguno): ");
                                    listarGrupos(gservice);
                                    ag =gservice.requestAll();
                                    idGrupoAlumnoStr = sc.nextLine();
                                    if (!idGrupoAlumnoStr.isEmpty()) {
                                        idGrupoAlumno = Integer.parseInt(idGrupoAlumnoStr);
                                    }
                                    try {
                                        if (!idGrupoAlumnoStr.isEmpty()) {
                                            idGrupoAlumno = Integer.parseInt(idGrupoAlumnoStr);
                                            aservice.update(id, nombreAlumno, apellido1Alumno, apellido2Alumno, dniAlumno, idGrupoAlumno);
                                        }else{
                                            aservice.update(id, nombreAlumno, apellido1Alumno, apellido2Alumno, dniAlumno);
                                        }

                                    } catch (SQLException e) {
                                        // TODO: handle exception
                                    }
                                    break;
                                case 3:
                                    System.out.println("Elija el alumno a borrar:");
                                    listarAlumnos(aservice);
                                    long idAlumnoBorrar = Integer.parseInt(sc.nextLine());
                                    try {
                                        aservice.delete(idAlumnoBorrar);
                                    } catch (SQLException e) {
                                        // TODO: handle exception
                                    }
                                    break;
                                case 4:
                                    listarAlumnos(aservice);
                                    break;
                                case 5:
                                    salirAlumnos = true;
                                    break;
                                default:
                                    break;
                                }
                                
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                        break;
                    
                    case 6:
                        salir = true;
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
         
    }
}



