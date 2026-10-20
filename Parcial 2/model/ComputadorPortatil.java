package model;

import java.time.LocalDate;

public class ComputadorPortatil extends Equipo{
     private float displaySize;
     private int ram;
     private boolean bateriaExtraible;
     public ComputadorPortatil(String id, String sN, int alamcenamiento, String brand, String model, String desc, LocalDate yom, float displaySize, int ram, boolean bateriaExtraible){
         super(id, sN, alamcenamiento, brand, model, desc, yom);
         this.displaySize = displaySize;
         this.ram= ram;
         this.bateriaExtraible =bateriaExtraible;
     }

    public float getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(float displaySize) {
        this.displaySize = displaySize;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public boolean isBateriaExtraible() {
        return bateriaExtraible;
    }

    public void setBateriaExtraible(boolean bateriaExtraible) {
        this.bateriaExtraible = bateriaExtraible;
    }
}