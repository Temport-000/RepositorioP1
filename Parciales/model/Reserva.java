package model;
import java.util.ArrayList;

public class Reserva {
    private int codigo;
    private String fecha;
    private int numeroNoches;
    private int cantidadHuespedes;
    private String estado;
    private String metodoPago;
    private double valorTotal;
    private Huesped huesped;
    private ArrayList<Habitacion> habitaciones;

    public Reserva(int codigo, String fecha, int numeroNoches, int cantidadHuespedes,
                   String estado, String metodoPago, Huesped huesped) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.numeroNoches = numeroNoches;
        this.cantidadHuespedes = cantidadHuespedes;
        this.estado = estado;
        this.metodoPago = metodoPago;
        this.huesped = huesped;
        this.habitaciones = new ArrayList<>();
        this.valorTotal = 0;
    }

    public boolean agregarHabitacion(Habitacion habitacion) {
        if (!habitacion.getEstado().equals("Disponible")) {
            return false;
        }
        habitaciones.add(habitacion);
        if (estado.equals("Confirmada")) {
            habitacion.setEstado("Reservada");
        }
        calcularValorTotal();
        return true;
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Habitacion h : habitaciones) {
            total += h.getPrecioPorNoche() * numeroNoches;
        }
        valorTotal = total;
        return valorTotal;
    }

    public boolean esCapicua() {
        int n = codigo;
        int invertido = 0;
        while (n > 0) {
            invertido = invertido * 10 + n % 10;
            n = n / 10;
        }
        return invertido == codigo;
    }


    public int getCodigo() {
        return codigo;
    }
    public String getFecha() {
        return fecha;
    }
    public int getNumeroNoches() {
        return numeroNoches;
    }
    public int getCantidadHuespedes() {
        return cantidadHuespedes;
    }
    public String getEstado() {
        return estado;
    }
    public String getMetodoPago() {
        return metodoPago;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public Huesped getHuesped() {
        return huesped;
    }
    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }
}