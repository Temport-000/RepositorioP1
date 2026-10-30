package co.edu.uniquindio.poo.taller.model;

public class Producto {
  private String iD;
  private String name;
  private int stock;

  public Producto(String iD, String name, int stock) {
      this.iD = iD;
      this.name = name;
      this.stock = stock;
  }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
