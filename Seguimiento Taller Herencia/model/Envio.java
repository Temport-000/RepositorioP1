package model;

public abstract class Envio {

    private String codigo;
    private float peso; // kilogramos
    private String destinatario;
    private EmpresaEnvios ownedByEmpresa;
    private final double valorFijo;
    private final double incremento; // costo por kilogramo

    public Envio(String codigo, float peso, String destinatario, EmpresaEnvios ownedByEmpresa,
                 double valorFijo, double incremento) {
        this.codigo = codigo;
        this.peso = peso;
        this.destinatario = destinatario;
        this.ownedByEmpresa = ownedByEmpresa;
        this.valorFijo = valorFijo;
        this.incremento = incremento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public EmpresaEnvios getOwnedByEmpresa() {
        return ownedByEmpresa;
    }

    public void setOwnedByEmpresa(EmpresaEnvios ownedByEmpresa) {
        this.ownedByEmpresa = ownedByEmpresa;
    }

    public double calcularCostoBase() {
        return valorFijo + peso * incremento;
    }

    public abstract double calcularCostoTotal();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{codigo='" + codigo + "', destinatario='" + destinatario
                + "', peso=" + peso + "kg, costo=" + calcularCostoTotal() + "}";
    }
}