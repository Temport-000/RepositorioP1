package model;

public class Tecnico {
    private String name;
    private String id;
    private int code;
    private EstadoLaboral estado;
    private Especialidad especialidad;

    public Tecnico(String name, String id, int code, EstadoLaboral estado, Especialidad especialidad) {
        this.name = name;
        this.id = id;
        this.code = code;
        this.estado = estado;
        this.especialidad = especialidad;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }
    public EstadoLaboral getEstado() { return estado; }
    public void setEstado(EstadoLaboral estado) { this.estado = estado; }
    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }


    public boolean estaAutorizadoPara(Equipo equipo) {
        if (especialidad == Especialidad.SOPORTE_GENERAL) {
            return true;
        }
        if (especialidad == Especialidad.COMPUTADORES) {
            return equipo instanceof ComputadorPortatil || equipo instanceof ComputadorEscritorio;
        }
        if (especialidad == Especialidad.DISPOSITIVOS_MOVILES) {
            return equipo instanceof Telefono;
        }
        return false;
    }

    @Override
    public String toString() {
        return name + " (" + id + ", " + especialidad + ", " + estado + ")";
    }
}