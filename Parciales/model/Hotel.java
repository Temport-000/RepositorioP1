package model;
import java.util.ArrayList;

public class Hotel {
    public static final String[] DIAS =
            {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;
    private ArrayList<Huesped> huespedes;
    private Habitacion[] habitaciones;
    private int totalHabitaciones;
    private Reserva[] reservas;
    private int totalReservas;
    private String[][] matrizOcupacion;   // filas = habitaciones, columnas = días ("O" o "D")

    public Hotel(String nombreComercial, String nit, String direccion, String telefono,
                 int capacidadHabitaciones, int capacidadReservas) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.huespedes = new ArrayList<>();
        this.habitaciones = new Habitacion[capacidadHabitaciones];
        this.totalHabitaciones = 0;
        this.reservas = new Reserva[capacidadReservas];
        this.totalReservas = 0;
        this.matrizOcupacion = new String[capacidadHabitaciones][DIAS.length];
        for (int i = 0; i < capacidadHabitaciones; i++) {
            for (int j = 0; j < DIAS.length; j++) {
                matrizOcupacion[i][j] = "D";
            }
        }
    }

    public void registrarHuesped(Huesped huesped) {
        huespedes.add(huesped);
    }

    public boolean registrarHabitacion(Habitacion habitacion) {
        if (totalHabitaciones == habitaciones.length) {
            return false;
        }
        habitaciones[totalHabitaciones] = habitacion;
        totalHabitaciones++;
        return true;
    }

    public boolean registrarReserva(Reserva reserva) {
        if (totalReservas == reservas.length) {
            return false;
        }
        reservas[totalReservas] = reserva;
        totalReservas++;
        reserva.getHuesped().agregarReserva(reserva);
        return true;
    }

    // 1. Consultar huésped por teléfono
    public void consultarHuespedPorTelefono(String telefono) {
        for (Huesped h : huespedes) {
            if (h.getTelefono().equals(telefono)) {
                System.out.println("Nombre: " + h.getNombreCompleto());
                System.out.println("Documento: " + h.getDocumento());
                System.out.println("Ciudad: " + h.getCiudad());
                System.out.println("Reservas realizadas: " + h.getReservas().size());
                for (Reserva r : h.getReservas()) {
                    System.out.println("  - Reserva " + r.getCodigo() + " | " + r.getFecha()
                            + " | " + r.getEstado() + " | $" + r.getValorTotal());
                }
                return;
            }
        }
        System.out.println("No existe un huésped con ese teléfono.");
    }


    public void controlarDisponibilidad() {
        if (totalHabitaciones == 0) {
            System.out.println("No hay habitaciones registradas.");
            return;
        }
        int disponibles = 0;
        int ocupadas = 0;
        int reservadas = 0;
        int mantenimiento = 0;
        Habitacion mayor = habitaciones[0];
        Habitacion menor = habitaciones[0];

        for (int i = 0; i < totalHabitaciones; i++) {
            Habitacion h = habitaciones[i];
            if (h.getEstado().equals("Disponible")) {
                disponibles++;
            } else if (h.getEstado().equals("Ocupada")) {
                ocupadas++;
            } else if (h.getEstado().equals("Reservada")) {
                reservadas++;
            } else if (h.getEstado().equals("Mantenimiento")) {
                mantenimiento++;
            }
            if (h.getPrecioPorNoche() > mayor.getPrecioPorNoche()) {
                mayor = h;
            }
            if (h.getPrecioPorNoche() < menor.getPrecioPorNoche()) {
                menor = h;
            }
        }
        System.out.println("Habitaciones disponibles: " + disponibles);
        System.out.println("Habitaciones ocupadas: " + ocupadas);
        System.out.println("Habitaciones reservadas: " + reservadas);
        System.out.println("Habitaciones en mantenimiento: " + mantenimiento);
        System.out.println("Mayor precio por noche: Habitación " + mayor.getNumero()
                + " ($" + mayor.getPrecioPorNoche() + ")");
        System.out.println("Menor precio por noche: Habitación " + menor.getNumero()
                + " ($" + menor.getPrecioPorNoche() + ")");
    }

    // 3. Matriz de ocupación: muestra la matriz y calcula día mayor, día menor y total
    public void analizarOcupacion() {
        if (totalHabitaciones == 0) {
            System.out.println("No hay habitaciones registradas.");
            return;
        }
        System.out.print("Habitación");
        for (int j = 0; j < DIAS.length; j++) {
            System.out.print(" | " + DIAS[j]);
        }
        System.out.println();

        // Cada fila: número de habitación + su estado por día
        for (int i = 0; i < totalHabitaciones; i++) {
            System.out.print("Habitación " + habitaciones[i].getNumero());
            for (int j = 0; j < DIAS.length; j++) {
                System.out.print(" | " + matrizOcupacion[i][j]);
            }
            System.out.println();
        }

        int[] ocupadasPorDia = new int[DIAS.length];
        int total = 0;
        for (int j = 0; j < DIAS.length; j++) {
            for (int i = 0; i < totalHabitaciones; i++) {
                if (matrizOcupacion[i][j].equals("O")) {
                    ocupadasPorDia[j]++;
                    total++;
                }
            }
        }
        int diaMayor = 0;
        int diaMenor = 0;
        for (int j = 1; j < DIAS.length; j++) {
            if (ocupadasPorDia[j] > ocupadasPorDia[diaMayor]) {
                diaMayor = j;
            }
            if (ocupadasPorDia[j] < ocupadasPorDia[diaMenor]) {
                diaMenor = j;
            }
        }
        System.out.println("\nDía con mayor ocupación: " + DIAS[diaMayor]
                + " (" + ocupadasPorDia[diaMayor] + " habitaciones)");
        System.out.println("Día con menor ocupación: " + DIAS[diaMenor]
                + " (" + ocupadasPorDia[diaMenor] + " habitaciones)");
        System.out.println("Total de habitaciones ocupadas en la semana: " + total);
    }

    // 4. Reservas especiales (código capicúa)
    public void listarReservasEspeciales() {
        int contador = 0;
        for (int i = 0; i < totalReservas; i++) {
            if (reservas[i].esCapicua()) {
                System.out.println("Reserva especial: " + reservas[i].getCodigo()
                        + " | " + reservas[i].getFecha());
                contador++;
            }
        }
        if (contador == 0) {
            System.out.println("No hay reservas especiales.");
        }
    }

    // 5. Ingresos del hotel en una fecha
    public double calcularIngresosPorFecha(String fecha) {
        double total = 0;
        for (int i = 0; i < totalReservas; i++) {
            if (reservas[i].getFecha().equals(fecha)) {
                total += reservas[i].getValorTotal();
            }
        }
        return total;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }
    public String getNit() {
        return nit;
    }
    public String getDireccion() {
        return direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public ArrayList<Huesped> getHuespedes() {
        return huespedes;
    }
    public Habitacion[] getHabitaciones() {
        return habitaciones;
    }
    public int getTotalHabitaciones() {
        return totalHabitaciones;
    }
    public Reserva[] getReservas() {
        return reservas;
    }
    public int getTotalReservas() {
        return totalReservas;
    }
    public String[][] getMatrizOcupacion() {
        return matrizOcupacion;
    }
}