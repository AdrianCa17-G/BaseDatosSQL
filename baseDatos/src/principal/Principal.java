package principal;

import dao.EstudianteDAO;
import modelo.Estudiante;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        EstudianteDAO dao = new EstudianteDAO();
        dao.crearTabla();

        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n========= SISTEMA =========");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Buscar estudiante");
            System.out.println("4. Actualizar estudiante");
            System.out.println("5. Eliminar estudiante");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> registrar(sc, dao);
                case 2 -> listar(dao);
                case 3 -> buscar(sc, dao);
                case 4 -> actualizar(sc, dao);
                case 5 -> eliminar(sc, dao);
                case 6 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida");
            }

        } while (opcion != 6);
    }

    private static void registrar(Scanner sc, EstudianteDAO dao) {
        System.out.print("Cédula: ");
        String cedula = sc.nextLine();
        System.out.print("Nombres: ");
        String nombres = sc.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Carrera: ");
        String carrera = sc.nextLine();
        System.out.print("Semestre: ");
        int semestre = Integer.parseInt(sc.nextLine());

        Estudiante e = new Estudiante(cedula, nombres, apellidos, correo, carrera, semestre);
        dao.guardar(e);
        System.out.println("Estudiante registrado correctamente.");
    }

    private static void listar(EstudianteDAO dao) {
        ArrayList<Estudiante> lista = dao.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            for (Estudiante e : lista) {
                System.out.println("--------------------");
                System.out.println(e);
            }
        }
    }

    private static void buscar(Scanner sc, EstudianteDAO dao) {
        System.out.print("Ingrese el ID a buscar: ");
        int id = Integer.parseInt(sc.nextLine());
        Estudiante e = dao.buscarPorId(id);
        if (e != null) {
            System.out.println(e);
        } else {
            System.out.println("No se encontró un estudiante con ese ID.");
        }
    }

    private static void actualizar(Scanner sc, EstudianteDAO dao) {
        System.out.print("Ingrese el ID a actualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        Estudiante e = dao.buscarPorId(id);

        if (e == null) {
            System.out.println("No existe un estudiante con ese ID.");
            return;
        }

        System.out.print("Nuevos nombres: ");
        e.setNombres(sc.nextLine());
        System.out.print("Nuevos apellidos: ");
        e.setApellidos(sc.nextLine());
        System.out.print("Nuevo correo: ");
        e.setCorreo(sc.nextLine());
        System.out.print("Nueva carrera: ");
        e.setCarrera(sc.nextLine());
        System.out.print("Nuevo semestre: ");
        e.setSemestre(Integer.parseInt(sc.nextLine()));

        dao.actualizar(e);
        System.out.println("Estudiante actualizado correctamente.");
    }

    private static void eliminar(Scanner sc, EstudianteDAO dao) {
        System.out.print("Ingrese el ID a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());
        dao.eliminar(id);
        System.out.println("Estudiante eliminado correctamente.");
    }
}