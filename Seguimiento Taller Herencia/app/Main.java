package app;
import model.*;


import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmpresaEnvios empresa = new EmpresaEnvios("Mensajería Express");
        int opcion;

        do {
            System.out.println("1. Registrar envío");
            System.out.println("2. Consultar envío");
            System.out.println("3. Actualizar envío");
            System.out.println("4. Eliminar envío");
            System.out.println("5. Listar envíos");
            System.out.println("6. Ver envío más costoso");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Codigo del envio:");
                    String codigo = sc.nextLine();
                    System.out.println("Tipo de envio:");
                    System.out.println("  1. Nacional");
                    System.out.println("  2. Internacional");
                    System.out.println("  3. Exprés");
                    System.out.print("Seleccione el tipo: ");
                    int tipo = Integer.parseInt(sc.nextLine());
                    System.out.print("Nombre del destinatario: ");
                    String destinatario = sc.nextLine();
                    System.out.print("Peso en kilogramos: ");
                    float peso = Float.parseFloat(sc.nextLine());
                    Envio envio;
                    if (tipo == 2) {
                        System.out.print("País de destino: ");
                        String pais = sc.nextLine();
                        System.out.print("¿Requiere seguro? (s/n): ");
                        boolean seguro = sc.nextLine().equalsIgnoreCase("s");
                        envio = new EnvioInternacional(codigo, peso, destinatario, empresa, pais, seguro);
                    } else {
                        System.out.print("Ciudad de destino: ");
                        String ciudad = sc.nextLine();
                        System.out.print("¿Requiere entrega a domicilio? (si/no): ");
                        boolean domicilio = sc.nextLine().equalsIgnoreCase("si");
                        if (tipo == 3) {
                            System.out.print("Horas máximas de entrega: ");
                            int horas = Integer.parseInt(sc.nextLine());
                            envio = new EnvioExpres(codigo, peso, destinatario, empresa, ciudad, domicilio, horas);
                        } else {
                            envio = new EnvioNacional(codigo, peso, destinatario, empresa, ciudad, domicilio);
                        }
                    }
                    System.out.println(empresa.registrarEnvio(envio));
                    break;

                case 2:
                    System.out.println("CONSULTAR ENVÍO");
                    System.out.print("Código del envío: ");
                    Envio envio2 = empresa.consultarEnvio(sc.nextLine());
                    if (envio2 != null) {
                        System.out.println("Envío encontrado:");
                        System.out.println(envio2);
                    } else {
                        System.out.println("El envío no existe");
                    }
                    break;

                case 3:
                    System.out.println("ACTUALIZAR ENVIO");
                    System.out.print("Codigo del envío a actualizar: ");
                    Envio envio3 = empresa.consultarEnvio(sc.nextLine());
                    if (envio3 == null) {
                        System.out.println("El envio no existe");
                    } else {
                        System.out.println("Envio actual: " + envio3);
                        System.out.print("Nuevo destinatario: ");
                        envio3.setDestinatario(sc.nextLine());
                        System.out.print("Nuevo peso en kilogramos: ");
                        envio3.setPeso(Float.parseFloat(sc.nextLine()));
                        System.out.println("Envio actualizado");
                        System.out.println("Envio nuevo: " + envio3);
                    }
                    break;

                case 4: {
                    System.out.println("ELIMINAR ENVIO");
                    System.out.print("Codigo del envío a eliminar: ");
                    System.out.println("Resultado: " + empresa.eliminarEnvio(sc.nextLine()));
                    break;
                }
                case 5: {
                    System.out.println("LISTA DE ENVIOS");
                    List<Envio> envios = empresa.getListaEnvios();
                    if (envios.isEmpty()) {
                        System.out.println("No hay envíos registrados");
                    } else {
                        int numero = 1;
                        for (Envio e : envios) {
                            System.out.println(numero + ". " + e);
                            numero++;
                        }
                    }
                    break;
                }
                case 6: {
                    System.out.println("ENVIO MÁS COSTOSO");
                    Envio mayor = empresa.masCostoso();
                    if (mayor != null) {
                        System.out.println(mayor);
                    } else {
                        System.out.println("No hay envíos registrados");
                    }
                    break;
                }
                case 0: {
                    System.out.println("Byeeeee");
                    break;
                }
                default: {
                    System.out.println("Opcion no valida, intente de nuevo");
                }
            }
        } while (opcion != 0);
    }
}