package model;

public class EnvioExpres extends EnvioNacional {

    private static final double INCREMENTO_EXPRES = 0.30;
    private static final double RECARGO_24_HORAS = 5_000;

    private int horasMaximas;

    public EnvioExpres(String codigo, float peso, String destinatario, EmpresaEnvios ownedByEmpresa,
                       String ciudadDestino, boolean domicilio, int horasMaximas) {
        super(codigo, peso, destinatario, ownedByEmpresa, ciudadDestino, domicilio);
        this.horasMaximas = horasMaximas;
    }

    public int getHorasMaximas() {
        return horasMaximas;
    }

    public void setHorasMaximas(int horasMaximas) {
        this.horasMaximas = horasMaximas;
    }

    @Override
    public double calcularCostoTotal() {
        double costo = super.calcularCostoTotal() * (1 + INCREMENTO_EXPRES);
        if (horasMaximas <= 24) {
            costo += RECARGO_24_HORAS;
        }
        return costo;
    }
}