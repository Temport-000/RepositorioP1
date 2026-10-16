package model;

public class EnvioInternacional extends Envio {

    private static final double VALOR_FIJO = 15_000;
    private static final double COSTO_POR_KILO = 9_000;
    private static final double RECARGO_SEGURO = 20_000;

    private String paisDestino;
    private boolean seguro;

    public EnvioInternacional(String codigo, float peso, String destinatario, EmpresaEnvios ownedByEmpresa,
                              String paisDestino, boolean seguro) {
        super(codigo, peso, destinatario, ownedByEmpresa, VALOR_FIJO, COSTO_POR_KILO);
        this.paisDestino = paisDestino;
        this.seguro = seguro;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    public boolean isSeguro() {
        return seguro;
    }

    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }

    @Override
    public double calcularCostoTotal() {
        double costo = calcularCostoBase();
        if (seguro) {
            costo += RECARGO_SEGURO;
        }
        return costo;
    }
}