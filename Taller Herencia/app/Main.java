import model.CategoriaEmpleado;
import model.Empleado;
import model.EmpleadoPlanta;
import model.EmpleadoTemporal;
import model.EmpleadoVentas;
import model.Empresa;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

void main() {
    int opcion;
    Scanner sc = new Scanner(System.in);
    Empresa empresa = new Empresa(new ArrayList<>());
    do {
        System.out.println("1. Agregar empleado de planta");
        System.out.println("2. Agregar empleado de ventas");
        System.out.println("3. Agregar empleado temporal");
        System.out.println("4. Mostrar todos los empleados");
        System.out.println("5. Buscar empleado por documento");
        System.out.println("6. Mostrar empleado con mayor salario neto");
        System.out.println("7. Mostrar nomina total");
        System.out.println("8. Mostrar resumen de pagos");
        System.out.println("9. Salir");
        opcion = Integer.parseInt(sc.nextLine());
        System.out.println();
        switch (opcion) {
            case 1: {
                System.out.print("Ingrese el nombre del empleado: ");
                String name = sc.nextLine();
                System.out.print("Ingrese el documento del empleado: ");
                String docu = sc.nextLine();
                while (empresa.buscarEmpleado(docu) != null) {
                    System.out.println("Ya existe un empleado con ese documento, intentelo de nuevo");
                    System.out.print("Ingrese el documento del empleado: ");
                    docu = sc.nextLine();
                }
                System.out.print("Ingrese la edad del empleado: ");
                int age = Integer.parseInt(sc.nextLine());
                while (age <= 0) {
                    System.out.println("La edad debe ser mayor que 0, intentelo de nuevo");
                    System.out.print("Ingrese la edad del empleado: ");
                    age = Integer.parseInt(sc.nextLine());
                }
                System.out.print("Ingrese el salario base del empleado: ");
                float salar = Float.parseFloat(sc.nextLine());
                while (salar < 0) {
                    System.out.println("No se pueden ingresar salarios base negativos, intentelo de nuevo");
                    System.out.print("Ingrese el salario base del empleado: ");
                    salar = Float.parseFloat(sc.nextLine());
                }
                System.out.print("Ingrese la categoria del empleado. (1. Junior, 2. Semi senior, 3. Senior): ");
                String numCat = sc.nextLine();
                while (!numCat.equals("1") && !numCat.equals("2") && !numCat.equals("3")) {
                    System.out.println("Solo se admite el numero que acompana la categoria (1. Junior, 2. Semi senior, 3. Senior)");
                    System.out.print("Ingrese la categoria del empleado. (1. Junior, 2. Semi senior, 3. Senior): ");
                    numCat = sc.nextLine();
                }
                CategoriaEmpleado categoria;
                if (numCat.equals("1")) {
                    categoria = CategoriaEmpleado.JUNIOR;
                } else if (numCat.equals("2")) {
                    categoria = CategoriaEmpleado.SEMI_SENIOR;
                } else {
                    categoria = CategoriaEmpleado.SENIOR;
                }
                System.out.print("Ingrese el porcentaje de descuento de salud del empleado (solo numero sin el %): ");
                float porcentajeSalud = Float.parseFloat(sc.nextLine());
                while (porcentajeSalud < 0 || porcentajeSalud > 100) {
                    System.out.println("El descuento debe estar entre 0 y 100, intentelo de nuevo");
                    System.out.print("Ingrese el porcentaje de descuento de salud del empleado (solo numero sin el %): ");
                    porcentajeSalud = Float.parseFloat(sc.nextLine());
                }
                System.out.print("Ingrese el porcentaje de descuento de pension del empleado (solo numero sin el %): ");
                float porcentajePension = Float.parseFloat(sc.nextLine());
                while (porcentajePension < 0 || porcentajePension > 100) {
                    System.out.println("El descuento debe estar entre 0 y 100, intentelo de nuevo");
                    System.out.print("Ingrese el porcentaje de descuento de pension del empleado (solo numero sin el %): ");
                    porcentajePension = Float.parseFloat(sc.nextLine());
                }
                System.out.print("Ingrese el cargo del empleado: ");
                String cargo = sc.nextLine();
                System.out.print("Ingrese las horas extra del empleado: ");
                int horasExtra = Integer.parseInt(sc.nextLine());
                while (horasExtra < 0) {
                    System.out.println("No se permiten horas extra negativas, intentelo de nuevo");
                    System.out.print("Ingrese las horas extra del empleado: ");
                    horasExtra = Integer.parseInt(sc.nextLine());
                }
                System.out.print("Ingrese el valor de la hora extra: ");
                float valorHoraExtra = Float.parseFloat(sc.nextLine());
                while (valorHoraExtra < 0) {
                    System.out.println("El valor de la hora extra no puede ser negativo, intentelo de nuevo");
                    System.out.print("Ingrese el valor de la hora extra: ");
                    valorHoraExtra = Float.parseFloat(sc.nextLine());
                }
                System.out.print("Ingrese el auxilio de transporte: ");
                float auxilio = Float.parseFloat(sc.nextLine());
                while (auxilio < 0) {
                    System.out.println("El auxilio de transporte no puede ser negativo, intentelo de nuevo");
                    System.out.print("Ingrese el auxilio de transporte: ");
                    auxilio = Float.parseFloat(sc.nextLine());
                }
                EmpleadoPlanta planta = new EmpleadoPlanta(name, docu, age, salar, categoria, porcentajeSalud, porcentajePension, cargo, horasExtra, valorHoraExtra, auxilio);
                System.out.println(empresa.registrarEmpleado(planta));
                break;
            }
            case 2: {
                System.out.print("Ingrese el nombre del empleado: ");
                String name = sc.nextLine();
                System.out.print("Ingrese el documento del empleado: ");
                String docu = sc.nextLine();
                while (empresa.buscarEmpleado(docu) != null) {
                    System.out.println("Ya existe un empleado con ese documento, intentelo de nuevo");
                    System.out.print("Ingrese el documento del empleado: ");
                    docu = sc.nextLine();
                }
                System.out.print("Ingrese la edad del empleado: ");
                int age = Integer.parseInt(sc.nextLine());
                while (age <= 0) {
                    System.out.println("La edad debe ser mayor que 0, intentelo de nuevo");
                    System.out.print("Ingrese la edad del empleado: ");
                    age = Integer.parseInt(sc.nextLine());
                }
                System.out.print("Ingrese el salario base del empleado: ");
                float salar = Float.parseFloat(sc.nextLine());
                while (salar < 0) {
                    System.out.println("No se pueden ingresar salarios base negativos, intentelo de nuevo");
                    System.out.print("Ingrese el salario base del empleado: ");
                    salar = Float.parseFloat(sc.nextLine());
                }
                System.out.print("Ingrese la categoria del empleado. (1. Junior, 2. Semi senior, 3. Senior): ");
                String numCat = sc.nextLine();
                while (!numCat.equals("1") && !numCat.equals("2") && !numCat.equals("3")) {
                    System.out.println("Solo se admite el numero que acompana la categoria (1. Junior, 2. Semi senior, 3. Senior)");
                    System.out.print("Ingrese la categoria del empleado. (1. Junior, 2. Semi senior, 3. Senior): ");
                    numCat = sc.nextLine();
                }
                CategoriaEmpleado categoria;
                if (numCat.equals("1")) {
                    categoria = CategoriaEmpleado.JUNIOR;
                } else if (numCat.equals("2")) {
                    categoria = CategoriaEmpleado.SEMI_SENIOR;
                } else {
                    categoria = CategoriaEmpleado.SENIOR;
                }
                System.out.print("Ingrese el porcentaje de descuento de salud del empleado (solo numero sin el %): ");
                float porcentajeSalud = Float.parseFloat(sc.nextLine());
                while (porcentajeSalud < 0 || porcentajeSalud > 100) {
                    System.out.println("El descuento debe estar entre 0 y 100, intentelo de nuevo");
                    System.out.print("Ingrese el porcentaje de descuento de salud del empleado (solo numero sin el %): ");
                    porcentajeSalud = Float.parseFloat(sc.nextLine());
                }
                System.out.print("Ingrese el porcentaje de descuento de pension del empleado (solo numero sin el %): ");
                float porcentajePension = Float.parseFloat(sc.nextLine());
                while (porcentajePension < 0 || porcentajePension > 100) {
                    System.out.println("El descuento debe estar entre 0 y 100, intentelo de nuevo");
                    System.out.print("Ingrese el porcentaje de descuento de pension del empleado (solo numero sin el %): ");
                    porcentajePension = Float.parseFloat(sc.nextLine());
                }
                System.out.print("Ingrese el total de ventas del empleado: ");
                float totalVentas = Float.parseFloat(sc.nextLine());
                while (totalVentas < 0) {
                    System.out.println("El total de ventas no puede ser negativo, intentelo de nuevo");
                    System.out.print("Ingrese el total de ventas del empleado: ");
                    totalVentas = Float.parseFloat(sc.nextLine());
                }
                System.out.print("Ingrese el porcentaje de comision del empleado (solo numero sin el %): ");
                float porcentajeComision = Float.parseFloat(sc.nextLine());
                while (porcentajeComision < 0 || porcentajeComision > 100) {
                    System.out.println("El porcentaje de comision debe estar entre 0 y 100, intentelo de nuevo");
                    System.out.print("Ingrese el porcentaje de comision del empleado (solo numero sin el %): ");
                    porcentajeComision = Float.parseFloat(sc.nextLine());
                }
                EmpleadoVentas ventas = new EmpleadoVentas(name, docu, age, salar, categoria, porcentajeSalud, porcentajePension, totalVentas, porcentajeComision);
                System.out.println(empresa.registrarEmpleado(ventas));
                break;
            }
            case 3: {
                System.out.print("Ingrese el nombre del empleado: ");
                String name = sc.nextLine();
                System.out.print("Ingrese el documento del empleado: ");
                String docu = sc.nextLine();
                while (empresa.buscarEmpleado(docu) != null) {
                    System.out.println("Ya existe un empleado con ese documento, intentelo de nuevo");
                    System.out.print("Ingrese el documento del empleado: ");
                    docu = sc.nextLine();
                }
                System.out.print("Ingrese la edad del empleado: ");
                int age = Integer.parseInt(sc.nextLine());
                while (age <= 0) {
                    System.out.println("La edad debe ser mayor que 0, intentelo de nuevo");
                    System.out.print("Ingrese la edad del empleado: ");
                    age = Integer.parseInt(sc.nextLine());
                }
                System.out.print("Ingrese el salario base del empleado: ");
                float salar = Float.parseFloat(sc.nextLine());
                while (salar < 0) {
                    System.out.println("No se pueden ingresar salarios base negativos, intentelo de nuevo");
                    System.out.print("Ingrese el salario base del empleado: ");
                    salar = Float.parseFloat(sc.nextLine());
                }
                System.out.print("Ingrese la categoria del empleado. (1. Junior, 2. Semi senior, 3. Senior): ");
                String numCat = sc.nextLine();
                while (!numCat.equals("1") && !numCat.equals("2") && !numCat.equals("3")) {
                    System.out.println("Solo se admite el numero que acompana la categoria (1. Junior, 2. Semi senior, 3. Senior)");
                    System.out.print("Ingrese la categoria del empleado. (1. Junior, 2. Semi senior, 3. Senior): ");
                    numCat = sc.nextLine();
                }
                CategoriaEmpleado categoria;
                if (numCat.equals("1")) {
                    categoria = CategoriaEmpleado.JUNIOR;
                } else if (numCat.equals("2")) {
                    categoria = CategoriaEmpleado.SEMI_SENIOR;
                } else {
                    categoria = CategoriaEmpleado.SENIOR;
                }
                System.out.print("Ingrese el porcentaje de descuento de salud del empleado (solo numero sin el %): ");
                float porcentajeSalud = Float.parseFloat(sc.nextLine());
                while (porcentajeSalud < 0 || porcentajeSalud > 100) {
                    System.out.println("El descuento debe estar entre 0 y 100, intentelo de nuevo");
                    System.out.print("Ingrese el porcentaje de descuento de salud del empleado (solo numero sin el %): ");
                    porcentajeSalud = Float.parseFloat(sc.nextLine());
                }
                System.out.print("Ingrese el porcentaje de descuento de pension del empleado (solo numero sin el %): ");
                float porcentajePension = Float.parseFloat(sc.nextLine());
                while (porcentajePension < 0 || porcentajePension > 100) {
                    System.out.println("El descuento debe estar entre 0 y 100, intentelo de nuevo");
                    System.out.print("Ingrese el porcentaje de descuento de pension del empleado (solo numero sin el %): ");
                    porcentajePension = Float.parseFloat(sc.nextLine());
                }
                System.out.print("Ingrese los dias trabajados del empleado: ");
                int diasTrabajados = Integer.parseInt(sc.nextLine());
                while (diasTrabajados < 0) {
                    System.out.println("Los dias trabajados no pueden ser negativos, intentelo de nuevo");
                    System.out.print("Ingrese los dias trabajados del empleado: ");
                    diasTrabajados = Integer.parseInt(sc.nextLine());
                }
                System.out.print("Ingrese el valor por dia del empleado: ");
                float valorDia = Float.parseFloat(sc.nextLine());
                while (valorDia < 0) {
                    System.out.println("El valor por dia no puede ser negativo, intentelo de nuevo");
                    System.out.print("Ingrese el valor por dia del empleado: ");
                    valorDia = Float.parseFloat(sc.nextLine());
                }
                EmpleadoTemporal temporal = new EmpleadoTemporal(name, docu, age, salar, categoria, porcentajeSalud, porcentajePension, diasTrabajados, valorDia);
                System.out.println(empresa.registrarEmpleado(temporal));
                break;
            }
            case 4: {
                empresa.mostrarEmpleados();
                break;
            }
            case 5: {
                System.out.print("Ingrese el documento del empleado a buscar: ");
                String documento = sc.nextLine();
                Empleado encontrado = empresa.buscarEmpleado(documento);
                if (encontrado == null) {
                    System.out.println("No existe un empleado con ese documento");
                } else {
                    encontrado.mostrarInformacion();
                }
                break;
            }
            case 6: {
                Optional<Empleado> mejorPagado = empresa.buscarEmpleadoMasGana();
                if (mejorPagado.isPresent()) {
                    System.out.println("Empleado con mayor salario neto:");
                    mejorPagado.get().mostrarInformacion();
                } else {
                    System.out.println("No hay empleados registrados");
                }
                break;
            }
            case 7: {
                System.out.println("Nomina total de la empresa: " + empresa.calcularNomina());
                break;
            }
            case 8: {
                empresa.mostrarResumenesPago();
                break;
            }
            case 9: {
                System.out.println("Byeeeee");
                break;
            }
            default: {
                System.out.println("Opcion invalida");
                break;
            }
        }
        System.out.println();
    } while (opcion != 9);
}