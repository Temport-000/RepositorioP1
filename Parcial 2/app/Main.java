import model.*;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static Empresa empresa = new Empresa("TechRepair");
    private static int consecutivoServicio = 1;

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Opción: ");
            ejecutar(opcion);
        } while (opcion != 0);
        System.out.println("Hasta pronto.");
    }

    private static void mostrarMenu() {
        System.out.println("TechRepair");
        System.out.println(" 1. Registrar cliente");
        System.out.println(" 2. Registrar equipo");
        System.out.println(" 3. Registrar técnico");
        System.out.println(" 4. Registrar repuesto");
        System.out.println(" 5. Crear orden de servicio");
        System.out.println(" 6. Agregar detalle a una orden");
        System.out.println(" 7. Asignar técnico");
        System.out.println(" 8. Registrar repuestos utilizados");
        System.out.println(" 9. Calcular valor de un detalle");
        System.out.println("10. Calcular valor total de la orden");
        System.out.println("11. Actualizar estado de un detalle");
        System.out.println("12. Finalizar una orden");
        System.out.println("13. Consultar órdenes de un cliente");
        System.out.println("14. Consultar trabajos de un técnico");
        System.out.println("15. Consultar equipos pendientes");
        System.out.println("16. Generar reporte de servicios");
        System.out.println("17. Generar reporte de técnicos");
        System.out.println("18. Consultar inventario crítico");
        System.out.println(" 0. Salir");
    }

    private static void ejecutar(int opcion) {
        switch (opcion) {
            case 1:
                registrarCliente();
                break;
            case 2:
                registrarEquipo();
                break;
            case 3:
                registrarTecnico();
                break;
            case 4:
                registrarRepuesto();
                break;
            case 5:
                crearOrden();
                break;
            case 6:
                agregarDetalle();
                break;
            case 7:
                asignarTecnico();
                break;
            case 8:
                registrarRepuestoUtilizado();
                break;
            case 9:
                calcularValorDetalle();
                break;
            case 10:
                calcularTotalOrden();
                break;
            case 11:
                actualizarEstado();
                break;
            case 12:
                String idOrden = leerTexto("Código de la orden: ");
                System.out.println(empresa.finalizarOrden(idOrden));
                break;
            case 13:
                String idCliente = leerTexto("Documento del cliente: ");
                System.out.println(empresa.consultarOrdenesCliente(idCliente));
                break;
            case 14:
                String idTecnico = leerTexto("Documento del técnico: ");
                System.out.println(empresa.consultarTrabajosTecnico(idTecnico));
                break;
            case 15:
                System.out.println(empresa.consultarEquiposPendientes());
                break;
            case 16:
                System.out.println(empresa.generarReporteServicios());
                break;
            case 17:
                System.out.println(empresa.generarReporteTecnicos());
                break;
            case 18:
                System.out.println(empresa.consultarInventarioCritico());
                break;
            case 0:
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    private static void registrarCliente() {
        String id = leerTexto("Documento: ");
        String nombre = leerTexto("Nombre completo: ");
        String tel = leerTexto("Teléfono: ");
        String correo = leerTexto("Correo: ");
        String direccion = leerTexto("Dirección: ");
        Cliente cliente = new Cliente(id, nombre, tel, correo, direccion);
        System.out.println(empresa.registrarCliente(cliente));
    }

    private static void registrarEquipo() {
        String idCliente = leerTexto("Documento del cliente: ");
        System.out.println("Categoría: 1. Portátil  2. Escritorio  3. Teléfono");
        int tipo = leerEntero("Opción: ");
        String id = leerTexto("Código del equipo: ");
        String serie = leerTexto("Número de serie: ");
        String marca = leerTexto("Marca: ");
        String modelo = leerTexto("Modelo: ");
        int anio = leerEntero("Año de fabricación: ");
        String desc = leerTexto("Estado físico al ingresar: ");
        int almacenamiento = leerEntero("Almacenamiento (GB): ");
        LocalDate yom = LocalDate.of(anio, 1, 1);
        Equipo equipo;
        if (tipo == 1) {
            float pantalla = leerEntero("Pantalla (pulgadas): ");
            int ram = leerEntero("RAM (GB): ");
            boolean extraible = leerBoolean("¿Batería extraíble? (s/n): ");
            equipo = new ComputadorPortatil(id, serie, almacenamiento, marca, modelo, desc, yom, pantalla, ram, extraible);
        } else if (tipo == 2) {
            String cpu = leerTexto("Procesador: ");
            int ram = leerEntero("RAM (GB): ");
            boolean gpu = leerBoolean("¿Tarjeta gráfica dedicada? (s/n): ");
            equipo = new ComputadorEscritorio(id, serie, almacenamiento, marca, modelo, desc, yom, cpu, ram, gpu);
        } else if (tipo == 3) {
            String so = leerTexto("Sistema operativo: ");
            int bateria = leerEntero("Batería (mAh): ");
            boolean g5 = leerBoolean("¿Compatible con 5G? (s/n): ");
            equipo = new Telefono(id, serie, almacenamiento, marca, modelo, desc, yom, so, bateria, g5);
        } else {
            System.out.println("Categoría no válida");
            return;
        }
        System.out.println(empresa.registrarEquipo(equipo, idCliente));
    }

    private static void registrarTecnico() {
        String nombre = leerTexto("Nombre: ");
        String id = leerTexto("Documento: ");
        int codigo = leerEntero("Código: ");
        EstadoLaboral estado = EstadoLaboral.INACTIVO;
        if (leerBoolean("¿Está activo? (s/n): ")) {
            estado = EstadoLaboral.ACTIVO;
        }
        System.out.println("Especialidad: 1. Computadores  2. Dispositivos móviles  3. Soporte general");
        int op = leerEntero("Opción: ");
        Especialidad especialidad = Especialidad.SOPORTE_GENERAL;
        if (op == 1) {
            especialidad = Especialidad.COMPUTADORES;
        } else if (op == 2) {
            especialidad = Especialidad.DISPOSITIVOS_MOVILES;
        }
        Tecnico tecnico = new Tecnico(nombre, id, codigo, estado, especialidad);
        System.out.println(empresa.registrarTecnico(tecnico));
    }

    private static void registrarRepuesto() {
        String codigo = leerTexto("Código: ");
        String nombre = leerTexto("Nombre: ");
        String desc = leerTexto("Descripción: ");
        int precio = leerEntero("Precio unitario: ");
        int stock = leerEntero("Existencias iniciales: ");
        String categoria = leerTexto("Categoría de compatibilidad: ");
        Repuesto repuesto = new Repuesto(codigo, precio, stock, nombre, desc, categoria);
        System.out.println(empresa.agregarRepuesto(repuesto));
    }

    private static void crearOrden() {
        String idCliente = leerTexto("Documento del cliente: ");
        String idOrden = leerTexto("Código de la orden: ");
        String obs = leerTexto("Observaciones generales: ");
        Orden orden = new Orden(idOrden, LocalDate.now(), obs, EstadoOrden.ABIERTA);
        System.out.println(empresa.agregarOrden(orden, idCliente));
    }

    private static void agregarDetalle() {
        String idOrden = leerTexto("Código de la orden: ");
        String idDetalle = leerTexto("Código del detalle: ");
        String codEquipo = leerTexto("Código del equipo: ");
        if (!empresa.buscarEquipo(codEquipo).isPresent()) {
            System.out.println("Equipo no encontrado");
            return;
        }
        Equipo equipo = empresa.buscarEquipo(codEquipo).get();
        System.out.println("Servicio: 1. Diagnóstico  2. Mantenimiento preventivo  3. Reparación correctiva");
        int tipo = leerEntero("Opción: ");
        String falla = leerTexto("Falla o necesidad reportada: ");
        Servicio servicio;
        int horas = 0;
        if (tipo == 1) {
            servicio = new Diagnostico(consecutivoServicio, "Diagnóstico", "Identificación de fallas");
        } else if (tipo == 2) {
            servicio = new MantenimientoPreventivo(consecutivoServicio, "Mantenimiento preventivo",
                    "Limpieza, revisión y optimización");
        } else if (tipo == 3) {
            servicio = new ReparacionCorrectiva(consecutivoServicio, "Reparación correctiva",
                    "Solución de fallas identificadas");
            horas = leerEntero("Horas de trabajo: ");
        } else {
            System.out.println("Servicio no válido");
            return;
        }
        consecutivoServicio++;
        DetalleOrden detalle = new DetalleOrden(idDetalle, horas, falla, "", EstadoOrden.ABIERTA, null, servicio, equipo);
        System.out.println(empresa.registrarDetalleOrden(detalle, idOrden));
    }

    private static void asignarTecnico() {
        String idOrden = leerTexto("Código de la orden: ");
        String idDetalle = leerTexto("Código del detalle: ");
        String idTecnico = leerTexto("Documento del técnico: ");
        System.out.println(empresa.asignarTecnico(idOrden, idDetalle, idTecnico));
    }

    private static void registrarRepuestoUtilizado() {
        String idOrden = leerTexto("Código de la orden: ");
        String idDetalle = leerTexto("Código del detalle: ");
        String codigo = leerTexto("Código del repuesto: ");
        int cantidad = leerEntero("Cantidad: ");
        System.out.println(empresa.registrarRepuestoUtilizado(idOrden, idDetalle, codigo, cantidad));
    }

    private static void calcularValorDetalle() {
        String idOrden = leerTexto("Código de la orden: ");
        String idDetalle = leerTexto("Código del detalle: ");
        int valor = empresa.calcularValorDetalle(idOrden, idDetalle);
        if (valor < 0) {
            System.out.println("Detalle no encontrado");
        } else {
            System.out.println("Valor del detalle: $" + valor);
        }
    }

    private static void calcularTotalOrden() {
        String idOrden = leerTexto("Código de la orden: ");
        int total = empresa.calcularTotalOrden(idOrden);
        if (total < 0) {
            System.out.println("Orden no encontrada");
        } else {
            System.out.println("Total de la orden: $" + total);
        }
    }

    private static void actualizarEstado() {
        String idOrden = leerTexto("Código de la orden: ");
        String idDetalle = leerTexto("Código del detalle: ");
        EstadoOrden[] estados = EstadoOrden.values();
        for (int i = 0; i < estados.length; i++) {
            System.out.println((i + 1) + ". " + estados[i]);
        }
        int op = leerEntero("Nuevo estado: ");
        if (op < 1 || op > estados.length) {
            System.out.println("Estado no válido");
            return;
        }
        System.out.println(empresa.actualizarEstadoDetalle(idOrden, idDetalle, estados[op - 1]));
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        int numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }

    private static boolean leerBoolean(String mensaje) {
        String respuesta = leerTexto(mensaje);
        return respuesta.equalsIgnoreCase("s");
    }
}