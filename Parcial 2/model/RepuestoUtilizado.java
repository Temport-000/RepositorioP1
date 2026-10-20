package model;

public class RepuestoUtilizado {
    private Repuesto repuesto;
    private int cantidad;
    private int precioAplicado;

    public RepuestoUtilizado(Repuesto repuesto, int cantidad) {
        this.repuesto = repuesto;
        this.cantidad = cantidad;
        this.precioAplicado = repuesto.getPrecioU();
    }

    public Repuesto getRepuesto() { return repuesto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public int getPrecioAplicado() { return precioAplicado; }

    public int getSubtotal() {
        return cantidad * precioAplicado;
    }
}
