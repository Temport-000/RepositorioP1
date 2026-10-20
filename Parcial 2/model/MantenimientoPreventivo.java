package model;

public class MantenimientoPreventivo extends Servicio {
    public static final int TARIFA_PORTATIL = 60000;
    public static final int TARIFA_ESCRITORIO = 70000;
    public static final int TARIFA_TELEFONO = 40000;

    // La tarifa base es 0 porque el valor depende de la categoría del equipo
    public MantenimientoPreventivo(int code, String name, String desc) {
        super(code, 0, name, desc);
    }

    @Override
    public int calcularCosto(DetalleOrden detalle) {
        Equipo equipo = detalle.getEquipo();
        if (equipo instanceof ComputadorPortatil) {
            return TARIFA_PORTATIL;
        }
        if (equipo instanceof ComputadorEscritorio) {
            return TARIFA_ESCRITORIO;
        }
        if (equipo instanceof Telefono) {
            return TARIFA_TELEFONO;
        }
        return 0;
    }
}