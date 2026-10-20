package model;

public class Repuesto {
    private String code;
    private int precioU;
    private int stock;
    private String name;
    private String desc;
    private String categoria;

    public Repuesto(String code, int precioU, int stock, String name, String desc, String categoria) {
        this.code = code;
        this.precioU = precioU;
        this.stock = stock;
        this.name = name;
        this.desc = desc;
        this.categoria = categoria;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPrecioU() {
        return precioU;
    }

    public void setPrecioU(int precioU) {
        this.precioU = precioU;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    public boolean descontar(int cantidad) {
        if (cantidad <= 0 || cantidad > stock) {
            return false;
        }
        stock -= cantidad;
        return true;
    }

    @Override
    public String toString() {
        return code + " - " + name + " (" + categoria + ") | precio: $" + precioU
                + " | existencias: " + stock;
    }
}