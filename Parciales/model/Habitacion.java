package model;
public class Habitacion {
    private int numero;
    private String tipo;
    private int piso;
    private int capacidadMaxima;
    private double precioPorNoche;
    private String estado;

    public Habitacion(int numero, String tipo, int piso, int capacidadMaxima,
                      double precioPorNoche, String estado) {
        this.numero = numero;
        this.tipo = tipo;
        this.piso = piso;
        this.capacidadMaxima = capacidadMaxima;
        this.precioPorNoche = precioPorNoche;
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }
    public String getTipo() {
        return tipo;
    }
    public int getPiso() {
        return piso;
    }
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
    public double getPrecioPorNoche() {
        return precioPorNoche;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}