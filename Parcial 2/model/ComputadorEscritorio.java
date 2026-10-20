package model;

import java.time.LocalDate;

public class ComputadorEscritorio extends Equipo{
    private String cpu;
    private int ram;
    private boolean gpu;

    public ComputadorEscritorio (String id, String sN, int alamcenamiento, String brand, String model, String desc, LocalDate yom, String cpu,int ram, boolean gpu){
     super(id, sN, alamcenamiento, brand, model, desc, yom);
     this.cpu= cpu;
     this.ram= ram;
     this.gpu=gpu;
    }
    public String getCpu() {
        return cpu;
    }
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }
    public int getRam() {
        return ram;
    }
    public void setRam(int ram) {
        this.ram = ram;
    }
    public boolean isGpu() {
        return gpu;
    }
    public void setGpu(boolean gpu) {
        this.gpu = gpu;
    }
}