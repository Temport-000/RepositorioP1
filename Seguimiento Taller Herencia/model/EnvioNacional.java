package model;

public class EnvioNacional extends Envio {

    private static final double VALOR_FIJO = 6_000;
    private static final double COSTO_POR_KILO = 2_000;
    private static final double RECARGO_DOMICILIO = 3_000;

    private String ciudadDestino;
    private boolean domicilio;

    public EnvioNacional(String codigo, float peso, String destinatario, EmpresaEnvios ownedByEmpresa,
                         String ciudadDestino, boolean domicilio) {
        super(codigo, peso, destinatario, ownedByEmpresa, VALOR_FIJO, COSTO_POR_KILO);
        this.ciudadDestino = ciudadDestino;
        this.domicilio = domicilio;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public boolean isDomicilio() {
        return domicilio;
    }

    public void setDomicilio(boolean domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public double calcularCostoTotal() {
        double costo = calcularCostoBase();
        if (domicilio) {
            costo += RECARGO_DOMICILIO;
        }
        return costo;
    }
}