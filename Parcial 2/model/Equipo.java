package model;

import java.time.LocalDate;

public abstract class Equipo{
    private String id;
    private String sN;
    private int alamcenamiento;
    private String brand;
    private String model;
    private String desc;
    private LocalDate yom;

    public Equipo(String id, String sN, int alamcenamiento, String brand, String model, String desc, LocalDate yom) {
        this.id = id;
        this.sN= sN;
        this.alamcenamiento = alamcenamiento;
        this.brand = brand;
        this.model = model;
        this.desc = desc;
        this.yom = yom;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getsN() {
        return sN;
    }

    public void setsN(String sN) {
        this.sN = sN;
    }

    public int getAlamcenamiento() {
        return alamcenamiento;
    }

    public void setAlamcenamiento(int alamcenamiento) {
        this.alamcenamiento = alamcenamiento;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDate getYom() {
        return yom;
    }

    public void setYom(LocalDate yom) {
        this.yom = yom;
    }


    public String getTipo() {
        return getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return id + " (" + getTipo() + " " + brand + " " + model + ", serie " + sN + ")";
    }
}