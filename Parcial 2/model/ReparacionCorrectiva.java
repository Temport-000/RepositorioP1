package model;

public class ReparacionCorrectiva extends Servicio {
    public static final int VALOR_HORA = 45000;

    public ReparacionCorrectiva(int code, String name, String desc) {
        super(code, VALOR_HORA, name, desc);
    }

    @Override
    public int calcularCosto(DetalleOrden detalle) {
        int manoObra = detalle.getHours() * VALOR_HORA;
        return manoObra + detalle.calcularCostoRepuestos();
    }
}