package modelo;

public class Estudiante {
    
    private int id;
    private String cedula;
    private String nombres;
    private String apellidos;
    private String correo;
    private String carrera;
    private int semestre;

    public Estudiante() {
    }

    public Estudiante(String cedula, String nombres, String apellidos, String correo, String carrera, int semestre) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.carrera = carrera;
        this.semestre = semestre;
    }

    public Estudiante(int id, String cedula, String nombres, String apellidos, String correo, String carrera, int semestre) {
        this.id = id;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.carrera = carrera;
        this.semestre = semestre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + "\nNombre: " + nombres + " " + apellidos
                + "\nCorreo: " + correo
                + "\nCarrera: " + carrera
                + "\nSemestre: " + semestre;
    }
}


