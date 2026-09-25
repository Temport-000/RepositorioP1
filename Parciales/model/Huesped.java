package model;
import java.util.ArrayList;

public class Huesped {
    private String documento;
    private String nombreCompleto;
    private int edad;
    private String telefono;
    private String ciudad;
    private ArrayList<Reserva> reservas;

    public Huesped(String documento, String nombreCompleto, int edad, String telefono, String ciudad) {
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.reservas = new ArrayList<>();
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }
    public String getDocumento() {
        return documento;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public int getEdad() {
        return edad;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getCiudad() {
        return ciudad;
    }
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }
}