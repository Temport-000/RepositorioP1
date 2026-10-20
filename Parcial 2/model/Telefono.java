package model;

import java.time.LocalDate;

public class Telefono extends Equipo{
    private String os;
    private int bateria;
    private boolean es5G;
    public Telefono(String id, String sN, int alamcenamiento, String brand, String model, String desc, LocalDate yom,String os, int bateria,boolean es5G){
        super(id, sN, alamcenamiento, brand, model, desc, yom);
        this.os=os;
        this.bateria=bateria;
        this.es5G=es5G;
    }
    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public boolean isEs5G() {
        return es5G;
    }

    public void setEs5G(boolean es5G) {
        this.es5G = es5G;
    }
}