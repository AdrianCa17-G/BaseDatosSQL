package dao;

import conexion.ConexionSQLite;
import modelo.Estudiante;

import java.sql.*;
import java.util.ArrayList;

public class EstudianteDAO {

    public void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS estudiante (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cedula TEXT NOT NULL," +
                "nombres TEXT NOT NULL," +
                "apellidos TEXT NOT NULL," +
                "correo TEXT," +
                "carrera TEXT," +
                "semestre INTEGER)";

        try (Connection conn = ConexionSQLite.conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void guardar(Estudiante e) {
        String sql = "INSERT INTO estudiante (cedula, nombres, apellidos, correo, carrera, semestre) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getCedula());
            ps.setString(2, e.getNombres());
            ps.setString(3, e.getApellidos());
            ps.setString(4, e.getCorreo());
            ps.setString(5, e.getCarrera());
            ps.setInt(6, e.getSemestre());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Estudiante> listar() {
        ArrayList<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiante";

        try (Connection conn = ConexionSQLite.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Estudiante e = new Estudiante(
                        rs.getInt("id"),
                        rs.getString("cedula"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("correo"),
                        rs.getString("carrera"),
                        rs.getInt("semestre")
                );
                lista.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public Estudiante buscarPorId(int id) {
        String sql = "SELECT * FROM estudiante WHERE id = ?";
        Estudiante e = null;

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    e = new Estudiante(
                            rs.getInt("id"),
                            rs.getString("cedula"),
                            rs.getString("nombres"),
                            rs.getString("apellidos"),
                            rs.getString("correo"),
                            rs.getString("carrera"),
                            rs.getInt("semestre")
                    );
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return e;
    }

    public void actualizar(Estudiante e) {
        String sql = "UPDATE estudiante SET cedula=?, nombres=?, apellidos=?, correo=?, carrera=?, semestre=? WHERE id=?";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getCedula());
            ps.setString(2, e.getNombres());
            ps.setString(3, e.getApellidos());
            ps.setString(4, e.getCorreo());
            ps.setString(5, e.getCarrera());
            ps.setInt(6, e.getSemestre());
            ps.setInt(7, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM estudiante WHERE id = ?";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}