package app;

import java.util.Scanner;
import model.Hotel;
import model.Habitacion;
import model.Huesped;
import model.Reserva;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel("StayPlus", "900.123.4567", "Calle 10 # 5-20", "6067412345", 20, 100);
        int opcion;

        do {
            System.out.println("\n===== HOTEL " + hotel.getNombreComercial() + " =====");
            System.out.println("1. Registrar huésped");
            System.out.println("2. Registrar habitación");
            System.out.println("3. Crear reserva");
            System.out.println("4. Consultar huésped por teléfono");
            System.out.println("5. Control de disponibilidad de habitaciones");
            System.out.println("6. Marcar ocupación en la matriz");
            System.out.println("7. Ver matriz y análisis de ocupación");
            System.out.println("8. Reservas especiales (capicúa)");
            System.out.println("9. Ingresos por fecha");
            System.out.println("10. Cargar datos de prueba");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(sc.nextLine());
            System.out.println();


            switch (opcion) {
                case 1: {
                    System.out.print("Documento: ");
                    String documento = sc.nextLine();

                    System.out.print("Nombre completo: ");
                    String nombre = sc.nextLine();

                    System.out.print("Edad: ");
                    int edad = Integer.parseInt(sc.nextLine());

                    System.out.print("Teléfono: ");
                    String telefono = sc.nextLine();

                    System.out.print("Ciudad de procedencia: ");
                    String ciudad = sc.nextLine();

                    hotel.registrarHuesped(new Huesped(documento, nombre, edad, telefono, ciudad));
                    System.out.println("Huésped registrado.");
                    break;
                }
                case 2: {
                    System.out.print("Número de habitación: ");
                    int numero = Integer.parseInt(sc.nextLine());

                    System.out.print("Tipo (Individual, Doble o Suite): ");
                    String tipo = sc.nextLine();

                    System.out.print("Piso: ");
                    int piso = Integer.parseInt(sc.nextLine());

                    System.out.print("Capacidad máxima: ");
                    int capacidad = Integer.parseInt(sc.nextLine());

                    System.out.print("Precio por noche: ");
                    double precio = Double.parseDouble(sc.nextLine());

                    System.out.print("Estado (Disponible, Reservada, Ocupada o Mantenimiento): ");
                    String estado = sc.nextLine();

                    if (hotel.registrarHabitacion(new Habitacion(numero, tipo, piso, capacidad, precio, estado))) {
                        System.out.println("Habitación registrada.");

                    } else {
                        System.out.println("No hay espacio para más habitaciones.");
                    }
                    break;
                }
                case 3: {
                    System.out.print("Documento del huésped: ");
                    String documento = sc.nextLine();
                    Huesped huesped = null;
                    for (Huesped huespedMomento : hotel.getHuespedes()) {
                        if (huespedMomento.getDocumento().equals(documento)) {
                            huesped = huespedMomento;
                        }
                    }
                    if (huesped == null) {
                        System.out.println("El huésped no está registrado.");
                        break;
                    }
                    System.out.print("Código de reserva: ");
                    int codigo = Integer.parseInt(sc.nextLine());
                    System.out.print("Fecha (dd/mm/aaaa): ");
                    String fecha = sc.nextLine();
                    System.out.print("Número de noches: ");
                    int noches = Integer.parseInt(sc.nextLine());
                    System.out.print("Cantidad de huéspedes: ");
                    int cantidad = Integer.parseInt(sc.nextLine());
                    System.out.print("Estado (Pendiente, Confirmada o Finalizada): ");
                    String estado = sc.nextLine();
                    System.out.print("Método de pago (Efectivo, Tarjeta o Transferencia bancaria): ");
                    String metodo = sc.nextLine();

                    Reserva reserva = new Reserva(codigo, fecha, noches, cantidad, estado, metodo, huesped);
                    int numeroHab;
                    do {
                        System.out.print("Número de habitación a agregar (0 para terminar): ");
                        numeroHab = Integer.parseInt(sc.nextLine());
                        if (numeroHab != 0) {
                            Habitacion habitacion = null;
                            for (int i = 0; i < hotel.getTotalHabitaciones(); i++) {
                                if (hotel.getHabitaciones()[i].getNumero() == numeroHab) {
                                    habitacion = hotel.getHabitaciones()[i];
                                }
                            }
                            if (habitacion == null) {
                                System.out.println("La habitación no existe.");
                            } else if (reserva.agregarHabitacion(habitacion)) {
                                System.out.println("Habitación agregada.");
                            } else {
                                System.out.println("La habitación no está disponible.");
                            }
                        }
                    } while (numeroHab != 0);

                    if (hotel.registrarReserva(reserva)) {
                        System.out.println("Reserva registrada. Valor total: $" + reserva.getValorTotal());
                    } else {
                        System.out.println("No hay espacio para más reservas.");
                    }
                    break;
                }
                case 4: {
                    System.out.print("Teléfono del huésped: ");
                    hotel.consultarHuespedPorTelefono(sc.nextLine());
                    break;
                }
                case 5:
                    hotel.controlarDisponibilidad();
                    break;
                case 6: {
                    System.out.print("Número de habitación: ");
                    int numero = Integer.parseInt(sc.nextLine());
                    int fila = -1;
                    for (int i = 0; i < hotel.getTotalHabitaciones(); i++) {
                        if (hotel.getHabitaciones()[i].getNumero() == numero) {
                            fila = i;
                        }
                    }
                    if (fila == -1) {
                        System.out.println("La habitación no existe.");
                    } else {
                        for (int i = 0; i < Hotel.DIAS.length; i++) {
                            System.out.println((i + 1) + ". " + Hotel.DIAS[i]);
                        }
                        System.out.print("escoja un día (1-7): ");
                        int dia = Integer.parseInt(sc.nextLine());
                        System.out.print("Estado (O = Ocupada, D = Disponible): ");
                        String estado = sc.nextLine().toUpperCase();
                        if (dia >= 1 && dia <= 7 && (estado.equals("O") || estado.equals("D"))) {
                            hotel.getMatrizOcupacion()[fila][dia - 1] = estado;
                            System.out.println("Matriz actualizada.");
                        } else {
                            System.out.println("Datos no válidos.");
                        }
                    }
                    break;
                }
                case 7:
                    hotel.analizarOcupacion();
                    break;
                case 8:
                    hotel.listarReservasEspeciales();
                    break;
                case 9: {
                    System.out.print("Fecha a consultar (dd/mm/aaaa): ");
                    String fecha = sc.nextLine();
                    System.out.println("Ingreso total del " + fecha + ": $" + hotel.calcularIngresosPorFecha(fecha));
                    break;
                }
                case 10: {
                    Huesped ana = new Huesped("1094001", "Ana Gómez", 28, "3001112233", "Armenia");
                    Huesped carlos = new Huesped("1094002", "Carlos Ruiz", 35, "3004445566", "Pereira");
                    Huesped laura = new Huesped("1094003", "Laura Pérez", 41, "3007778899", "Bogotá");
                    hotel.registrarHuesped(ana);
                    hotel.registrarHuesped(carlos);
                    hotel.registrarHuesped(laura);

                    Habitacion h101 = new Habitacion(101, "Individual", 1, 1, 80000, "Disponible");
                    Habitacion h102 = new Habitacion(102, "Doble", 1, 2, 120000, "Disponible");
                    Habitacion h201 = new Habitacion(201, "Doble", 2, 2, 130000, "Disponible");
                    Habitacion h202 = new Habitacion(202, "Suite", 2, 4, 250000, "Disponible");
                    Habitacion h301 = new Habitacion(301, "Suite", 3, 5, 300000, "Mantenimiento");
                    hotel.registrarHabitacion(h101);
                    hotel.registrarHabitacion(h102);
                    hotel.registrarHabitacion(h201);
                    hotel.registrarHabitacion(h202);
                    hotel.registrarHabitacion(h301);

                    Reserva r1 = new Reserva(1221, "22/09/2026", 2, 2, "Confirmada", "Tarjeta", ana);
                    r1.agregarHabitacion(h102);
                    Reserva r2 = new Reserva(4567, "22/09/2026", 3, 1, "Confirmada", "Efectivo", carlos);
                    r2.agregarHabitacion(h101);
                    Reserva r3 = new Reserva(3003, "23/09/2026", 1, 4, "Pendiente", "Transferencia bancaria", laura);
                    r3.agregarHabitacion(h202);
                    hotel.registrarReserva(r1);
                    hotel.registrarReserva(r2);
                    hotel.registrarReserva(r3);

                    hotel.getMatrizOcupacion()[0][0] = "O";   // 101 lunes
                    hotel.getMatrizOcupacion()[0][1] = "O";   // 101 martes
                    hotel.getMatrizOcupacion()[1][1] = "O";   // 102 martes
                    hotel.getMatrizOcupacion()[1][2] = "O";   // 102 miércoles
                    hotel.getMatrizOcupacion()[3][1] = "O";   // 202 martes
                    hotel.getMatrizOcupacion()[2][4] = "O";   // 201 viernes
                    System.out.println("Datos de prueba cargados.");
                    System.out.println("Teléfonos: 3001112233, 3004445566, 3007778899. Fechas: 22/09/2026 y 23/09/2026");
                    break;
                }
                case 0:
                    System.out.println("Hasta pronto.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
        sc.close();
    }
}