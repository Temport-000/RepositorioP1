package model;

public class Diagnostico extends Servicio {
    public static final int TARIFA = 35000;

    public Diagnostico(int code, String name, String desc) {
        super(code, TARIFA, name, desc);
    }

    @Override
    public int calcularCosto(DetalleOrden detalle) {
        return TARIFA;
    }
}
